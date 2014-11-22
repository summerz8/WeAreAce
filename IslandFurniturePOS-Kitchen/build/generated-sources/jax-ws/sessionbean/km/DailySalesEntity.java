
package sessionbean.km;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dailySalesEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dailySalesEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="sales" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="salesAfterDiscount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="salesDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="kitchen" type="{http://KM.SessionBean/}kitchenEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dailySalesEntity", propOrder = {
    "id",
    "sales",
    "salesAfterDiscount",
    "salesDate",
    "kitchen"
})
public class DailySalesEntity {

    protected Long id;
    protected Double sales;
    protected Double salesAfterDiscount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar salesDate;
    protected KitchenEntity kitchen;

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
     * Gets the value of the sales property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSales() {
        return sales;
    }

    /**
     * Sets the value of the sales property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSales(Double value) {
        this.sales = value;
    }

    /**
     * Gets the value of the salesAfterDiscount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSalesAfterDiscount() {
        return salesAfterDiscount;
    }

    /**
     * Sets the value of the salesAfterDiscount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSalesAfterDiscount(Double value) {
        this.salesAfterDiscount = value;
    }

    /**
     * Gets the value of the salesDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSalesDate() {
        return salesDate;
    }

    /**
     * Sets the value of the salesDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSalesDate(XMLGregorianCalendar value) {
        this.salesDate = value;
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

}
