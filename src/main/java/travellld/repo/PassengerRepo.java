package travellld.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import travellld.model.passenger.Passenger;

public class PassengerRepo implements Repo<Passenger> {

    Map<Integer, Passenger> map;

    public PassengerRepo() {
        this.map = new HashMap<>();
    }

    @Override
    public List<Passenger> getAll() {
        return map.values().stream().collect(Collectors.toList());
    }

    @Override
    public Passenger getById(int id) {
        return map.get(id);
    }

    @Override
    public boolean save(Passenger passenger) {
        map.put(passenger.getId(), passenger);
        return true;
    }
    
}
