import axios from 'axios';
import React, { useState, useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { borradoExitoso, borradoFallido } from '../../components/sweetalert2/AlertComponent';
import Turnos from '../turnos/Turnos';
import Odontologo from './Odontologo'

const OdontologoContainer = ({ urlTurnos, urlOdontologos }) => {

    const navigate = useNavigate();

    const [odontologo, setOdontologo] = useState({
        "id": -1,
        "matricula": "",
        "nombre": "",
        "apellido": ""
    })

    const { id } = useParams();

    useEffect(() => {
        if (isNaN(id)) {
            navigate('/404')
        } else {
            axios.get(`${urlOdontologos}/${id}`)
                .then(respuesta => {
                    setOdontologo(() => respuesta.data);
                    setTurnos(() => respuesta.data.turnoSet);
                })
                .catch(err => {
                    console.log("Promesa rechazada:");
                    console.log(err);
                    if (err.response.status === 404) {
                        navigate('/404')
                    }
                })

        }
    }, []);

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

    // useEffect(() => {
    //     axios.get(urlTurnos + "/buscar-odontologo/" + id)
    //     .then(respuesta => {
    //         setTurnos(() => respuesta.data);
    //     })
    //     .catch(err => {
    //         console.log("Promesa rechazada:");
    //         console.log(err);
    //     })
    // }, []);

    const borrarOdontologo = () => {

        axios.delete(`${urlOdontologos}/${odontologo.id}`)
            .then(respuesta => {
                borradoExitoso("odontólogo")
                navigate("/odontologos")
            })
            .catch(err => {
                borradoFallido("odontólogo")
                console.log("Promesa rechazada:");
                console.log(err);
            })
    }

    return (
        <>
            <Odontologo borrarOdontologo={borrarOdontologo} odontologo={odontologo} />
            <Turnos turnos={turnos} borrarTurno={borrarTurno} />
        </>
    )
}

export default OdontologoContainer