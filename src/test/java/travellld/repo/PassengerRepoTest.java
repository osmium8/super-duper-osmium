package travellld.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import travellld.model.passenger.Passenger;
import travellld.model.passenger.privilege.StandardPrivilege;

class PassengerRepoTest {

    PassengerRepo passengerRepo;

    public PassengerRepoTest() {
        passengerRepo = new PassengerRepo();
    }

    private Passenger getPassenger() {
        return new Passenger("John Doe", 0, new StandardPrivilege(0.99));
    }
    
    @Test
    void getById_whenObjectForIdIsPresent_thenReturnsObject() {
        // Arrange
        Passenger passenger = getPassenger();
        passengerRepo.save(passenger);

        // Act
        Passenger result = passengerRepo.getById(0);
        
        // Assert
        assertEquals(passenger.getId(), result.getId());
    }

    @Test
    void getById_whenObjectForIdNotPresent_thenReturnsNull() {
        // Arrange
        Passenger passenger = getPassenger();
        passengerRepo.save(passenger);

        // Act
        Passenger result = passengerRepo.getById(Integer.MAX_VALUE);

        // Assert
        assertNull(result);
    }

    @Test
    void getAll_whenObjectsArePresent_thenReturnsList() {
        // Arrange
        Passenger passenger = getPassenger();
        passengerRepo.save(passenger);

        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);

        // Act
        List<Passenger> result = new ArrayList<>();
        passengerRepo.getAll().forEach(p -> result.add(p));

        // Assert
        assertIterableEquals(passengers, result);
        assertEquals(1, result.size());
    }

    @Test
    void save_WhenObjectSaved_thenAssertionSucceeds() {
        // Arrange
        Passenger passenger = getPassenger();
        passengerRepo.save(passenger);

        // Act
        Passenger result = passengerRepo.getById(passenger.getId());

        // Assert
        assertEquals(passenger.getId(), result.getId());	     
    }
}
