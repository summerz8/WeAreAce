/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean;

import entity.UserEntity;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zhangshiyu
 */
@Stateful
public class IFManagerBean implements IFManagerBeanRemote {

    @PersistenceContext
    private EntityManager em;
    
    public IFManagerBean(){
    }
    
    @Override
    public boolean checkAccount(String userId, String pwd){
        UserEntity user;
        boolean check = false;
        
        System.out.println("IFManagerBean: checkAccount()");
        user = em.find(UserEntity.class, userId);
        
        //if the user exsit and password correct
        if (user == null) {
            System.out.println("User Not Found!");         
        } else if ((user.getPwd().equals(pwd))){
            check = true;
            System.out.println("User Found!");
        } else {
            System.out.println("User Found but password inccorect!");
        }
        
        return check;      
    }
}
