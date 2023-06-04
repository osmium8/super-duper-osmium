package travellld.service;

import travellld.exception.business.DestinationActivityMismatchException;
import travellld.exception.business.TravelPackageDestinationMismatchException;
import travellld.exception.business.TravelPackageNotBookedException;
import travellld.exception.object.ActivityNotFoundException;
import travellld.exception.object.DestinationNotFoundException;
import travellld.exception.object.PassengerNotFoundException;
import travellld.exception.object.TravelPackageNotFoundException;
import travellld.model.TravelPackage;
import travellld.model.destination.Activity;
import travellld.model.destination.Destination;
import travellld.model.passenger.Passenger;
import travellld.repo.ActivityRepo;
import travellld.repo.DestinationRepo;
import travellld.repo.PassengerRepo;
import travellld.repo.TravelPackageRepo;

public class BookingService {

    private ActivityRepo activityRepo;
    private DestinationRepo destinationRepo;
    private TravelPackageRepo travelPackageRepo;
    private PassengerRepo passengerRepo;

    public BookingService(ActivityRepo activityRepo, DestinationRepo destinationRepo, TravelPackageRepo travelPackageRepo,
            PassengerRepo passengerRepo) {
        this.activityRepo = activityRepo;
        this.destinationRepo = destinationRepo;
        this.travelPackageRepo = travelPackageRepo;
        this.passengerRepo = passengerRepo;
    }

    /**
     * Books an {@link Activity} for a {@link Passenger} corresponding to the given {@link TravelPackage} and {@link Destination} ids.
     * @param passengerId
     * @param travelPackageId
     * @param destinationId
     * @param activityId
     * @return {@code true} If Activity is booked {@code false} Otherwise
     * @throws PassengerNotFoundException
     * @throws TravelPackageNotFoundException
     * @throws DestinationNotFoundException
     * @throws ActivityNotFoundException
     * @throws DestinationActivityMismatchException If the specified Activity is not present for the specified Destination.
     * @throws TravelPackageDestinationMismatchException If the specified Destination is not present for the specified TravelPackage.
     * @throws TravelPackageNotBookedException If the specified TravelPackage is not booked for the specified Passenger.
     */
    public boolean bookActivityForPassenger(int passengerId, int travelPackageId, int destinationId, int activityId) throws PassengerNotFoundException, TravelPackageNotFoundException, DestinationNotFoundException, ActivityNotFoundException, DestinationActivityMismatchException, TravelPackageDestinationMismatchException, TravelPackageNotBookedException {
        Passenger passenger = passengerRepo.getById(passengerId);
        TravelPackage travelPackage = travelPackageRepo.getById(travelPackageId);
        Destination destination = destinationRepo.getById(destinationId);
        Activity activity = activityRepo.getById(activityId);

        if (passenger == null) {
            throw new PassengerNotFoundException("Passenger not found for given Id: "+passengerId);
        }

        if (travelPackage == null) {
            throw new TravelPackageNotFoundException("TravelPackage not found for given Id: "+travelPackageId);
        }

        if (destination == null) {
            throw new DestinationNotFoundException("Destination not found for given Id: "+destinationId);
        }

        if (activity == null) {
            throw new ActivityNotFoundException("Activity not found for given Id: "+activityId);
        }

        return passenger.bookActivity(travelPackage, destination, activity);
    }
}
