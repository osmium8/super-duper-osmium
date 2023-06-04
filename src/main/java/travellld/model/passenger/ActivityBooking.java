package travellld.model.passenger;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import travellld.model.destination.Activity;
import travellld.model.destination.Destination;

@Getter
@Setter
@AllArgsConstructor
public class ActivityBooking {
    private int id;
    private Double price;
    private Activity activity;
    private Destination destination;

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;

        if (this.getClass() != o.getClass())
            return false;

        if (this == o)
            return true;

        ActivityBooking obj = (ActivityBooking)(o);

        return this.id == obj.id
            && this.price.equals(obj.price) 
            && this.activity.getId() == obj.activity.getId()
            && this.destination.getId() == obj.destination.getId();
    }

    @Override
    public final int hashCode() {
        int result = 17;

        if (this.destination != null) {
            result = 31 * result + this.destination.hashCode();
        }
        if (this.price != null) {
            result = 31 * result + this.price.hashCode();
        }
        if (this.activity != null) {
            result = 31 * result + this.activity.hashCode();
        }

        return result;
    }
}
