
package sessionbean.km;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ingredientPurchaseOrderEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ingredientPurchaseOrderEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="actualTotal" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="forecast" type="{http://KM.SessionBean/}ingredientForecastEntity" minOccurs="0"/>
 *         &lt;element name="unconfirmedIPOSQuantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ingredientPurchaseOrderEntity", propOrder = {
    "id",
    "total",
    "actualTotal",
    "status",
    "forecast",
    "unconfirmedIPOSQuantity"
})
public class IngredientPurchaseOrderEntity {

    protected Long id;
    protected Double total;
    protected Double actualTotal;
    protected String status;
    protected IngredientForecastEntity forecast;
    protected Integer unconfirmedIPOSQuantity;

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
     * Gets the value of the total property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotal(Double value) {
        this.total = value;
    }

    /**
     * Gets the value of the actualTotal property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getActualTotal() {
        return actualTotal;
    }

    /**
     * Sets the value of the actualTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setActualTotal(Double value) {
        this.actualTotal = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the forecast property.
     * 
     * @return
     *     possible object is
     *     {@link IngredientForecastEntity }
     *     
     */
    public IngredientForecastEntity getForecast() {
        return forecast;
    }

    /**
     * Sets the value of the forecast property.
     * 
     * @param value
     *     allowed object is
     *     {@link IngredientForecastEntity }
     *     
     */
    public void setForecast(IngredientForecastEntity value) {
        this.forecast = value;
    }

    /**
     * Gets the value of the unconfirmedIPOSQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUnconfirmedIPOSQuantity() {
        return unconfirmedIPOSQuantity;
    }

    /**
     * Sets the value of the unconfirmedIPOSQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUnconfirmedIPOSQuantity(Integer value) {
        this.unconfirmedIPOSQuantity = value;
    }

}
