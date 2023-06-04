package travellld.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import travellld.model.destination.Destination;


public class DestinationRepo implements Repo<Destination> {

    Map<Integer, Destination> map;

    public DestinationRepo() {
        map = new HashMap<>();
    }

    @Override
    public List<Destination> getAll() {
        return (List<Destination>) map.values();
    }

    @Override
    public Destination getById(int id) {
        return map.get(id);
    }

    @Override
    public boolean save(Destination destination) {
        map.put(destination.getId(), destination);
        return true;
    }
    
}
