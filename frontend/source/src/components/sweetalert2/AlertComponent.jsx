import Swal from 'sweetalert2'
import withReactContent from 'sweetalert2-react-content'

const MySwal = withReactContent(Swal)

const mensajeExito = "Listo :>"
const mensajeError = "Ups... >.<"

const textoBotonError = "Entiendo :("

export const borradoExitoso = (entidad) => {
    MySwal.fire({
        title: <strong>{mensajeExito}</strong>,
        html: <i>Has eliminado el {entidad} satisfactoriamente.</i>,
        icon: 'success',
        timer: 1500,
        showConfirmButton: false,
      })
}

export const borradoFallido = (entidad) => {
    MySwal.fire({
        title: <strong>{mensajeError}</strong>,
        html: <i>No se ha podido eliminar el {entidad}.</i>,
        icon: 'error',
        // timer: 1500,
        showConfirmButton: true,
        confirmButtonText: textoBotonError,
      })
}

export const creacionExitosa = (entidad) => {
    MySwal.fire({
        title: <strong>{mensajeExito}</strong>,
        html: <i>Se ha creado un nuevo {entidad} satisfactoriamente.</i>,
        icon: 'success',
        timer: 1500,
        showConfirmButton: false,
      })
}

export const creacionFallida = (entidad) => {
    MySwal.fire({
        title: <strong>{mensajeError}</strong>,
        html: <i>No se ha podido crear el {entidad}.</i>,
        icon: 'error',
        // timer: 1500,
        showConfirmButton: true,
        confirmButtonText: textoBotonError,
      })
}

export const modificacionExitosa = (entidad) => {
    MySwal.fire({
        title: <strong>{mensajeExito}</strong>,
        html: <i>Se ha modificado el {entidad} satisfactoriamente.</i>,
        icon: 'success',
        timer: 1500,
        showConfirmButton: false,
      })
}

export const modificacionFallida = (entidad) => {
    MySwal.fire({
        title: <strong>{mensajeError}</strong>,
        html: <i>No se ha podido modificar el {entidad}.</i>,
        icon: 'error',
        // timer: 1500,
        showConfirmButton: true,
        confirmButtonText: textoBotonError,
      })
}