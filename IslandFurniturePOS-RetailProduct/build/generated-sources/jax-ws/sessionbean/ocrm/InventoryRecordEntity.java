
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for inventoryRecordEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inventoryRecordEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="factoryProduct" type="{http://OCRM.SessionBean/}factoryProductEntity" minOccurs="0"/>
 *         &lt;element name="factoryRawMaterial" type="{http://OCRM.SessionBean/}factoryRawMaterialEntity" minOccurs="0"/>
 *         &lt;element name="factoryRetailProduct" type="{http://OCRM.SessionBean/}factoryRetailProductEntity" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="recordDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inventoryRecordEntity", propOrder = {
    "amount",
    "factoryProduct",
    "factoryRawMaterial",
    "factoryRetailProduct",
    "id",
    "recordDate"
})
public class InventoryRecordEntity {

    protected Double amount;
    protected FactoryProductEntity factoryProduct;
    protected FactoryRawMaterialEntity factoryRawMaterial;
    protected FactoryRetailProductEntity factoryRetailProduct;
    protected Long id;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar recordDate;

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
     * Gets the value of the recordDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRecordDate() {
        return recordDate;
    }

    /**
     * Sets the value of the recordDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRecordDate(XMLGregorianCalendar value) {
        this.recordDate = value;
    }

}
