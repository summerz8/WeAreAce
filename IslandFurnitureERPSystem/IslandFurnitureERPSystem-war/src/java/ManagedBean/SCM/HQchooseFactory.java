/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.SCM;

import SessionBean.CommonInFrastructure.Factory_StoreManagementModuleLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dan
 */
@Named(value = "hqFactory")
@ViewScoped
public class HQchooseFactory {
    @EJB
    private Factory_StoreManagementModuleLocal FSMM;

    private Long HFactoryId;

    /**
     * Creates a new instance of HQchooseFactory
     */
    public HQchooseFactory() {
    }

    public void RememberHFactoryId(ActionEvent event) {
        if(FSMM.getFactory(HFactoryId)!=null){
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("HFactoryId", HFactoryId);
        try {
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/secured/restricted/Factory/FactoryResourceControl.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(HQchooseFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Factory not exist!", ""));
        }
    }

    public Long getHFactoryId() {
        return HFactoryId;
    }

    public void setHFactoryId(Long HFactoryId) {
        this.HFactoryId = HFactoryId;
    }

}
