package infoP;
// Generated Jan 28, 2017 10:30:50 PM by Hibernate Tools 4.3.1



/**
 * Contacts generated by hbm2java
 */
public class Contacts  implements java.io.Serializable {
    boolean update;
    public boolean selected;

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean isUpdate() {
        return update;
    }
    public void setUpdate(boolean update) {
        this.update = update;
    }

     private Integer id;
     private Userinfo userinfo;
     private String name;
     private String surname;
     private String number;
     private String country;

    public Contacts() {
    }
    
	
    public Contacts(Userinfo userinfo, String name, String number, String country) {
        this.userinfo = userinfo;
        this.name = name;
        this.number = number;
        this.country = country;
    }
    public Contacts(Userinfo userinfo, String name, String surname, String number, String country) {
       this.userinfo = userinfo;
       this.name = name;
       this.surname = surname;
       this.number = number;
       this.country = country;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Userinfo getUserinfo() {
        return this.userinfo;
    }
    
    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return this.surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getNumber() {
        return this.number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Contacts{" + "name=" + name + ", surname=" + surname + ", number=" + number + ", country=" + country + '}';
    }




}


