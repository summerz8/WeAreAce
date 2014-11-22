
package sessionbean.km;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dishEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dishEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="recipe" type="{http://KM.SessionBean/}ingredientItemEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="recipeQuantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="kitchen" type="{http://KM.SessionBean/}kitchenEntity" minOccurs="0"/>
 *         &lt;element name="forecasts" type="{http://KM.SessionBean/}menuItemForecastEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="dailySales" type="{http://KM.SessionBean/}dailySalesEntity" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dishEntity", propOrder = {
    "id",
    "name",
    "price",
    "recipe",
    "recipeQuantity",
    "remark",
    "deleted",
    "kitchen",
    "forecasts",
    "dailySales"
})
public class DishEntity {

    protected Long id;
    protected String name;
    protected Double price;
    @XmlElement(nillable = true)
    protected List<IngredientItemEntity> recipe;
    protected Integer recipeQuantity;
    protected String remark;
    protected boolean deleted;
    protected KitchenEntity kitchen;
    @XmlElement(nillable = true)
    protected List<MenuItemForecastEntity> forecasts;
    @XmlElement(nillable = true)
    protected List<DailySalesEntity> dailySales;

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
     * Gets the value of the recipe property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recipe property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecipe().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IngredientItemEntity }
     * 
     * 
     */
    public List<IngredientItemEntity> getRecipe() {
        if (recipe == null) {
            recipe = new ArrayList<IngredientItemEntity>();
        }
        return this.recipe;
    }

    /**
     * Gets the value of the recipeQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRecipeQuantity() {
        return recipeQuantity;
    }

    /**
     * Sets the value of the recipeQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRecipeQuantity(Integer value) {
        this.recipeQuantity = value;
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
     * {@link MenuItemForecastEntity }
     * 
     * 
     */
    public List<MenuItemForecastEntity> getForecasts() {
        if (forecasts == null) {
            forecasts = new ArrayList<MenuItemForecastEntity>();
        }
        return this.forecasts;
    }

    /**
     * Gets the value of the dailySales property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dailySales property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDailySales().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DailySalesEntity }
     * 
     * 
     */
    public List<DailySalesEntity> getDailySales() {
        if (dailySales == null) {
            dailySales = new ArrayList<DailySalesEntity>();
        }
        return this.dailySales;
    }

}
