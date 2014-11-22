
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
 * <p>Java class for ingredientPurchaseOrderToSupplierEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ingredientPurchaseOrderToSupplierEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actualTotal" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="creationTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ingredientPurchaseOrder" type="{http://KM.SessionBean/}ingredientPurchaseOrderEntity" minOccurs="0"/>
 *         &lt;element name="kitchen" type="{http://KM.SessionBean/}kitchenEntity" minOccurs="0"/>
 *         &lt;element name="purchaseItems" type="{http://KM.SessionBean/}ingredientItemEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="receipt" type="{http://KM.SessionBean/}ingredientReceiptEntity" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplier" type="{http://KM.SessionBean/}ingredientSupplierEntity" minOccurs="0"/>
 *         &lt;element name="targetDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ingredientPurchaseOrderToSupplierEntity", propOrder = {
    "actualTotal",
    "creationTime",
    "id",
    "ingredientPurchaseOrder",
    "kitchen",
    "purchaseItems",
    "receipt",
    "status",
    "supplier",
    "targetDate",
    "total"
})
public class IngredientPurchaseOrderToSupplierEntity {

    protected Double actualTotal;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar creationTime;
    protected Long id;
    protected IngredientPurchaseOrderEntity ingredientPurchaseOrder;
    protected KitchenEntity kitchen;
    @XmlElement(nillable = true)
    protected List<IngredientItemEntity> purchaseItems;
    protected IngredientReceiptEntity receipt;
    protected String status;
    protected IngredientSupplierEntity supplier;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar targetDate;
    protected Double total;

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
     * Gets the value of the ingredientPurchaseOrder property.
     * 
     * @return
     *     possible object is
     *     {@link IngredientPurchaseOrderEntity }
     *     
     */
    public IngredientPurchaseOrderEntity getIngredientPurchaseOrder() {
        return ingredientPurchaseOrder;
    }

    /**
     * Sets the value of the ingredientPurchaseOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link IngredientPurchaseOrderEntity }
     *     
     */
    public void setIngredientPurchaseOrder(IngredientPurchaseOrderEntity value) {
        this.ingredientPurchaseOrder = value;
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
     * Gets the value of the purchaseItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the purchaseItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPurchaseItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IngredientItemEntity }
     * 
     * 
     */
    public List<IngredientItemEntity> getPurchaseItems() {
        if (purchaseItems == null) {
            purchaseItems = new ArrayList<IngredientItemEntity>();
        }
        return this.purchaseItems;
    }

    /**
     * Gets the value of the receipt property.
     * 
     * @return
     *     possible object is
     *     {@link IngredientReceiptEntity }
     *     
     */
    public IngredientReceiptEntity getReceipt() {
        return receipt;
    }

    /**
     * Sets the value of the receipt property.
     * 
     * @param value
     *     allowed object is
     *     {@link IngredientReceiptEntity }
     *     
     */
    public void setReceipt(IngredientReceiptEntity value) {
        this.receipt = value;
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

    /**
     * Gets the value of the targetDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTargetDate() {
        return targetDate;
    }

    /**
     * Sets the value of the targetDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTargetDate(XMLGregorianCalendar value) {
        this.targetDate = value;
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

}
