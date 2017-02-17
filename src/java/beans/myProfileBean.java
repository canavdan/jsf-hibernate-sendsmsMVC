package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import login.Util;
import login.Database;
import infoP.Userinfo;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Nemesis
 */
@ManagedBean(name="pB")
@SessionScoped
public class myProfileBean implements Serializable {

	private static final long serialVersionUID = 1L;

    public myProfileBean() {
    }
    public boolean update=true;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
    
    public String username,password,name,surname,number,adress,email;

    public String getUsername() {
        username = Util.getUserName();
        return username;
    }

    public void setUsername(String username) {
        username = Util.getUserName();
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String up(){
        setUpdate(false);
        return null;
    }
    Database dataGet=new Database();
     Userinfo ui=new Userinfo();
     public void data(){
         username = Util.getUserName();
              ui=dataGet.getUserID(username);
       setName(ui.getName());
       setSurname(ui.getSurname());
       setPassword(ui.getPassword());
       setNumber(ui.getNumber());
       setAdress(ui.getAdress());
       setEmail(ui.getCountry());
        //return dataGet.getUserID(username);      
     }
    public void reset(){
         setName("");
       setSurname("");
       setPassword("");
       setNumber("");
       setAdress("");
       setEmail("");
    }
    public String change(){
        username = Util.getUserName();
        Userinfo u=new Userinfo();
        u.setUserId(dataGet.getUserID2(username));
        u.setUsername(username);
        u.setPassword(password);
        u.setName(name);
        u.setSurname(surname);
        u.setNumber(number);
        u.setAdress(adress);
        u.setCountry(email);
        dataGet.updateUser(u);
        setUpdate(true);
        return "myprofile";
    }
    public void validateMail(FacesContext f, UIComponent c, Object obj) {
        String s5 = (String) obj;
        try {
            InternetAddress emailAddr = new InternetAddress(s5);
            emailAddr.validate();
        } catch (AddressException ex) {
           throw new ValidatorException(new FacesMessage("Please enter valid e-mail adress"));
        }
        if(dataGet.registerEmailControl(s5) && !email.equals(s5) )
             throw new ValidatorException(new FacesMessage("Email is not available"));
    }
}
