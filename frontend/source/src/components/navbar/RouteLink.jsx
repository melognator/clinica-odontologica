import React from 'react'
import Nav from 'react-bootstrap/Nav'
import NavDropdown from 'react-bootstrap/NavDropdown'
import { Route, useNavigate } from 'react-router-dom'

const RouteLink = ({ route }) => {

    const navigate = useNavigate();
    const renderizarRoute = () => {
        switch (route.type) {
            case "singleRoute":
                return <Nav.Link key={route.key} onClick={() => navigate(route.route)}>{route.label}</Nav.Link>
            case "category":
                return <NavDropdown key={route.key} title={route.categoryLabel}>{route.routes.map(singleRoute => <RouteLink key={singleRoute.label} route={singleRoute} />)}</NavDropdown>
            case "route":
                return <NavDropdown.Item key={route.key} onClick={() => navigate(route.route)}>{route.label}</NavDropdown.Item>
        }
        
    }

    // <Nav.Link onClick={() => navigate("/")}>Inicio</Nav.Link>
    // {/* <Nav.Link href="#link">Link</Nav.Link> */}
    // <NavDropdown title="Odontólogos" id="odontologo-nav-dropdown">
    //     <NavDropdown.Item onClick={() => navigate("/odontologos")}>Listado</NavDropdown.Item>
    //     {/* <NavDropdown.Item onClick={() => handleComponent("buscar-odontologo")}>Buscar</NavDropdown.Item> */}
    //     <NavDropdown.Item onClick={() => navigate("/odontologos/crear")}>Crear nuevo</NavDropdown.Item>
    //     {/* <NavDropdown.Divider /> */}
    // </NavDropdown>
    // <NavDropdown title="Pacientes" id="paciente-nav-dropdown">
    //     <NavDropdown.Item onClick={() => navigate("/pacientes")}>Listado</NavDropdown.Item>
    //     {/* <NavDropdown.Item onClick={() => handleComponent("buscar-paciente")}>Buscar</NavDropdown.Item> */}
    //     <NavDropdown.Item onClick={() => navigate("/pacientes/crear")}>Crear nuevo</NavDropdown.Item>
    //     {/* <NavDropdown.Divider /> */}
    // </NavDropdown>
    // <NavDropdown title="Turnos" id="turno-nav-dropdown">
    //     <NavDropdown.Item onClick={() => navigate("/turnos")}>Listado</NavDropdown.Item>
    //     {/* <NavDropdown.Item onClick={() => handleComponent("buscar-paciente")}>Buscar</NavDropdown.Item> */}
    //     <NavDropdown.Item onClick={() => navigate("/turnos/crear")}>Crear nuevo</NavDropdown.Item>
    //     {/* <NavDropdown.Divider /> */}
    // </NavDropdown>

    return renderizarRoute()
}

export default RouteLink