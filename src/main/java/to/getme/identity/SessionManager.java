package to.getme.identity;

import to.getme.view.ViewManager;
import org.jboss.seam.faces.event.qualifier.*;
import org.jboss.seam.security.Identity;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.event.PhaseEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */

@Named
@SessionScoped
public class SessionManager implements Serializable {

    @Inject
    ViewManager viewManager;

    @Inject
    Identity identity;

    public void login() {
        if (identity.isLoggedIn()) {
            return;
        }
        identity.login();
        viewManager.redirect("home.xhtml");
    }

    public void logout() {
        identity.logout();
        viewManager.redirect("login.xhtml");
    }

    public void after(@Observes @After @RestoreView PhaseEvent phaseEvent, NavigationHandler navHandler) throws IOException {
        HttpServletRequest request = (HttpServletRequest) phaseEvent.getFacesContext().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) phaseEvent.getFacesContext().getExternalContext().getResponse();
        
        Boolean isLoginPage = request.getServletPath().matches("^/login|^/login\\.xhtml");

        //Session has expired
        if (request.getRequestedSessionId() != null && request.getSession().isNew()) {
            identity.logout();
            viewManager.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Your session has expired", "Please login to proceed"));
            response.sendRedirect("login.xhtml");
            return;
        }

        if (!isLoginPage && !identity.isLoggedIn()) {
            //If not logged in redirect to login page
            response.sendRedirect("login.xhtml");
            return;
        } else if (isLoginPage && identity.isLoggedIn()) {
            //If logged in and going to login page send to home
            response.sendRedirect("home.xhtml");
            return;
        }
    }
}
