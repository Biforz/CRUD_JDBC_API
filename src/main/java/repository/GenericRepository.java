package repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    List<T> showAll();

    T showById(ID id);

    T add(T t);

    T update(ID id, T t);

    void deleteById(ID id);


}
