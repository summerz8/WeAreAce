/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.ACRM;

import Entity.Store.ACRM.RFMEntity;
import SessionBean.ACRM.AnalyticalCRMSessionBeanLocal;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author summer
 */
@Named(value = "viewRFMBean")
@ViewScoped
public class ViewRFM implements Serializable {

    @EJB
    private AnalyticalCRMSessionBeanLocal acrm;

    private Collection<RFMEntity> RFMList;

    public ViewRFM() {
    }

    public AnalyticalCRMSessionBeanLocal getAcrm() {
        return acrm;
    }

    public void setAcrm(AnalyticalCRMSessionBeanLocal acrm) {
        this.acrm = acrm;
    }

    public Collection<RFMEntity> getRFMList() {
        System.out.println(" viewRFMBean: getRFMList()");
        RFMList = acrm.getAllMembersRFM();
 
        System.out.println("RFMList get()");
        for (RFMEntity rfm : RFMList) {
            System.out.println("viewRFMBean: RFM = " + rfm.toString());
        }

        return RFMList;
    }

    public void setRFMList(Collection<RFMEntity> RFMList) {
        this.RFMList = RFMList;
    }

}
