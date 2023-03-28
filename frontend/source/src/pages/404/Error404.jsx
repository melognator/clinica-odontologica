import React from 'react'
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import { useNavigate } from 'react-router-dom';

const Error404 = () => {

    const navigate = useNavigate()
  return (
    <Container className="d-flex-column justify-content-center align-items-center mt-5 text-center">
        <h1 style={{fontSize: "6rem"}}>¡404!</h1>
        <p className="text-muted">No se ha encontrado esta página... F</p>
        <Button onClick={() => navigate("/")}>Volver</Button>
    </Container>
  )
}

export default Error404