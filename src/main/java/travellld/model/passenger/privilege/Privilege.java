package travellld.model.passenger.privilege;

public interface Privilege {
    public Double getEffectivePrice(Double cost);
    public boolean canPurchase(Double cost);
    public boolean purchase(Double cost);
}
