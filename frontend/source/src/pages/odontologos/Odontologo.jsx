import React from 'react'
import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import Button from 'react-bootstrap/Button';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash, faPenToSquare } from '@fortawesome/free-solid-svg-icons'
import { useNavigate } from 'react-router-dom';

const OdontologoCompontent = ({odontologo, borrarOdontologo}) => {
    const spanStyle = {"fontWeight": 400, fontStyle: "italic"};
    const navigate = useNavigate()
  return (
    <Card key={odontologo.id} className="mb-3">
        <Card.Header><Card.Title className="p-1" style={{margin: 0}}>{odontologo.nombreCompleto}</Card.Title></Card.Header>
        <Card.Body>
            <Card.Subtitle className="mb-2">Datos del odontólogo</Card.Subtitle>
            <p className="mb-0" key="id"><span style={spanStyle}>ID:</span> {odontologo.id}</p>
            <p className="mb-0" key="matricula"><span style={spanStyle}>Matrícula:</span> {odontologo.matricula}</p>
            <p className="mb-0" key="nombre"><span style={spanStyle}>Nombre:</span> {odontologo.nombre}</p>
            <p className="mb-0" key="apellido"><span style={spanStyle}>Apellido:</span> {odontologo.apellido}</p>
            <p className="mb-0" key="turnos"><span style={spanStyle}>Cantidad de turnos:</span> {odontologo.turnoSet.length}</p>

            <div className="d-flex justify-content-between">
                <Button className="mt-3" variant="primary" onClick={() => navigate("/odontologos/" + odontologo.id)}>
                    Ver odontologo
                </Button>
                <div>
                    <Button className="p-0 ms-3 mt-3" style={{ width:"2.25rem", height:"2.25rem", boxSizing: "content-box"}} variant="primary" onClick={() => navigate("/odontologos/modificar", { state: odontologo })}><FontAwesomeIcon icon={faPenToSquare} /></Button>
                    <Button className="p-0 ms-3 mt-3" style={{ width:"2.25rem", height:"2.25rem", boxSizing: "content-box"}} variant="danger" onClick={() => borrarOdontologo(odontologo.id)}><FontAwesomeIcon icon={faTrash} /></Button>
                </div>
                {/* <Button className="mt-3" variant="danger" onClick={() => borrarOdontologo(odontologo.id)} type="submit">
                    Borrar odontologo
                </Button> */}
            </div>
            
        </Card.Body>
    </Card>
  )
}

export default OdontologoCompontent