package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import login.Util;
import login.Database;
import infoP.Contacts;
import infoP.Messages;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Nemesis
 */
@ManagedBean(name="m")
@SessionScoped
public class multismsBean implements Serializable {

	private static final long serialVersionUID = 1L;

    List<Contacts> selectedDataList = new ArrayList<Contacts>();
    public multismsBean() {
    }

    public List<Contacts> getSelectedDataList() {
        return selectedDataList;
    }

    public void setSelectedDataList(List<Contacts> selectedDataList) {
        this.selectedDataList = selectedDataList;
    }
    public String multiNumbers;

    public String getMultiNumbers() {
        multiNumbers = createmultiNumbers();
        return multiNumbers;
    }

    public void setMultiNumbers(String multiNumbers) {
        this.multiNumbers = multiNumbers;
    }
     public String messages;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
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
    
    
    public String createmultiNumbers() {
        String nums;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < selectedDataList.size(); i++) {
            stringBuilder.append(selectedDataList.get(i).getNumber());
            stringBuilder.append(",");
        }
        return stringBuilder.toString();
    }

    public String clear() {
        setMessages("");
        selectedDataList.clear();
        setMultiNumbers("");
        return "multisms";
    }

    public String sentMultiSms() {
        Database dataGet = new Database();    
        username = Util.getUserName();
        for (Contacts selectedDataList1 : selectedDataList) {        
            Messages m1 = new Messages();
             m1.settoNumber(selectedDataList1.getNumber());
            m1.setMessage(messages);
            m1.setUserinfo(dataGet.getUserID(username));
            SimpleDateFormat datee = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
            Date today = Calendar.getInstance().getTime();
            m1.setDate(datee.format(today));
            dataGet.addMessages(m1);
            sendSMS(m1.gettoNumber(),m1.getMessage());
        }
       // sendSMS();      
        return "sent";
    }
    
     public void sendSMS(String num,String mes) {
        try {
            // Construct data
            String data = "";
            /*
             * Note the suggested encoding for certain parameters, notably
             * the username, password and especially the message.  ISO-8859-1
             * is essentially the character set that we use for message bodies,
             * with a few exceptions for e.g. Greek characters.  For a full list,
             * see:  http://developer.bulksms.com/eapi/submission/character-encoding/
             */
            data += "username=" + URLEncoder.encode("YOURUSERNAME", "ISO-8859-1");
            data += "&password=" + URLEncoder.encode("YOURPASSWORD", "ISO-8859-1");
            data += "&message=" + URLEncoder.encode(mes, "ISO-8859-1");
            data += "&want_report=1";
            data += "&msisdn=" + num;

            // Send data
            // Please see the FAQ regarding HTTPS (port 443) and HTTP (port 80/5567)
            URL url = new URL("https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Print the response output...
                System.out.println(line);
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
