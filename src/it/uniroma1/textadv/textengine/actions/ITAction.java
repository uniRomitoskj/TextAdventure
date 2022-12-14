package it.uniroma1.textadv.textengine.actions;

import it.uniroma1.textadv.textengine.exceptions.ActionNotKnownException;
import it.uniroma1.textadv.textengine.languages.EnglishAndItalian;
import it.uniroma1.textadv.textengine.languages.Language;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Enumerazione contenente delle azioni in italiano.
 */
public enum ITAction implements Action {

    AIUTO(Action::help, "\t\t\tVisualizza la lista dei comandi o un comando specifico."),

    ESCI(Action::quit, "\t\t\tEsci dal gioco."),

    INVENTARIO(Action::inventory, "\t\tVisualizza il tuo inventario."),

    GUARDA(Action::look, "\t\t\tDescrive l'oggetto richiesto, altrimenti, se non richiedi nulla, descrive la stanza in cui ti " +
            "trovi."),

    VAI(Action::go, "\t\t\t\tUsalo per spostarti nelle varie stanze indicando il nome del passaggio per cui vuoi passare o la direzione (nord, sud, est, ovest)"),

    ENTRA(Action::enter, "\t\t\tUsalo per entrare in un determinato passaggio."),

    PRENDI(Action::take, "\t\t\tPrendi qualcosa dalla stanza in cui ti trovi, da dentro contenitore, o da un personaggio. Troverai ciò che hai preso nell'inventario."),

    LASCIA(Action::drop, "\t\t\tLascia un oggetto che hai nell'inventario nella stanza in cui ti trovi."),

    DAI(Action::give, "\t\t\t\tUsalo per dare un oggetto ad un altro personaggio."),

    USA(Action::use, "\t\t\t\tServe ad usare un oggetto che porti nell'inventario o che si trova nella stanza."),

    APRI(Action::open, "\t\t\tApri un oggetto o un passaggio chiuso. Se è chiuso a chiave o bloccato dovrai specificare con cosa aprirlo."),

    ROMPI(Action::breakItem, "\t\t\tUsalo per rompere un oggetto nella stanza dove ti trovi (non tutti gli oggetti possono essere rotti)."),

    PARLA(Action::speak, "\t\t\tUsalo per dialogare con altri personaggi."),

    ACCAREZZA(Action::pet, "\t\tUsalo per accarezzare un animale.");

    /**
     * Descrizione dell'azione.
     */
    private final String DESCRIPTION;

    /**
     * Metodo che viene eseguito quando viene eseguita l'azione.
     */
    private final BiFunction<List<String>, Language, String> method;

    ITAction(BiFunction<List<String>, Language, String> method, String description) {
        this.method = method;
        DESCRIPTION = description;
    }

    @Override
    public String getDescription() {
        return this + DESCRIPTION;
    }

    @Override
    public String execute(List<String> args) {
        return method.apply(args, EnglishAndItalian.IT);
    }

    /**
     * Restituisce l'{@link ActionFactory} che permette di ottenere le azioni definite in questa enumerazione.
     *
     * @return la factory per le azioni italiane
     */
    public static ActionFactory getFactory() {
        return new ActionFactory() {
            @Override
            public Action getAction(String name) throws ActionNotKnownException {
                try {
                    return ITAction.valueOf(name.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new ActionNotKnownException();
                }
            }

            @Override
            public Action[] values() {
                return ITAction.values();
            }
        };
    }
}
