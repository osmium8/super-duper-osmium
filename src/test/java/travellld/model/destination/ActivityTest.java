package travellld.model.destination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ActivityTest {
    
    private Activity getActivity(int bookedSlots, int maxCapacity) {
        Activity activity = new Activity(0, "Dummy", "Activity", 100.00, maxCapacity);
        activity.setBookedSlots(bookedSlots);
        return activity;
    }

    @Test
    void createBooking_whenSlotsAvailable_thenBookedSlotsIncrement() {
        // Arrange
        int bookedSlots = 0;
        Activity activity = this.getActivity(bookedSlots, 100);

        // Act
        Boolean result = activity.createBooking();
        int expectedBookedSlots = 1;

        // Assert
        assertEquals(expectedBookedSlots, activity.getBookedSlots());
    }

    @Test
    void createBooking_whenSlotsAvailable_thenReturnsTrue() {
        // Arrange
        Activity activity = this.getActivity(0, 100);

        // Act
        Boolean result = activity.createBooking();

        // Assert
        assertTrue(result);
    }

    @Test
    void createBooking_whenSlotsUnavailable_thenReturnsFalse() {
        // Arrange
        Activity activity = this.getActivity(1, 1);

        // Act
        Boolean result = activity.createBooking();

        // Assert
        assertFalse(result);
    }

    @Test
    void isAvailable_whenSlotsAvailable_thenReturnsTrue() {
        // Arrange
        Activity activity = this.getActivity(0, 1);

        // Act
        Boolean result = activity.isAvailable();

        // Assert
        assertTrue(result);
    }

    @Test
    void isAvailable_whenSlotsUnavailable_thenReturnsFalse() {
        // Arrange
        Activity activity = this.getActivity(1, 1);

        // Act
        Boolean result = activity.isAvailable();

        // Assert
        assertFalse(result);
    }
}
