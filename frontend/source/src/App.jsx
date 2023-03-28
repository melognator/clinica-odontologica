import React, {useState} from 'react'
import NavbarComponent from './components/navbar/Navbar'
import 'bootstrap/dist/css/bootstrap.min.css';
import OdontologosComponent from './pages/odontologos/Odontologos';
import CrearOdontologoComponent from './pages/odontologos-crear/CrearOdontologo';
import CrearPacienteComponent from './pages/pacientes-crear/CrearPaciente';
import PacientesComponent from './pages/pacientes/Pacientes';
import CrearTurnoComponent from './pages/turnos-crear/CrearTurno';
import TurnosComponent from './pages/turnos/Turnos';

const App = () => {
  const [info, setInfo] = useState({componenteActual: "crear-odontologo"})

  const handleComponent = (componente) => (
    setInfo({...info, componenteActual: componente})
  )

  const WIP = (<div>Work in progress...</div>)

  const renderComponent = () => {
    switch(info.componenteActual) {
      case "listar-odontologo":
        return <OdontologosComponent />
      case "crear-odontologo":
        return <CrearOdontologoComponent handleComponent={handleComponent}/>
      case "listar-paciente":
        return <PacientesComponent />
      case "crear-paciente":
        return <CrearPacienteComponent handleComponent={handleComponent}/>
      case "listar-turno":
        return <TurnosComponent />
      case "crear-turno":
        return <CrearTurnoComponent handleComponent={handleComponent}/>
    }
  }

  return (
    <React.Fragment>
      <NavbarComponent handleComponent={handleComponent} />
      {renderComponent()}
    </React.Fragment>
  )
}

export default App