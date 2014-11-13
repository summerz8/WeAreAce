/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.IM;

import SessionBean.CommonInFrastructure.Factory_StoreManagementModuleLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author summer
 */
@Named(value = "hqStore")
@ViewScoped
public class HQchooseStore {
    @EJB
    private Factory_StoreManagementModuleLocal FSMM;

    private Long HStoreId;

    public HQchooseStore() {
    }
    
    public void RememberHStoreId(ActionEvent event) {
        if(FSMM.getStore(HStoreId)!=null){
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("HStoreId", HStoreId);
        try {
            String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(url + "/secured/restricted/Store/StoreResourceControl.xhtml");

        } catch (IOException ex) {
            Logger.getLogger(HQchooseStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Store not exist!", ""));
        }
    }

    public Long getHStoreId() {
        return HStoreId;
    }

    public void setHStoreId(Long HStoreId) {
        this.HStoreId = HStoreId;
    }
    
    
    
}
