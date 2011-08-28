package to.getme.view;

import org.jboss.seam.faces.event.qualifier.After;
import org.jboss.seam.faces.event.qualifier.RestoreView;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */

@Named
@SessionScoped
public class ViewManager implements Serializable {

    @Inject
    Logger log;

    private List<FacesMessage> messages = new ArrayList<FacesMessage>();

    public void redirect(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException e) {
            log.log(Level.SEVERE, "Failed redirecting to " + url, e);
        }
    }

    public void addMessage(FacesMessage message) {
        messages.add(message);
    }

    private void flushMessages(@Observes @After @RestoreView PhaseEvent phaseEvent) {
        for (FacesMessage message : messages) {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        messages.clear();
    }
}
