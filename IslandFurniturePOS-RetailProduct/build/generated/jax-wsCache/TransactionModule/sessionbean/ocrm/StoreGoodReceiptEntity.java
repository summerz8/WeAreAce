
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for storeGoodReceiptEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="storeGoodReceiptEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creationTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="doe" type="{http://OCRM.SessionBean/}deliveryOrderEntity" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="inventoryType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ome" type="{http://OCRM.SessionBean/}outboundMovementEntity" minOccurs="0"/>
 *         &lt;element name="orginalAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="receivedAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="se" type="{http://OCRM.SessionBean/}storeEntity" minOccurs="0"/>
 *         &lt;element name="spe" type="{http://OCRM.SessionBean/}storeProductEntity" minOccurs="0"/>
 *         &lt;element name="srpe" type="{http://OCRM.SessionBean/}storeRetailProductEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "storeGoodReceiptEntity", propOrder = {
    "creationTime",
    "doe",
    "id",
    "inventoryType",
    "ome",
    "orginalAmount",
    "receivedAmount",
    "se",
    "spe",
    "srpe"
})
public class StoreGoodReceiptEntity {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationTime;
    protected DeliveryOrderEntity doe;
    protected Long id;
    protected Integer inventoryType;
    protected OutboundMovementEntity ome;
    protected Double orginalAmount;
    protected Double receivedAmount;
    protected StoreEntity se;
    protected StoreProductEntity spe;
    protected StoreRetailProductEntity srpe;

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
     * Gets the value of the doe property.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryOrderEntity }
     *     
     */
    public DeliveryOrderEntity getDoe() {
        return doe;
    }

    /**
     * Sets the value of the doe property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryOrderEntity }
     *     
     */
    public void setDoe(DeliveryOrderEntity value) {
        this.doe = value;
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
     * Gets the value of the inventoryType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getInventoryType() {
        return inventoryType;
    }

    /**
     * Sets the value of the inventoryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setInventoryType(Integer value) {
        this.inventoryType = value;
    }

    /**
     * Gets the value of the ome property.
     * 
     * @return
     *     possible object is
     *     {@link OutboundMovementEntity }
     *     
     */
    public OutboundMovementEntity getOme() {
        return ome;
    }

    /**
     * Sets the value of the ome property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutboundMovementEntity }
     *     
     */
    public void setOme(OutboundMovementEntity value) {
        this.ome = value;
    }

    /**
     * Gets the value of the orginalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOrginalAmount() {
        return orginalAmount;
    }

    /**
     * Sets the value of the orginalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOrginalAmount(Double value) {
        this.orginalAmount = value;
    }

    /**
     * Gets the value of the receivedAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getReceivedAmount() {
        return receivedAmount;
    }

    /**
     * Sets the value of the receivedAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setReceivedAmount(Double value) {
        this.receivedAmount = value;
    }

    /**
     * Gets the value of the se property.
     * 
     * @return
     *     possible object is
     *     {@link StoreEntity }
     *     
     */
    public StoreEntity getSe() {
        return se;
    }

    /**
     * Sets the value of the se property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreEntity }
     *     
     */
    public void setSe(StoreEntity value) {
        this.se = value;
    }

    /**
     * Gets the value of the spe property.
     * 
     * @return
     *     possible object is
     *     {@link StoreProductEntity }
     *     
     */
    public StoreProductEntity getSpe() {
        return spe;
    }

    /**
     * Sets the value of the spe property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreProductEntity }
     *     
     */
    public void setSpe(StoreProductEntity value) {
        this.spe = value;
    }

    /**
     * Gets the value of the srpe property.
     * 
     * @return
     *     possible object is
     *     {@link StoreRetailProductEntity }
     *     
     */
    public StoreRetailProductEntity getSrpe() {
        return srpe;
    }

    /**
     * Sets the value of the srpe property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreRetailProductEntity }
     *     
     */
    public void setSrpe(StoreRetailProductEntity value) {
        this.srpe = value;
    }

}
