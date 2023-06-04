package travellld.model.passenger.privilege;

import lombok.Getter;

@Getter
public class GoldPrivilege implements Privilege {

    private Double balance;
    private static final int DISCOUNT_PERCENTAGE = 10;
    private static final Double CANCELLATION_CHARGES = 0.99;

    public GoldPrivilege(Double balance) {
        this.balance = balance;
    }
    
    @Override
    public boolean canPurchase(Double cost) {
        Double effectivePrice = cost - ((cost * GoldPrivilege.DISCOUNT_PERCENTAGE)/100);
        return effectivePrice < this.balance;
    }

    @Override
    public boolean purchase(Double cost) {
        if (this.canPurchase(cost)) {
            this.balance -= cost;
            return true;
        }
        return false;
    }

    @Override
    public Double getEffectivePrice(Double cost) {
        return cost - ((cost * GoldPrivilege.DISCOUNT_PERCENTAGE)/100);
    }
}
