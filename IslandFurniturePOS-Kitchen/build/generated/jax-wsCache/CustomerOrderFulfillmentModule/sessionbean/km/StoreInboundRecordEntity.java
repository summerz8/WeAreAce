
package sessionbean.km;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for storeInboundRecordEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="storeInboundRecordEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="creationTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="storeProduct" type="{http://KM.SessionBean/}storeProductEntity" minOccurs="0"/>
 *         &lt;element name="storeRetailProduct" type="{http://KM.SessionBean/}storeRetailProductEntity" minOccurs="0"/>
 *         &lt;element name="toBin" type="{http://KM.SessionBean/}storeWarehouseBinEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "storeInboundRecordEntity", propOrder = {
    "amount",
    "creationTime",
    "id",
    "storeProduct",
    "storeRetailProduct",
    "toBin"
})
public class StoreInboundRecordEntity {

    protected Double amount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationTime;
    protected Long id;
    protected StoreProductEntity storeProduct;
    protected StoreRetailProductEntity storeRetailProduct;
    protected StoreWarehouseBinEntity toBin;

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

    /**
     * Gets the value of the toBin property.
     * 
     * @return
     *     possible object is
     *     {@link StoreWarehouseBinEntity }
     *     
     */
    public StoreWarehouseBinEntity getToBin() {
        return toBin;
    }

    /**
     * Sets the value of the toBin property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreWarehouseBinEntity }
     *     
     */
    public void setToBin(StoreWarehouseBinEntity value) {
        this.toBin = value;
    }

}
