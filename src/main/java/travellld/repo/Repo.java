package travellld.repo;

import java.util.List;

public interface Repo<T> {
    public List<T> getAll();
    public T getById(int id);
    public boolean save(T t);
}
