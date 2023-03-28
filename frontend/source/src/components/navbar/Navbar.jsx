import React from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { useNavigate } from 'react-router-dom';
import { routes } from '../../routes/routes';
import RouteLink from './RouteLink';

const NavbarComponent = () => {

  const renderizarRoutes = () => (
    routes.map(route => <RouteLink key={route.key} route={route} />)
  )

  return (
    <Navbar bg="light" expand="lg">
      <Container>
        <Navbar.Brand>Clínica Dávila</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            {renderizarRoutes()}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  )
}

export default NavbarComponent