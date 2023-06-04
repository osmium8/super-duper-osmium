package travellld.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import travellld.model.TravelPackage;
import travellld.model.destination.Activity;
import travellld.model.destination.Destination;
import travellld.model.passenger.Passenger;
import travellld.model.passenger.privilege.StandardPrivilege;
import travellld.repo.ActivityRepo;
import travellld.repo.DestinationRepo;
import travellld.repo.PassengerRepo;
import travellld.repo.TravelPackageRepo;

class ReceptionServiceTest {

    static ReceptionService receptionService;
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
        receptionService = new ReceptionService(travelPackageRepo);

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
    }

    @Test
    void getPassengersDetailForTravelPackage_whenDataForIdIsPresent_thenReturnsValidString() {
        // Arrange
        passengerRepo.save(new Passenger("John Doe",0, new StandardPrivilege(1000.00)));
        Passenger passenger = passengerRepo.getById(0);
        TravelPackage travelPackage = travelPackageRepo.getById(0);
        passenger.bookTravelPackage(travelPackage);

        // Act
        String passengerDetails = receptionService.getPassengersDetailForTravelPackage(0);

        // Assert
        assertTrue(passengerDetails.contains("Punjab"));
        assertTrue(passengerDetails.contains("1000"));
        assertTrue(passengerDetails.contains("John Doe"));
    }

    @Test
    void getPassengersDetailForTravelPackage_whenDataForIdIsNotPresent_thenReturnsEmptyString() {
        // Arrange
        ReceptionService receptionService = new ReceptionService(new TravelPackageRepo());
        
        // Act
        String passengerDetails = receptionService.getPassengersDetailForTravelPackage(0);

        // Assert
        assertEquals("", passengerDetails);
    }

    @Test
    void getItenary_whenDataForIdIsPresent_thenReturnsValidString() {
        // Arrange
        
        // Act
        String itenary = receptionService.getItenary(0);

        // Assert
        assertTrue(itenary.contains("Punjab"));
        assertTrue(itenary.contains("Chandigarh"));
        assertTrue(itenary.contains("Jump"));
        assertTrue(itenary.contains("Moosa"));
        assertTrue(itenary.contains("Roll"));
    }

    @Test
    void getItenary_whenDataForIdIsNotPresent_thenReturnsEmptyString() {
        // Arrange
        ReceptionService receptionService = new ReceptionService(new TravelPackageRepo());
        
        // Act
        String itenary = receptionService.getItenary(0);

        // Assert
        assertEquals("", itenary);
    }

}
