/*
Зміна написи у графічному вікні. У центрі верхньої панелі (Canvas)
"Виведення напису" у графічному контексті виводиться довільний напис.
У нижній панелі (Panel) "Параметри напису" задаються наступні компоненти:
напис (Label) "Текст:", текстове поле (TextField), напис (Label) "Колір:",
список, що розкривається (Choice) зі значеннями: "Чорний" ( колір за
замовчуванням), "Червоний", "Зелений" і "Синій", напис (JLabel) "Розмір:",
список, що розкривається (Choice) зі значеннями "10pt", "12pt"
(розмір за замовчуванням), "14pt" і "16pt", напис (Label) "Координата X:",
текстове поле (TextField), напис (Label) "Координата Y:",
текстове поле (TextField), а також кнопка (Button) "Вивести рядок".
Спочатку в текстових полях "Координата X:" та "Координата Y:"
встановлюються координати початку базової лінії напису
(текстові поля задані як нередаговані). Під час введення даних напис
змінює текст, колір та/або розмір. При клацанні мишею в області верхньої панелі
 текстових полях виводяться координати точки клацання,
 і напис перемальовується в цій точці.
 */
import java.awt.*;
import java.awt.event.*;
/**
 * Represents a window to display text and modify its properties.
 * The graphical context renders the text string based on user-defined parameters.
 */
public class ex21 extends Frame  {
    private Label textLabel, colorLabel, sizeLabel, xCoordLabel, yCoordLabel;
    private TextField textField, xCoordField, yCoordField;
    private Choice colorChoice, sizeChoice;
    private Button drawButton;
    private String text ;
    private Color chosenColor ;
    private Canvas canvas;
    private int fontSize ;
    {
        text = "Текст";
        chosenColor = Color.BLACK;
        fontSize = 12;
    }
    /**
     * Constructs an ex21 window to display and modify text properties.
     * Initializes and sets up the graphical components for rendering text.
     */
    public ex21() {
        setSize(1280, 720);
        setTitle("Виведення тексту");

        canvas = new Canvas() {
            public void paint(Graphics g) {
                g.setColor(chosenColor);
                g.setFont(new Font("Arial", Font.PLAIN, fontSize));
                g.drawString(text, Integer.parseInt(xCoordField.getText()), Integer.parseInt(yCoordField.getText()));
            }
        };
        canvas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                xCoordField.setText(Integer.toString(e.getX()));
                yCoordField.setText(Integer.toString(e.getY()));
                canvas.repaint();
            }
        });
        add(canvas,BorderLayout.CENTER);

        Panel inputPanel = new Panel();
        Label l1 = new Label( "Виведення напису");
        inputPanel.setLayout(new FlowLayout());
        Label params = new Label("Параметри напису");
        inputPanel.add(params);
        textLabel = new Label("Текст:");
        inputPanel.add(textLabel);

        textField = new TextField(text, 20);
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                text = textField.getText();
                canvas.repaint();

            }
        });
        inputPanel.add(textField);

        colorLabel = new Label("Колір:");
        inputPanel.add(colorLabel);

        colorChoice = new Choice();
        colorChoice.add("Чорний");
        colorChoice.add("Червоний");
        colorChoice.add("Зелений");
        colorChoice.add("Синій");

        colorChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String selectedColor = colorChoice.getSelectedItem();
                switch (selectedColor) {
                    case "Чорний":
                        chosenColor = Color.BLACK;
                        canvas.repaint();
                        break;
                    case "Червоний":
                        chosenColor = Color.RED;
                        canvas.repaint();
                        break;
                    case "Зелений":
                        chosenColor = Color.GREEN;
                        canvas.repaint();
                        break;
                    case "Синій":
                        chosenColor = Color.BLUE;
                        canvas.repaint();
                        break;
                    default:
                        chosenColor = Color.BLACK;
                        canvas.repaint();
                }
            }
        });
        inputPanel.add(colorChoice);

        sizeLabel = new Label("Розмір:");
        inputPanel.add(sizeLabel);

        sizeChoice = new Choice();
        sizeChoice.add("10pt");
        sizeChoice.add("12pt");
        sizeChoice.add("14pt");
        sizeChoice.add("16pt");
        sizeChoice.select("12pt");
        sizeChoice.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String selectedSize = sizeChoice.getSelectedItem();
                fontSize = Integer.parseInt(selectedSize.substring(0, selectedSize.length() - 2));
                canvas.repaint();
            }
        });
        inputPanel.add(sizeChoice);

        xCoordLabel = new Label("Координата X:");
        inputPanel.add(xCoordLabel);

        xCoordField = new TextField("100", 5);
        xCoordField.setEditable(false);
        inputPanel.add(xCoordField);

        yCoordLabel = new Label("Координата Y:");

        inputPanel.add(yCoordLabel);

        yCoordField = new TextField("100", 5);
        yCoordField.setEditable(false);
        inputPanel.add(yCoordField);

        drawButton = new Button("Вивести рядок");
        drawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.repaint();
            }
        });
        inputPanel.add(drawButton);

        add(inputPanel, BorderLayout.SOUTH);
        l1.setAlignment(Label.CENTER);
        Font font = new Font("Arial", Font.BOLD, 18); // Create a font (Arial, bold, size 18)
        l1.setFont(font);
        add(l1, BorderLayout.NORTH);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    /**
     * Main method to launch the ex21 window.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {

        new ex21();
    }
}

