import java.io.*;
import java.util.Scanner;

class Gestione {
    private String nome;
    private String cognome;
    private int eta;
    private String username;
    private String password;
    private Conto conto;  // Oggetto della classe Conto per gestire il saldo

    static Scanner tastiera = new Scanner(System.in);

    public Gestione(String nome, String cognome, int eta, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.username = username;
        this.password = password;
        this.conto = new Conto(0.0, 0.0); // Inizializzo il conto con saldi vuoti
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

    public void menuGestioneFinanziaria() {
        int scelta;
        do {
            System.out.println("---------------------------MENU'---------------------------");
            System.out.println("1) Depositare soldi");
            System.out.println("2) Prelevare soldi");
            System.out.println("3) Possibilità di investire soldi");
            System.out.println("4) Andare avanti di n mesi");
            System.out.println("5) Visualizzare lo stato del proprio conto");
            System.out.println("6) Visualizzare stato proprio portafoglio");
            System.out.println("7) USCIRE\n");
            System.out.print("Inserisci l'opzione desiderata: ");
            scelta = conversioneInt(tastiera.nextLine());

            switch (scelta) {
                case 1:
                    System.out.print("Inserisci quanto vuoi depositare nel conto Bancario: ");
                    double deposito = conversioneDouble(tastiera.nextLine());
                    conto.deposita(deposito);  // Usa il metodo della classe Conto
                    break;
                case 2:
                    System.out.print("Inserisci quanto vuoi prelevare dal conto Bancario: ");
                    double prelievo = conversioneDouble(tastiera.nextLine());
                    conto.preleva(prelievo);  // Usa il metodo della classe Conto
                    break;
                case 3:
                    // Logica per investire soldi (da implementare)
                    break;
                case 4:
                    System.out.print("Inserisci quanti mesi vuoi avanzare: ");
                    int mesi = conversioneInt(tastiera.nextLine());
                    conto.aggiornaSaldo(mesi);  // Usa il metodo della classe Conto
                    break;
                case 5:
                    System.out.println("Saldo conto bancario: " + conto.getContoBancario());
                    break;
                case 6:
                    System.out.println("Saldo portafoglio: " + conto.getPortafoglio());
                    break;
            }
        } while (scelta != 7);
    }

    // Metodo per convertire la stringa in int
    public static int conversioneInt(String input) {
        int toInt = -1;
        boolean ok;
        do {
            ok = true;
            try {
                toInt = Integer.parseInt(input);
                if (toInt <= 0) {
                    System.out.println("Il numero deve essere maggiore di 0");
                    ok = false;
                    System.out.print("Reinserisci: ");
                    input = tastiera.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato non valido");
                System.out.print("Reinserisci: ");
                ok = false;
                input = tastiera.nextLine();
            }
        } while (!ok);

        return toInt;
    }

    // Metodo per convertire la stringa in double
    public static double conversioneDouble(String input) {
        double toDouble = -1;
        boolean ok;
        do {
            ok = true;
            try {
                toDouble = Double.parseDouble(input);
                if (toDouble <= 0) {
                    System.out.println("Il numero deve essere maggiore di 0");
                    ok = false;
                    System.out.print("Reinserisci: ");
                    input = tastiera.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Formato non valido");
                System.out.print("Reinserisci: ");
                ok = false;
                input = tastiera.nextLine();
            }
        } while (!ok);

        return toDouble;
    }
}