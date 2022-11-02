package ua.lviv.iot.dblabs.dao;


import java.util.List;
import java.util.Optional;

public interface GeneralDAO<T, ID> {
    List<T> findAll();

    Optional<T> findById(ID id);

    int create(T entity);

    int update(ID id, T entity);

    int delete(ID id);
}
