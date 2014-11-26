
package sessionbean.ocrm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for integratedPlannedOrderEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="integratedPlannedOrderEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="factory" type="{http://OCRM.SessionBean/}factoryEntity" minOccurs="0"/>
 *         &lt;element name="factoryRawMaterialAmount" type="{http://OCRM.SessionBean/}factoryRawMaterialAmountEntity" minOccurs="0"/>
 *         &lt;element name="factoryRetailProductAmount" type="{http://OCRM.SessionBean/}factoryRetailProductAmountEntity" minOccurs="0"/>
 *         &lt;element name="generatedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="plannedOrderList" type="{http://OCRM.SessionBean/}plannedOrderEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="purchaseOrder" type="{http://OCRM.SessionBean/}purchaseOrderEntity" minOccurs="0"/>
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
@XmlType(name = "integratedPlannedOrderEntity", propOrder = {
    "factory",
    "factoryRawMaterialAmount",
    "factoryRetailProductAmount",
    "generatedDate",
    "id",
    "plannedOrderList",
    "purchaseOrder",
    "status",
    "targetPeriod"
})
public class IntegratedPlannedOrderEntity {

    protected FactoryEntity factory;
    protected FactoryRawMaterialAmountEntity factoryRawMaterialAmount;
    protected FactoryRetailProductAmountEntity factoryRetailProductAmount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar generatedDate;
    protected Long id;
    @XmlElement(nillable = true)
    protected List<PlannedOrderEntity> plannedOrderList;
    protected PurchaseOrderEntity purchaseOrder;
    protected String status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar targetPeriod;

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
     * Gets the value of the factoryRawMaterialAmount property.
     * 
     * @return
     *     possible object is
     *     {@link FactoryRawMaterialAmountEntity }
     *     
     */
    public FactoryRawMaterialAmountEntity getFactoryRawMaterialAmount() {
        return factoryRawMaterialAmount;
    }

    /**
     * Sets the value of the factoryRawMaterialAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactoryRawMaterialAmountEntity }
     *     
     */
    public void setFactoryRawMaterialAmount(FactoryRawMaterialAmountEntity value) {
        this.factoryRawMaterialAmount = value;
    }

    /**
     * Gets the value of the factoryRetailProductAmount property.
     * 
     * @return
     *     possible object is
     *     {@link FactoryRetailProductAmountEntity }
     *     
     */
    public FactoryRetailProductAmountEntity getFactoryRetailProductAmount() {
        return factoryRetailProductAmount;
    }

    /**
     * Sets the value of the factoryRetailProductAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactoryRetailProductAmountEntity }
     *     
     */
    public void setFactoryRetailProductAmount(FactoryRetailProductAmountEntity value) {
        this.factoryRetailProductAmount = value;
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
     * Gets the value of the plannedOrderList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plannedOrderList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlannedOrderList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlannedOrderEntity }
     * 
     * 
     */
    public List<PlannedOrderEntity> getPlannedOrderList() {
        if (plannedOrderList == null) {
            plannedOrderList = new ArrayList<PlannedOrderEntity>();
        }
        return this.plannedOrderList;
    }

    /**
     * Gets the value of the purchaseOrder property.
     * 
     * @return
     *     possible object is
     *     {@link PurchaseOrderEntity }
     *     
     */
    public PurchaseOrderEntity getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * Sets the value of the purchaseOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link PurchaseOrderEntity }
     *     
     */
    public void setPurchaseOrder(PurchaseOrderEntity value) {
        this.purchaseOrder = value;
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
