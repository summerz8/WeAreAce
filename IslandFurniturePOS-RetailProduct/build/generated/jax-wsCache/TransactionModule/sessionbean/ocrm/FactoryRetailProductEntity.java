
package sessionbean.ocrm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for factoryRetailProductEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="factoryRetailProductEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="blockedInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="contracts" type="{http://OCRM.SessionBean/}contractEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="factory" type="{http://OCRM.SessionBean/}factoryEntity" minOccurs="0"/>
 *         &lt;element name="factoryBinStoredProducts" type="{http://OCRM.SessionBean/}factoryBinStoredProductEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="factoryRetailProdctId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="inventoryRecords" type="{http://OCRM.SessionBean/}inventoryRecordEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="isDeleted" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="minimumInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="retailProduct" type="{http://OCRM.SessionBean/}retailProductEntity" minOccurs="0"/>
 *         &lt;element name="returnedInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="storeRetailProducts" type="{http://OCRM.SessionBean/}storeRetailProductEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unrestrictedInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "factoryRetailProductEntity", propOrder = {
    "blockedInventory",
    "contracts",
    "description",
    "factory",
    "factoryBinStoredProducts",
    "factoryRetailProdctId",
    "inventoryRecords",
    "isDeleted",
    "minimumInventory",
    "name",
    "retailProduct",
    "returnedInventory",
    "storeRetailProducts",
    "unit",
    "unrestrictedInventory"
})
public class FactoryRetailProductEntity {

    protected Double blockedInventory;
    @XmlElement(nillable = true)
    protected List<ContractEntity> contracts;
    protected String description;
    protected FactoryEntity factory;
    @XmlElement(nillable = true)
    protected List<FactoryBinStoredProductEntity> factoryBinStoredProducts;
    protected Long factoryRetailProdctId;
    @XmlElement(nillable = true)
    protected List<InventoryRecordEntity> inventoryRecords;
    protected Boolean isDeleted;
    protected Double minimumInventory;
    protected String name;
    protected RetailProductEntity retailProduct;
    protected Double returnedInventory;
    @XmlElement(nillable = true)
    protected List<StoreRetailProductEntity> storeRetailProducts;
    protected String unit;
    protected Double unrestrictedInventory;

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
     * Gets the value of the contracts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contracts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContracts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContractEntity }
     * 
     * 
     */
    public List<ContractEntity> getContracts() {
        if (contracts == null) {
            contracts = new ArrayList<ContractEntity>();
        }
        return this.contracts;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
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
     * Gets the value of the factoryRetailProdctId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFactoryRetailProdctId() {
        return factoryRetailProdctId;
    }

    /**
     * Sets the value of the factoryRetailProdctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFactoryRetailProdctId(Long value) {
        this.factoryRetailProdctId = value;
    }

    /**
     * Gets the value of the inventoryRecords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inventoryRecords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInventoryRecords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InventoryRecordEntity }
     * 
     * 
     */
    public List<InventoryRecordEntity> getInventoryRecords() {
        if (inventoryRecords == null) {
            inventoryRecords = new ArrayList<InventoryRecordEntity>();
        }
        return this.inventoryRecords;
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
     * Gets the value of the storeRetailProducts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the storeRetailProducts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStoreRetailProducts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StoreRetailProductEntity }
     * 
     * 
     */
    public List<StoreRetailProductEntity> getStoreRetailProducts() {
        if (storeRetailProducts == null) {
            storeRetailProducts = new ArrayList<StoreRetailProductEntity>();
        }
        return this.storeRetailProducts;
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

}
