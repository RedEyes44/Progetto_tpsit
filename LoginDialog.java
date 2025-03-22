import javax.swing.*;

public class LoginDialog {
    private JFrame parentFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginDialog(JFrame parentFrame) {
        this.parentFrame = parentFrame;
    }

    public Utente showLoginDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);

        int option = JOptionPane.showConfirmDialog(parentFrame, panel, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            return GestioneUtente.login(username, password); // Presumendo che GestioneUtente sia la classe che gestisce l'accesso
        }
        return null; // Se l'utente annulla, ritorna null
    }
}