import React, { useEffect, useState } from 'react'
import Odontologo from './Odontologo';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form'
import axios from "axios"
import SearchComponent from '../../components/form/SearchComponent';
import { borradoExitoso, borradoFallido } from '../../components/sweetalert2/AlertComponent';

const Odontologos = ({urlOdontologos}) => {

  const [busqueda, setBusqueda] = useState("");
  const [odontologos, setOdontologos] = useState([]);

  useEffect(() => {
    axios.get(urlOdontologos)
    .then(respuesta => {
      setOdontologos(() => respuesta.data);
    })
    .catch(err => {
      console.log("Promesa rechazada:");
      console.log(err);
    })
  }, []);

  const borrarOdontologo = (id) => {

    axios.delete(`${urlOdontologos}/${id}`)
    .then(respuesta => {
      setOdontologos(() => odontologos.filter((odontologo) => odontologo.id != id));
      borradoExitoso("odontólogo")
    })
    .catch(err => {
      borradoFallido("odontólogo")
      console.log("Promesa rechazada:");
      console.log(err);
    })
  }

  const renderizarOdontologos = () => {
    const odontologosBuscados = odontologos.filter((odontologo) => (odontologo.nombreCompleto.toLowerCase().includes(busqueda.toLowerCase())))
    return odontologosBuscados.length > 0 ? (
      odontologosBuscados.map((odontologo) => (
        <Col key={odontologo.id} xl={4} lg={6} md={6} sm={12} xs={12}>
          <Odontologo borrarOdontologo={borrarOdontologo} odontologo={odontologo} />
        </Col>
      ))
    ) : (
      <h6 className="text-center">No se han encontrado odontologos...</h6>
    )
  }

  return (
    <Container className="mt-3">
      <SearchComponent onChange={(e) => setBusqueda(e.target.value)} placeholder="Buscar odontólogo..." />
      <Row>
          {renderizarOdontologos()}
      </Row>
    </Container>
  )
}

export default Odontologos