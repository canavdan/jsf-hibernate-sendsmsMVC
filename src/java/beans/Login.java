package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import login.Util;
import login.LoginDAO;
import static com.mchange.v2.c3p0.impl.C3P0Defaults.user;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.apache.catalina.manager.util.SessionUtils;

/**
 *
 * @author Nemesis
 */
@ManagedBean(name = "l")
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;

    public Login() {
    }
    public String username="";
     public String password;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String validateUsernamePassword() {
		boolean valid = LoginDAO.validate(username,password);
		if (valid) {
			HttpSession session = Util.getSession();
			session.setAttribute("username", username);
			return "index";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect Username and Passowrd",
							"Please enter correct username and Password"));
			return "login2.xhtml";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = Util.getSession();
		session.invalidate();
		return "login";
	}
        public void logout2() {
		HttpSession session = Util.getSession();
		session.invalidate();
		
	}
    
}
