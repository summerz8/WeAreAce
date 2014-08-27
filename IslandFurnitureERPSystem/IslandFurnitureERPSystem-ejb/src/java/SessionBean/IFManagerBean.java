/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean;

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
    
    
}
