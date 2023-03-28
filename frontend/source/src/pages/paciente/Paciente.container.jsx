import axios from 'axios';
import React, { useState, useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { borradoExitoso, borradoFallido } from '../../components/sweetalert2/AlertComponent';
import Turnos from '../turnos/Turnos';
import Paciente from './Paciente'

const PacienteContainer = ({ urlTurnos, urlPacientes }) => {

    const [paciente, setPaciente] = useState({
        "id": -1,
        "nombre": "",
        "apellido": "",
        "email": "",
        "dni": "",
        "domicilio": {
            "calle": "",
            "numero": 0,
            "localidad": "",
            "provincia": ""
        }
    })

    const { id } = useParams();

    useEffect(() => {
        axios.get(`${urlPacientes}/${id}`)
            .then(respuesta => {
                setTurnos(() => respuesta.data.turnoSet);
                setPaciente(() => respuesta.data);
            })
            .catch(err => {
                console.log("Promesa rechazada:");
                console.log(err);
            })
    }, []);

    const [turnos, setTurnos] = useState([]);

    // useEffect(() => {
    //     axios.get(urlTurnos + "/buscar-paciente/" + id)
    //     .then(respuesta => {
    //         setTurnos(() => respuesta.data);
    //     })
    //     .catch(err => {
    //         console.log("Promesa rechazada:");
    //         console.log(err);
    //     })
    // }, []);


    const borrarTurno = (id) => {
        axios.delete(`${urlTurnos}/${id}`)
          .then(respuesta => {
            borradoExitoso("turno")
            setTurnos(() => turnos.filter((turno) => turno.id != id));
          })
          .catch(err => {
            borradoFallido("turno")
            console.log("Promesa rechazada:");
            console.log(err);
          })
      }


    // useEffect(() => {
    //     axios.get(urlTurnos)
    //     .then(respuesta => {
    //         setTurnos(() => respuesta.data);
    //     })
    //     .catch(err => {
    //         console.log("Promesa rechazada:");
    //         console.log(err);
    //     })
    // }, []);



    const navigate = useNavigate();

    const borrarPaciente = () => {

        axios.delete(`${urlPacientes}/${paciente.id}`)
            .then(respuesta => {
                borradoExitoso("paciente")
                navigate("/pacientes")
            })
            .catch(err => {
                borradoFallido("paciente")
                console.log("Promesa rechazada:");
                console.log(err);
            })
    }

    return (
        <>
            <Paciente borrarPaciente={borrarPaciente} paciente={paciente} />
            <Turnos turnos={turnos} borrarTurno={borrarTurno} />
        </>
    )
}

export default PacienteContainer