
package sessionbean.km;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ingredientForecastEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ingredientForecastEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="creationTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="menuItemForecast" type="{http://KM.SessionBean/}menuItemForecastEntity" minOccurs="0"/>
 *         &lt;element name="purchaseOrder" type="{http://KM.SessionBean/}ingredientPurchaseOrderEntity" minOccurs="0"/>
 *         &lt;element name="issue" type="{http://KM.SessionBean/}ingredientIssueEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ingredientForecastEntity", propOrder = {
    "id",
    "creationTime",
    "menuItemForecast",
    "purchaseOrder",
    "issue"
})
public class IngredientForecastEntity {

    protected Long id;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationTime;
    protected MenuItemForecastEntity menuItemForecast;
    protected IngredientPurchaseOrderEntity purchaseOrder;
    protected IngredientIssueEntity issue;

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
     * Gets the value of the creationTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationTime() {
        return creationTime;
    }

    /**
     * Sets the value of the creationTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationTime(XMLGregorianCalendar value) {
        this.creationTime = value;
    }

    /**
     * Gets the value of the menuItemForecast property.
     * 
     * @return
     *     possible object is
     *     {@link MenuItemForecastEntity }
     *     
     */
    public MenuItemForecastEntity getMenuItemForecast() {
        return menuItemForecast;
    }

    /**
     * Sets the value of the menuItemForecast property.
     * 
     * @param value
     *     allowed object is
     *     {@link MenuItemForecastEntity }
     *     
     */
    public void setMenuItemForecast(MenuItemForecastEntity value) {
        this.menuItemForecast = value;
    }

    /**
     * Gets the value of the purchaseOrder property.
     * 
     * @return
     *     possible object is
     *     {@link IngredientPurchaseOrderEntity }
     *     
     */
    public IngredientPurchaseOrderEntity getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * Sets the value of the purchaseOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link IngredientPurchaseOrderEntity }
     *     
     */
    public void setPurchaseOrder(IngredientPurchaseOrderEntity value) {
        this.purchaseOrder = value;
    }

    /**
     * Gets the value of the issue property.
     * 
     * @return
     *     possible object is
     *     {@link IngredientIssueEntity }
     *     
     */
    public IngredientIssueEntity getIssue() {
        return issue;
    }

    /**
     * Sets the value of the issue property.
     * 
     * @param value
     *     allowed object is
     *     {@link IngredientIssueEntity }
     *     
     */
    public void setIssue(IngredientIssueEntity value) {
        this.issue = value;
    }

}
