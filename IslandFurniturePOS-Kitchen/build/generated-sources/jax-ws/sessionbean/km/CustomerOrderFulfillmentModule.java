
package sessionbean.km;

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
@WebService(name = "CustomerOrderFulfillmentModule", targetNamespace = "http://KM.SessionBean/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CustomerOrderFulfillmentModule {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<sessionbean.km.ComboEntity>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getComboByKitchenId", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.GetComboByKitchenId")
    @ResponseWrapper(localName = "getComboByKitchenIdResponse", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.GetComboByKitchenIdResponse")
    @Action(input = "http://KM.SessionBean/CustomerOrderFulfillmentModule/getComboByKitchenIdRequest", output = "http://KM.SessionBean/CustomerOrderFulfillmentModule/getComboByKitchenIdResponse")
    public List<ComboEntity> getComboByKitchenId(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<sessionbean.km.DishEntity>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getDishByKitchenId", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.GetDishByKitchenId")
    @ResponseWrapper(localName = "getDishByKitchenIdResponse", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.GetDishByKitchenIdResponse")
    @Action(input = "http://KM.SessionBean/CustomerOrderFulfillmentModule/getDishByKitchenIdRequest", output = "http://KM.SessionBean/CustomerOrderFulfillmentModule/getDishByKitchenIdResponse")
    public List<DishEntity> getDishByKitchenId(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<sessionbean.km.DishItemEntity>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getDishItemByOrderId", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.GetDishItemByOrderId")
    @ResponseWrapper(localName = "getDishItemByOrderIdResponse", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.GetDishItemByOrderIdResponse")
    @Action(input = "http://KM.SessionBean/CustomerOrderFulfillmentModule/getDishItemByOrderIdRequest", output = "http://KM.SessionBean/CustomerOrderFulfillmentModule/getDishItemByOrderIdResponse")
    public List<DishItemEntity> getDishItemByOrderId(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<sessionbean.km.ComboItemEntity>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getComboItemByOrderId", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.GetComboItemByOrderId")
    @ResponseWrapper(localName = "getComboItemByOrderIdResponse", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.GetComboItemByOrderIdResponse")
    @Action(input = "http://KM.SessionBean/CustomerOrderFulfillmentModule/getComboItemByOrderIdRequest", output = "http://KM.SessionBean/CustomerOrderFulfillmentModule/getComboItemByOrderIdResponse")
    public List<ComboItemEntity> getComboItemByOrderId(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns sessionbean.km.KitchenEntity
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findKitchenByStoreStaffId", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.FindKitchenByStoreStaffId")
    @ResponseWrapper(localName = "findKitchenByStoreStaffIdResponse", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.FindKitchenByStoreStaffIdResponse")
    @Action(input = "http://KM.SessionBean/CustomerOrderFulfillmentModule/findKitchenByStoreStaffIdRequest", output = "http://KM.SessionBean/CustomerOrderFulfillmentModule/findKitchenByStoreStaffIdResponse")
    public KitchenEntity findKitchenByStoreStaffId(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param poSid
     * @param storestaffId
     * @param kitchenId
     * @param memberId
     * @return
     *     returns sessionbean.km.KitchenOrderEntity
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createOrder", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.CreateOrder")
    @ResponseWrapper(localName = "createOrderResponse", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.CreateOrderResponse")
    @Action(input = "http://KM.SessionBean/CustomerOrderFulfillmentModule/createOrderRequest", output = "http://KM.SessionBean/CustomerOrderFulfillmentModule/createOrderResponse")
    public KitchenOrderEntity createOrder(
        @WebParam(name = "kitchenId", targetNamespace = "")
        Long kitchenId,
        @WebParam(name = "memberId", targetNamespace = "")
        Long memberId,
        @WebParam(name = "storestaffId", targetNamespace = "")
        String storestaffId,
        @WebParam(name = "POSid", targetNamespace = "")
        String poSid);

    /**
     * 
     * @param quantity
     * @param orderId
     * @param comboId
     * @return
     *     returns java.lang.Long
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addComboItem", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.AddComboItem")
    @ResponseWrapper(localName = "addComboItemResponse", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.AddComboItemResponse")
    @Action(input = "http://KM.SessionBean/CustomerOrderFulfillmentModule/addComboItemRequest", output = "http://KM.SessionBean/CustomerOrderFulfillmentModule/addComboItemResponse")
    public Long addComboItem(
        @WebParam(name = "orderId", targetNamespace = "")
        Long orderId,
        @WebParam(name = "comboId", targetNamespace = "")
        Long comboId,
        @WebParam(name = "quantity", targetNamespace = "")
        Integer quantity);

    /**
     * 
     * @param orderId
     * @param comboItemId
     * @return
     *     returns java.lang.Long
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteComboItem", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.DeleteComboItem")
    @ResponseWrapper(localName = "deleteComboItemResponse", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.DeleteComboItemResponse")
    @Action(input = "http://KM.SessionBean/CustomerOrderFulfillmentModule/deleteComboItemRequest", output = "http://KM.SessionBean/CustomerOrderFulfillmentModule/deleteComboItemResponse")
    public Long deleteComboItem(
        @WebParam(name = "orderId", targetNamespace = "")
        Long orderId,
        @WebParam(name = "comboItemId", targetNamespace = "")
        Long comboItemId);

    /**
     * 
     * @param orderId
     * @return
     *     returns java.lang.Long
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "confirmOrder", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.ConfirmOrder")
    @ResponseWrapper(localName = "confirmOrderResponse", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.ConfirmOrderResponse")
    @Action(input = "http://KM.SessionBean/CustomerOrderFulfillmentModule/confirmOrderRequest", output = "http://KM.SessionBean/CustomerOrderFulfillmentModule/confirmOrderResponse")
    public Long confirmOrder(
        @WebParam(name = "orderId", targetNamespace = "")
        Long orderId);

    /**
     * 
     * @param orderId
     * @param received
     * @return
     *     returns java.lang.Double
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkOut", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.CheckOut")
    @ResponseWrapper(localName = "checkOutResponse", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.CheckOutResponse")
    @Action(input = "http://KM.SessionBean/CustomerOrderFulfillmentModule/checkOutRequest", output = "http://KM.SessionBean/CustomerOrderFulfillmentModule/checkOutResponse")
    public Double checkOut(
        @WebParam(name = "orderId", targetNamespace = "")
        Long orderId,
        @WebParam(name = "received", targetNamespace = "")
        Double received);

    /**
     * 
     * @param orderId
     * @return
     *     returns java.lang.Long
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "cancelOrder", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.CancelOrder")
    @ResponseWrapper(localName = "cancelOrderResponse", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.CancelOrderResponse")
    @Action(input = "http://KM.SessionBean/CustomerOrderFulfillmentModule/cancelOrderRequest", output = "http://KM.SessionBean/CustomerOrderFulfillmentModule/cancelOrderResponse")
    public Long cancelOrder(
        @WebParam(name = "orderId", targetNamespace = "")
        Long orderId);

    /**
     * 
     * @param arg0
     * @return
     *     returns sessionbean.km.KitchenOrderEntity
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findOrderById", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.FindOrderById")
    @ResponseWrapper(localName = "findOrderByIdResponse", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.FindOrderByIdResponse")
    @Action(input = "http://KM.SessionBean/CustomerOrderFulfillmentModule/findOrderByIdRequest", output = "http://KM.SessionBean/CustomerOrderFulfillmentModule/findOrderByIdResponse")
    public KitchenOrderEntity findOrderById(
        @WebParam(name = "arg0", targetNamespace = "")
        Long arg0);

    /**
     * 
     * @param quantity
     * @param orderId
     * @param dishId
     * @return
     *     returns java.lang.Long
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addDishItem", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.AddDishItem")
    @ResponseWrapper(localName = "addDishItemResponse", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.AddDishItemResponse")
    @Action(input = "http://KM.SessionBean/CustomerOrderFulfillmentModule/addDishItemRequest", output = "http://KM.SessionBean/CustomerOrderFulfillmentModule/addDishItemResponse")
    public Long addDishItem(
        @WebParam(name = "orderId", targetNamespace = "")
        Long orderId,
        @WebParam(name = "dishId", targetNamespace = "")
        Long dishId,
        @WebParam(name = "quantity", targetNamespace = "")
        Integer quantity);

    /**
     * 
     * @param orderId
     * @param dishItemId
     * @return
     *     returns java.lang.Long
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteDishItem", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.DeleteDishItem")
    @ResponseWrapper(localName = "deleteDishItemResponse", targetNamespace = "http://KM.SessionBean/", className = "sessionbean.km.DeleteDishItemResponse")
    @Action(input = "http://KM.SessionBean/CustomerOrderFulfillmentModule/deleteDishItemRequest", output = "http://KM.SessionBean/CustomerOrderFulfillmentModule/deleteDishItemResponse")
    public Long deleteDishItem(
        @WebParam(name = "orderId", targetNamespace = "")
        Long orderId,
        @WebParam(name = "dishItemId", targetNamespace = "")
        Long dishItemId);

}
