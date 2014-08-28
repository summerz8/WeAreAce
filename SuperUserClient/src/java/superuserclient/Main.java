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
        System.out.println("\n\nPlease select <'Q' or 'q' to exit>:");
        System.out.println("Choose the action number below if you want to perform an action");
        System.out.println("1. Create an global headquarter account");
    }

    private void doFunctions() {
        Scanner sc = new Scanner(System.in);
        Main client = new Main();

        String choice = sc.next();

        while (!(choice.equals("Q") || choice.equals("q"))) {
            if (choice.equals("1")) {
                createGlobalHQ(sc);

            }
        }
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
        
        IFMB.createUser(department, userLevel, lastName, firstName, position, gender);
    }

}
