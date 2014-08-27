/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean;

import javax.ejb.Remote;

/**
 *
 * @author zhangshiyu
 */
@Remote
public interface IFManagerBeanRemote {
    public boolean checkAccount(String userId, String pwd);
}
