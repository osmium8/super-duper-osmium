package travellld.model.passenger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import travellld.exception.business.DestinationActivityMismatchException;
import travellld.exception.business.TravelPackageDestinationMismatchException;
import travellld.exception.business.TravelPackageNotBookedException;
import travellld.model.TravelPackage;
import travellld.model.destination.Activity;
import travellld.model.destination.Destination;
import travellld.model.passenger.privilege.StandardPrivilege;

class PassengerTest {

    private static Activity activity;
    private static Destination destination;
    private static TravelPackage travelPackage;

    @BeforeAll
    private static void setup() {
        Activity activity1 = new Activity(0, "Jump", "Jump Jump Jump", 10.00, 50);
        Activity activity2 = new Activity(1, "Roll", "Roll Roll Roll", 11.00, 25);

        Destination destination1 = new Destination(0, "Chandigarh");
        destination1.addActivity(activity1);

        Destination destination2 = new Destination(1, "Moosa");
        destination2.addActivity(activity2);

        travelPackage = new TravelPackage(0, "Punjab", 1000, 1.00);
        travelPackage.addDestination(destination1);
        travelPackage.addDestination(destination2);

        destination = destination1;
        activity = activity1;
    }

    @Test
    void bookActivity_whenValidConditions_thenBookedActivitiesFieldIsUpdated() throws Exception {
        // Arrange
        Passenger passenger = getStandardPrivilegePassenger();
        passenger.bookTravelPackage(travelPackage);

        List<ActivityBooking> expectedBookedActivities = new ArrayList<>();
        expectedBookedActivities.add(new ActivityBooking(0, activity.getCost(), activity, destination));

        // Act
        boolean result = passenger.bookActivity(travelPackage, destination, activity);
        List<ActivityBooking> actualBookedActivities = passenger.getBookedActivities();
        
        // Assert
        assertEquals(expectedBookedActivities, actualBookedActivities);
    }
    
    @Test
    void bookActivity_whenTravelPackageIsNotBooked_thenThrowsException() throws Exception {
        // Arrange
        Passenger passenger = getStandardPrivilegePassenger();

        // Act & Assert
        assertThrows(TravelPackageNotBookedException.class, () -> {
            boolean result = passenger.bookActivity(travelPackage, destination, activity);
        });
    }

    @Test
    /**
     * tests integration as well
     */
    void bookActivity_whenTravelPackageIsBooked_thenReturnsTrue() throws Exception {
        // Arrange
        Passenger passenger = getStandardPrivilegePassenger();
        passenger.bookTravelPackage(travelPackage);

        // Act
        boolean result = passenger.bookActivity(travelPackage, destination, activity);

        // Assert
        assertTrue(result);
    }

    @Test
    void bookActivity_whenTravelPackageDestinationMismatch_thenThrowsException() throws Exception {
        // Arrange
        Passenger passenger = getStandardPrivilegePassenger();
        passenger.bookTravelPackage(travelPackage);
        Destination destination = new Destination(0, "temp");

        // Act & Assert
        assertThrows(TravelPackageDestinationMismatchException.class, () -> {
            boolean result = passenger.bookActivity(travelPackage, destination, activity);
        });
    }

    @Test
    void bookActivity_whenDestinationActivityMismatch_thenThrowsException() throws Exception {
        // Arrange
        Passenger passenger = getStandardPrivilegePassenger();
        passenger.bookTravelPackage(travelPackage);
        Activity activity = new Activity(0, "temp", "temp", 100.00, 100);

        // Act & Assert
        assertThrows(DestinationActivityMismatchException.class, () -> {
            boolean result = passenger.bookActivity(travelPackage, destination, activity);
        });
    }

    private Passenger getStandardPrivilegePassenger() {
        return new Passenger("John Doe",0, new StandardPrivilege(1000.00));
    }
}
