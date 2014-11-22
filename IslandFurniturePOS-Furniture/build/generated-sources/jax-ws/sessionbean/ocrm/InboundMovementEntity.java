
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for inboundMovementEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inboundMovementEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="factoryRawMaterial" type="{http://OCRM.SessionBean/}factoryRawMaterialEntity" minOccurs="0"/>
 *         &lt;element name="factoryRetailProduct" type="{http://OCRM.SessionBean/}factoryRetailProductEntity" minOccurs="0"/>
 *         &lt;element name="fromGoodsRecipt" type="{http://OCRM.SessionBean/}goodsReceiptEntity" minOccurs="0"/>
 *         &lt;element name="inboundMovementId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stockTypeIndicator" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="toBin" type="{http://OCRM.SessionBean/}factoryBinEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inboundMovementEntity", propOrder = {
    "creationDate",
    "factoryRawMaterial",
    "factoryRetailProduct",
    "fromGoodsRecipt",
    "inboundMovementId",
    "quantity",
    "status",
    "stockTypeIndicator",
    "toBin"
})
public class InboundMovementEntity {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationDate;
    protected FactoryRawMaterialEntity factoryRawMaterial;
    protected FactoryRetailProductEntity factoryRetailProduct;
    protected GoodsReceiptEntity fromGoodsRecipt;
    protected Long inboundMovementId;
    protected Double quantity;
    protected String status;
    protected int stockTypeIndicator;
    protected FactoryBinEntity toBin;

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
     * Gets the value of the factoryRawMaterial property.
     * 
     * @return
     *     possible object is
     *     {@link FactoryRawMaterialEntity }
     *     
     */
    public FactoryRawMaterialEntity getFactoryRawMaterial() {
        return factoryRawMaterial;
    }

    /**
     * Sets the value of the factoryRawMaterial property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactoryRawMaterialEntity }
     *     
     */
    public void setFactoryRawMaterial(FactoryRawMaterialEntity value) {
        this.factoryRawMaterial = value;
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
     * Gets the value of the fromGoodsRecipt property.
     * 
     * @return
     *     possible object is
     *     {@link GoodsReceiptEntity }
     *     
     */
    public GoodsReceiptEntity getFromGoodsRecipt() {
        return fromGoodsRecipt;
    }

    /**
     * Sets the value of the fromGoodsRecipt property.
     * 
     * @param value
     *     allowed object is
     *     {@link GoodsReceiptEntity }
     *     
     */
    public void setFromGoodsRecipt(GoodsReceiptEntity value) {
        this.fromGoodsRecipt = value;
    }

    /**
     * Gets the value of the inboundMovementId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInboundMovementId() {
        return inboundMovementId;
    }

    /**
     * Sets the value of the inboundMovementId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInboundMovementId(Long value) {
        this.inboundMovementId = value;
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
     * Gets the value of the stockTypeIndicator property.
     * 
     */
    public int getStockTypeIndicator() {
        return stockTypeIndicator;
    }

    /**
     * Sets the value of the stockTypeIndicator property.
     * 
     */
    public void setStockTypeIndicator(int value) {
        this.stockTypeIndicator = value;
    }

    /**
     * Gets the value of the toBin property.
     * 
     * @return
     *     possible object is
     *     {@link FactoryBinEntity }
     *     
     */
    public FactoryBinEntity getToBin() {
        return toBin;
    }

    /**
     * Sets the value of the toBin property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactoryBinEntity }
     *     
     */
    public void setToBin(FactoryBinEntity value) {
        this.toBin = value;
    }

}
