package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import login.Util;
import login.Database;
import infoP.Contacts;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Nemesis
 */
@ManagedBean(name = "a")
@SessionScoped
public class addContactBean implements Serializable {

	private static final long serialVersionUID = 1L;

    public addContactBean() {
    }
    public String name,surname,country,number;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public void validateName(FacesContext f, UIComponent c, Object obj) {
        String s1 = (String) obj;
        if (s1.length() < 2) {
            throw new ValidatorException(new FacesMessage("Name cannot be empty."));
        }
        //return true;
    }
    public void validateNumber(FacesContext f, UIComponent c, Object obj) {
        String s4 = (String) obj;
        if (s4.length() < 2) {
            throw new ValidatorException(new FacesMessage("Please enter valid number."));
        }
    }
    public void clear() {
        setName("");
        setSurname(null);
        setNumber(null);
        setCountry(null);
    }
    Database dataget=new Database();
    public String username2;

    public String getUsername2() {
        username2 = Util.getUserName();
        return username2;
    }

    public void setUsername2(String username2) {
        username2 = Util.getUserName();
        this.username2 = username2;
    }
    
    public String addContactDatabase(){
        username2 = Util.getUserName();
        Contacts c1=new Contacts();
        c1.setName(name);
        c1.setSurname(surname);
        c1.setNumber(number);
        c1.setCountry("aa");
        c1.setUserinfo(dataget.getUserID(username2));
        System.out.println(c1);
        dataget.addContact(c1);
        return "contacts";
    }
}
