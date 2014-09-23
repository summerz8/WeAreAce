/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superuserclient;

import SessionBean.IFManagerBeanRemote;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

/**
 *
 * @author zhangshiyu
 */
public class Main {

    @EJB
    private static IFManagerBeanRemote IFMB;

    public static void main(String[] args) {
        Main superUser = new Main();
        //superUser.setUp();
        superUser.displayMenu();
        superUser.doFunctions();
    }

    private void displayMenu() {
        System.out.println("Choose the action number below if you want to perform an action");
        System.out.println("1. Create an global headquarter account");
        
        System.out.println("\n\nPlease select <'Q' or 'q' to exit>:");
        
    }

    private void doFunctions() {
        Scanner sc = new Scanner(System.in);
        Main client = new Main();

        String choice = sc.nextLine();

        while (!(choice.equals("Q") || choice.equals("q"))) {
            if (choice.equals("1")) {
                createGlobalHQ(sc);
            }
            
            client.displayMenu();
            choice = sc.nextLine();
        }
        
        System.out.println("Exiting...");
    }

    private void createGlobalHQ(Scanner sc) {
        //String department = "H";
        Integer userLevel =  0;
        System.out.println("Please enter user's lastname:");
        String lastName = sc.nextLine();
        System.out.println("Please enter user's firstname:");
        String firstName = sc.nextLine();
        System.out.println("Please enter user's position:");
        String position = sc.nextLine();
        System.out.println("Please enter user's gender:");
        String gender = sc.nextLine();
        System.out.println("Please enter user's department(H, F, or S):");
        String department = sc.nextLine();
        System.out.println("Please enter user's birthday(format: dd-mm-yyyy)");
        String birthday = sc.nextLine();
        
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Calendar bir = Calendar.getInstance();
        try {
            bir.setTime(df.parse(birthday));
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String info = IFMB.createUser(department, userLevel, lastName, firstName, position, gender, 1000000, bir);
        String userId = info.substring(0, 8);
        String pwd = info.substring(9);
        
        
        
        System.out.println("The new created user account id is: " + userId);
        System.out.println(userId);
        System.out.println("The new created user account password is: " + pwd);
        System.out.println("Please change this system self-genereated password as soon as possible for security concern.");       
    }
//    private void setUp() {
//        IFMB.setUpIdNumber();
//    }
    

}
