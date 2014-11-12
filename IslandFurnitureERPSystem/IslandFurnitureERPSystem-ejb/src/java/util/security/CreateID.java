/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.security;

import java.util.Date;

/**
 *
 * @author hangsun
 */
public class CreateID {

    private final static String str = "1234567890";
    private final static int pixLen = str.length();
    private static volatile int pixOne = 0;
    private static volatile int pixTwo = 0;
    private static volatile int pixThree = 0;
    private static volatile int pixFour = 0;
 
    
    final public synchronized static String generate() {
        StringBuilder sb = new StringBuilder();
        sb.append(Long.toOctalString(System.currentTimeMillis()));
        pixFour++;
        if (pixFour == pixLen) {
            pixFour = 0;
            pixThree++;
            if (pixThree == pixLen) {
                pixThree = 0;
                pixTwo++;
                if (pixTwo == pixLen) {
                    pixTwo = 0;
                    pixOne++;
                    if (pixOne == pixLen) {
                        pixOne = 0;
                    }
                }
            }
        }
        return sb.append(str.charAt(pixOne)).append(str.charAt(pixTwo)).append(str.charAt(pixThree)).append(str.charAt(pixFour)).toString();
    }
 
    
    public synchronized static String generate9() {
 
        int n = (int) (Math.random() * 900000000) + 100000000;
        return n + "";
    }
 
    
    public synchronized static String generateStrID(String str) {
        int count = 0;
        String time = Long.toString(System.currentTimeMillis());
        time = time.substring(time.length() - 9, time.length());
        if (count > 99) {
            count = 0;
        } else {
            count++;
        }
        if (count < 10) {
            str += time + "0" + count;
        } else {
            str += time + count;
        }
        return str;
    }
}
