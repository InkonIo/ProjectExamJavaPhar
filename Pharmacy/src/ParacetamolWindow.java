import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ParacetamolWindow extends JFrame {
    private String userEmail;
    private ArrayList<String> selectedMedicines;
    private int count = 0;
    private JLabel countLabel;

    public ParacetamolWindow(String userEmail, ArrayList<String> selectedMedicines) {
        this.userEmail = userEmail;
        this.selectedMedicines = selectedMedicines;

        setTitle("Парацетамол");
        setBounds(100, 100, 500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Основной контейнер
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(200, 230, 229));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Загружаем и уменьшаем изображение
        ImageIcon icon = new ImageIcon("/Users/inkonio/Desktop/Utilities/Prokec/exam/src/images/paracetomo.png");
        Image scaledImage = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

        // Панель с текстом
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        textPanel.setBackground(new Color(200, 230, 229));

        JLabel nameLabel = new JLabel("Парацетамол - 500 тг.");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel descLabel = new JLabel("<html>Снижает температуру и боль.<br>Противовоспалительное действие.</html>");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        textPanel.add(nameLabel);
        textPanel.add(descLabel);

        // Панель для управления количеством и кнопок
        JPanel controlPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        controlPanel.setBackground(new Color(200, 230, 229));

        // Панель для счетчика
        JPanel countPanel = new JPanel();
        countPanel.setBackground(new Color(200, 230, 229));

        JButton minusButton = createStyledButton("-", new Color(167, 0, 0), Color.WHITE);
        minusButton.setPreferredSize(new Dimension(50, 40));
        minusButton.addActionListener(e -> decreaseCount());

        countLabel = new JLabel("0", SwingConstants.CENTER);
        countLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton plusButton = createStyledButton("+", new Color(0, 167, 89), Color.WHITE);
        plusButton.setPreferredSize(new Dimension(50, 40));
        plusButton.addActionListener(e -> increaseCount());

        countPanel.add(minusButton);
        countPanel.add(Box.createHorizontalStrut(10));
        countPanel.add(countLabel);
        countPanel.add(Box.createHorizontalStrut(10));
        countPanel.add(plusButton);

        // Панель для кнопок
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(200, 230, 229));

        JButton addToBasketButton = createStyledButton("Добавить", new Color(0, 167, 89), Color.WHITE);
        addToBasketButton.setPreferredSize(new Dimension(120, 40));
        addToBasketButton.addActionListener(e -> addToBasket());

        JButton backButton = createStyledButton("Назад", new Color(167, 0, 0), Color.WHITE);
        backButton.setPreferredSize(new Dimension(120, 40));
        backButton.addActionListener(e -> {
            dispose();
            new SimpleWindow(userEmail, selectedMedicines);
        });

        buttonPanel.add(addToBasketButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(backButton);

        controlPanel.add(countPanel);
        controlPanel.add(buttonPanel);

        // Добавляем компоненты в основной контейнер
        panel.add(imageLabel, BorderLayout.WEST);
        panel.add(textPanel, BorderLayout.CENTER);
        panel.add(controlPanel, BorderLayout.EAST);

        getContentPane().add(panel);
        setVisible(true);
    }

    // Метод для добавления в корзину с учетом количества
    private void addToBasket() {
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                selectedMedicines.add("Парацетамол");
            }
        }
    }

    // Уменьшаем количество
    private void decreaseCount() {
        if (count > 0) {
            count--;
            countLabel.setText(String.valueOf(count));
        }
    }

    // Увеличиваем количество
    private void increaseCount() {
        count++;
        countLabel.setText(String.valueOf(count));
    }

    // Метод для стилизованных кнопок
    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return button;
    }
}
