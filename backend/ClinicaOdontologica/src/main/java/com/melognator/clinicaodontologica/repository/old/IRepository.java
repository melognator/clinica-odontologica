package com.melognator.clinicaodontologica.repository.old;

import java.util.List;

public interface IRepository<T> {

    T guardar(T t);
    T buscar (Long id);
    void actualizar (T t);
    void eliminar (Long id);

    T buscarPorString(String valor);
    List<T> buscarTodo();

}
