/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.OCRM.MembershipLevelEntity;
import SessionBean.OCRM.MemberRegistrationModuleLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author dan
 */
@Named(value = "membershipLevelBean")
@ViewScoped
public class MembershipLevelBean {

    @EJB
    private MemberRegistrationModuleLocal MRMM;

    private List<MembershipLevelEntity> membershipLevelList;

    /**
     * Creates a new instance of MembershipLevelBean
     */
    public MembershipLevelBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("MemberControlBean: init:");
        membershipLevelList = MRMM.getMembership();
        System.out.println(membershipLevelList.get(0).isInviteOnlyEvent());
    }

    public Boolean check(MembershipLevelEntity e) {
        return e.isInviteOnlyEvent();
    }
    
    public Boolean check2(MembershipLevelEntity e) {
        return e.isFreeParking();
    }
    
    public Boolean check3(MembershipLevelEntity e) {
        return e.isExpressCheckout();
    }
    
    public Boolean check4(MembershipLevelEntity e) {
        return e.isFreeDelivery();
    }
    

    public MemberRegistrationModuleLocal getMRMM() {
        return MRMM;
    }

    public void setMRMM(MemberRegistrationModuleLocal MRMM) {
        this.MRMM = MRMM;
    }

    public List<MembershipLevelEntity> getMembershipLevelList() {
        return membershipLevelList;
    }

    public void setMembershipLevelList(List<MembershipLevelEntity> membershipLevelList) {
        this.membershipLevelList = membershipLevelList;
    }

}
