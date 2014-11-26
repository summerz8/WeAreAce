/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.ACRM;

import Entity.Store.ACRM.RFMEntity;
import Entity.Store.OCRM.MemberEntity;
import SessionBean.ACRM.AnalyticalCRMSessionBeanLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
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
    private RFMEntity[] selectedRFMs;

    private MemberEntity selectedMember;
    
    public ViewRFM() {
    }
    
    @PostConstruct
    public void init()
    {
        RFMList = acrm.getAllMembersRFM();

        System.out.println("RFMList get()");
        for (RFMEntity rfm : RFMList) {
            System.out.println("viewRFMBean: RFM = " + rfm.toString());
        }
    }

    public AnalyticalCRMSessionBeanLocal getAcrm() {
        return acrm;
    }

    public void setAcrm(AnalyticalCRMSessionBeanLocal acrm) {
        this.acrm = acrm;
    }

    public Collection<RFMEntity> getRFMList() {
        System.out.println(" viewRFMBean: getRFMList()");
        

        return RFMList;
    }

    public void setRFMList(Collection<RFMEntity> RFMList) {
        this.RFMList = RFMList;
    }

    public RFMEntity[] getSelectedRFMs() {
        return selectedRFMs;
    }

    public void setSelectedRFMs(RFMEntity[] selectedRFMs) {
        this.selectedRFMs = selectedRFMs;
    }

    public MemberEntity getSelectedMember() {
        return selectedMember;
    }

    public void setSelectedMember(MemberEntity selectedMember) {
        this.selectedMember = selectedMember;
    }
    
    public String sendEmail(){
        
        System.out.println("ViewRFMBean: sendEmail:");
        
        Collection<MemberEntity> members = new ArrayList<>();
        for (RFMEntity rfm : selectedRFMs) {
            members.add(rfm.getMember());
        }

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("customers", members);

        return "SendCustomerEmail?faces-redirect=true";
    }

}
