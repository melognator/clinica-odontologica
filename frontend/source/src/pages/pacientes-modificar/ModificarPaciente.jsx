import React, {useEffect, useState} from 'react'
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Card from 'react-bootstrap/Card';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import axios from 'axios';
import { useLocation, useNavigate } from 'react-router-dom';
import FormCreateComponent from '../../components/form/FormCreateComponent';
import { modificacionExitosa, modificacionFallida } from '../../components/sweetalert2/AlertComponent';

const ModificarPaciente = ({urlPacientes, setPacientes, pacientes}) => {

    const navigate = useNavigate();

    const { state } = useLocation();

    const [verPaciente, setVerPaciente] = useState(false);

    const [datosPaciente, setDatosPaciente] = useState(state ? state : {
        "id": "",
        "nombre": "",
        "apellido": "",
        "dni": "",
        "email": "",
        "domicilio": {
            "calle": "",
            "numero": "",
            "localidad": "",
            "provincia": ""
        }
    })

    const isHidden = false;
    const colClasses = "mb-1";
    const labelClasses = "mb-1";
    const inputClasses = "pt-1 pb-1 ps-2";
    const inputStyle = {
        fontSize: "0.90rem"
    }

    const handleCambioNombre = (e) => {
        setDatosPaciente(prevState => ({ ...prevState, "nombre": e.target.value }))
    }

    const handleCambioApellido = (e) => {
        setDatosPaciente(prevState => ({ ...prevState, "apellido": e.target.value }))
    }

    const handleCambioDNI = (e) => {
        setDatosPaciente(prevState => ({ ...prevState, "dni": e.target.value }))
    }

    const handleCambioEmail = (e) => {
        setDatosPaciente(prevState => ({ ...prevState, "email": e.target.value }))
    }

    const handleCambioCalle = (e) => {
        setDatosPaciente(prevState => ({ ...prevState, domicilio: { ...prevState.domicilio, "calle": e.target.value } }))
    }

    const handleCambioNumero = (e) => {
        if (!isNaN(e.target.value)) {
            if (e.target.value >= 0) {
                setDatosPaciente(prevState => ({ ...prevState, domicilio: { ...prevState.domicilio, "numero": e.target.value } }))
            }
        }
    }

    const handleCambioLocalidad = (e) => {
        setDatosPaciente(prevState => ({ ...prevState, domicilio: { ...prevState.domicilio, "localidad": e.target.value } }))
    }

    const handleCambioProvincia = (e) => {
        setDatosPaciente(prevState => ({ ...prevState, domicilio: { ...prevState.domicilio, "provincia": e.target.value } }))
    }

    const handleCambioVerPaciente = (e) => {
        setVerPaciente(() => e.target.checked)
    }

    useEffect(() => {
        if (datosPaciente.id && pacientes.length > 0) {
            const pacienteBuscado = pacientes.find(paciente => paciente.id == datosPaciente.id)
            setDatosPaciente(pacienteBuscado)
        }
    }, [datosPaciente.id])

    const handleCambioPaciente = (e) => {
        setDatosPaciente(prevState => ({ ...prevState, "id": e.target.value }))
    }

    const renderizarOpcionesPacientes = () => {
        return pacientes.map((paciente) => (
            <option key={paciente.id} value={paciente.id}>{paciente.nombreCompleto}</option>
        ))
    }

    const modificarPaciente = (e) => {
        e.preventDefault();
        axios.put(urlPacientes, datosPaciente)
            .then(response => {
                modificacionExitosa("paciente")
                if (verPaciente) {
                    // TODO: luego cambiar a paciente/id
                    navigate("/pacientes/" + datosPaciente.id);
                } else {
                    setPacientes(prevState => prevState.map((paciente) => paciente.id == datosPaciente.id ? datosPaciente : paciente))
                    setDatosPaciente({
                        "id": "",
                        "nombre": "",
                        "apellido": "",
                        "dni": "",
                        "email": "",
                        "domicilio": {
                            "calle": "",
                            "numero": "",
                            "localidad": "",
                            "provincia": ""
                        }
                    })
                }
            })
            .catch(err => {
                modificacionFallida("paciente")
                console.log("Promesa rechazada:")
                console.log(err)
            })
        return false;
    }

    return (
        <Container className="mt-3">
            <FormCreateComponent 
            onSubmit={(e) => modificarPaciente(e)} 
            title={"Modificar paciente"} 
            buttonLabel={"Modificar paciente"} 
            verOnChange={(e) => handleCambioVerPaciente(e)} 
            verLabel={"Ver paciente modificado"} 
            formGroups={[{
                "key": "paciente",
                // "subtitle": "Datos del paciente",
                "inputs": [
                    {
                        "label": "Paciente",
                        "type": "select",
                        "required": true,
                        "placeholder": "Seleccionar un paciente...",
                        "options": renderizarOpcionesPacientes(),
                        "value": datosPaciente.id,
                        "onChange": (e) => handleCambioPaciente(e)
                    },
                    
                ]
            },
            {
                "key": "datos",
                "subtitle": "Datos del paciente",
                "inputs": [
                    {
                        "type": "text",
                        "label": "Nombre",
                        "placeholder": "Marisela",
                        "required": true,
                        "value": datosPaciente.nombre,
                        "onChange": (e) => handleCambioNombre(e)
                    },
                    {
                        "type": "text",
                        "label": "Apellido",
                        "placeholder": "Rivas",
                        "required": true,
                        "value": datosPaciente.apellido,
                        "onChange": (e) => handleCambioApellido(e)
                    },
                    {
                        "type": "text",
                        "label": "DNI",
                        "placeholder": "1.234.567-8",
                        "required": true,
                        "value": datosPaciente.dni,
                        "onChange": (e) => handleCambioDNI(e)
                    },
                    {
                        "type": "email",
                        "label": "Email",
                        "placeholder": "marisela@rivas.com",
                        "required": true,
                        "value": datosPaciente.email,
                        "onChange": (e) => handleCambioEmail(e)
                    },
                ]
            },
            {
                "key": "domicilio",
                "subtitle": "Domicilio del paciente",
                "inputs": [
                    {
                        "type": "text",
                        "label": "Calle",
                        "placeholder": "Jackson St.",
                        "required": true,
                        "value": datosPaciente.domicilio.calle,
                        "onChange": (e) => handleCambioCalle(e)
                    },
                    {
                        "type": "text",
                        "label": "Numero de calle",
                        "placeholder": "1337",
                        "required": true,
                        "value": datosPaciente.domicilio.numero,
                        "onChange": (e) => handleCambioNumero(e)
                    },
                    {
                        "type": "text",
                        "label": "Localidad",
                        "placeholder": "Las Piedras",
                        "required": true,
                        "value": datosPaciente.domicilio.localidad,
                        "onChange": (e) => handleCambioLocalidad(e)
                    },
                    {
                        "type": "text",
                        "label": "Provincia",
                        "placeholder": "Maldonado",
                        "required": true,
                        "value": datosPaciente.domicilio.provincia,
                        "onChange": (e) => handleCambioProvincia(e)
                    },
                ]
            }]}/>
        </Container>

    )
}

export default ModificarPaciente