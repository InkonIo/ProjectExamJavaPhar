import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CitramonWindow extends JFrame {
    private String userEmail;
    private ArrayList<String> selectedMedicines;

    public CitramonWindow(String userEmail, ArrayList<String> selectedMedicines) {
        this.userEmail = userEmail;
        this.selectedMedicines = selectedMedicines;

        setTitle("Цитрамон");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Получаем актуальную цену из базы данных
        double price = MedicineDatabase.getPrice("Цитрамон");

        // Панель с информацией о Цитрамоне
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel priceLabel = new JLabel("Цитрамон - " + price + " тг.");
        panel.add(priceLabel);

        // Кнопка добавления в корзину
        JButton addToBasketButton = new JButton("Добавить в корзину");
        addToBasketButton.addActionListener(e -> {
            selectedMedicines.add("Цитрамон");
            JOptionPane.showMessageDialog(this, "Цитрамон добавлен в корзину");
        });
        panel.add(addToBasketButton);

        // Кнопка "Назад"
        JButton backButton = new JButton("Назад");
        backButton.addActionListener(e -> {
            dispose();
            new SimpleWindow(userEmail, selectedMedicines);
        });
        panel.add(backButton);

        // Добавляем панель в окно
        getContentPane().add(panel);

        setVisible(true);
    }
}
