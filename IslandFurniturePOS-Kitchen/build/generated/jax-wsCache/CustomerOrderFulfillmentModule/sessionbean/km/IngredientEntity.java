
package sessionbean.km;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ingredientEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ingredientEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="stock" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="lotSize" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="forecasts" type="{http://KM.SessionBean/}ingredientForecastEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="purchaseOrders" type="{http://KM.SessionBean/}ingredientPurchaseOrderEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="issues" type="{http://KM.SessionBean/}ingredientIssueEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="storagePlaces" type="{http://KM.SessionBean/}storagePlaceEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="kitchen" type="{http://KM.SessionBean/}kitchenEntity" minOccurs="0"/>
 *         &lt;element name="supplier" type="{http://KM.SessionBean/}ingredientSupplierEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ingredientEntity", propOrder = {
    "id",
    "name",
    "price",
    "unit",
    "remark",
    "stock",
    "lotSize",
    "deleted",
    "forecasts",
    "purchaseOrders",
    "issues",
    "storagePlaces",
    "kitchen",
    "supplier"
})
public class IngredientEntity {

    protected Long id;
    protected String name;
    protected Double price;
    protected String unit;
    protected String remark;
    protected Double stock;
    protected Double lotSize;
    protected boolean deleted;
    @XmlElement(nillable = true)
    protected List<IngredientForecastEntity> forecasts;
    @XmlElement(nillable = true)
    protected List<IngredientPurchaseOrderEntity> purchaseOrders;
    @XmlElement(nillable = true)
    protected List<IngredientIssueEntity> issues;
    @XmlElement(nillable = true)
    protected List<StoragePlaceEntity> storagePlaces;
    protected KitchenEntity kitchen;
    protected IngredientSupplierEntity supplier;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
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
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * Gets the value of the stock property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getStock() {
        return stock;
    }

    /**
     * Sets the value of the stock property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setStock(Double value) {
        this.stock = value;
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
     * Gets the value of the deleted property.
     * 
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the value of the deleted property.
     * 
     */
    public void setDeleted(boolean value) {
        this.deleted = value;
    }

    /**
     * Gets the value of the forecasts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the forecasts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getForecasts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IngredientForecastEntity }
     * 
     * 
     */
    public List<IngredientForecastEntity> getForecasts() {
        if (forecasts == null) {
            forecasts = new ArrayList<IngredientForecastEntity>();
        }
        return this.forecasts;
    }

    /**
     * Gets the value of the purchaseOrders property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the purchaseOrders property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPurchaseOrders().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IngredientPurchaseOrderEntity }
     * 
     * 
     */
    public List<IngredientPurchaseOrderEntity> getPurchaseOrders() {
        if (purchaseOrders == null) {
            purchaseOrders = new ArrayList<IngredientPurchaseOrderEntity>();
        }
        return this.purchaseOrders;
    }

    /**
     * Gets the value of the issues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the issues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIssues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IngredientIssueEntity }
     * 
     * 
     */
    public List<IngredientIssueEntity> getIssues() {
        if (issues == null) {
            issues = new ArrayList<IngredientIssueEntity>();
        }
        return this.issues;
    }

    /**
     * Gets the value of the storagePlaces property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the storagePlaces property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStoragePlaces().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StoragePlaceEntity }
     * 
     * 
     */
    public List<StoragePlaceEntity> getStoragePlaces() {
        if (storagePlaces == null) {
            storagePlaces = new ArrayList<StoragePlaceEntity>();
        }
        return this.storagePlaces;
    }

    /**
     * Gets the value of the kitchen property.
     * 
     * @return
     *     possible object is
     *     {@link KitchenEntity }
     *     
     */
    public KitchenEntity getKitchen() {
        return kitchen;
    }

    /**
     * Sets the value of the kitchen property.
     * 
     * @param value
     *     allowed object is
     *     {@link KitchenEntity }
     *     
     */
    public void setKitchen(KitchenEntity value) {
        this.kitchen = value;
    }

    /**
     * Gets the value of the supplier property.
     * 
     * @return
     *     possible object is
     *     {@link IngredientSupplierEntity }
     *     
     */
    public IngredientSupplierEntity getSupplier() {
        return supplier;
    }

    /**
     * Sets the value of the supplier property.
     * 
     * @param value
     *     allowed object is
     *     {@link IngredientSupplierEntity }
     *     
     */
    public void setSupplier(IngredientSupplierEntity value) {
        this.supplier = value;
    }

}
