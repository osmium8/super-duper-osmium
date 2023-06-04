package travellld.model.passenger.privilege;

import lombok.Getter;

@Getter
public class StandardPrivilege implements Privilege {

    private Double balance;
    private static final int DISCOUNT_PERCENTAGE = 0;
    private static final Double CANCELLATION_CHARGES = 0.99;

    public StandardPrivilege(Double balance) {
        this.balance = balance;
    }
    
    @Override
    public boolean canPurchase(Double cost) {
        return this.getEffectivePrice(cost) < this.balance;
    }

    @Override
    public Double getEffectivePrice(Double cost) {
        return cost - ((cost * StandardPrivilege.DISCOUNT_PERCENTAGE)/100);
    }

    @Override
    public boolean purchase(Double cost) {
        if (this.canPurchase(cost)) {
            this.balance -= this.getEffectivePrice(cost);
            return true;
        }
        return false;
    }
}