import React, {useState} from 'react'
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

const CrearOdontologo = ({urlOdontologos}) => {

    const navigate = useNavigate();

    const [verOdontologo, setVerOdontologo] = useState(false);

    const [datosOdontologo, setDatosOdontologo] = useState({
        "nombre": "",
        "apellido": "",
        "matricula": "",
    })

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

    const crearOdontologo = (e) => {
        e.preventDefault();
        axios.post(urlOdontologos, datosOdontologo)
            .then(response => {
                creacionExitosa("odontólogo")
                if (verOdontologo) {
                    // TODO: luego cambiar a odontologo/id
                    navigate("/odontologos/" + response.data.id);
                } else {
                    setDatosOdontologo({
                        "nombre": "",
                        "apellido": "",
                        "matricula": "",
                    })
                }
            })
            .catch(err => {
                creacionFallida("odontólogo")
                console.log("Promesa rechazada:")
                console.log(err)
            })
        return false;
    }

    return (
        <Container className="mt-3">
            <FormCreateComponent 
            onSubmit={(e) => crearOdontologo(e)} 
            title={"Añadir nuevo odontólogo"} 
            buttonLabel={"Crear odontólogo"} 
            verOnChange={(e) => handleCambioVerOdontologo(e)} 
            verLabel={"Ver odontólogo creado"} 
            formGroups={[{
                "key": "datos",
                // "subtitle": "Datos del odontólogo",
                "inputs": [
                    {
                        "type": "text",
                        "label": "Matrícula",
                        "placeholder": "515151",
                        "required": true,
                        "value": datosOdontologo.matricula,
                        "onChange": (e) => handleCambioMatricula(e)
                    },
                    {
                        "type": "text",
                        "label": "Nombre",
                        "placeholder": "Jhon",
                        "required": true,
                        "value": datosOdontologo.nombre,
                        "onChange": (e) => handleCambioNombre(e)
                    },
                    {
                        "type": "text",
                        "label": "Apellido",
                        "placeholder": "Jhonson",
                        "required": true,
                        "value": datosOdontologo.apellido,
                        "onChange": (e) => handleCambioApellido(e)
                    }
                ]
            }]}/>
        </Container>

    )
}

export default CrearOdontologo