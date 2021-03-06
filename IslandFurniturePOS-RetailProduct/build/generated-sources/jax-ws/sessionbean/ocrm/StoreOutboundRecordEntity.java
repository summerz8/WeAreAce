
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for storeOutboundRecordEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="storeOutboundRecordEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="creationTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fromBin" type="{http://OCRM.SessionBean/}storeWarehouseBinEntity" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="isReceivedByFactory" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="settledQuantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="store" type="{http://OCRM.SessionBean/}storeEntity" minOccurs="0"/>
 *         &lt;element name="storeProduct" type="{http://OCRM.SessionBean/}storeProductEntity" minOccurs="0"/>
 *         &lt;element name="storeRetailProduct" type="{http://OCRM.SessionBean/}storeRetailProductEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "storeOutboundRecordEntity", propOrder = {
    "amount",
    "creationTime",
    "fromBin",
    "id",
    "isReceivedByFactory",
    "settledQuantity",
    "store",
    "storeProduct",
    "storeRetailProduct"
})
public class StoreOutboundRecordEntity {

    protected Double amount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationTime;
    protected StoreWarehouseBinEntity fromBin;
    protected Long id;
    protected Boolean isReceivedByFactory;
    protected Double settledQuantity;
    protected StoreEntity store;
    protected StoreProductEntity storeProduct;
    protected StoreRetailProductEntity storeRetailProduct;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmount(Double value) {
        this.amount = value;
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
     * Gets the value of the fromBin property.
     * 
     * @return
     *     possible object is
     *     {@link StoreWarehouseBinEntity }
     *     
     */
    public StoreWarehouseBinEntity getFromBin() {
        return fromBin;
    }

    /**
     * Sets the value of the fromBin property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreWarehouseBinEntity }
     *     
     */
    public void setFromBin(StoreWarehouseBinEntity value) {
        this.fromBin = value;
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
     * Gets the value of the isReceivedByFactory property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsReceivedByFactory() {
        return isReceivedByFactory;
    }

    /**
     * Sets the value of the isReceivedByFactory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsReceivedByFactory(Boolean value) {
        this.isReceivedByFactory = value;
    }

    /**
     * Gets the value of the settledQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSettledQuantity() {
        return settledQuantity;
    }

    /**
     * Sets the value of the settledQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSettledQuantity(Double value) {
        this.settledQuantity = value;
    }

    /**
     * Gets the value of the store property.
     * 
     * @return
     *     possible object is
     *     {@link StoreEntity }
     *     
     */
    public StoreEntity getStore() {
        return store;
    }

    /**
     * Sets the value of the store property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreEntity }
     *     
     */
    public void setStore(StoreEntity value) {
        this.store = value;
    }

    /**
     * Gets the value of the storeProduct property.
     * 
     * @return
     *     possible object is
     *     {@link StoreProductEntity }
     *     
     */
    public StoreProductEntity getStoreProduct() {
        return storeProduct;
    }

    /**
     * Sets the value of the storeProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreProductEntity }
     *     
     */
    public void setStoreProduct(StoreProductEntity value) {
        this.storeProduct = value;
    }

    /**
     * Gets the value of the storeRetailProduct property.
     * 
     * @return
     *     possible object is
     *     {@link StoreRetailProductEntity }
     *     
     */
    public StoreRetailProductEntity getStoreRetailProduct() {
        return storeRetailProduct;
    }

    /**
     * Sets the value of the storeRetailProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreRetailProductEntity }
     *     
     */
    public void setStoreRetailProduct(StoreRetailProductEntity value) {
        this.storeRetailProduct = value;
    }

}
