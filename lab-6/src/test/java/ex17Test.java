import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ex17Test {

    @Test
    public void testChangeArc_WithValidInput() {
        ex17 testFrame = new ex17("Test Window");
        testFrame.widthField.setText("100");
        testFrame.heightField.setText("100");
        testFrame.coorXField.setText("50");
        testFrame.coorYField.setText("50");
        testFrame.startCornerField.setText("30");
        testFrame.finishCornerField.setText("130");

        testFrame.changeArc();

        assertEquals(100, testFrame.width);
        assertEquals(100, testFrame.height);
        assertEquals(50, testFrame.coorX);
        assertEquals(50, testFrame.coorY);
        assertEquals(30, testFrame.startCorner);
        assertEquals(130, testFrame.finishCorner);
    }

    @Test
    public void testChangeArc_WithInvalidInput() {
        ex17 testFrame = new ex17("Test Window");
        testFrame.widthField.setText("abc");
        testFrame.heightField.setText("def");
        testFrame.coorXField.setText("invalid");
        testFrame.coorYField.setText("text");
        testFrame.startCornerField.setText("30");
        testFrame.finishCornerField.setText("400");

        testFrame.changeArc();

        // Перевірте, чи значення не змінюються при некоректних даних
        assertNotEquals("abc", testFrame.width);
        assertNotEquals("def", testFrame.height);
        assertNotEquals("invalid", testFrame.coorX);
        assertNotEquals("text", testFrame.coorY);
        assertNotEquals("400", testFrame.finishCorner);
    }
}
