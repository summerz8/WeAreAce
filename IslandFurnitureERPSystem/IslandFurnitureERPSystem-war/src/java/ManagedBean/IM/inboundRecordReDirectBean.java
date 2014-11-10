/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean.IM;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author zhengyuan
 */
@Named(value = "inboundRecordReDirectBean")
@ViewScoped
public class inboundRecordReDirectBean {

    /**
     * Creates a new instance of inboundRecordReDirectBean
     */
    public inboundRecordReDirectBean() {
    }
    
    private Integer result;
    
    
    public void IBRredirect(ActionEvent event) throws IOException{
        if(result == 0){
        String path = "/secured/restricted/Store/IM/createAnInboundMovementRecord.xhtml";
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url + path);
        System.err.println("go to another page");  
            
        }else if (result ==1){
        String path = "/secured/restricted/Store/IM/createAnInboundMovementRecordRetail.xhtml";
        String url = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(url + path);
        System.err.println("go to another page");
            
            
        }
        
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
    
    
    
}
