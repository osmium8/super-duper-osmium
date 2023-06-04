package travellld.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import travellld.model.TravelPackage;

public class TravelPackageRepo implements Repo<TravelPackage> {

    Map<Integer, TravelPackage> map;

    public TravelPackageRepo() {
        this.map = new HashMap<>();
    }

    @Override
    public List<TravelPackage> getAll() {
        return (List<TravelPackage>) map.values();
    }

    @Override
    public TravelPackage getById(int id) {
        return map.get(id);
    }

    @Override
    public boolean save(TravelPackage travelPackage) {
        map.put(travelPackage.getId(), travelPackage);
        return true;
    }
    
}
