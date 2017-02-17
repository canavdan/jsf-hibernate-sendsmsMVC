package login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import beans.Login;
import infoP.Contacts;
import infoP.Messages;
import infoP.Userinfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Database implements Serializable {

	private static final long serialVersionUID = 1L;

    public boolean searchLogin(String user, String password) {
        Userinfo ui;
        List<Userinfo> users = new ArrayList<Userinfo>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql = "from Userinfo WHERE username=:p1 and password=:p2";
            Query query = session.createQuery(hql);
            query.setParameter("p1", user);
            query.setParameter("p2", password);
            ui = (Userinfo) query.uniqueResult();
            if (!ui.getUsername().isEmpty()) {
                return true;
            }
            // users = (List<Userinfo>)session.createQuery("from Userinfo WHERE username=':parametre' and password=':parametre2'").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return false;
    }

    public boolean registerUsernameControl(String username) {
        Userinfo ui;
        List<Userinfo> users = new ArrayList<Userinfo>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql = "from Userinfo WHERE username=:p1";
            Query query = session.createQuery(hql);
            query.setParameter("p1", username);
            ui = (Userinfo) query.uniqueResult();
            if (!ui.getUsername().isEmpty()) {
                return true;
            }
            // users = (List<Userinfo>)session.createQuery("from Userinfo WHERE username=':parametre' and password=':parametre2'").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return false;
    }

    public boolean registerEmailControl(String email) {
        Userinfo ui;
        List<Userinfo> users = new ArrayList<Userinfo>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql = "from Userinfo WHERE country=:p1";
            Query query = session.createQuery(hql);
            query.setParameter("p1", email);
            ui = (Userinfo) query.uniqueResult();
            if (!ui.getUsername().isEmpty()) {
                return true;
            }
            // users = (List<Userinfo>)session.createQuery("from Userinfo WHERE username=':parametre' and password=':parametre2'").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return false;
    }

    public void addUserDatabase(Userinfo u) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(u);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
    public List<Contacts> getAllUsers(int id2) {
        List<Contacts> users = new ArrayList<Contacts>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql = "from Contacts WHERE user_id=:p1";
            Query query = session.createQuery(hql);
            query.setParameter("p1",id2);
             users = (List<Contacts>)query.list();
           // users = (List<Contacts>)session.createQuery("from Contacts").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        //System.out.println(users);
        return users;
    }
    public List<Messages> getAllMessages(int id2) {
        List<Messages> mes = new ArrayList<Messages>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql = "from Messages WHERE user_id=:p1";
            Query query = session.createQuery(hql);
            query.setParameter("p1",id2);
             mes = (List<Messages>)query.list();
           // users = (List<Contacts>)session.createQuery("from Contacts").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        //System.out.println(users);
        return mes;
    }
   // public String username2;
    public Userinfo getUserID(String usernam){
        int id=0;
        //System.out.println("1231231231231231");System.out.println("1231231231231231");
       // System.out.println("1231231231231231");System.out.println("1231231231231231");
        Login l1=new Login();
        //String usernam=l1.username;
       // System.out.println(usernam);
        Util t=new Util();   
        Userinfo ui = null;
        List<Userinfo> users = new ArrayList<Userinfo>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql = "from Userinfo WHERE username=:p1";
            Query query = session.createQuery(hql);
            query.setParameter("p1", usernam);
            ui = (Userinfo) query.uniqueResult();           
            /*if (!ui.getUsername().isEmpty()) {
                return true;
            }*/
            id=ui.getUserId();       
            // users = (List<Userinfo>)session.createQuery("from Userinfo WHERE username=':parametre' and password=':parametre2'").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }   
      //  System.out.println("1231231231231231");System.out.println("1231231231231231");
      //  System.out.println("1231231231231231");System.out.println("1231231231231231");
      //  System.out.println(usernam);
     //   System.out.println(ui.getName() + ui.getAdress());
        return ui;
    }
  public int getUserID2(String usernam){
        int id=0;
     
        Login l1=new Login();
        //String usernam=l1.username;
        //System.out.println(usernam);
        Util t=new Util();   
        Userinfo ui = null;
        List<Userinfo> users = new ArrayList<Userinfo>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String hql = "from Userinfo WHERE username=:p1";
            Query query = session.createQuery(hql);
            query.setParameter("p1", usernam);
            ui = (Userinfo) query.uniqueResult();           
            /*if (!ui.getUsername().isEmpty()) {
                return true;
            }*/
            id=ui.getUserId();       
            // users = (List<Userinfo>)session.createQuery("from Userinfo WHERE username=':parametre' and password=':parametre2'").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }   
        //System.out.println("1231231231231231");System.out.println("1231231231231231");
       // System.out.println("1231231231231231");System.out.println("1231231231231231");
       // System.out.println(usernam);
        //System.out.println(ui.getName() + ui.getAdress());
        return id;
    }
     public void deleteContact(int scid) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Contacts sc = (Contacts) session.load(Contacts.class, new Integer(scid));
            session.delete(sc);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
     public void deleteMessages(int scid) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Messages sc = (Messages) session.load(Messages.class, new Integer(scid));
            session.delete(sc);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
      public void updateSc(Contacts sc) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(sc);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
       public void updateUser(Userinfo sc) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(sc);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
      public void addContact(Contacts sc){
          Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(sc);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.flush();
            session.close();
        }
      }
      public void addMessages(Messages m){
               Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            //session.merge(m);
            session.save(m);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
        } finally {
           // session.flush();
            session.close();
        }
      }
}
