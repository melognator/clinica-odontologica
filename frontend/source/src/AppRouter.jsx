import React from 'react'
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import CrearOdontologoPage from './pages/odontologos-crear/CrearOdontologo.container'
import CrearPacientePage from './pages/pacientes-crear/CrearPaciente.container'
import CrearTurnoPage from './pages/turnos-crear/CrearTurno.container'
import OdontologosPage from './pages/odontologos/Odontologos.container'
import PacientesPage from './pages/pacientes/Pacientes.container'
import LayoutPage from './components/layout/Layout.container';
import HomePage from './pages/home/Home.container';
import TurnosPage from './pages/turnos/Turnos.container';
import OdontologoPage from './pages/odontologo/Odontologo.container';
import PacientePage from './pages/paciente/Paciente.container';
import ModificarOdontologoPage from './pages/odontologos-modificar/ModificarOdontologo.container';
import ModificarPacientePage from './pages/pacientes-modificar/ModificarPaciente.container';
import Error404Page from './pages/404/Error404.container';

const AppRouter = () => {
  let proxy = ""
//   proxy = "http://127.0.0.1:8080"
  return (
    <BrowserRouter>
        <Routes>
            <Route element={<LayoutPage />}>
                <Route path="/" element={<HomePage/>}/>
                <Route path="/odontologos" element={<OdontologosPage urlOdontologos={proxy + '/api/odontologos'} />} />
                <Route path="/odontologos/:id" element={<OdontologoPage urlOdontologos={proxy + '/api/odontologos'} urlTurnos={proxy + '/api/turnos'} />} />
                <Route path="/odontologos/crear" element={<CrearOdontologoPage urlOdontologos={proxy + '/api/odontologos'} />} />
                <Route path="/odontologos/modificar" element={<ModificarOdontologoPage urlOdontologos={proxy + '/api/odontologos'} />} />
                <Route path="/pacientes" element={<PacientesPage urlPacientes={proxy + '/api/pacientes'} />} />
                <Route path="/pacientes/:id" element={<PacientePage urlPacientes={proxy + '/api/pacientes'} urlTurnos={proxy + '/api/turnos'} />} />
                <Route path="/pacientes/crear" element={<CrearPacientePage urlPacientes={proxy + '/api/pacientes'} />} />
                <Route path="/pacientes/modificar" element={<ModificarPacientePage urlPacientes={proxy + '/api/pacientes'} />} />
                <Route path="/turnos" element={<TurnosPage urlTurnos={proxy + '/api/turnos'}  />} />
                <Route path="/turnos/crear" element={<CrearTurnoPage urlOdontologos={proxy + '/api/odontologos'} urlPacientes={proxy + '/api/pacientes'} urlTurnos={proxy + '/api/turnos'} />} />
                <Route path="*" element={<Error404Page />} />
            </Route>
        </Routes>
    </BrowserRouter>
  )
}

export default AppRouter