
package sessionbean.km;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for factoryRawMaterialAmountEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="factoryRawMaterialAmountEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="factoryRawMaterial" type="{http://KM.SessionBean/}factoryRawMaterialEntity" minOccurs="0"/>
 *         &lt;element name="rawMaterialAmountId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
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
@XmlType(name = "factoryRawMaterialAmountEntity", propOrder = {
    "amount",
    "factoryRawMaterial",
    "rawMaterialAmountId",
    "unit"
})
public class FactoryRawMaterialAmountEntity {

    protected Double amount;
    protected FactoryRawMaterialEntity factoryRawMaterial;
    protected Long rawMaterialAmountId;
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
     * Gets the value of the rawMaterialAmountId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRawMaterialAmountId() {
        return rawMaterialAmountId;
    }

    /**
     * Sets the value of the rawMaterialAmountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRawMaterialAmountId(Long value) {
        this.rawMaterialAmountId = value;
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
