
package sessionbean.km;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for kitchenOrderEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="kitchenOrderEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="totalWithDiscount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="received" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="due" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="totalDishItemQuantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fulfilledDishItemQuantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="creationTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="POSid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kitchen" type="{http://KM.SessionBean/}kitchenEntity" minOccurs="0"/>
 *         &lt;element name="member" type="{http://KM.SessionBean/}memberEntity" minOccurs="0"/>
 *         &lt;element name="storeStaff" type="{http://KM.SessionBean/}storeUserEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "kitchenOrderEntity", propOrder = {
    "id",
    "total",
    "totalWithDiscount",
    "received",
    "due",
    "totalDishItemQuantity",
    "fulfilledDishItemQuantity",
    "creationTime",
    "status",
    "poSid",
    "kitchen",
    "member",
    "storeStaff"
})
public class KitchenOrderEntity {

    protected Long id;
    protected Double total;
    protected Double totalWithDiscount;
    protected Double received;
    protected Double due;
    protected Integer totalDishItemQuantity;
    protected Integer fulfilledDishItemQuantity;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationTime;
    protected String status;
    @XmlElement(name = "POSid")
    protected String poSid;
    protected KitchenEntity kitchen;
    protected MemberEntity member;
    protected StoreUserEntity storeStaff;

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
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotal(Double value) {
        this.total = value;
    }

    /**
     * Gets the value of the totalWithDiscount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalWithDiscount() {
        return totalWithDiscount;
    }

    /**
     * Sets the value of the totalWithDiscount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalWithDiscount(Double value) {
        this.totalWithDiscount = value;
    }

    /**
     * Gets the value of the received property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getReceived() {
        return received;
    }

    /**
     * Sets the value of the received property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setReceived(Double value) {
        this.received = value;
    }

    /**
     * Gets the value of the due property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDue() {
        return due;
    }

    /**
     * Sets the value of the due property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDue(Double value) {
        this.due = value;
    }

    /**
     * Gets the value of the totalDishItemQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalDishItemQuantity() {
        return totalDishItemQuantity;
    }

    /**
     * Sets the value of the totalDishItemQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalDishItemQuantity(Integer value) {
        this.totalDishItemQuantity = value;
    }

    /**
     * Gets the value of the fulfilledDishItemQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFulfilledDishItemQuantity() {
        return fulfilledDishItemQuantity;
    }

    /**
     * Sets the value of the fulfilledDishItemQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFulfilledDishItemQuantity(Integer value) {
        this.fulfilledDishItemQuantity = value;
    }

    /**
     * Gets the value of the creationTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationTime() {
        return creationTime;
    }

    /**
     * Sets the value of the creationTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationTime(XMLGregorianCalendar value) {
        this.creationTime = value;
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
     * Gets the value of the poSid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOSid() {
        return poSid;
    }

    /**
     * Sets the value of the poSid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOSid(String value) {
        this.poSid = value;
    }

    /**
     * Gets the value of the kitchen property.
     * 
     * @return
     *     possible object is
     *     {@link KitchenEntity }
     *     
     */
    public KitchenEntity getKitchen() {
        return kitchen;
    }

    /**
     * Sets the value of the kitchen property.
     * 
     * @param value
     *     allowed object is
     *     {@link KitchenEntity }
     *     
     */
    public void setKitchen(KitchenEntity value) {
        this.kitchen = value;
    }

    /**
     * Gets the value of the member property.
     * 
     * @return
     *     possible object is
     *     {@link MemberEntity }
     *     
     */
    public MemberEntity getMember() {
        return member;
    }

    /**
     * Sets the value of the member property.
     * 
     * @param value
     *     allowed object is
     *     {@link MemberEntity }
     *     
     */
    public void setMember(MemberEntity value) {
        this.member = value;
    }

    /**
     * Gets the value of the storeStaff property.
     * 
     * @return
     *     possible object is
     *     {@link StoreUserEntity }
     *     
     */
    public StoreUserEntity getStoreStaff() {
        return storeStaff;
    }

    /**
     * Sets the value of the storeStaff property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreUserEntity }
     *     
     */
    public void setStoreStaff(StoreUserEntity value) {
        this.storeStaff = value;
    }

}
