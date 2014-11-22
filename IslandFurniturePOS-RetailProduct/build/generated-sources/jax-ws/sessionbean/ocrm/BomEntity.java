
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for bomEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bomEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="BOMId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="isDeleted" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="product" type="{http://OCRM.SessionBean/}productEntity" minOccurs="0"/>
 *         &lt;element name="rawMaterial" type="{http://OCRM.SessionBean/}rawMaterialEntity" minOccurs="0"/>
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
@XmlType(name = "bomEntity", propOrder = {
    "amount",
    "bomId",
    "isDeleted",
    "product",
    "rawMaterial",
    "unit"
})
public class BomEntity {

    protected Double amount;
    @XmlElement(name = "BOMId")
    protected Long bomId;
    protected Boolean isDeleted;
    protected ProductEntity product;
    protected RawMaterialEntity rawMaterial;
    protected String unit;

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
     * Gets the value of the bomId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBOMId() {
        return bomId;
    }

    /**
     * Sets the value of the bomId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBOMId(Long value) {
        this.bomId = value;
    }

    /**
     * Gets the value of the isDeleted property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsDeleted() {
        return isDeleted;
    }

    /**
     * Sets the value of the isDeleted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDeleted(Boolean value) {
        this.isDeleted = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link ProductEntity }
     *     
     */
    public ProductEntity getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductEntity }
     *     
     */
    public void setProduct(ProductEntity value) {
        this.product = value;
    }

    /**
     * Gets the value of the rawMaterial property.
     * 
     * @return
     *     possible object is
     *     {@link RawMaterialEntity }
     *     
     */
    public RawMaterialEntity getRawMaterial() {
        return rawMaterial;
    }

    /**
     * Sets the value of the rawMaterial property.
     * 
     * @param value
     *     allowed object is
     *     {@link RawMaterialEntity }
     *     
     */
    public void setRawMaterial(RawMaterialEntity value) {
        this.rawMaterial = value;
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
