import React, { useState, useEffect } from 'react'
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import FormCreateComponent from '../../components/form/FormCreateComponent';
import { creacionExitosa, creacionFallida } from '../../components/sweetalert2/AlertComponent';

const CrearTurno = ({ urlTurnos, urlPacientes, urlOdontologos }) => {

    const navigate = useNavigate();

    const [pacientes, setPacientes] = useState([]);
    const [odontologos, setOdontologos] = useState([]);

    const [verTurno, setVerTurno] = useState(false);

    var defaultDate = new Date();    
    defaultDate = new Date(defaultDate.getTime() - defaultDate.getTimezoneOffset() * 60000)
    defaultDate = defaultDate.toISOString().slice(0, -8)

    const [datosTurno, setDatosTurno] = useState({
        "odontologoId": "",
        "pacienteId": "",
        "fecha": defaultDate
    })

    const isHidden = false;
    const colClasses = "mb-1";
    const labelClasses = "mb-1";
    const inputClasses = "pt-1 pb-1 ps-2";
    const inputStyle = {
        fontSize: "0.90rem"
    }

    const handleCambioOdontologo = (e) => {
        setDatosTurno(prevState => ({ ...prevState, "odontologoId": e.target.value }))
    }

    const handleCambioPaciente = (e) => {
        setDatosTurno(prevState => ({ ...prevState, "pacienteId": e.target.value }))
    }

    const handleCambioFecha = (e) => {
        setDatosTurno(prevState => ({ ...prevState, "fecha": e.target.value }))
    }

    const handleCambioVerTurno = (e) => {
        setVerTurno(() => e.target.checked)
    }

    useEffect(() => {
        axios.get(urlPacientes)
            .then(respuesta => {
                setPacientes(() => respuesta.data);
            })
            .catch(err => {
                console.log("Promesa rechazada:");
                console.log(err);
            })

        axios.get(urlOdontologos)
            .then(respuesta => {
                setOdontologos(() => respuesta.data);
            })
            .catch(err => {
                console.log("Promesa rechazada:");
                console.log(err);
            })
    }, []);

    const crearTurno = (e) => {
        e.preventDefault();
        axios.post(urlTurnos, datosTurno)
            .then(response => {
                creacionExitosa("turno")
                if (verTurno) {
                    navigate("/turnos", { state: {turnoId: "id=" + response.data.id.toString()} });
                } else {
                    setDatosTurno({
                        "odontologoId": "" ,
                        "pacienteId": "",
                        "fecha": defaultDate
                    })
                }
            })
            .catch(err => {
                creacionFallida("turno")
                console.log("Promesa rechazada:")
                console.log(err)
            })
        return false;
    }

    const renderizarOpcionesOdontologos = () => {
        return odontologos.map((odontologo) => (
            <option key={odontologo.id} value={odontologo.id}>{odontologo.nombreCompleto}</option>
        ))
    }

    const renderizarOpcionesPacientes = () => {
        return pacientes.map((paciente) => (
            <option key={paciente.id} value={paciente.id}>{paciente.nombreCompleto}</option>
        ))
    }

    return (
        <Container className="mt-3">

            <FormCreateComponent
                onSubmit={(e) => crearTurno(e)}
                title={"Registrar un turno"}
                buttonLabel={"Registrar turno"}
                verOnChange={(e) => handleCambioVerTurno(e)}
                verLabel={"Ver turno registrado"}
                formGroups={[
                {
                    "key": "data",
                    "subtitle": "",

                    "inputs": [
                        {
                            "label": "Odontólogo",
                            "type": "select",
                            "required": true,
                            "placeholder": "Seleccionar un odontólogo...",
                            "options": renderizarOpcionesOdontologos(),
                            "value": datosTurno.odontologoId,
                            "onChange": (e) => handleCambioOdontologo(e)
                        },
                        {
                            "label": "Paciente",
                            "type": "select",
                            "required": true,
                            "placeholder": "Seleccionar un paciente...",
                            "options": renderizarOpcionesPacientes(),
                            "value": datosTurno.pacienteId,
                            "onChange": (e) => handleCambioPaciente(e)
                        },
                        {
                            "type": "datetime-local",
                            "label": "Fecha",
                            "placeholder": "Click para seleccionar",
                            "required": true,
                            "value": datosTurno.fecha,
                            "onChange": (e) => handleCambioFecha(e)
                        },
                    ]
                }]} />
            
        </Container>

    )
}

export default CrearTurno