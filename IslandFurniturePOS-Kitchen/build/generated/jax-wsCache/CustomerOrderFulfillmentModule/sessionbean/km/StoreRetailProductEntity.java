
package sessionbean.km;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for storeRetailProductEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="storeRetailProductEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="storeRetailProductId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unrestrictedInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="minimumInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="returnedInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="storeRemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deleteFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="factoryRetailProduct" type="{http://KM.SessionBean/}factoryRetailProductEntity" minOccurs="0"/>
 *         &lt;element name="store" type="{http://KM.SessionBean/}storeEntity" minOccurs="0"/>
 *         &lt;element name="retailProduct" type="{http://KM.SessionBean/}retailProductEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "storeRetailProductEntity", propOrder = {
    "storeRetailProductId",
    "name",
    "unrestrictedInventory",
    "minimumInventory",
    "returnedInventory",
    "storeRemark",
    "unit",
    "deleteFlag",
    "factoryRetailProduct",
    "store",
    "retailProduct"
})
public class StoreRetailProductEntity {

    protected Long storeRetailProductId;
    @XmlElement(name = "Name")
    protected String name;
    protected Double unrestrictedInventory;
    protected Double minimumInventory;
    protected Double returnedInventory;
    protected String storeRemark;
    protected String unit;
    protected Boolean deleteFlag;
    protected FactoryRetailProductEntity factoryRetailProduct;
    protected StoreEntity store;
    protected RetailProductEntity retailProduct;

    /**
     * Gets the value of the storeRetailProductId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStoreRetailProductId() {
        return storeRetailProductId;
    }

    /**
     * Sets the value of the storeRetailProductId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStoreRetailProductId(Long value) {
        this.storeRetailProductId = value;
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
     * Gets the value of the storeRemark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreRemark() {
        return storeRemark;
    }

    /**
     * Sets the value of the storeRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreRemark(String value) {
        this.storeRemark = value;
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
     * Gets the value of the retailProduct property.
     * 
     * @return
     *     possible object is
     *     {@link RetailProductEntity }
     *     
     */
    public RetailProductEntity getRetailProduct() {
        return retailProduct;
    }

    /**
     * Sets the value of the retailProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link RetailProductEntity }
     *     
     */
    public void setRetailProduct(RetailProductEntity value) {
        this.retailProduct = value;
    }

}
