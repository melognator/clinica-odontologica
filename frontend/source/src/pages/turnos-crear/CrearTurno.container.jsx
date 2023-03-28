import React from 'react'
import CrearTurno from './CrearTurno'

const CrearTurnoContainer = ({urlTurnos, urlPacientes, urlOdontologos}) => {
  return (
    <CrearTurno urlTurnos={urlTurnos} urlPacientes={urlPacientes} urlOdontologos={urlOdontologos} />
  )
}

export default CrearTurnoContainer