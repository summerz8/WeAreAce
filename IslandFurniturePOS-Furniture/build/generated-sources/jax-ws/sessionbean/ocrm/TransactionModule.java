
package sessionbean.ocrm;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "TransactionModule", targetNamespace = "http://OCRM.SessionBean/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface TransactionModule {


    /**
     * 
     * @param poSid
     * @param location
     * @param staffId
     * @param memberId
     * @return
     *     returns java.lang.Long
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createTransaction", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.CreateTransaction")
    @ResponseWrapper(localName = "createTransactionResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.CreateTransactionResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/createTransactionRequest", output = "http://OCRM.SessionBean/TransactionModule/createTransactionResponse")
    public Long createTransaction(
        @WebParam(name = "staffId", targetNamespace = "")
        String staffId,
        @WebParam(name = "memberId", targetNamespace = "")
        Long memberId,
        @WebParam(name = "location", targetNamespace = "")
        int location,
        @WebParam(name = "POSid", targetNamespace = "")
        String poSid);

    /**
     * 
     * @param itemId
     * @param amount
     * @param transactionId
     */
    @WebMethod
    @RequestWrapper(localName = "createTransactionItem", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.CreateTransactionItem")
    @ResponseWrapper(localName = "createTransactionItemResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.CreateTransactionItemResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/createTransactionItemRequest", output = "http://OCRM.SessionBean/TransactionModule/createTransactionItemResponse")
    public void createTransactionItem(
        @WebParam(name = "itemId", targetNamespace = "")
        Long itemId,
        @WebParam(name = "amount", targetNamespace = "")
        int amount,
        @WebParam(name = "transactionId", targetNamespace = "")
        Long transactionId);

    /**
     * 
     * @param transactionId
     * @return
     *     returns java.lang.Double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "caculateTotalPrice", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.CaculateTotalPrice")
    @ResponseWrapper(localName = "caculateTotalPriceResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.CaculateTotalPriceResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/caculateTotalPriceRequest", output = "http://OCRM.SessionBean/TransactionModule/caculateTotalPriceResponse")
    public Double caculateTotalPrice(
        @WebParam(name = "transactionId", targetNamespace = "")
        Long transactionId);

    /**
     * 
     * @param tendered
     * @param transactionId
     */
    @WebMethod
    @RequestWrapper(localName = "setTendered", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.SetTendered")
    @ResponseWrapper(localName = "setTenderedResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.SetTenderedResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/setTenderedRequest", output = "http://OCRM.SessionBean/TransactionModule/setTenderedResponse")
    public void setTendered(
        @WebParam(name = "transactionId", targetNamespace = "")
        Long transactionId,
        @WebParam(name = "tendered", targetNamespace = "")
        Double tendered);

    /**
     * 
     * @param moneyChange
     * @param transactionId
     */
    @WebMethod
    @RequestWrapper(localName = "caculateChange", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.CaculateChange")
    @ResponseWrapper(localName = "caculateChangeResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.CaculateChangeResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/caculateChangeRequest", output = "http://OCRM.SessionBean/TransactionModule/caculateChangeResponse")
    public void caculateChange(
        @WebParam(name = "transactionId", targetNamespace = "")
        Long transactionId,
        @WebParam(name = "moneyChange", targetNamespace = "")
        Double moneyChange);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.Double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "caculateRedemption", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.CaculateRedemption")
    @ResponseWrapper(localName = "caculateRedemptionResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.CaculateRedemptionResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/caculateRedemptionRequest", output = "http://OCRM.SessionBean/TransactionModule/caculateRedemptionResponse")
    public Double caculateRedemption(
        @WebParam(name = "arg0", targetNamespace = "")
        Double arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Long arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkItem", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.CheckItem")
    @ResponseWrapper(localName = "checkItemResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.CheckItemResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/checkItemRequest", output = "http://OCRM.SessionBean/TransactionModule/checkItemResponse")
    public Boolean checkItem(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<sessionbean.ocrm.TransactionItemEntity>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getTransactionItemList", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.GetTransactionItemList")
    @ResponseWrapper(localName = "getTransactionItemListResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.GetTransactionItemListResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/getTransactionItemListRequest", output = "http://OCRM.SessionBean/TransactionModule/getTransactionItemListResponse")
    public List<TransactionItemEntity> getTransactionItemList(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "deleteUnfinishedTransaction", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.DeleteUnfinishedTransaction")
    @ResponseWrapper(localName = "deleteUnfinishedTransactionResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.DeleteUnfinishedTransactionResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/deleteUnfinishedTransactionRequest", output = "http://OCRM.SessionBean/TransactionModule/deleteUnfinishedTransactionResponse")
    public void deleteUnfinishedTransaction(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0);

    /**
     * 
     * @param storeStaffId
     * @return
     *     returns sessionbean.ocrm.StoreEntity
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getStoreByStaffId", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.GetStoreByStaffId")
    @ResponseWrapper(localName = "getStoreByStaffIdResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.GetStoreByStaffIdResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/getStoreByStaffIdRequest", output = "http://OCRM.SessionBean/TransactionModule/getStoreByStaffIdResponse")
    public StoreEntity getStoreByStaffId(
        @WebParam(name = "storeStaffId", targetNamespace = "")
        String storeStaffId);

    /**
     * 
     * @param arg0
     * @return
     *     returns sessionbean.ocrm.TransactionEntity
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getTransactionById", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.GetTransactionById")
    @ResponseWrapper(localName = "getTransactionByIdResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.GetTransactionByIdResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/getTransactionByIdRequest", output = "http://OCRM.SessionBean/TransactionModule/getTransactionByIdResponse")
    public TransactionEntity getTransactionById(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "changeAmount", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.ChangeAmount")
    @ResponseWrapper(localName = "changeAmountResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.ChangeAmountResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/changeAmountRequest", output = "http://OCRM.SessionBean/TransactionModule/changeAmountResponse")
    public void changeAmount(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        int arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "deleteTransactionItem", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.DeleteTransactionItem")
    @ResponseWrapper(localName = "deleteTransactionItemResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.DeleteTransactionItemResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/deleteTransactionItemRequest", output = "http://OCRM.SessionBean/TransactionModule/deleteTransactionItemResponse")
    public void deleteTransactionItem(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        Long arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkItemType", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.CheckItemType")
    @ResponseWrapper(localName = "checkItemTypeResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.CheckItemTypeResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/checkItemTypeRequest", output = "http://OCRM.SessionBean/TransactionModule/checkItemTypeResponse")
    public int checkItemType(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "inventoryMovement", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.InventoryMovement")
    @ResponseWrapper(localName = "inventoryMovementResponse", targetNamespace = "http://OCRM.SessionBean/", className = "sessionbean.ocrm.InventoryMovementResponse")
    @Action(input = "http://OCRM.SessionBean/TransactionModule/inventoryMovementRequest", output = "http://OCRM.SessionBean/TransactionModule/inventoryMovementResponse")
    public void inventoryMovement(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0);

}