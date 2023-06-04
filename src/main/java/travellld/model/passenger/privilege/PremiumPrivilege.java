package travellld.model.passenger.privilege;

import lombok.Getter;

@Getter
public class PremiumPrivilege implements Privilege {

    private static final Double CANCELLATION_CHARGES = 0.00;

    @Override
    public boolean canPurchase(Double cost) {
        return true;
    }

    @Override
    public Double getEffectivePrice(Double cost) {
        return 0.00;
    }

    @Override
    public boolean purchase(Double cost) {
        return true;
    }
}
