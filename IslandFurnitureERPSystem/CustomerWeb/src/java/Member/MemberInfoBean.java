/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

import Entity.Store.OCRM.MemberEntity;
import Entity.Store.OCRM.ShoppingCartItemEntity;
import SessionBean.OCRM.CustomerWebMemberModuleLocal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "memberInfo")
@ViewScoped
public class MemberInfoBean {

    @EJB
    private CustomerWebMemberModuleLocal MRMM;

    private MemberEntity member;
    private String firstName;
    private String email;
    
    private Long memberId;
    private String midName;
    private String lastName;
    private String title;
    private String gender;
    private String address;
    private String postal;
    private String password;
    private Calendar birthday;
    private List<ShoppingCartItemEntity> itemList;

    private Date birDate;// used to convert birthday between string and calendar
    private String birString;
    
    private String first;

    public MemberInfoBean() {
    }

    @PostConstruct
    public void init() {
        firstName = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("FirstName");
        email = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Email");
        if (firstName != null) {
            member = MRMM.getMember(email);
            midName = member.getMidName();
            lastName = member.getLastName();
            title = member.getTitle();
            gender = member.getGender();
            address = member.getAddress();
            postal = member.getPostalCode();
            password = member.getPwd();
            birthday = member.getBirthday();
            memberId = member.getMemberId();
            itemList= member.getShoppingCartList();
            first=firstName;

        }
    }

     
    public String upDate() {
        System.out.println("MemberControlBean: upDateMemberInfo: ");
        MRMM.ModifyMember(memberId,lastName, midName, first, birthday, gender, title, address, postal, email);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Email", email);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("FirstName", firstName);
        return "MemberPage?faces-redirect=true";
    }
    
    public String upDateShoppingCart(){
        MRMM.upDateShoppingCart(memberId,itemList);
    
        return "MemberPage?faces-redirect=true";
    }
    
    
    public String logOut(){
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Email", null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("FirstName", null);
        return "HomePage?faces-redirect=true";
    }
    
    public String logOut2(){
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Email", null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("FirstName", null);
        return "../../Singapore/HomePage?faces-redirect=true";
    }
    
    public String logOut3(){
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Email", null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("FirstName", null);
        return "../../China/HomePage?faces-redirect=true";
    }
    
    
            
    public String removeItem(Long id){
        MRMM.removeItem(memberId,id);
        
        return "ShoppingCart?faces-redirect=true";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFristName(String firstName) {
        this.firstName = firstName;
    }


    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public Date getBirDate() {
        return birDate;
    }

    public void setBirDate(Date birDate) {
        this.birDate = birDate;
    }

    public String getBirString() {
        return birString;
    }

    public void setBirString(String birString) {
        this.birString = birString;
    }

    public CustomerWebMemberModuleLocal getMRMM() {
        return MRMM;
    }

    public void setMRMM(CustomerWebMemberModuleLocal MRMM) {
        this.MRMM = MRMM;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public List<ShoppingCartItemEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<ShoppingCartItemEntity> itemList) {
        this.itemList = itemList;
    }

    
}
