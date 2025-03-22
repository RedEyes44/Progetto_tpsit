

public class Utente {
    private String nome;
    private String cognome;
    private String eta;
    private String username;
    private String password;
    private Conto conto;

    // Costruttore
    public Utente(String nome, String cognome, String eta, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.username = username;
        this.password = password;
        this.conto = new Conto(nome, cognome, Integer.parseInt(eta), username, password); // Passa i parametri
    }

    // Getter e Setter
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEta() {
        return eta;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Conto getConto() {
        return conto;
    }


    // Metodo per convertire l'utente in formato CSV
    public String toCSV() {
        return nome + "," + cognome + "," + eta + "," + username + "," + password + ","
                + conto.getBanca() + "," + conto.getPortafoglio() + "," + conto.getMese() + "," + conto.getAnno();
    }

    // Metodo per creare un oggetto Utente da una riga CSV
    public static Utente fromCSV(String line) {
        String[] parts = line.split(","); // Carattere separatore
        if (parts.length < 9)
            return null; // Restituisce null se i dati sono incompleti

        String nome = parts[0];
        String cognome = parts[1];
        String eta = parts[2];
        String username = parts[3];
        String password = parts[4];
        double banca = Double.parseDouble(parts[5]);
        double portafoglio = Double.parseDouble(parts[6]);
        int mese = Integer.parseInt(parts[7]);
        int anno = Integer.parseInt(parts[8]);

        Utente utente = new Utente(nome, cognome, eta, username, password);
        utente.getConto().setBanca(banca);
        utente.getConto().setPortafoglio(portafoglio);
        utente.getConto().setMese(mese);
        utente.getConto().setAnno(anno);

        return utente; // Restituisce l'oggetto utente
    }
}