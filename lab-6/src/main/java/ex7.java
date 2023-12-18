/*
Виведення фігур у графічному вікні. У вікні визначено рядок меню (MenuBar),
в якому визначено два меню (Menu) – "Колір" та "Вивід".
У меню "Колір" визначено два пункти меню "Зелений" і "Синій".
У меню "Вивід" визначено три пункти меню (CheckBoxMenuItem):
"Квадрат", "Коло", і "Трикутник". У панелі (Canvas) вікна
 виводиться в графічному контексті вибрана в меню "Вивід" фігура.
 При виборі пункту першого меню вся фігура забарвлюється заданим кольором.
 */

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
/**
 * This class demonstrates drawing different shapes in a graphical window using AWT.
 * Shapes can be selected from the menu and colored accordingly.
 */
public class ex7 extends Frame {
    private CheckboxMenuItem square, circle, triangle;
    private Menu colorMenu, drawMenu;
    private Color chosenColor = Color.BLACK;
    private List<String> selectedShapes;
    private String selectedShape;
    private Canvas canvas;
    /**
     * Constructor to initialize the graphical window with menus and canvas.
     * @param name The name of the window.
     */
    public ex7(String name){
        super(name);
        selectedShapes = new ArrayList<>();
        setSize(400, 400);
        setLocation(100, 100);

        MenuBar menuBar = new MenuBar();

        colorMenu = new Menu("Колір");
        MenuItem green = new MenuItem("Зелений");
        MenuItem blue = new MenuItem("Синій");
        colorMenu.add(green);
        colorMenu.add(blue);
        menuBar.add(colorMenu);
        green.addActionListener(e -> {
            chosenColor = Color.GREEN;
            repaintCanvas();
        });
        blue.addActionListener(e -> {
            chosenColor = Color.BLUE;
            repaintCanvas();
        });


        drawMenu = new Menu("Вивід");
        square = new CheckboxMenuItem("Квадрат");
        circle = new CheckboxMenuItem("Коло");
        triangle = new CheckboxMenuItem("Трикутник");
        drawMenu.add(square);
        drawMenu.add(circle);
        drawMenu.add(triangle);
        menuBar.add(drawMenu);
        square.addItemListener(e->handleShapeSelection("Квадрат") );
        circle.addItemListener(e -> handleShapeSelection("Коло"));
        triangle.addItemListener(e -> handleShapeSelection("Трикутник"));
        setMenuBar(menuBar);

        canvas = new Canvas() {
            public void paint(Graphics g) {
                g.setColor(chosenColor);
                //g.fillRect(50, 50, 100, 100);
                for(String shape:selectedShapes) {
                    if (shape.equals("Квадрат")) {
                        g.fillRect(50, 50, 100, 100);
                    }
                    else if (shape.equals("Коло")) {
                        g.fillOval(170, 100, 100, 100);
                    }
                    else if (shape.equals("Трикутник")) {
                        int[] xPoints = {300, 250, 350};
                        int[] yPoints = {150, 250, 250};
                        g.fillPolygon(xPoints, yPoints, 3);
                    }
                }
            }
        };
        add(canvas);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        setVisible(true);
    }
    /**
     * Handles the selection of shapes.
     * @param shape The shape selected.
     */
        public void handleShapeSelection(String shape) {
        System.out.println("Метод handleShapeSelection вызван");
        if (!selectedShapes.contains(shape)) {
            selectedShapes.add(shape); // Добавляем фигуру в список, если ее там еще нет
        }
        else {
            selectedShapes.remove(shape); // Убираем фигуру из списка, если она уже была выбрана
        }
        System.out.println(selectedShapes);
        repaintCanvas();
    }
    /**
     * Repaints the canvas with the updated shapes.
     */
    private void repaintCanvas() {
        canvas.repaint();
        System.out.println(selectedShapes);
    }
    /**
     * Main method to start the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new ex7("My Window");
    }
    /**
     * Getter for the list of selected shapes.
     * @return The list of selected shapes.
     */
    public List<String> getSelectedShapes() {
        return selectedShapes;
    }
}
