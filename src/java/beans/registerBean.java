package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@ManagedBean(name = "r")
@SessionScoped
public class registerBean implements Serializable {

	private static final long serialVersionUID = 1L;

    public registerBean() {
    }
    public String username, password, name, surname, adress, number, country;

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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void validateName(FacesContext f, UIComponent c, Object obj) {
        String s1 = (String) obj;
        if (s1.length() < 2) {
            throw new ValidatorException(new FacesMessage("Name cannot be empty."));
        }
        //return true;
    }
    Database d1=new Database();
    public void validateUserName(FacesContext f, UIComponent c, Object obj) {
        String s2 = (String) obj;
        if(d1.registerUsernameControl(s2))
            throw new ValidatorException(new FacesMessage("Username is not available"));
        /*if (s2.length() < 2) {
            throw new ValidatorException(new FacesMessage("Username cannot be empty."));
        }*/
    }

    /*public void validatePassword(FacesContext f, UIComponent c, Object obj) {
        String s3 = (String) obj;
        if (s3.length() < 6) {
            throw new ValidatorException(new FacesMessage("Password must be >=6 characters. "));
        }
    }*/

    public void validateNumber(FacesContext f, UIComponent c, Object obj) {
        String s4 = (String) obj;
        if (s4.length() < 2) {
            throw new ValidatorException(new FacesMessage("Please enter valid number."));
        }
    }

    public void validateMail(FacesContext f, UIComponent c, Object obj) {
        String s5 = (String) obj;
        try {
            InternetAddress emailAddr = new InternetAddress(s5);
            emailAddr.validate();
        } catch (AddressException ex) {
           throw new ValidatorException(new FacesMessage("Please enter valid e-mail adress"));
        }
        if(d1.registerEmailControl(s5))
             throw new ValidatorException(new FacesMessage("Email is not available"));
    }

    public void clear() {
        setName("");
        setAdress(null);
        setPassword(null);
        setUsername(null);
        setSurname(null);
        setNumber(null);
        setCountry(null);

    }
    public String addUserDatabase(){
        Userinfo u=new Userinfo();
        
        u.setName(name);
        u.setSurname(surname);
        u.setUsername(username);
        u.setAdress(adress);
        u.setNumber(number);
        u.setPassword(password);
        u.setCountry(country);
        d1.addUserDatabase(u);
        return "added";
    }
    
    public String password2;
    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
