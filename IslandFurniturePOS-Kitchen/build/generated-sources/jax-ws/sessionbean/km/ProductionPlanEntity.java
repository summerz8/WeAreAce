
package sessionbean.km;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for productionPlanEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="productionPlanEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confirmDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="factoryProduct" type="{http://KM.SessionBean/}factoryProductEntity" minOccurs="0"/>
 *         &lt;element name="generatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="plannedOrder" type="{http://KM.SessionBean/}plannedOrderEntity" minOccurs="0"/>
 *         &lt;element name="productionPlanId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetPeriod" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="weeklyProductionPlanEntity" type="{http://KM.SessionBean/}weeklyProductionPlanEntity" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "productionPlanEntity", propOrder = {
    "confirmDate",
    "factoryProduct",
    "generatedDate",
    "plannedOrder",
    "productionPlanId",
    "quantity",
    "remark",
    "status",
    "targetPeriod",
    "weeklyProductionPlanEntity"
})
public class ProductionPlanEntity {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar confirmDate;
    protected FactoryProductEntity factoryProduct;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar generatedDate;
    protected PlannedOrderEntity plannedOrder;
    protected Long productionPlanId;
    protected Double quantity;
    protected String remark;
    protected String status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar targetPeriod;
    @XmlElement(nillable = true)
    protected List<WeeklyProductionPlanEntity> weeklyProductionPlanEntity;

    /**
     * Gets the value of the confirmDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConfirmDate() {
        return confirmDate;
    }

    /**
     * Sets the value of the confirmDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConfirmDate(XMLGregorianCalendar value) {
        this.confirmDate = value;
    }

    /**
     * Gets the value of the factoryProduct property.
     * 
     * @return
     *     possible object is
     *     {@link FactoryProductEntity }
     *     
     */
    public FactoryProductEntity getFactoryProduct() {
        return factoryProduct;
    }

    /**
     * Sets the value of the factoryProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactoryProductEntity }
     *     
     */
    public void setFactoryProduct(FactoryProductEntity value) {
        this.factoryProduct = value;
    }

    /**
     * Gets the value of the generatedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGeneratedDate() {
        return generatedDate;
    }

    /**
     * Sets the value of the generatedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGeneratedDate(XMLGregorianCalendar value) {
        this.generatedDate = value;
    }

    /**
     * Gets the value of the plannedOrder property.
     * 
     * @return
     *     possible object is
     *     {@link PlannedOrderEntity }
     *     
     */
    public PlannedOrderEntity getPlannedOrder() {
        return plannedOrder;
    }

    /**
     * Sets the value of the plannedOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlannedOrderEntity }
     *     
     */
    public void setPlannedOrder(PlannedOrderEntity value) {
        this.plannedOrder = value;
    }

    /**
     * Gets the value of the productionPlanId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductionPlanId() {
        return productionPlanId;
    }

    /**
     * Sets the value of the productionPlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductionPlanId(Long value) {
        this.productionPlanId = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setQuantity(Double value) {
        this.quantity = value;
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
     * Gets the value of the targetPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTargetPeriod() {
        return targetPeriod;
    }

    /**
     * Sets the value of the targetPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTargetPeriod(XMLGregorianCalendar value) {
        this.targetPeriod = value;
    }

    /**
     * Gets the value of the weeklyProductionPlanEntity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the weeklyProductionPlanEntity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWeeklyProductionPlanEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WeeklyProductionPlanEntity }
     * 
     * 
     */
    public List<WeeklyProductionPlanEntity> getWeeklyProductionPlanEntity() {
        if (weeklyProductionPlanEntity == null) {
            weeklyProductionPlanEntity = new ArrayList<WeeklyProductionPlanEntity>();
        }
        return this.weeklyProductionPlanEntity;
    }

}
