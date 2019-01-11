package JDBC.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void add(T t);

    void update(T t);

    Optional<T> findById(Integer id);

    List<T> findAll();

    void deleteById(Integer id);

    void deleteAll();

}
