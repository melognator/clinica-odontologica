package com.melognator.clinicaodontologica.repository.old;

import com.melognator.clinicaodontologica.entity.Turno;
import com.melognator.clinicaodontologica.repository.old.IRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TurnoDAOLista implements IRepository<Turno> {

    // TODO: Quitar static cuando se implemente la ORM
    private static final List<Turno> turnosBD = new ArrayList<>();

    @Override
    public Turno guardar(Turno turno) {
        turnosBD.add(turno);
        return turno;
    }

    @Override
    public Turno buscar(Long id) {
        for (Turno turno:turnosBD) {
            if (turno.getId().equals(id)){
                return turno;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Turno turno) {
        eliminar(turno.getId());
        guardar(turno);
    }

    @Override
    public void eliminar(Long id) {
        Turno turnoAEliminar = buscar(id);
        if (turnoAEliminar!=null){
            turnosBD.remove(turnoAEliminar);
        }
    }

    @Override
    public Turno buscarPorString(String valor) {
        return null;
    }

    @Override
    public List<Turno> buscarTodo() {
        return turnosBD;
    }
}
