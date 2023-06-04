package travellld.service;

import travellld.model.TravelPackage;
import travellld.repo.TravelPackageRepo;

public class ReceptionService {

    private TravelPackageRepo travelPackageRepo;

    public ReceptionService(TravelPackageRepo travelPackageRepo) {
        this.travelPackageRepo = travelPackageRepo;
    }

    /**
     * Returns String of Itenary for a given {@link TravelPackage} id.
     * @param travelPackageId
     * @return String containing details of destinations if present otherwise an empty String.
     */
    public String getItenary(int travelPackageId) {
        TravelPackage travelPackage = travelPackageRepo.getById(travelPackageId);
        if (travelPackage != null) {
            return travelPackage.getItenary();
        }
        return "";
    }

    /**
     * Returns String of booked passenger details for a given {@link TravelPackage} id.
     * @param travelPackageId
     * @return String containing details of booked passengers if present otherwise an empty String.
     */
    public String getPassengersDetailForTravelPackage(int travelPackageId) {
        TravelPackage travelPackage = travelPackageRepo.getById(travelPackageId);
        if (travelPackage != null) {
            return travelPackage.getPassengersDetail();
        }
        return "";
    }
}
