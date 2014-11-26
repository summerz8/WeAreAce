
package sessionbean.km;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for factoryRetailProductAmountEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="factoryRetailProductAmountEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="factoryRetailProduct" type="{http://KM.SessionBean/}factoryRetailProductEntity" minOccurs="0"/>
 *         &lt;element name="factoryRetailProductAmountId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
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
@XmlType(name = "factoryRetailProductAmountEntity", propOrder = {
    "amount",
    "factoryRetailProduct",
    "factoryRetailProductAmountId",
    "unit"
})
public class FactoryRetailProductAmountEntity {

    protected Double amount;
    protected FactoryRetailProductEntity factoryRetailProduct;
    protected Long factoryRetailProductAmountId;
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
     * Gets the value of the factoryRetailProductAmountId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFactoryRetailProductAmountId() {
        return factoryRetailProductAmountId;
    }

    /**
     * Sets the value of the factoryRetailProductAmountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFactoryRetailProductAmountId(Long value) {
        this.factoryRetailProductAmountId = value;
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
