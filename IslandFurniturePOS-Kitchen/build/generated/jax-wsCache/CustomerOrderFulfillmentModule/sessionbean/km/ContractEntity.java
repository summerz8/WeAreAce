
package sessionbean.km;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for contractEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="contractEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contractEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="contractId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="contractPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="contractStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="factoryRawMaterial" type="{http://KM.SessionBean/}factoryRawMaterialEntity" minOccurs="0"/>
 *         &lt;element name="factoryRetailProduct" type="{http://KM.SessionBean/}factoryRetailProductEntity" minOccurs="0"/>
 *         &lt;element name="isDeleted" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="leadTime" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="lotSize" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="supplier" type="{http://KM.SessionBean/}supplierEntity" minOccurs="0"/>
 *         &lt;element name="typeIndicator" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "contractEntity", propOrder = {
    "contractEndDate",
    "contractId",
    "contractPrice",
    "contractStartDate",
    "factoryRawMaterial",
    "factoryRetailProduct",
    "isDeleted",
    "leadTime",
    "lotSize",
    "supplier",
    "typeIndicator",
    "unit"
})
public class ContractEntity {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar contractEndDate;
    protected Long contractId;
    protected Double contractPrice;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar contractStartDate;
    protected FactoryRawMaterialEntity factoryRawMaterial;
    protected FactoryRetailProductEntity factoryRetailProduct;
    protected Boolean isDeleted;
    protected Integer leadTime;
    protected Double lotSize;
    protected SupplierEntity supplier;
    protected int typeIndicator;
    protected String unit;

    /**
     * Gets the value of the contractEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getContractEndDate() {
        return contractEndDate;
    }

    /**
     * Sets the value of the contractEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setContractEndDate(XMLGregorianCalendar value) {
        this.contractEndDate = value;
    }

    /**
     * Gets the value of the contractId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getContractId() {
        return contractId;
    }

    /**
     * Sets the value of the contractId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setContractId(Long value) {
        this.contractId = value;
    }

    /**
     * Gets the value of the contractPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getContractPrice() {
        return contractPrice;
    }

    /**
     * Sets the value of the contractPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setContractPrice(Double value) {
        this.contractPrice = value;
    }

    /**
     * Gets the value of the contractStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getContractStartDate() {
        return contractStartDate;
    }

    /**
     * Sets the value of the contractStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setContractStartDate(XMLGregorianCalendar value) {
        this.contractStartDate = value;
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
     * Gets the value of the leadTime property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLeadTime() {
        return leadTime;
    }

    /**
     * Sets the value of the leadTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLeadTime(Integer value) {
        this.leadTime = value;
    }

    /**
     * Gets the value of the lotSize property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLotSize() {
        return lotSize;
    }

    /**
     * Sets the value of the lotSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLotSize(Double value) {
        this.lotSize = value;
    }

    /**
     * Gets the value of the supplier property.
     * 
     * @return
     *     possible object is
     *     {@link SupplierEntity }
     *     
     */
    public SupplierEntity getSupplier() {
        return supplier;
    }

    /**
     * Sets the value of the supplier property.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplierEntity }
     *     
     */
    public void setSupplier(SupplierEntity value) {
        this.supplier = value;
    }

    /**
     * Gets the value of the typeIndicator property.
     * 
     */
    public int getTypeIndicator() {
        return typeIndicator;
    }

    /**
     * Sets the value of the typeIndicator property.
     * 
     */
    public void setTypeIndicator(int value) {
        this.typeIndicator = value;
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
