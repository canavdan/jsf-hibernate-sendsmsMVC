package login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nemesis
 */
public class LoginDAO {
    public static boolean validate(String user, String password) {
		Database d1=new Database();
                return d1.searchLogin(user, password);
		//return false;
	}
}
