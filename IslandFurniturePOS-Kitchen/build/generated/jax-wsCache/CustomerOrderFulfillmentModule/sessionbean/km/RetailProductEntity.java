
package sessionbean.km;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for retailProductEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="retailProductEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="deleteFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="factoryRetailProducts" type="{http://KM.SessionBean/}factoryRetailProductEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="retailProductId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="storeRetailProducts" type="{http://KM.SessionBean/}storeRetailProductEntity" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "retailProductEntity", propOrder = {
    "deleteFlag",
    "description",
    "factoryRetailProducts",
    "name",
    "price",
    "retailProductId",
    "storeRetailProducts",
    "unit"
})
public class RetailProductEntity {

    protected Boolean deleteFlag;
    protected String description;
    @XmlElement(nillable = true)
    protected List<FactoryRetailProductEntity> factoryRetailProducts;
    protected String name;
    protected Double price;
    protected Long retailProductId;
    @XmlElement(nillable = true)
    protected List<StoreRetailProductEntity> storeRetailProducts;
    protected String unit;

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
     * Gets the value of the factoryRetailProducts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the factoryRetailProducts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFactoryRetailProducts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FactoryRetailProductEntity }
     * 
     * 
     */
    public List<FactoryRetailProductEntity> getFactoryRetailProducts() {
        if (factoryRetailProducts == null) {
            factoryRetailProducts = new ArrayList<FactoryRetailProductEntity>();
        }
        return this.factoryRetailProducts;
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
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPrice(Double value) {
        this.price = value;
    }

    /**
     * Gets the value of the retailProductId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRetailProductId() {
        return retailProductId;
    }

    /**
     * Sets the value of the retailProductId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRetailProductId(Long value) {
        this.retailProductId = value;
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

}
