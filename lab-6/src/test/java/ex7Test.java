import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ex7Test {

    @Test
    public void testHandleShapeSelection_AddShape() {
        ex7 testFrame = new ex7("Test Window");
        List<String> selectedShapes = testFrame.getSelectedShapes();

        testFrame.handleShapeSelection("Квадрат");
        assertTrue(selectedShapes.contains("Квадрат"));

        testFrame.handleShapeSelection("Трикутник");
        assertTrue(selectedShapes.contains("Трикутник"));
    }

    @Test
    public void testHandleShapeSelection_RemoveShape() {
        ex7 testFrame = new ex7("Test Window");
        List<String> selectedShapes = testFrame.getSelectedShapes();

        testFrame.handleShapeSelection("Квадрат");
        testFrame.handleShapeSelection("Трикутник");

        testFrame.handleShapeSelection("Квадрат");
        assertFalse(selectedShapes.contains("Квадрат"));
    }
}
