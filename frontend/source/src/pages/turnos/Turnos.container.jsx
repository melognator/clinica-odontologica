import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Turnos from './Turnos'

import { borradoExitoso, borradoFallido } from '../../components/sweetalert2/AlertComponent';

const TurnosContainer = ({urlTurnos}) => {

  const [turnos, setTurnos] = useState([]);

  const borrarTurno = (id) => {
    axios.delete(`${urlTurnos}/${id}`)
      .then(respuesta => {
        setTurnos(() => turnos.filter((turno) => turno.id != id));
        borradoExitoso("turno")
      })
      .catch(err => {
        borradoFallido("turno")
        console.log("Promesa rechazada:");
        console.log(err);
      })
  }

  useEffect(() => {
    axios.get(urlTurnos)
      .then(respuesta => {
        setTurnos(() => respuesta.data);
      })
      .catch(err => {
        console.log("Promesa rechazada:");
        console.log(err);
      })
  }, []);

  return <Turnos turnos={turnos} borrarTurno={borrarTurno} />
}

export default TurnosContainer