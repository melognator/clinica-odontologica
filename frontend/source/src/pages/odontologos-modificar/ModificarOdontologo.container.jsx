import axios from 'axios'
import React, { useEffect, useRef, useState } from 'react'
import ModificarOdontologo from './ModificarOdontologo'

const ModificarOdontologoContainer = ({ urlOdontologos }) => {

  const [odontologos, setOdontologos] = useState([])
  const [loaded, setLoaded] = useState(false)

  useEffect(() => {
    axios.get(urlOdontologos)
      .then(respuesta => {
        setOdontologos(() => respuesta.data);
      })
      .catch(err => {
        console.log("Promesa rechazada:");
        console.log(err);
      })
  }, [])

  return <ModificarOdontologo odontologos={odontologos} setOdontologos={setOdontologos} urlOdontologos={urlOdontologos} />
}

export default ModificarOdontologoContainer