package travellld.model.passenger.privilege;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StandardPrivilegeTest {

    private StandardPrivilege getPrivilege(Double balance) {
        return new StandardPrivilege(balance);
    }

    @Test
    void purchase_whenCostLessThanBalance_thenBalanceDeducted() {
        // Arrange
        Double balance = 100.00;
        Double cost = 10.00;
        StandardPrivilege privilege = getPrivilege(balance);
        Double expectedBalanceAfterPurchase = balance - cost;

        // Act
        Boolean result = privilege.purchase(cost);

        // Assert
        assertTrue(result);
        assertEquals(expectedBalanceAfterPurchase, privilege.getBalance());;
    }

    @Test
    void purchase_whenCostLessThanBalance_thenReturnsTrue() {
        // Arrange
        StandardPrivilege privilege = getPrivilege(100.00);

        // Act
        Boolean result = privilege.purchase(10.00);

        // Assert
        assertTrue(result);
    }

    @Test
    void effectivePrice_whenCostInput_thenReturnsSame() {
        // Arrange
        StandardPrivilege privilege = getPrivilege(100.00);

        // Act
        Double actualCost = 10.00;
        Double effectivePrice = privilege.getEffectivePrice(actualCost);

        // Assert
        assertEquals(actualCost, effectivePrice);
    }

    @Test
    void canPurchase_whenCostLessThanBalance_thenReturnsTrue() {
        // Arrange
        StandardPrivilege privilege = getPrivilege(100.00);

        // Act
        boolean result = privilege.canPurchase(10.00);

        // Assert
        assertTrue(result);
    }

    @Test
    void canPurchase_whenCostLessThanBalance_thenReturnsFalse() {
        // Arrange
        StandardPrivilege privilege = getPrivilege(1.00);

        // Act
        boolean result = privilege.canPurchase(10.00);

        // Assert
        assertFalse(result);
    }
    
}
