package ua.alextwit.twitserver.model.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    T save(T t);

    Optional<T> findById(Long id);

    List<T> getAll();

    T update(T t);

    void delete(Long id);
}
