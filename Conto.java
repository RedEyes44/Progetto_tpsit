import java.io.*;
import java.util.Scanner;

class Conto {
    private String nome;
    private String cognome;
    private int eta;
    private String username;
    private String password;
    private double contoBancario;
    private double portafoglio;
    private double banca;
    private int mese; 
    private int anno;

    static Scanner tastiera = new Scanner(System.in);

    public Conto(String nome, String cognome, int eta, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.username = username;
        this.password = password;
        this.contoBancario = 0.0;
        this.portafoglio = 0.0;
    }

    // Metodo di registrazione
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

    // Metodo di login
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

    // Metodo per gestire le operazioni finanziarie
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
                    deposita(deposito);
                    break;
                case 2:
                    System.out.print("Inserisci quanto vuoi prelevare dal conto Bancario: ");
                    double prelievo = conversioneDouble(tastiera.nextLine());
                    preleva(prelievo);
                    break;
                case 3:
                    System.out.print("Inserisci l'importo da investire: ");
                    double soldiDaInvestire = conversioneDouble(tastiera.nextLine());
                    System.out.print("Inserisci il livello di rischio (1-100): ");
                    int rischio = conversioneInt(tastiera.nextLine());
                    investi(rischio, soldiDaInvestire);
                    break;
                case 4:
                    System.out.print("Inserisci quanti mesi vuoi avanzare: ");
                    int mesi = conversioneInt(tastiera.nextLine());
                    aggiornaSaldo(mesi);
                    break;
                case 5:
                    System.out.println("Saldo conto bancario: " + contoBancario);
                    break;
                case 6:
                    System.out.println("Saldo portafoglio: " + portafoglio);
                    break;
            }
        } while (scelta != 7);
    }

    // Metodo per depositare soldi nel conto
    public void deposita(double saldo) {
        if (saldo <= portafoglio) {
            portafoglio -= saldo;
            contoBancario += saldo;
            System.out.println("Deposito effettuato.");
        } else {
            System.out.println("Saldo del portafoglio non sufficiente.");
        }
    }

    // Metodo per prelevare soldi dal conto
    public void preleva(double saldo) {
        if (saldo <= contoBancario) {
            contoBancario -= saldo;
            portafoglio += saldo;
            System.out.println("Prelievo effettuato.");
        } else {
            System.out.println("Saldo del conto bancario non sufficiente.");
        }
    }

    // Metodo per aggiornare il saldo del conto in base ai mesi
    public void aggiornaSaldo(int mesi) {
        contoBancario += mesi * 100;
        System.out.println("Saldo aggiornato.");
    }

    // Metodo per investire soldi con un determinato livello di rischio
    public void investi(int grandezzaRischio, double soldiDaInvestire) {
        int rischio = (int) (Math.random() * grandezzaRischio);
        double guadagno;
        if (rischio <= 50) {
            guadagno = soldiDaInvestire * (grandezzaRischio / 20.0);
        } else {
            guadagno = (20.0 / grandezzaRischio) * soldiDaInvestire;
        }
        contoBancario += guadagno;
        System.out.println("Investimento effettuato. Guadagno: " + guadagno);
    }

    // Metodo per la conversione della stringa in int
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

    // Metodo per la conversione della stringa in double
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

    // Setter per banca
    public void setBanca(double banca) {
        this.banca = banca;
    }

    // Setter per portafoglio
    public void setPortafoglio(double portafoglio) {
        this.portafoglio = portafoglio;
    }

    // Setter per mese
    public void setMese(int mese) {
        this.mese = mese;
    }

    // Setter per anno
    public void setAnno(int anno) {
        this.anno = anno;
    }

    // Getter per banca
    public double getBanca() {
        return banca;
    }

    // Getter per portafoglio
    public double getPortafoglio() {
        return portafoglio;
    }

    // Getter per mese
    public int getMese() {
        return mese;
    }

    // Getter per anno
    public int getAnno() {
        return anno;
    }

}
