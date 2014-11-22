
package sessionbean.ocrm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for factoryProductEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="factoryProductEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="factoryProductId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="unrestrictedInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="blockedInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="returnedInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minimumInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="deleteFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="factory" type="{http://OCRM.SessionBean/}factoryEntity" minOccurs="0"/>
 *         &lt;element name="factoryBinStoredProducts" type="{http://OCRM.SessionBean/}factoryBinStoredProductEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="product" type="{http://OCRM.SessionBean/}productEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "factoryProductEntity", propOrder = {
    "factoryProductId",
    "unrestrictedInventory",
    "blockedInventory",
    "returnedInventory",
    "name",
    "unit",
    "minimumInventory",
    "deleteFlag",
    "factory",
    "factoryBinStoredProducts",
    "product"
})
public class FactoryProductEntity {

    protected Long factoryProductId;
    protected Double unrestrictedInventory;
    protected Double blockedInventory;
    protected Double returnedInventory;
    protected String name;
    protected String unit;
    protected Double minimumInventory;
    protected Boolean deleteFlag;
    protected FactoryEntity factory;
    @XmlElement(nillable = true)
    protected List<FactoryBinStoredProductEntity> factoryBinStoredProducts;
    protected ProductEntity product;

    /**
     * Gets the value of the factoryProductId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFactoryProductId() {
        return factoryProductId;
    }

    /**
     * Sets the value of the factoryProductId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFactoryProductId(Long value) {
        this.factoryProductId = value;
    }

    /**
     * Gets the value of the unrestrictedInventory property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getUnrestrictedInventory() {
        return unrestrictedInventory;
    }

    /**
     * Sets the value of the unrestrictedInventory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setUnrestrictedInventory(Double value) {
        this.unrestrictedInventory = value;
    }

    /**
     * Gets the value of the blockedInventory property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBlockedInventory() {
        return blockedInventory;
    }

    /**
     * Sets the value of the blockedInventory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBlockedInventory(Double value) {
        this.blockedInventory = value;
    }

    /**
     * Gets the value of the returnedInventory property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getReturnedInventory() {
        return returnedInventory;
    }

    /**
     * Sets the value of the returnedInventory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setReturnedInventory(Double value) {
        this.returnedInventory = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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

    /**
     * Gets the value of the minimumInventory property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinimumInventory() {
        return minimumInventory;
    }

    /**
     * Sets the value of the minimumInventory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinimumInventory(Double value) {
        this.minimumInventory = value;
    }

    /**
     * Gets the value of the deleteFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDeleteFlag() {
        return deleteFlag;
    }

    /**
     * Sets the value of the deleteFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDeleteFlag(Boolean value) {
        this.deleteFlag = value;
    }

    /**
     * Gets the value of the factory property.
     * 
     * @return
     *     possible object is
     *     {@link FactoryEntity }
     *     
     */
    public FactoryEntity getFactory() {
        return factory;
    }

    /**
     * Sets the value of the factory property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactoryEntity }
     *     
     */
    public void setFactory(FactoryEntity value) {
        this.factory = value;
    }

    /**
     * Gets the value of the factoryBinStoredProducts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the factoryBinStoredProducts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFactoryBinStoredProducts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FactoryBinStoredProductEntity }
     * 
     * 
     */
    public List<FactoryBinStoredProductEntity> getFactoryBinStoredProducts() {
        if (factoryBinStoredProducts == null) {
            factoryBinStoredProducts = new ArrayList<FactoryBinStoredProductEntity>();
        }
        return this.factoryBinStoredProducts;
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

}
