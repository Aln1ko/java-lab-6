/*
15. Зміна фігури у графічному вікні. У центрі верхньої панелі (Canvas)
"Виведення прямокутника" у графічному контексті малюється прямокутник із
заокругленими вершинами. У нижній панелі (Panel) "Параметри прямокутника"
задаються наступні компоненти: напис (Label) "Колір:",
список що розгортається (Choice) зі значеннями: "Чорний" (за замовчуванням),
"Червоний", "Зелений" і "Синій" ", напис (Label) "Ширина закруглення",
текстове поле (TextField), напис (Label) "Висота закруглення:",
текстове поле (TextField) та кнопка (Button) "Змінити прямокутник".
При наборі даних у списках, що входять, введення даних у текстових полях
(у пікселях) і при натисканні кнопки "Змінити прямокутник" прямокутник
перемальовується заданим кольором і з заданим новими значеннями ширини і
висоти закруглень. Перед виведенням фігури виконується перевірка, чи введені всі
дані в текстових полях і чи є вони цілими числами.

 */
import java.awt.*;
import java.awt.event.*;
/**
 * Represents a graphical window to draw a rounded rectangle with customizable parameters.
 * The user can define the color, round width, and round height of the rectangle using text fields and choices.
 */
public class ex15 extends Frame {
    private Choice colorChoice;
    TextField widthField, heightField;
    private Canvas canvas;
    int width,height;
    private Label message;

    {
        width = 10;
        height = 10;
        message= new Label("Виведення прямокутника");
    }
    /**
     * Constructs an ex15 window.
     * Initializes and sets up the graphical components for drawing a rounded rectangle.
     * @param title The title of the window
     */
    public ex15(String title) {
        super(title);
        setSize(1280,720);

        canvas = new Canvas() {
            public void paint(Graphics g) {
                Color chosenColor;
                switch (colorChoice.getSelectedItem()) {
                    case "Червоний":
                        chosenColor = Color.RED;
                        break;
                    case "Зелений":
                        chosenColor = Color.GREEN;
                        break;
                    case "Синій":
                        chosenColor = Color.BLUE;
                        break;
                    default:
                        chosenColor = Color.BLACK;
                }
                g.setColor(chosenColor);
                g.fillRoundRect(200,200,300,300,width,height);
            }
        };
        add(canvas, BorderLayout.CENTER);
        message.setAlignment(Label.CENTER); // Center align the text
        Font font = new Font("Arial", Font.BOLD, 18); // Create a font (Arial, bold, size 18)
        message.setFont(font);
        add(message,BorderLayout.NORTH);

        Panel inputPanel = new Panel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(25);
        flowLayout.setVgap(10);
        inputPanel.setLayout(flowLayout);

        Label params = new Label("Параметри прямокутника:");
        Label color = new Label("Колір:");
        colorChoice = new Choice();
        colorChoice.add("Чорний");
        colorChoice.add("Червоний");
        colorChoice.add("Зелений");
        colorChoice.add("Синій");

        Label width = new Label("Ширина закруглення:");
        widthField = new TextField("10");
        Label height = new Label("Висота закруглення:");
        heightField = new TextField("10");
        widthField.setPreferredSize(new Dimension(100, 20));
        heightField.setPreferredSize(new Dimension(100, 20));

        inputPanel.add(params);
        inputPanel.add(color);
        inputPanel.add(colorChoice);
        inputPanel.add(width);
        inputPanel.add(widthField);
        inputPanel.add(height);
        inputPanel.add(heightField);

        Button changeButton = new Button("Змінити прямокутник");
        changeButton.addActionListener(e -> changeRectangle());
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
     * Changes the dimensions and color of the rounded rectangle based on user input.
     * Validates the input data in the text fields before updating the rectangle.
     */
     void changeRectangle() {
        String widthText = widthField.getText();
        String heightText = heightField.getText();
        if(widthText.equals("") && heightText.equals("")) {
            message.setForeground(Color.RED);
            message.setText("Потрібно заповнити всі поля");
            message.repaint();
        }
        else if (widthText.equals("")){
            message.setForeground(Color.RED);
            message.setText("Потрібно заповнити всі поля");
            message.repaint();
        }
        else if(heightText.equals("")){
            message.setForeground(Color.RED);
            message.setText("Потрібно заповнити всі поля");
            message.repaint();
        }
        else {
            try {
                width = Integer.parseInt(widthText);
                height = Integer.parseInt(heightText);
                canvas.repaint();
                message.setForeground(Color.black);
                message.setText("Виведення прямокутника");
                message.repaint();
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
     * Launches the ex15 window.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new ex15("Зміна прямокутника");
    }
}
