import javax.swing.*;
import java.awt.*;

public class LSRTBankGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LSRTBankGUI().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Maze Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setLayout(new BorderLayout());
        JLabel bankLabel = new JLabel("\t LSRT BANK OF LOS SANTOS", JLabel.LEFT);
        bankLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(bankLabel, BorderLayout.WEST);

        JLabel balanceLabel = new JLabel("Account Balance: $39,884,862.00", JLabel.RIGHT);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        headerPanel.add(balanceLabel, BorderLayout.EAST);

        frame.add(headerPanel, BorderLayout.NORTH);

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainPanel.setLayout(new BorderLayout());
        
        JLabel userLabel = new JLabel("UTENTE", JLabel.CENTER);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userLabel.setOpaque(true);
        userLabel.setBackground(Color.RED);
        userLabel.setForeground(Color.WHITE);
        mainPanel.add(userLabel, BorderLayout.NORTH);

        JLabel serviceLabel = new JLabel("Choose a service.", JLabel.CENTER);
        serviceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(serviceLabel, BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        
        JButton depositButton = createStyledButton("Deposit");
        JButton withdrawButton = createStyledButton("Withdraw");
        JButton transactionButton = createStyledButton("Transaction Log");
        
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(transactionButton);
        
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }
}
