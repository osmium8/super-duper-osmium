package travellld.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import travellld.exception.object.ActivityNotFoundException;
import travellld.exception.object.DestinationNotFoundException;
import travellld.exception.object.PassengerNotFoundException;
import travellld.exception.object.TravelPackageNotFoundException;
import travellld.model.TravelPackage;
import travellld.model.destination.Activity;
import travellld.model.destination.Destination;
import travellld.model.passenger.Passenger;
import travellld.model.passenger.privilege.StandardPrivilege;
import travellld.repo.ActivityRepo;
import travellld.repo.DestinationRepo;
import travellld.repo.PassengerRepo;
import travellld.repo.TravelPackageRepo;

class BookingServiceTest {

    static BookingService bookingService;
    static PassengerRepo passengerRepo;
    static ActivityRepo activityRepo;
    static TravelPackageRepo travelPackageRepo;
    static DestinationRepo destinationRepo;

    @BeforeAll
    private static void setup() {
        passengerRepo = new PassengerRepo();
        activityRepo = new ActivityRepo();
        travelPackageRepo = new TravelPackageRepo();
        destinationRepo = new DestinationRepo();
        bookingService = new BookingService(activityRepo, destinationRepo, travelPackageRepo, passengerRepo);

        // populate data in repositories
        activityRepo.save(new Activity(0, "Jump", "Jump Jump Jump", 10.00, 50));
        activityRepo.save(new Activity(1, "Roll", "Roll Roll Roll", 11.00, 25));

        Destination destination1 = new Destination(0, "Chandigarh");
        destination1.addActivity(activityRepo.getById(0));

        Destination destination2 = new Destination(1, "Moosa");
        destination2.addActivity(activityRepo.getById(1));

        destinationRepo.save(destination1);
        destinationRepo.save(destination2);

        TravelPackage travelPackage = new TravelPackage(0, "Punjab", 1000, 1.00);
        travelPackage.addDestination(destination1);
        travelPackage.addDestination(destination2);
        
        travelPackageRepo.save(travelPackage);

        passengerRepo.save(new Passenger("John Doe",0, new StandardPrivilege(1000.00)));
    }
    
    @Test
    void bookActivityForPassenger_whenPassengerNotPresentForId_thenThrowPassengerNotFoundException() {
        assertThrows(PassengerNotFoundException.class, () -> {
            boolean result = bookingService.bookActivityForPassenger(999, 0, 0, 0);
        });
    }

    @Test
    void bookActivityForPassenger_whenDestinationNotPresentForId_thenThrowDestinationNotFoundException() {
        assertThrows(DestinationNotFoundException.class, () -> {
            boolean result = bookingService.bookActivityForPassenger(0, 0, 999, 0);
        });
    }

    @Test
    void bookActivityForPassenger_whenTravelPackageNotPresentForId_thenThrowTravelPackageNotFoundException() throws Exception {
        assertThrows(TravelPackageNotFoundException.class, () -> {
            boolean result = bookingService.bookActivityForPassenger(0, 999, 0, 0);
        });
    }

    @Test
    void bookActivityForPassenger_whenActivityNotPresentForId_thenThrowActivityNotFoundException() throws Exception {
        assertThrows(ActivityNotFoundException.class, () -> {
            boolean result = bookingService.bookActivityForPassenger(0, 0, 0, 999);
        });
    }

    @Test
    void bookActivityForPassenger_integration_whenValidConditions_thenReturnsTrue() throws Exception {
        // Arrange
        Passenger passenger = passengerRepo.getById(0);
        TravelPackage travelPackage = travelPackageRepo.getById(0);
        passenger.bookTravelPackage(travelPackage);

        // Act
        boolean result = bookingService.bookActivityForPassenger(0, 0, 0, 0);

        // Assert
        assertTrue(result);
    }
}
