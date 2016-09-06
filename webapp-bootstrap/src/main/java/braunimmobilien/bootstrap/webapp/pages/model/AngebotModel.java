package braunimmobilien.bootstrap.webapp.pages.model;

import braunimmobilien.model.Angebot;
import braunimmobilien.service.AngebotManager;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 * A detachable model that can load an User object from persistent store.
 * 
 * @author ivaynberg
 */
public class AngebotModel extends LoadableDetachableModel {
    private AngebotManager angebotManager;
    private String id;
    
    public AngebotModel(AngebotManager angebotManager) {
        this.angebotManager = angebotManager;
    }

    /**
     * @param user object this model will represent
     * @param userManager the userManager 
     */
    public AngebotModel(Angebot angebot, AngebotManager angebotManager) {
        super(angebot);
        this.id = angebot.getId();
        this.angebotManager = angebotManager;
    }

    protected Object load() {
        return angebotManager.getAngebot(String.valueOf(id));
    }

}
