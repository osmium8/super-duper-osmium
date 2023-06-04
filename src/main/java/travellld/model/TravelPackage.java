package travellld.model;

import java.util.ArrayList;
import java.util.List;

import travellld.model.destination.Activity;
import travellld.model.destination.Destination;
import travellld.model.passenger.Passenger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelPackage {

    private int id;
    private String name;
    private int capacity;
    private Double cost;
    private List<Destination> destinations;
    private List<Passenger> passengers;

    public TravelPackage(int id, String name, int capacity, Double cost) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.cost = cost;
        this.destinations = new ArrayList<>();
        this.passengers = new ArrayList<>();
    }

    public String getItenary() {
        StringBuilder result = new StringBuilder("Travel Package => "+this.name);
        for (Destination destination: destinations) {
            result.append("\n\t"+destination);
            List<Activity> activities = destination.getActivities();
            for (Activity activity: activities) {
                result.append("\n\t\t"+activity.toString());
            }
        }
        return result.toString();
    }

    public String getPassengersDetail() {
        StringBuilder result = new StringBuilder("name: "+this.name);
        result.append("\n\tcapacity: "+this.capacity);
        for (Passenger passenger: this.passengers) {
            result.append("\n\t\tBooked Passengers:");
            result.append("\n\t\t name: "+passenger.toString());
        }
        return result.toString();
    }

    
    public boolean addDestination(Destination destination) {
        this.destinations.add(destination);
        return true;
    }

    public boolean addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
        return true;
    }

}
