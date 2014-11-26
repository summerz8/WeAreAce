
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for outboundMovementEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="outboundMovementEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="factoryProduct" type="{http://OCRM.SessionBean/}factoryProductEntity" minOccurs="0"/>
 *         &lt;element name="factoryRetailProduct" type="{http://OCRM.SessionBean/}factoryRetailProductEntity" minOccurs="0"/>
 *         &lt;element name="fromBin" type="{http://OCRM.SessionBean/}factoryBinEntity" minOccurs="0"/>
 *         &lt;element name="outboundMovementId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="receivedByStore" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="stockTypeIndicator" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="toStore" type="{http://OCRM.SessionBean/}storeEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "outboundMovementEntity", propOrder = {
    "creationDate",
    "factoryProduct",
    "factoryRetailProduct",
    "fromBin",
    "outboundMovementId",
    "quantity",
    "receivedByStore",
    "stockTypeIndicator",
    "toStore"
})
public class OutboundMovementEntity {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;
    protected FactoryProductEntity factoryProduct;
    protected FactoryRetailProductEntity factoryRetailProduct;
    protected FactoryBinEntity fromBin;
    protected Long outboundMovementId;
    protected Double quantity;
    protected Boolean receivedByStore;
    protected Integer stockTypeIndicator;
    protected StoreEntity toStore;

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the factoryProduct property.
     * 
     * @return
     *     possible object is
     *     {@link FactoryProductEntity }
     *     
     */
    public FactoryProductEntity getFactoryProduct() {
        return factoryProduct;
    }

    /**
     * Sets the value of the factoryProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactoryProductEntity }
     *     
     */
    public void setFactoryProduct(FactoryProductEntity value) {
        this.factoryProduct = value;
    }

    /**
     * Gets the value of the factoryRetailProduct property.
     * 
     * @return
     *     possible object is
     *     {@link FactoryRetailProductEntity }
     *     
     */
    public FactoryRetailProductEntity getFactoryRetailProduct() {
        return factoryRetailProduct;
    }

    /**
     * Sets the value of the factoryRetailProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactoryRetailProductEntity }
     *     
     */
    public void setFactoryRetailProduct(FactoryRetailProductEntity value) {
        this.factoryRetailProduct = value;
    }

    /**
     * Gets the value of the fromBin property.
     * 
     * @return
     *     possible object is
     *     {@link FactoryBinEntity }
     *     
     */
    public FactoryBinEntity getFromBin() {
        return fromBin;
    }

    /**
     * Sets the value of the fromBin property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactoryBinEntity }
     *     
     */
    public void setFromBin(FactoryBinEntity value) {
        this.fromBin = value;
    }

    /**
     * Gets the value of the outboundMovementId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOutboundMovementId() {
        return outboundMovementId;
    }

    /**
     * Sets the value of the outboundMovementId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOutboundMovementId(Long value) {
        this.outboundMovementId = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantity(Double value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the receivedByStore property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReceivedByStore() {
        return receivedByStore;
    }

    /**
     * Sets the value of the receivedByStore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReceivedByStore(Boolean value) {
        this.receivedByStore = value;
    }

    /**
     * Gets the value of the stockTypeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStockTypeIndicator() {
        return stockTypeIndicator;
    }

    /**
     * Sets the value of the stockTypeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStockTypeIndicator(Integer value) {
        this.stockTypeIndicator = value;
    }

    /**
     * Gets the value of the toStore property.
     * 
     * @return
     *     possible object is
     *     {@link StoreEntity }
     *     
     */
    public StoreEntity getToStore() {
        return toStore;
    }

    /**
     * Sets the value of the toStore property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreEntity }
     *     
     */
    public void setToStore(StoreEntity value) {
        this.toStore = value;
    }

}
