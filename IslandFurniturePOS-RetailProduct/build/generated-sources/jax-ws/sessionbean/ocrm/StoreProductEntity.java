
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for storeProductEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="storeProductEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="storeProductId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minimumInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="unrestrictedInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="returnedInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="selfPick" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="deleteFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="storeRemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intransitInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="factoryProduct" type="{http://OCRM.SessionBean/}factoryProductEntity" minOccurs="0"/>
 *         &lt;element name="store" type="{http://OCRM.SessionBean/}storeEntity" minOccurs="0"/>
 *         &lt;element name="product" type="{http://OCRM.SessionBean/}productEntity" minOccurs="0"/>
 *         &lt;element name="onairInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="warningOnAirInv" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "storeProductEntity", propOrder = {
    "storeProductId",
    "name",
    "unit",
    "minimumInventory",
    "unrestrictedInventory",
    "returnedInventory",
    "selfPick",
    "deleteFlag",
    "storeRemark",
    "intransitInventory",
    "factoryProduct",
    "store",
    "product",
    "onairInventory",
    "warningOnAirInv"
})
public class StoreProductEntity {

    protected Long storeProductId;
    protected String name;
    protected String unit;
    protected Double minimumInventory;
    protected Double unrestrictedInventory;
    protected Double returnedInventory;
    protected Boolean selfPick;
    protected Boolean deleteFlag;
    protected String storeRemark;
    protected Double intransitInventory;
    protected FactoryProductEntity factoryProduct;
    protected StoreEntity store;
    protected ProductEntity product;
    protected Double onairInventory;
    protected Double warningOnAirInv;

    /**
     * Gets the value of the storeProductId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStoreProductId() {
        return storeProductId;
    }

    /**
     * Sets the value of the storeProductId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStoreProductId(Long value) {
        this.storeProductId = value;
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
     * Gets the value of the selfPick property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSelfPick() {
        return selfPick;
    }

    /**
     * Sets the value of the selfPick property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSelfPick(Boolean value) {
        this.selfPick = value;
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
     * Gets the value of the intransitInventory property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getIntransitInventory() {
        return intransitInventory;
    }

    /**
     * Sets the value of the intransitInventory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setIntransitInventory(Double value) {
        this.intransitInventory = value;
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
     * Gets the value of the onairInventory property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOnairInventory() {
        return onairInventory;
    }

    /**
     * Sets the value of the onairInventory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOnairInventory(Double value) {
        this.onairInventory = value;
    }

    /**
     * Gets the value of the warningOnAirInv property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getWarningOnAirInv() {
        return warningOnAirInv;
    }

    /**
     * Sets the value of the warningOnAirInv property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setWarningOnAirInv(Double value) {
        this.warningOnAirInv = value;
    }

}
