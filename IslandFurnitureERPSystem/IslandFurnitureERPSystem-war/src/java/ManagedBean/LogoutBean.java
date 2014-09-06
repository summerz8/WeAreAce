/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean;

import SessionBean.IFManagerBeanRemote;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author dan
 */
@Named(value = "logoutBean")
@ManagedBean
@ApplicationScoped
public class LogoutBean {

    /**
     * Creates a new instance of LogoutBean
     */
    @EJB
    private IFManagerBeanRemote IFMB;
    
    public LogoutBean() {
        
    }
    
        public void performLogout(ActionEvent event){
        System.out.println("LogoutBean: performLogout:()");
               
        try {
           
                HttpServletRequest request= (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
                request.getSession().setAttribute("flag", null);
                
                System.out.println("LogoutBean: Logout successfully...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }   
    
    }
}
