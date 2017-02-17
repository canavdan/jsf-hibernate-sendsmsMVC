package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import login.Util;
import login.Database;
import infoP.Contacts;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nemesis
 */
@ManagedBean(name = "e")
@SessionScoped
public class exportimportBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of exportimportBean
     */
    public exportimportBean() {
    }
    public String username;

    public String getUsername() {
        username = Util.getUserName();
        return username;
    }

    public void setUsername(String username) {
        username = Util.getUserName();
        this.username = username;
    }
    Database dataget = new Database();
   private List<Contacts> dataList;
    int id = 0;

    public List<Contacts> getDataList() {
        username = Util.getUserName();
        id = dataget.getUserID2(username);
        dataList = dataget.getAllUsers(id);
        return dataList;
    }

    public void setDataList(List<Contacts> dataList) {
        this.dataList = dataList;
    }
     
   /* @ManagedProperty("#{carService}")
    private CarService service;*/
     
   // @PostConstruct
    /*public void init() {
        dataList = service.createCars(100);
    }*/
    
    
    /*public List<Contacts> getContacts() {
        username = Util.getUserName();
        id = dataget.getUserID2(username);
        dataList = dataget.getAllUsers(id);
        return dataList;
        // return dataget.getAllUsers(id);
    }*/

    /*public String export() {
        username = Util.getUserName();
         id = dataget.getUserID2(username);
        dataList = dataget.getAllUsers(id);

        String fileName = null; 
      
        if (dataList != null) {
            fileName = "KullaniciListesi.xls";
        }
        final String header = "Name, Surname, Number, Country";
        final String newline = "\n";
        final String comma = ",";
       try {        
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(header.toString());
            bw.write(newline);
            for (Contacts k : dataList) {
                if (k.getName() != null) {
                    bw.write(k.getName());
                } else {
                    bw.write(" ");
                }
                bw.write(comma);
                if (k.getSurname() != null) {
                    bw.write(k.getSurname());
                } else {
                    bw.write(" ");
                }
                bw.write(comma);
                if (k.getNumber() != null) {
                    bw.write(k.getNumber());
                } else {
                    bw.write(" ");
                }
                bw.write(comma);
                if (k.getCountry() != null) {
                    bw.write(k.getCountry());
                } else {
                    bw.write(" ");
                }
                bw.write(newline);
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
      // downloadFile();
        return "sent";
    }*/
 /*public void downloadFile() {

    File file = new File("/home/marco/file.txt");
    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  

    response.setHeader("Content-Disposition", "attachment;filename=file.txt");  
    response.setContentLength((int) file.length());  
    ServletOutputStream out = null;  
    try {  
        FileInputStream input = new FileInputStream(file);  
        byte[] buffer = new byte[1024];  
        out = response.getOutputStream();  
        int i = 0;  
        while ((i = input.read(buffer)) != -1) {  
            out.write(buffer);  
            out.flush();  
        }  
        FacesContext.getCurrentInstance().getResponseComplete();  
    } catch (IOException err) {  
        err.printStackTrace();  
    } finally {  
        try {  
            if (out != null) {  
                out.close();  
            }  
        } catch (IOException err) {  
            err.printStackTrace();  
        }  
    }  
}*/

}
