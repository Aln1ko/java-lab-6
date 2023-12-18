import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ex15Test {

    @Test
    public void testChangeRectangle_WithValidInput() {
        ex15 testFrame = new ex15("Test Window");
        testFrame.widthField.setText("20");
        testFrame.heightField.setText("30");

        testFrame.changeRectangle();

        assertEquals(20, testFrame.width);
        assertEquals(30, testFrame.height);
    }

    @Test
    public void testChangeRectangle_WithInvalidInput() {
        ex15 testFrame = new ex15("Test Window");
        testFrame.widthField.setText("abc");
        testFrame.heightField.setText("def");

        testFrame.changeRectangle();

        assertEquals(10, testFrame.width); // Перевірка, що значення не змінилися при невірних даних
        assertEquals(10, testFrame.height);
    }
}
