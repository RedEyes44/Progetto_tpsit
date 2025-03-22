import java.util.ArrayList;
import java.util.List;

public class StoricoTransazioni {

    // Lista che tiene traccia delle transazioni
    private List<String> transazioni;

    // Costruttore
    public StoricoTransazioni() {
        this.transazioni = new ArrayList<>();
    }

    // Metodo per aggiungere una transazione
    public void aggiungiTransazione(String tipoTransazione, double importo) {
        String transazione = tipoTransazione + ": " + importo + "€";
        transazioni.add(transazione);
    }

    // Metodo per visualizzare tutte le transazioni
    public void visualizzaStorico() {
        if (transazioni.isEmpty()) {
            System.out.println("Nessuna transazione effettuata.");
        } else {
            System.out.println("Storico delle transazioni:");
            for (String transazione : transazioni) {
                System.out.println(transazione);
            }
        }
    }

    // Metodo per ottenere l'elenco delle transazioni
    public List<String> getTransazioni() {
        return transazioni;
    }
}