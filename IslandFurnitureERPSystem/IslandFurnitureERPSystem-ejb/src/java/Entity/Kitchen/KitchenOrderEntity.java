/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Kitchen;

import Entity.CommonInfrastructure.StoreUserEntity;
import Entity.Store.OCRM.MemberEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Yoky
 */
@Entity
@XmlAccessorType(value = XmlAccessType.FIELD)
public class KitchenOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    @XmlTransient
    private List<DishItemEntity> dishes = new ArrayList<>();
    @OneToMany
    @XmlTransient
    private List<ComboItemEntity> combos = new ArrayList<>();
    @OneToMany
    @XmlTransient
    private List<DetailedDishItemEntity> detailedDishItems = new ArrayList<>();
    private Double total;
    private Double totalWithDiscount;
    private Double received;
    private Double due;
    private Integer totalDishItemQuantity;
    private Integer fulfilledDishItemQuantity;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar creationTime;
    private String status;  //Unconfirmed, Confirmed, Fulfilled, Cancelled, Served
    private String POSid;
    @ManyToOne
    private KitchenEntity kitchen;
    @ManyToOne  
    private MemberEntity member;
    @ManyToOne
    private StoreUserEntity storeStaff;

    public KitchenOrderEntity() {
        total = 0.0;
        totalWithDiscount = 0.0;
        received = 0.0;
        due = 0.0;
        this.status = "Unconfirmed";
        member = null;
        totalDishItemQuantity = 0;
        fulfilledDishItemQuantity = 0;
        creationTime = Calendar.getInstance();
    }

    public KitchenOrderEntity(KitchenEntity kitchen, StoreUserEntity storeStaff) {
        this.kitchen = kitchen;
        this.storeStaff = storeStaff;
        total = 0.0;
        totalWithDiscount = 0.0;
        received = 0.0;
        due = 0.0;
        this.status = "Unconfirmed";
        member = null;
        totalDishItemQuantity = 0;
        fulfilledDishItemQuantity = 0;
        creationTime = Calendar.getInstance();
    }

    public String getPOSid() {
        return POSid;
    }

    public void setPOSid(String POSid) {
        this.POSid = POSid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DishItemEntity> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishItemEntity> dishes) {
        this.dishes = dishes;
    }

    public List<ComboItemEntity> getCombos() {
        return combos;
    }

    public void setCombos(List<ComboItemEntity> combos) {
        this.combos = combos;
    }

    public List<DetailedDishItemEntity> getDetailedDishItems() {
        return detailedDishItems;
    }

    public void setDetailedDishItems(List<DetailedDishItemEntity> detailedDishItems) {
        this.detailedDishItems = detailedDishItems;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotalWithDiscount() {
        return totalWithDiscount;
    }

    public void setTotalWithDiscount(Double totalWithDiscount) {
        this.totalWithDiscount = totalWithDiscount;
    }

    public Double getReceived() {
        return received;
    }

    public void setReceived(Double received) {
        this.received = received;
    }

    public Double getDue() {
        return due;
    }

    public void setDue(Double due) {
        this.due = due;
    }

    public Integer getTotalDishItemQuantity() {
        return totalDishItemQuantity;
    }

    public void setTotalDishItemQuantity(Integer totalDishItemQuantity) {
        this.totalDishItemQuantity = totalDishItemQuantity;
    }

    public Integer getFulfilledDishItemQuantity() {
        return fulfilledDishItemQuantity;
    }

    public void setFulfilledDishItemQuantity(Integer fulfilledDishItemQuantity) {
        this.fulfilledDishItemQuantity = fulfilledDishItemQuantity;
    }

    public Calendar getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Calendar creationTime) {
        this.creationTime = creationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public KitchenEntity getKitchen() {
        return kitchen;
    }

    public void setKitchen(KitchenEntity kitchen) {
        this.kitchen = kitchen;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public StoreUserEntity getStoreStaff() {
        return storeStaff;
    }

    public void setStoreStaff(StoreUserEntity storeStaff) {
        this.storeStaff = storeStaff;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KitchenOrderEntity)) {
            return false;
        }
        KitchenOrderEntity other = (KitchenOrderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Kitchen.OrderEntity[ id=" + id + " ]";
    }

}
