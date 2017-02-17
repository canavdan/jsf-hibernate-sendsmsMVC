package beans;


import login.Util;
import login.Database;
import infoP.Contacts;
import infoP.Messages;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
@ManagedBean(name = "b")
@SessionScoped
public class infoBean implements Serializable {

	private static final long serialVersionUID = 1L;

    public infoBean() {
    }
    public String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public String name, info, city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    boolean update;

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
    Database dataget = new Database();
    int id = 0;

    List<Contacts> dataList;

    public List<Contacts> getSchools() {
        username = Util.getUserName();
        id = dataget.getUserID2(username);
        dataList = dataget.getAllUsers(id);
        return dataList;
        // return dataget.getAllUsers(id);
    }
    public String username;

    public Database getDataget() {
        return dataget;
    }

    public void setDataget(Database dataget) {
        this.dataget = dataget;
    }

    public String getUsername() {
        username = Util.getUserName();
        return username;
    }

    public void setUsername(String username) {
        username = Util.getUserName();
        this.username = username;
    }

    public void deleteContact(int id) {
        // Database dataget=new Database();
        dataget.deleteContact(id);
    }

    public String saveRecord() {
        //  Database dataget=new Database();  
        for (Contacts sc : dataget.getAllUsers(id)) {
            sc.setUpdate(false);
        }
        return null;
    }

    public String updateRecord(Contacts sc) {
        //DataGet dataget = new DataGet();     
        sc.setUpdate(true);
        dataget.updateSc(sc);
        return null;
    }
    public boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    List<Contacts> selectedDataList2;
    List<Contacts> selectedDataList = new ArrayList<Contacts>();
    // selectedDataList  = new ArrayList<Contacts>();

    public String chooseNumber() {

        // selectedDataList2 = getSchools();
        for (Contacts c : dataList) {

            if (c.isSelected()) {
                selectedDataList.add(c);
                c.setSelected(false);
            }
        }
        if (selectedDataList.size() > 1) {
            return "multisms";
        } else {
            return "sentsms";
        }
    }

    public List<Contacts> getSelectedDataList() {
        return selectedDataList;
    }
    public String multiNumbers;

    public String getMultiNumbers() {
        multiNumbers = createmultiNumbers();
        return multiNumbers;
    }

    public void setMultiNumbers(String multiNumbers) {
        this.multiNumbers = multiNumbers;
    }

    public String createmultiNumbers() {
        String nums;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < selectedDataList.size(); i++) {
            stringBuilder.append(selectedDataList.get(i).getNumber());
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

    public String feedback;

    public String sentMail() {

        try {
            String from = "gameuserrs@gmail.com";
            String pass = "";
            String[] to = {"gameuserrs@yandex.com"};// host
            String host = "smtp.gmail.com";
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            message.setSubject("FEEDBACK");
            /*StringBuilder tmp = new StringBuilder("");
            for(int i=0;i<7;i++){
               int sayi = (int) (Math.random() * 10);//Rastgele şifre üretir
               tmp.append(sayi);
            }  */
            message.setText(feedback);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "sent";
    }

    /* public String sentMultiSms() {
        Database dataGet = new Database();     
        for (Contacts selectedDataList1 : selectedDataList) {        
            Messages m1 = new Messages();
             m1.settoNumber(selectedDataList1.getNumber());
            m1.setMessage(messages);
            m1.setUserinfo(dataGet.getUserID(username));
            SimpleDateFormat datee = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
            Date today = Calendar.getInstance().getTime();
            m1.setDate(datee.format(today));
            dataGet.addMessages(m1);
        }
       // Messages m1 = new Messages();
        return "sent";
    }*/
    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
