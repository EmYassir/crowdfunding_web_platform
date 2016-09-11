package fr.ensimag.ack.eggsale.web.action.user;

import fr.ensimag.ack.eggsale.db.entity.User;
import fr.ensimag.ack.eggsale.db.facade.UserFacade;
import fr.ensimag.ack.eggsale.web.action.BaseActionBean;
import fr.ensimag.ack.eggsale.web.session.SessionCart;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.DontValidate;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationErrors;

public class PersonaLoginActionBean extends BaseActionBean {
    
    private static String VIEW = VIEW_BASE_PATH + "user/login.jsp";

    @Validate(required=true)
    private String assertion;

    public String getAssertion() {
	return assertion;
    }

    public void setAssertion(String assertion) {
	this.assertion = assertion;
    }

    @EJB
    UserFacade userFacade;

    @DefaultHandler
    @DontValidate
    public Resolution form() {
        return new ForwardResolution(VIEW);
    }
    
    public Resolution login() {
	RedirectResolution res = null;
	
	OutputStreamWriter writer = null;
	BufferedReader reader = null;
	try {
	    //création de la connexion
	    HttpURLConnection connection = (HttpURLConnection)new URL("https://verifier.login.persona.org/verify").openConnection();
	    connection.setDoOutput(true);
	    connection.setRequestMethod("POST");
	    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

	    String urlParameters = "assertion=" + URLEncoder.encode(assertion, "UTF-8")
		+ "&audience=" + URLEncoder.encode("localhost", "UTF-8");

	    //envoi de la requête
	    writer = new OutputStreamWriter(connection.getOutputStream());
	    writer.write(urlParameters);
	    writer.flush();

	    //lecture de la réponse
	    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	    String ligne = reader.readLine();
	    if (ligne != null) {
		//Parsage du JSON sans librairie. La chaine recu est de la forme
		//{"status":"okay","email":"toto","audience":"localhost","expires":1355665018623,"issuer":"login.persona.org"}
		if (ligne.split("\"")[3].equals("okay")) {
		    String email = ligne.split("\"")[7];
		    User user = userFacade.findByEmail(email);
		    if (user != null) {
			getContext().setCurrentUserId(user.getId());
			res = new RedirectResolution(ShowActionBean.class);
			res.addParameter("userId", user.getId());
		    } else {
			getContext().getMessages().add(new LocalizableMessage("user.unknown"));
			res = new RedirectResolution(CreateActionBean.class);
		    }
		} else {
		    getContext().getValidationErrors().add(
			    "You are not allowed to connect",
			    new LocalizableError("authenticationFailure"));			
		    res = new RedirectResolution(PersonaLoginActionBean.class);
		}
	    }

	} catch (Exception e) {
	    Logger.getLogger(PersonaLoginActionBean.class.getName()).log(Level.SEVERE, null, e);
	} finally {
	    try {
		writer.close();
		reader.close();
	    } catch (Exception e) { }
	}
        
        return res;
    }
}
