import java.io.*;

class Utente {
    private String nome;
    private String cognome;
    private int eta;
    private String username;
    private String password;

    public Utente(String nome, String cognome, int eta, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.username = username;
        this.password = password;
    }

    public boolean registra() {
        File userFile = new File(username + ".txt");
        if (userFile.exists()) {
            System.out.println("Utente già registrato.");
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
            writer.write(nome + "\n");
            writer.write(cognome + "\n");
            writer.write(eta + "\n");
            writer.write(username + "\n");
            writer.write(password + "\n");
            return true;
        } catch (IOException e) {
            System.out.println("Errore nella creazione del file utente.");
        }
        return false;
    }

    public static boolean login(String username, String password) {
        File userFile = new File(username + ".txt");
        if (!userFile.exists()) {
            System.out.println("\nUtente non trovato.");
            return false;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            reader.readLine(); // Nome
            reader.readLine(); // Cognome
            reader.readLine(); // Età
            reader.readLine(); // Username
            String storedPassword = reader.readLine();
            return storedPassword != null && storedPassword.equals(password);
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file utente.");
        }
        return false;
    }
}
