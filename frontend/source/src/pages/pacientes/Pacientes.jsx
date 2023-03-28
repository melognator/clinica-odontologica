import React, { useEffect, useState } from 'react'
import Paciente from './Paciente';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import axios from "axios"
import SearchComponent from '../../components/form/SearchComponent';
import { borradoExitoso, borradoFallido } from '../../components/sweetalert2/AlertComponent';

const Pacientes = ({ urlPacientes }) => {

  const [busqueda, setBusqueda] = useState("");
  const [pacientes, setPacientes] = useState([]);

  useEffect(() => {
    axios.get(urlPacientes)
      .then(respuesta => {
        setPacientes(() => respuesta.data);
      })
      .catch(err => {
        console.log("Promesa rechazada:");
        console.log(err);
      })
  }, []);

  const borrarPaciente = (id) => {

    axios.delete(`${urlPacientes}/${id}`)
      .then(respuesta => {
        setPacientes(() => pacientes.filter((paciente) => paciente.id != id));
        borradoExitoso("paciente")
      })
      .catch(err => {
        borradoFallido("paciente")
        console.log("Promesa rechazada:");
        console.log(err);
      })
  }

  const renderizarPacientes = () => {
    const pacientesBuscados = pacientes.filter((paciente) => (paciente.nombreCompleto.toLowerCase().includes(busqueda.toLowerCase())))
    return pacientesBuscados.length > 0 ? (
      pacientesBuscados.map((paciente) => (
        <Col key={paciente.id} lg={6} md={6} sm={12} xs={12}>
          <Paciente urlPacientes={urlPacientes} borrarPaciente={borrarPaciente} paciente={paciente} />
        </Col>
      ))
    ) : (
      <h6 className="text-center">No se han encontrado pacientes...</h6>
    )
  }

  return (
    <Container className="mt-3">
      <SearchComponent onChange={(e) => setBusqueda(e.target.value)} placeholder="Buscar pacientes..." />
      <Row>
        {renderizarPacientes()}
      </Row>
    </Container>
  )
}

export default Pacientes