import axios from 'axios'
import React, { useEffect, useState } from 'react'
import ModificarPaciente from './ModificarPaciente'

const ModificarPacienteContainer = ({ urlPacientes }) => {

  const [pacientes, setPacientes] = useState([])

  useEffect(() => {
    axios.get(urlPacientes)
      .then(respuesta => {
        setPacientes(() => respuesta.data);
      })
      .catch(err => {
        console.log("Promesa rechazada:");
        console.log(err);
      })
  }, [])

  return <ModificarPaciente pacientes={pacientes} setPacientes={setPacientes} urlPacientes={urlPacientes} />
}

export default ModificarPacienteContainer