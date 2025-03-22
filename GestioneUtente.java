import java.io.*;



public class GestioneUtente {

    // Metodo per registrare un utente
    public boolean registra(Utente utente) {
        File userFile = new File(utente.getUsername() + ".txt");
        if (userFile.exists()) {
            System.out.println("Utente già registrato.");
            return false;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFile))) {
            writer.write(utente.getNome() + "\n");
            writer.write(utente.getCognome() + "\n");
            writer.write(utente.getEta() + "\n");
            writer.write(utente.getUsername() + "\n");
            writer.write(utente.getPassword() + "\n");
            writer.write(utente.getConto().getBanca() + "\n");
            writer.write(utente.getConto().getPortafoglio() + "\n");
            writer.write(utente.getConto().getMese() + "\n");
            writer.write(utente.getConto().getAnno() + "\n");
            return true;
        } catch (IOException e) {
            System.out.println("Errore nella creazione del file utente.");
        }
        return false;
    }

    // Metodo di login
    public static Utente login(String username, String password) {
        File userFile = new File(username + ".txt");
        if (!userFile.exists()) {
            System.out.println("\nUtente non trovato.");
            return null;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String nome = reader.readLine();
            String cognome = reader.readLine();
            String eta = reader.readLine();
            String user = reader.readLine();
            String storedPassword = reader.readLine();

            if (storedPassword != null && storedPassword.equals(password)) {
                // Creazione dell'utente e del conto associato
                double banca = Double.parseDouble(reader.readLine());
                double portafoglio = Double.parseDouble(reader.readLine());
                int mese = Integer.parseInt(reader.readLine());
                int anno = Integer.parseInt(reader.readLine());

                Utente utente = new Utente(nome, cognome, eta, user, password);
                utente.getConto().setBanca(banca);
                utente.getConto().setPortafoglio(portafoglio);
                utente.getConto().setMese(mese);
                utente.getConto().setAnno(anno);
                return utente; // Utente e conto creati
            } else {
                System.out.println("Password errata.");
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file utente.");
        }
        return null; // Restituisce null se il login non ha successo
    }
}