package infoP;
// Generated Jan 28, 2017 10:30:50 PM by Hibernate Tools 4.3.1



/**
 * Countrycode generated by hbm2java
 */
public class Countrycode  implements java.io.Serializable {


     private Integer id;
     private String iso;
     private String name;
     private String nicename;
     private String iso3;
     private String numcode;

    public Countrycode() {
    }

    public Countrycode(String iso, String name, String nicename, String iso3, String numcode) {
       this.iso = iso;
       this.name = name;
       this.nicename = nicename;
       this.iso3 = iso3;
       this.numcode = numcode;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getIso() {
        return this.iso;
    }
    
    public void setIso(String iso) {
        this.iso = iso;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getNicename() {
        return this.nicename;
    }
    
    public void setNicename(String nicename) {
        this.nicename = nicename;
    }
    public String getIso3() {
        return this.iso3;
    }
    
    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }
    public String getNumcode() {
        return this.numcode;
    }
    
    public void setNumcode(String numcode) {
        this.numcode = numcode;
    }




}


