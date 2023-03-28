import React, { useState, useEffect } from 'react'
import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import Button from 'react-bootstrap/Button';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faTrash, faPenToSquare } from '@fortawesome/free-solid-svg-icons'
import { useNavigate } from 'react-router-dom';
import Container from 'react-bootstrap/Container';

const Paciente = ({ paciente, borrarPaciente }) => {
    const spanStyle = { "fontWeight": 400, fontStyle: "italic" };
    const navigate = useNavigate()

    return (
        <Container className="mt-3">
            <Card key={paciente.id} className="mb-3">
                <Card.Header><Card.Title className="p-1" style={{ margin: 0 }}>{paciente.nombreCompleto}</Card.Title></Card.Header>
                <Card.Body>
                    <Card.Subtitle className="mb-2">Datos del paciente</Card.Subtitle>
                    <p className="mb-0" key="id"><span style={spanStyle}>ID:</span> {paciente.id}</p>
                    <p className="mb-0" key="nombre"><span style={spanStyle}>Nombre:</span> {paciente.nombre}</p>
                    <p className="mb-0" key="apellido"><span style={spanStyle}>Apellido:</span> {paciente.apellido}</p>
                    <p className="mb-0" key="dni"><span style={spanStyle}>DNI:</span> {paciente.dni}</p>
                    <p className="mb-0" key="email"><span style={spanStyle}>Email:</span> {paciente.email}</p>
                    <p className="mb-0" key="fecha"><span style={spanStyle}>Fecha de ingreso:</span> {paciente.fecha_ingreso}</p>
                </Card.Body>
                <hr className="m-0" />
                <Card.Body>
                    <Card.Subtitle className="mb-2">Domicilio del paciente</Card.Subtitle>
                    <p className="mb-0" key="calle"><span style={spanStyle}>Domicilio:</span> {paciente.domicilio.calle}</p>
                    <p className="mb-0" key="numero"><span style={spanStyle}>Numero:</span> {paciente.domicilio.numero}</p>
                    <p className="mb-0" key="localidad"><span style={spanStyle}>Localidad:</span> {paciente.domicilio.localidad}</p>
                    <p className="mb-0" key="provincia"><span style={spanStyle}>Provincia:</span> {paciente.domicilio.provincia}</p>

                    <div className="d-flex justify-content-between">
                        <div key="floater"></div>
                        <div key="buttons">
                            <Button className="p-0 ms-3 mt-3" style={{ width: "2.25rem", height: "2.25rem", boxSizing: "content-box" }} variant="primary" onClick={() => navigate("/pacientes/modificar", { state: paciente })}><FontAwesomeIcon icon={faPenToSquare} /></Button>
                            <Button className="p-0 ms-3 mt-3" style={{ width: "2.25rem", height: "2.25rem", boxSizing: "content-box" }} variant="danger" onClick={() => borrarPaciente(paciente.id)}><FontAwesomeIcon icon={faTrash} /></Button>
                        </div>
                        {/* <Button className="mt-3" variant="danger" onClick={() => borrarPaciente(paciente.id)}>
                    Borrar paciente
                </Button> */}
                    </div>
                </Card.Body>
            </Card>

        </Container>
    )
}

export default Paciente