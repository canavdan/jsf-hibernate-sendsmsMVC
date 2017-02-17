package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import login.Util;
import login.Database;
import infoP.Messages;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nemesis
 */
@ManagedBean(name = "c")
@SessionScoped
public class contactsBean implements Serializable {

	private static final long serialVersionUID = 1L;

    public String message, to, date;

    public contactsBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String username;

    public String getUsername() {
       username=Util.getUserName();
        return username;
    }

    public void setUsername(String username) {  
        username=Util.getUserName();
        this.username = username;
    }  
     Database dataget = new Database();  
    public List<Messages> getAllMessages() {
        setUsername(Util.getUserName());
        username=Util.getUserName();
        System.out.println(username);
        System.out.println("asdasdasdasdasdasdasda");
// username=getCountryParam();
        int id = dataget.getUserID2(username);
        return dataget.getAllMessages(id);
    }
   /* public void userBean(){
        username=getCountryParam();
    }*/

    public String deleteMessage(int idM) {
        dataget.deleteMessages(idM);
        return "mhistory";
    }


}
