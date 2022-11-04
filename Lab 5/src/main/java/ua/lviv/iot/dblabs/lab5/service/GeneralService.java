package ua.lviv.iot.dblabs.lab5.service;

import java.util.List;
import java.util.Optional;

public interface GeneralService<T, ID> {
    List<T> findAll();

    T findById(ID id);

    T create(T entity);

    void update(ID id, T entity);

    void delete(ID id);
}
