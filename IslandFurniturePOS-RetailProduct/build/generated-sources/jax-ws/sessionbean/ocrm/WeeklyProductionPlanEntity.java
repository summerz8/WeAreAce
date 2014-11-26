
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for weeklyProductionPlanEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="weeklyProductionPlanEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="productionMonth" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="productionPlan" type="{http://OCRM.SessionBean/}productionPlanEntity" minOccurs="0"/>
 *         &lt;element name="week" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="weeklyDemand" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="workingDayInMonth" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="workingDayInWeek" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "weeklyProductionPlanEntity", propOrder = {
    "id",
    "productionMonth",
    "productionPlan",
    "week",
    "weeklyDemand",
    "workingDayInMonth",
    "workingDayInWeek"
})
public class WeeklyProductionPlanEntity {

    protected Long id;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar productionMonth;
    protected ProductionPlanEntity productionPlan;
    protected Integer week;
    protected Double weeklyDemand;
    protected Integer workingDayInMonth;
    protected Integer workingDayInWeek;

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
     * Gets the value of the productionMonth property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProductionMonth() {
        return productionMonth;
    }

    /**
     * Sets the value of the productionMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProductionMonth(XMLGregorianCalendar value) {
        this.productionMonth = value;
    }

    /**
     * Gets the value of the productionPlan property.
     * 
     * @return
     *     possible object is
     *     {@link ProductionPlanEntity }
     *     
     */
    public ProductionPlanEntity getProductionPlan() {
        return productionPlan;
    }

    /**
     * Sets the value of the productionPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductionPlanEntity }
     *     
     */
    public void setProductionPlan(ProductionPlanEntity value) {
        this.productionPlan = value;
    }

    /**
     * Gets the value of the week property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWeek() {
        return week;
    }

    /**
     * Sets the value of the week property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWeek(Integer value) {
        this.week = value;
    }

    /**
     * Gets the value of the weeklyDemand property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getWeeklyDemand() {
        return weeklyDemand;
    }

    /**
     * Sets the value of the weeklyDemand property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setWeeklyDemand(Double value) {
        this.weeklyDemand = value;
    }

    /**
     * Gets the value of the workingDayInMonth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWorkingDayInMonth() {
        return workingDayInMonth;
    }

    /**
     * Sets the value of the workingDayInMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWorkingDayInMonth(Integer value) {
        this.workingDayInMonth = value;
    }

    /**
     * Gets the value of the workingDayInWeek property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWorkingDayInWeek() {
        return workingDayInWeek;
    }

    /**
     * Sets the value of the workingDayInWeek property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWorkingDayInWeek(Integer value) {
        this.workingDayInWeek = value;
    }

}
