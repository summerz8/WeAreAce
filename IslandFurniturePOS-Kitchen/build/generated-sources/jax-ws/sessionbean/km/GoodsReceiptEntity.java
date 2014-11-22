
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
 * <p>Java class for goodsReceiptEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="goodsReceiptEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="goodsReceiptId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="inboundMovements" type="{http://KM.SessionBean/}inboundMovementEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="originalAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="purchaseOrder" type="{http://KM.SessionBean/}purchaseOrderEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "goodsReceiptEntity", propOrder = {
    "amount",
    "createDate",
    "goodsReceiptId",
    "inboundMovements",
    "originalAmount",
    "purchaseOrder"
})
public class GoodsReceiptEntity {

    protected Double amount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    protected Long goodsReceiptId;
    @XmlElement(nillable = true)
    protected List<InboundMovementEntity> inboundMovements;
    protected Double originalAmount;
    protected PurchaseOrderEntity purchaseOrder;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmount(Double value) {
        this.amount = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the goodsReceiptId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getGoodsReceiptId() {
        return goodsReceiptId;
    }

    /**
     * Sets the value of the goodsReceiptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setGoodsReceiptId(Long value) {
        this.goodsReceiptId = value;
    }

    /**
     * Gets the value of the inboundMovements property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inboundMovements property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInboundMovements().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InboundMovementEntity }
     * 
     * 
     */
    public List<InboundMovementEntity> getInboundMovements() {
        if (inboundMovements == null) {
            inboundMovements = new ArrayList<InboundMovementEntity>();
        }
        return this.inboundMovements;
    }

    /**
     * Gets the value of the originalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOriginalAmount() {
        return originalAmount;
    }

    /**
     * Sets the value of the originalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOriginalAmount(Double value) {
        this.originalAmount = value;
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

}
