export const routes = [
    {   
        "type": "singleRoute",
        "key": "inicio",
        "label": "Inicio",
        "route": "/"
    },
    {
        "type": "category",
        "categoryLabel": "Odontólogos",
        "key": "odontologos",
        "routes": [
            {
                "type": "route",
                "label": "Crear nuevo",
                "route": "/odontologos/crear"
            },
            {
                "type": "route",
                "label": "Modificar",
                "route": "/odontologos/modificar"
            },
            {
                "type": "route",
                "label": "Listado",
                "route": "/odontologos"
            },
        ]
    },
    {
        "type": "category",
        "key": "pacientes",
        "categoryLabel": "Pacientes",
        "routes": [
            {
                "type": "route",
                "label": "Crear nuevo",
                "route": "/pacientes/crear"
            },
            {
                "type": "route",
                "label": "Modificar",
                "route": "/pacientes/modificar"
            },
            {
                "type": "route",
                "label": "Listado",
                "route": "/pacientes"
            },
        ]
    },
    {
        "type": "category",
        "key": "turnos",
        "categoryLabel": "Turnos",
        "routes": [
            {
                "type": "route",
                "label": "Crear nuevo",
                "route": "/turnos/crear"
            },
            {
                "type": "route",
                "label": "Listado",
                "route": "/turnos"
            },
        ]
    },
]