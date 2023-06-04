package travellld.model.passenger;

import travellld.exception.business.DestinationActivityMismatchException;
import travellld.exception.business.TravelPackageDestinationMismatchException;
import travellld.exception.business.TravelPackageNotBookedException;
import travellld.model.TravelPackage;
import travellld.model.destination.Activity;
import travellld.model.destination.Destination;
import travellld.model.passenger.privilege.Privilege;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Passenger {

    private String name;
    private int id;
    private List<TravelPackage> bookedTravelPackages;
    private List<ActivityBooking> bookedActivities;
    private Privilege privilege;

    public Passenger(String name, int id, Privilege privilege) {
        this.name = name;
        this.id = id;
        this.privilege = privilege;
        this.bookedActivities = new ArrayList<>();
        this.bookedTravelPackages = new ArrayList<>();
    }

    public String getActivitiesDetails() {
        StringBuilder result = new StringBuilder("name: "+this.name);
        result.append("\n number: "+this.id);
        for (ActivityBooking activityBooking: this.bookedActivities) {
            result.append("\n"+activityBooking.toString());
        }
        return result.toString();
    }

    
    public boolean bookActivity(TravelPackage travelPackage, Destination destination, Activity activity) throws DestinationActivityMismatchException, TravelPackageDestinationMismatchException, TravelPackageNotBookedException {
        if (this.bookedTravelPackages.contains(travelPackage) == false) {
            throw new TravelPackageNotBookedException("This user has not purchased the travel package");
        }

        if (travelPackage.getDestinations().contains(destination) == false) {
            throw new TravelPackageDestinationMismatchException("This destination is not in the itenary of the Travel Package.");
        }

        if (destination.getActivities().contains(activity) == false) {
            throw new DestinationActivityMismatchException("This activity is not available in the destination.");
        }

        Double cost = activity.getCost();

        if (this.privilege.canPurchase(cost) && activity.createBooking()) {
            // validated
            this.privilege.purchase(cost);
           bookedActivities.add(new ActivityBooking(0, this.privilege.getEffectivePrice(cost), activity, destination));
            return true;
        }
        
        return false;
    }

    public boolean bookTravelPackage(TravelPackage travelPackage) {
        Double effectivePrice = travelPackage.getCost();

        if (this.privilege.canPurchase(effectivePrice)) {
            this.privilege.purchase(effectivePrice);
            bookedTravelPackages.add(travelPackage);
            travelPackage.addPassenger(this);
            return true;
        }
        
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

}
