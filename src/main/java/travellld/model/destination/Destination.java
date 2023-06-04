package travellld.model.destination;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Destination {

    private int id;
    private String name;
    private List<Activity> activities;

    public Destination(int id, String name) {
        this.id = id;
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public boolean addActivity(Activity activity) {
        this.activities.add(activity);
        return true;
    }

    public String getAvailableActivitiesDetail() {
        StringBuilder result = new StringBuilder();
        for (Activity activity: this.activities) {
            if (activity.isAvailable()) {
                result.append(activity.toString());
                result.append("\n slots available: "+activity.getAvailableSlots());
            }
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return name;
    }
}
