/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.ReturnedItemMovementRecordEntity;
import SessionBean.OCRM.PostSaleServiceLocal;
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
@Named(value = "postSaleService")
@ViewScoped
public class PostSaleService {

    @EJB
    private PostSaleServiceLocal pssl;
    private Long storeId;
    private List<ReturnedItemMovementRecordEntity> recordList;
    private ReturnedItemMovementRecordEntity record;
    private Long storeProductId;
    private Long memberId=null;
    private String description;
    private String status;
    private String type;
    private Long transactionId;
    
    

    public PostSaleService() {
    }

    @PostConstruct
    public void Init() {
        storeId=(Long)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("departmentId");
        recordList=pssl.ListAllRecords(storeId);
    }

    public String create(){        System.out.println("11111");

        record=pssl.createRecord(type, storeId, storeProductId, description, memberId);
                System.out.println("77777");

        return "PostSaleService?faces-redirect=true";
    }
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public List<ReturnedItemMovementRecordEntity> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<ReturnedItemMovementRecordEntity> recordList) {
        this.recordList = recordList;
    }

    public PostSaleServiceLocal getPssl() {
        return pssl;
    }

    public void setPssl(PostSaleServiceLocal pssl) {
        this.pssl = pssl;
    }

    public ReturnedItemMovementRecordEntity getRecord() {
        return record;
    }

    public void setRecord(ReturnedItemMovementRecordEntity record) {
        this.record = record;
    }

    public Long getStoreProductId() {
        return storeProductId;
    }

    public void setStoreProductId(Long storeProductId) {
        this.storeProductId = storeProductId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
    
    
}
