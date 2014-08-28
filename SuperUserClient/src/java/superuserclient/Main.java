/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package superuserclient;

import SessionBean.IFManagerBeanRemote;
import java.util.Scanner;
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
<<<<<<< HEAD

        String choice = sc.nextLine();
=======
        String choice = sc.next();
>>>>>>> a2ab0684f7da8f1dc964af4c4be5e73c4206a4bb

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
        String department = "H";
        Integer userLevel =  0;
        System.out.println("Please enter user's lastname:");
        String lastName = sc.nextLine();
        System.out.println("Please enter user's firstname:");
        String firstName = sc.nextLine();
        System.out.println("Please enter user's position:");
        String position = sc.nextLine();
        System.out.println("Please enter user's gender:");
        String gender = sc.nextLine();
        
        String info = IFMB.createUser(department, userLevel, lastName, firstName, position, gender);
        String userId = info.substring(0, 7);
        String pwd = info.substring(9);
        
        System.out.println("The new created user account id is: " + userId);
        System.out.println("The new created user account password is: " + pwd);
        System.out.println("Please change this system self-genereated password as soon as possible for security concern.");       
    }

}
