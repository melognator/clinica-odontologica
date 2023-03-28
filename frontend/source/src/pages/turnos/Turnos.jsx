import React, { useEffect, useState } from 'react'
import Button from 'react-bootstrap/Button';
import Table from 'react-bootstrap/Table';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import SearchComponent from '../../components/form/SearchComponent';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash, faPenToSquare } from '@fortawesome/free-solid-svg-icons'
import { useLocation } from 'react-router-dom';

const Turnos = ({turnos, borrarTurno}) => {

  const { state } = useLocation();
  

  const [busqueda, setBusqueda] = useState(state?.turnoId ? state?.turnoId : "");

  const formatDate = (date) => {
    const splits = date.split("T")
    const time = date.split("T")[1].slice(0, -3)
    const splittedDate = splits[0].split("-")
    const year = splittedDate[0]
    const month = splittedDate[1]
    const day = splittedDate[2]
    return `${day}/${month}/${year} ${time}`
  }

  const renderizarTurnos = () => {
    const turnosBuscados = turnos.filter((turno) => (
      ("odontologo=" + turno.odontologoNombre.toLowerCase()).includes(busqueda.toLowerCase()) || 
      ("paciente=" + turno.pacienteNombre.toLowerCase()).includes(busqueda.toLowerCase())) ||
      ("id="+turno.id.toString()).includes(busqueda) ||
      ("fecha="+turno.fecha).includes(busqueda)
      )
    return turnosBuscados.length > 0 ? (
      <Table striped bordered hover>
            <thead>
              <tr>
                <th style={{width:0, textAlign: "center"}}>ID</th>
                <th>Odontólogo</th>
                <th>Paciente</th>
                <th>Fecha</th>
                <th style={{width:0}}>Acciones</th>
              </tr>
            </thead>
            <tbody>
              {turnosBuscados.map((turno) => (
                <tr key={turno.id}>
                  <td style={{textAlign: "center"}}>{turno.id}</td>
                  <td>{turno.odontologoNombre}</td>
                  <td>{turno.pacienteNombre}</td>
                  <td>{formatDate(turno.fecha)}</td>
                  <td className="d-flex justify-content-around">
                    {/* <Button className="p -0" style={{fontSize:"0.8rem", width:"1.5rem", height:"1.5rem", boxSizing: "content-box"}} variant="primary"><FontAwesomeIcon icon={faPenToSquare} /></Button> */}
                    <Button className="p-0" style={{fontSize:"0.8rem", width:"1.5rem", height:"1.5rem", boxSizing: "content-box"}} variant="danger" onClick={() => borrarTurno(turno.id)}><FontAwesomeIcon icon={faTrash} /></Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
    ) : (
      <h6 className="text-center">No se han encontrado turnos...</h6>
    )
  }


  return (
    <Container className="mt-3">
      <SearchComponent onChange={(e) => setBusqueda(e.target.value)} value={busqueda} placeholder="Buscar turnos..." />
      <Row>
        <Col>
          {renderizarTurnos()}
        </Col>
      </Row>
    </Container>
  )
}

export default Turnos