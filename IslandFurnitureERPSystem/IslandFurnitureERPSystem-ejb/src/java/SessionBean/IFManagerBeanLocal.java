/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean;

import javax.ejb.Local;

/**
 *
 * @author zhangshiyu
 */
@Local
public interface IFManagerBeanLocal {
    //public String createUser(String department, Integer userLevel, String lastName, String firstName, String position, String gender);
//    public String getUserId(String userId);
    public boolean checkAccount(String userId, String pwd);

    public String createUser(String department, Integer userLevel, String lastName, String firstName, String position, String gender, String departmentId);
}