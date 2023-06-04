package travellld.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import travellld.model.destination.Activity;

public class ActivityRepo implements Repo<Activity> {

    Map<Integer, Activity> map;

    public ActivityRepo() {
        this.map = new HashMap<>();
    }
    
    @Override
    public List<Activity> getAll() {
        return (List<Activity>) map.values();
    }

    @Override
    public Activity getById(int id) {
        return map.get(id);
    }

    @Override
    public boolean save(Activity activity) {
        map.put(activity.getId(), activity);
        return true;
    }
}
