
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
 * <p>Java class for plannedOrderEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="plannedOrderEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confirmDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="factory" type="{http://KM.SessionBean/}factoryEntity" minOccurs="0"/>
 *         &lt;element name="factoryRawMaterialAmountList" type="{http://KM.SessionBean/}factoryRawMaterialAmountEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="generatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="plannedOrderId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="productionPlan" type="{http://KM.SessionBean/}productionPlanEntity" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="targetPeriod" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "plannedOrderEntity", propOrder = {
    "confirmDate",
    "factory",
    "factoryRawMaterialAmountList",
    "generatedDate",
    "plannedOrderId",
    "productionPlan",
    "status",
    "targetPeriod"
})
public class PlannedOrderEntity {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar confirmDate;
    protected FactoryEntity factory;
    @XmlElement(nillable = true)
    protected List<FactoryRawMaterialAmountEntity> factoryRawMaterialAmountList;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar generatedDate;
    protected Long plannedOrderId;
    protected ProductionPlanEntity productionPlan;
    protected String status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar targetPeriod;

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
     * Gets the value of the factoryRawMaterialAmountList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the factoryRawMaterialAmountList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFactoryRawMaterialAmountList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FactoryRawMaterialAmountEntity }
     * 
     * 
     */
    public List<FactoryRawMaterialAmountEntity> getFactoryRawMaterialAmountList() {
        if (factoryRawMaterialAmountList == null) {
            factoryRawMaterialAmountList = new ArrayList<FactoryRawMaterialAmountEntity>();
        }
        return this.factoryRawMaterialAmountList;
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
     * Gets the value of the plannedOrderId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPlannedOrderId() {
        return plannedOrderId;
    }

    /**
     * Sets the value of the plannedOrderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPlannedOrderId(Long value) {
        this.plannedOrderId = value;
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

}
