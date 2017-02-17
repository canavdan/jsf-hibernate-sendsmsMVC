package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import login.Util;
import login.Database; 
import infoP.Messages;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Nemesis
 */
@ManagedBean(name = "s")
@SessionScoped
public class sentSMSBean implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of sentSMSBean
     */
    public sentSMSBean() {
    }
    public String message, to, username;
    public String countryCode;

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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getUsername() {
        username = Util.getUserName();
        return username;
    }

    public void setUsername(String username) {
        username = Util.getUserName();
        this.username = username;
    }

    public void clear() {
        setMessage("");
        setCountryCode(null);
        setTo(null);
    }

    public String sentSms() {
        username = Util.getUserName();
        Database dataGet = new Database();
        Messages m1 = new Messages();
        m1.settoNumber(to);
        m1.setMessage(message);
        m1.setUserinfo(dataGet.getUserID(username));
        SimpleDateFormat datee = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        m1.setDate(datee.format(today));
        dataGet.addMessages(m1);
        sendSMS(m1.gettoNumber(), m1.getMessage());
        return "sent";
    }

    public void sendSMS(String num, String messagee) {
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
            data += "&message=" + URLEncoder.encode(messagee, "ISO-8859-1");
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

    public String infoSMSCredits() {
        String a = "";
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

            // Send data
            // Please see the FAQ regarding HTTPS (port 443) and HTTP (port 80/5567)
            URL url = new URL("https://bulksms.vsms.net/eapi/user/get_credits/1/1.1");

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
                a = line;
                return line;
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

}
