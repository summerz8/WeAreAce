
package sessionbean.km;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for factoryRawMaterialEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="factoryRawMaterialEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="blockedInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="contracts" type="{http://KM.SessionBean/}contractEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="factory" type="{http://KM.SessionBean/}factoryEntity" minOccurs="0"/>
 *         &lt;element name="factoryBinStoredProducts" type="{http://KM.SessionBean/}factoryBinStoredProductEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="factoryRawMaterialId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="inventoryRecord" type="{http://KM.SessionBean/}inventoryRecordEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="isDeleted" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="materialName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minimumInventory" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="rawMaterial" type="{http://KM.SessionBean/}rawMaterialEntity" minOccurs="0"/>
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
@XmlType(name = "factoryRawMaterialEntity", propOrder = {
    "blockedInventory",
    "contracts",
    "description",
    "factory",
    "factoryBinStoredProducts",
    "factoryRawMaterialId",
    "inventoryRecord",
    "isDeleted",
    "materialName",
    "minimumInventory",
    "rawMaterial",
    "unit",
    "unrestrictedInventory"
})
public class FactoryRawMaterialEntity {

    protected Double blockedInventory;
    @XmlElement(nillable = true)
    protected List<ContractEntity> contracts;
    protected String description;
    protected FactoryEntity factory;
    @XmlElement(nillable = true)
    protected List<FactoryBinStoredProductEntity> factoryBinStoredProducts;
    protected Long factoryRawMaterialId;
    @XmlElement(nillable = true)
    protected List<InventoryRecordEntity> inventoryRecord;
    protected Boolean isDeleted;
    protected String materialName;
    protected Double minimumInventory;
    protected RawMaterialEntity rawMaterial;
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
     * Gets the value of the factoryRawMaterialId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFactoryRawMaterialId() {
        return factoryRawMaterialId;
    }

    /**
     * Sets the value of the factoryRawMaterialId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFactoryRawMaterialId(Long value) {
        this.factoryRawMaterialId = value;
    }

    /**
     * Gets the value of the inventoryRecord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inventoryRecord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInventoryRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InventoryRecordEntity }
     * 
     * 
     */
    public List<InventoryRecordEntity> getInventoryRecord() {
        if (inventoryRecord == null) {
            inventoryRecord = new ArrayList<InventoryRecordEntity>();
        }
        return this.inventoryRecord;
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
     * Gets the value of the materialName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * Sets the value of the materialName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterialName(String value) {
        this.materialName = value;
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
