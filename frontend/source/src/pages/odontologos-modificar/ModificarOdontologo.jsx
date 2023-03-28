import React, { useEffect, useRef, useState } from 'react'
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

const ModificarOdontologo = ({ urlOdontologos, setOdontologos, odontologos, loaded }) => {

    const navigate = useNavigate();

    const { state } = useLocation();
    
    const [verOdontologo, setVerOdontologo] = useState(false);
    
    const [datosOdontologo, setDatosOdontologo] = useState(state ? state : {
        "id": "",
        "nombre": "",
        "apellido": "",
        "matricula": "",
    })

    useEffect(() => {
        if (datosOdontologo.id && odontologos.length > 0) {
            const odontologoBuscado = odontologos.find(odontologo => odontologo.id == datosOdontologo.id)
            setDatosOdontologo(odontologoBuscado)
        }
    }, [datosOdontologo.id])

    const isHidden = false;
    const colClasses = "mb-1";
    const labelClasses = "mb-1";
    const inputClasses = "pt-1 pb-1 ps-2";
    const inputStyle = {
        fontSize: "0.90rem"
    }

    const handleCambioMatricula = (e) => {
        setDatosOdontologo(prevState => ({ ...prevState, "matricula": e.target.value }))
    }

    const handleCambioNombre = (e) => {
        setDatosOdontologo(prevState => ({ ...prevState, "nombre": e.target.value }))
    }

    const handleCambioApellido = (e) => {
        setDatosOdontologo(prevState => ({ ...prevState, "apellido": e.target.value }))
    }

    const handleCambioVerOdontologo = (e) => {
        setVerOdontologo(() => e.target.checked)
    }

    const handleCambioOdontologo = (e) => {
        setDatosOdontologo(prevState => ({ ...prevState, "id": e.target.value }))
    }

    const renderizarOpcionesOdontologos = () => {
        return odontologos.map((odontologo) => (
            <option key={odontologo.id} value={odontologo.id}>{odontologo.nombreCompleto}</option>
        ))
    }

    const modificarOdontologo = (e) => {
        e.preventDefault();
        axios.put(urlOdontologos, datosOdontologo)
            .then(response => {
                modificacionExitosa("odontólogo")
                if (verOdontologo) {
                    // TODO: luego cambiar a odontologo/id
                    navigate("/odontologos/" + datosOdontologo.id);
                } else {
                    setOdontologos(prevState => prevState.map((odontologo) => odontologo.id == datosOdontologo.id ? datosOdontologo : odontologo))
                    setDatosOdontologo({
                        "id": "",
                        "nombre": "",
                        "apellido": "",
                        "matricula": "",
                    })
                }
            })
            .catch(err => {
                modificacionFallida("odontólogo")
                console.log("Promesa rechazada:")
                console.log(err)
            })
        return false;
    }

    return (
        <Container className="mt-3">
            <FormCreateComponent
                onSubmit={(e) => modificarOdontologo(e)}
                title={"Modificar odontólogo"}
                buttonLabel={"Modificar odontólogo"}
                verOnChange={(e) => handleCambioVerOdontologo(e)}
                verLabel={"Ver odontólogo modificado"}
                formGroups={[{
                    "key": "datos",
                    // "subtitle": "Datos del odontólogo",
                    "inputs": [
                        {
                            "label": "Odontólogo",
                            "type": "select",
                            "required": true,
                            "placeholder": "Seleccionar un odontólogo...",
                            "options": renderizarOpcionesOdontologos(),
                            "value": datosOdontologo?.id,
                            "onChange": (e) => handleCambioOdontologo(e)
                        },
                        {
                            "type": "text",
                            "label": "Matrícula",
                            "placeholder": "515151",
                            "required": true,
                            "value": datosOdontologo?.matricula,
                            "onChange": (e) => handleCambioMatricula(e)
                        },
                        {
                            "type": "text",
                            "label": "Nombre",
                            "placeholder": "Jhon",
                            "required": true,
                            "value": datosOdontologo?.nombre,
                            "onChange": (e) => handleCambioNombre(e)
                        },
                        {
                            "type": "text",
                            "label": "Apellido",
                            "placeholder": "Jhonson",
                            "required": true,
                            "value": datosOdontologo?.apellido,
                            "onChange": (e) => handleCambioApellido(e)
                        }
                    ]
                }]} />
        </Container>

    )
}

export default ModificarOdontologo