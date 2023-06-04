package travellld.model.destination;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Activity {

    private int id;
    private String name;
    private String description;
    private Double cost;
    private int bookedSlots;
    private final int maxCapacity;

    public Activity(int id, String name, String description, Double cost, int capacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.maxCapacity = capacity;
        this.bookedSlots = 0;
    }

    public boolean isAvailable() {
        return bookedSlots < maxCapacity;
    }

    public int getAvailableSlots() {
        return maxCapacity - bookedSlots;
    }

    public boolean createBooking() {
        if (this.isAvailable()) {
            ++bookedSlots;
            return true;
        }
        return false;
    }

    public boolean cancelBooking() {
        if (this.isAvailable()) {
            ++bookedSlots;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Activity => "+this.name+", description: "+this.description+", cost: "+this.cost+", capacity: "+this.maxCapacity;
    }
}
