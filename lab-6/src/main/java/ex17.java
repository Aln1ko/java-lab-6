/*
Малювання фігури у графічному вікні. У верхній панелі (Canvas) "Виведення дуги"
у графічному контексті малюються дуги. У нижній панелі (Panel) "Параметри дуги"
задаються наступні компоненти: напис (Label) "Координата X:",
текстове поле (TextField), напис (Label) "Координата Y:", текстове поле (TextField),
напис (Label) "Ширина:", текстове поле (TextField), напис (Label) "Висота:",
текстове поле (TextField), напис (Label) "Початковий кут:",
текстове поле (TextField), напис (Label) "Кінцевий кут: ", текстове поле
(TextField) та кнопка (Button) "Вивести дугу". При введенні даних
(у пікселях та градусах – для кутів) і при натисканні кнопки "Вивести дугу"
дуга перемальовується із заданими параметрами. При введенні даних перевіряється,
чи дані введені і чи є дані цілими числами. Значення кутів повинні бути позитивними
або негативними цілими числами в діапазоні (за абсолютною величиною) від 0 до 360.

 */
import java.awt.*;
import java.awt.event.*;
/**
 * Represents a window for drawing arcs with customizable parameters.
 * Arcs are drawn in the graphical context based on user-defined values.
 */
public class ex17 extends Frame {
    TextField widthField, heightField,coorXField,coorYField,finishCornerField,startCornerField;
    private Canvas canvas;
    int width,height,coorX,coorY,finishCorner,startCorner;
    private Label message;

    {
        width = 1050;
        height = 1050;
        coorX = 50;
        coorY = 50;
        startCorner = 30;
        finishCorner = 130;
        message= new Label("Виведення дуги");
    }
    /**
     * Constructs an ex17 window.
     * Initializes and sets up the graphical components for drawing arcs.
     * @param title The title of the window
     */
    public ex17(String title) {
        super(title);
        setSize(1366,768);

        canvas = new Canvas() {
            public void paint(Graphics g) {
                g.setColor(Color.red);
                g.drawArc(coorX,coorY, width,height,startCorner , finishCorner);
            }
        };

        add(canvas, BorderLayout.CENTER);
        message.setAlignment(Label.CENTER); // Center align the text
        Font font = new Font("Arial", Font.BOLD, 18); // Create a font (Arial, bold, size 18)
        message.setFont(font);
        add(message,BorderLayout.NORTH);

        Panel inputPanel = new Panel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(10);
        flowLayout.setVgap(10);
        inputPanel.setLayout(flowLayout);

        Label params = new Label("Параметри дуги:");
        Label coorX = new Label("Координата X:");
        coorXField = new TextField("50");
        Label coorY = new Label("Координата Y:");
        coorYField = new TextField("50");

        coorXField.setPreferredSize(new Dimension(75, 20));
        coorYField.setPreferredSize(new Dimension(75, 20));

        Label width = new Label("Ширина:");
        widthField = new TextField("1050");
        Label height = new Label("Висота:");
        heightField = new TextField("1050");

        widthField.setPreferredSize(new Dimension(75, 20));
        heightField.setPreferredSize(new Dimension(75, 20));

        Label startCorner = new Label("Початковий кут:");
        startCornerField = new TextField("30");
        Label finishCorner = new Label("Кінцевий кут:");
        finishCornerField = new TextField("130");

        startCornerField.setPreferredSize(new Dimension(75, 20));
        finishCornerField.setPreferredSize(new Dimension(75, 20));

        inputPanel.add(params);
        inputPanel.add(coorX);
        inputPanel.add(coorXField);
        inputPanel.add(coorY);
        inputPanel.add(coorYField);
        inputPanel.add(width);
        inputPanel.add(widthField);
        inputPanel.add(height);
        inputPanel.add(heightField);
        inputPanel.add(startCorner);
        inputPanel.add(startCornerField);
        inputPanel.add(finishCorner);
        inputPanel.add(finishCornerField);

        Button changeButton = new Button("Вивести дугу");
        changeButton.addActionListener(e -> changeArc());
        inputPanel.add(changeButton);
        add(inputPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        setVisible(true);
    }
    /**
     * Updates the drawn arc based on user-defined parameters.
     * Validates the input data before drawing the arc.
     */
    void changeArc() {
        String widthText = widthField.getText();
        String heightText = heightField.getText();
        String xText = coorXField.getText();
        String yText = coorYField.getText();
        String startText = startCornerField.getText();
        String finishText = finishCornerField.getText();

        if(widthText.equals("")||heightText.equals("")||xText.equals("")||yText.equals("")
            ||startText.equals("")||finishText.equals("")){
            message.setForeground(Color.RED);
            message.setText("Потрібно заповнити всі поля");
            message.repaint();
        }

        else {
            try {
                width = Integer.parseInt(widthText);
                height = Integer.parseInt(heightText);
                coorX = Integer.parseInt(xText);
                coorY = Integer.parseInt(yText);
                startCorner = Integer.parseInt(startText);
                finishCorner = Integer.parseInt(finishText);

                if(startCorner > 360 || startCorner < -360){
                    message.setForeground(Color.RED);
                    message.setText("Початковий кут повинен бути в межах від -360 до 360");
                    message.repaint();
                }

                else if(finishCorner > 360 || finishCorner < -360){
                    message.setForeground(Color.RED);
                    message.setText("Кінцевий кут повинен бути в межах від -360 до 360");
                    message.repaint();
                }
                else{
                    canvas.repaint();
                    message.setForeground(Color.black);
                    message.setText("Виведення дуги");
                    message.repaint();
                }
            }
            catch (NumberFormatException ex) {
                ex.printStackTrace();
                message.setForeground(Color.RED);
                message.setText("Потрібно ввести цілі числа");
                message.repaint();
            }
        }
    }

    /**
     * Launches the ex17 window.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new ex17("Виведення дуги");
    }
}

