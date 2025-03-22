import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LSRTBankGUI {
    private JFrame frame;
    private JLabel balanceLabel;
    private Utente currentUser;
    private StoricoTransazioni storicoTransazioni;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LSRTBankGUI().createAndShowGUI());
    }

    private void createAndShowGUI() {
        // Mostra la finestra di login prima di tutto
        LoginDialog loginDialog = new LoginDialog(frame);
        currentUser = loginDialog.showLoginDialog();
    
        if (currentUser == null) {
            JOptionPane.showMessageDialog(frame, "Login failed.");
            System.exit(0); // Se il login fallisce, esci dall'applicazione
        } else {
            JOptionPane.showMessageDialog(frame, "Login successful!");
            storicoTransazioni = new StoricoTransazioni();
    
            frame = new JFrame("LSRT Bank");
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
    
            balanceLabel = new JLabel("Account Balance: $0.00", JLabel.RIGHT);
            balanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            headerPanel.add(balanceLabel, BorderLayout.EAST);
    
            frame.add(headerPanel, BorderLayout.NORTH);
    
            // Main panel
            JPanel mainPanel = new JPanel();
            mainPanel.setBackground(Color.LIGHT_GRAY);
            mainPanel.setLayout(new BorderLayout());
    
            JLabel userLabel = new JLabel("UTENTE: " + currentUser.getNome(), JLabel.CENTER);
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
    
            depositButton.addActionListener(e -> handleDeposit());
            withdrawButton.addActionListener(e -> handleWithdraw());
            transactionButton.addActionListener(e -> showTransactionLog());
    
            buttonPanel.add(depositButton);
            buttonPanel.add(withdrawButton);
            buttonPanel.add(transactionButton);
    
            frame.add(buttonPanel, BorderLayout.SOUTH);
    
            frame.setVisible(true);
    
            updateBalance(); // Dopo il login, aggiorna il saldo
        }
    }

    // Handle deposit
    private void handleDeposit() {
        String amountStr = JOptionPane.showInputDialog(frame, "Enter deposit amount:");
        try {
            double amount = Double.parseDouble(amountStr);
            if (currentUser != null && amount > 0) {
                currentUser.getConto().deposita(amount);
                storicoTransazioni.aggiungiTransazione("Deposit", amount);
                updateBalance();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid amount.");
        }
    }

    // Handle withdraw
    private void handleWithdraw() {
        String amountStr = JOptionPane.showInputDialog(frame, "Enter withdrawal amount:");
        try {
            double amount = Double.parseDouble(amountStr);
            if (currentUser != null && amount > 0) {
                currentUser.getConto().preleva(amount);
                storicoTransazioni.aggiungiTransazione("Withdraw", amount);
                updateBalance();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid amount.");
        }
    }

    // Show transaction log
    private void showTransactionLog() {
        List<String> transactions = storicoTransazioni.getTransazioni();
        if (transactions.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No transactions available.");
        } else {
            StringBuilder log = new StringBuilder();
            for (String transaction : transactions) {
                log.append(transaction).append("\n");
            }
            JOptionPane.showMessageDialog(frame, log.toString());
        }
    }

    // Update the balance label on the GUI
    private void updateBalance() {
        if (currentUser != null) {
            double balance = currentUser.getConto().getBanca();
            balanceLabel.setText("Account Balance: $" + balance);
        }
    }

    // Method to login a user
    public void login(String username, String password) {
        currentUser = GestioneUtente.login(username, password);
        if (currentUser != null) {
            JOptionPane.showMessageDialog(frame, "Login successful!");
            updateBalance();
        } else {
            JOptionPane.showMessageDialog(frame, "Login failed.");
        }
    }
}