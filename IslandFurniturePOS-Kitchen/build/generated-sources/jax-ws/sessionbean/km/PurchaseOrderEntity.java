
package sessionbean.km;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for purchaseOrderEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="purchaseOrderEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contract" type="{http://KM.SessionBean/}contractEntity" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="deliveryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="deliveryOrderList" type="{http://KM.SessionBean/}deliveryOrderEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="destination" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destinationId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="factory" type="{http://KM.SessionBean/}factoryEntity" minOccurs="0"/>
 *         &lt;element name="goodsReceiptList" type="{http://KM.SessionBean/}goodsReceiptEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="integratedPlannedOrder" type="{http://KM.SessionBean/}integratedPlannedOrderEntity" minOccurs="0"/>
 *         &lt;element name="isManual" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="isToStore" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "purchaseOrderEntity", propOrder = {
    "contract",
    "createDate",
    "deliveryDate",
    "deliveryOrderList",
    "destination",
    "destinationId",
    "factory",
    "goodsReceiptList",
    "id",
    "integratedPlannedOrder",
    "isManual",
    "isToStore",
    "itemName",
    "leadTime",
    "status",
    "totalAmount",
    "totalPrice",
    "unit"
})
public class PurchaseOrderEntity {

    protected ContractEntity contract;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deliveryDate;
    @XmlElement(nillable = true)
    protected List<DeliveryOrderEntity> deliveryOrderList;
    protected String destination;
    protected Long destinationId;
    protected FactoryEntity factory;
    @XmlElement(nillable = true)
    protected List<GoodsReceiptEntity> goodsReceiptList;
    protected Long id;
    protected IntegratedPlannedOrderEntity integratedPlannedOrder;
    protected Boolean isManual;
    protected Boolean isToStore;
    protected String itemName;
    protected Integer leadTime;
    protected String status;
    protected Double totalAmount;
    protected Double totalPrice;
    protected String unit;

    /**
     * Gets the value of the contract property.
     * 
     * @return
     *     possible object is
     *     {@link ContractEntity }
     *     
     */
    public ContractEntity getContract() {
        return contract;
    }

    /**
     * Sets the value of the contract property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractEntity }
     *     
     */
    public void setContract(ContractEntity value) {
        this.contract = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the deliveryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Sets the value of the deliveryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeliveryDate(XMLGregorianCalendar value) {
        this.deliveryDate = value;
    }

    /**
     * Gets the value of the deliveryOrderList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryOrderList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryOrderList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeliveryOrderEntity }
     * 
     * 
     */
    public List<DeliveryOrderEntity> getDeliveryOrderList() {
        if (deliveryOrderList == null) {
            deliveryOrderList = new ArrayList<DeliveryOrderEntity>();
        }
        return this.deliveryOrderList;
    }

    /**
     * Gets the value of the destination property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the value of the destination property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestination(String value) {
        this.destination = value;
    }

    /**
     * Gets the value of the destinationId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDestinationId() {
        return destinationId;
    }

    /**
     * Sets the value of the destinationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDestinationId(Long value) {
        this.destinationId = value;
    }

    /**
     * Gets the value of the factory property.
     * 
     * @return
     *     possible object is
     *     {@link FactoryEntity }
     *     
     */
    public FactoryEntity getFactory() {
        return factory;
    }

    /**
     * Sets the value of the factory property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactoryEntity }
     *     
     */
    public void setFactory(FactoryEntity value) {
        this.factory = value;
    }

    /**
     * Gets the value of the goodsReceiptList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the goodsReceiptList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGoodsReceiptList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GoodsReceiptEntity }
     * 
     * 
     */
    public List<GoodsReceiptEntity> getGoodsReceiptList() {
        if (goodsReceiptList == null) {
            goodsReceiptList = new ArrayList<GoodsReceiptEntity>();
        }
        return this.goodsReceiptList;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the integratedPlannedOrder property.
     * 
     * @return
     *     possible object is
     *     {@link IntegratedPlannedOrderEntity }
     *     
     */
    public IntegratedPlannedOrderEntity getIntegratedPlannedOrder() {
        return integratedPlannedOrder;
    }

    /**
     * Sets the value of the integratedPlannedOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegratedPlannedOrderEntity }
     *     
     */
    public void setIntegratedPlannedOrder(IntegratedPlannedOrderEntity value) {
        this.integratedPlannedOrder = value;
    }

    /**
     * Gets the value of the isManual property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsManual() {
        return isManual;
    }

    /**
     * Sets the value of the isManual property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsManual(Boolean value) {
        this.isManual = value;
    }

    /**
     * Gets the value of the isToStore property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsToStore() {
        return isToStore;
    }

    /**
     * Sets the value of the isToStore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsToStore(Boolean value) {
        this.isToStore = value;
    }

    /**
     * Gets the value of the itemName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the value of the itemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemName(String value) {
        this.itemName = value;
    }

    /**
     * Gets the value of the leadTime property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLeadTime() {
        return leadTime;
    }

    /**
     * Sets the value of the leadTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLeadTime(Integer value) {
        this.leadTime = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the totalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the value of the totalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalAmount(Double value) {
        this.totalAmount = value;
    }

    /**
     * Gets the value of the totalPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the value of the totalPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalPrice(Double value) {
        this.totalPrice = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

}
