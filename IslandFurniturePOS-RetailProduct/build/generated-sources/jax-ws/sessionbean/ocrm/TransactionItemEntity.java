
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transactionItemEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transactionItemEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="itemId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="itemName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pickupList" type="{http://OCRM.SessionBean/}pickupListEntity" minOccurs="0"/>
 *         &lt;element name="totalMemberPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="transaction" type="{http://OCRM.SessionBean/}transactionEntity" minOccurs="0"/>
 *         &lt;element name="transactionItemId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="unitMemberPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="unitPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transactionItemEntity", propOrder = {
    "amount",
    "itemId",
    "itemName",
    "pickupList",
    "totalMemberPrice",
    "totalPrice",
    "transaction",
    "transactionItemId",
    "unitMemberPrice",
    "unitPrice"
})
public class TransactionItemEntity {

    protected int amount;
    protected Long itemId;
    protected String itemName;
    protected PickupListEntity pickupList;
    protected Double totalMemberPrice;
    protected Double totalPrice;
    protected TransactionEntity transaction;
    protected Long transactionItemId;
    protected Double unitMemberPrice;
    protected Double unitPrice;

    /**
     * Gets the value of the amount property.
     * 
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(int value) {
        this.amount = value;
    }

    /**
     * Gets the value of the itemId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * Sets the value of the itemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setItemId(Long value) {
        this.itemId = value;
    }

    /**
     * Gets the value of the itemName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the value of the itemName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemName(String value) {
        this.itemName = value;
    }

    /**
     * Gets the value of the pickupList property.
     * 
     * @return
     *     possible object is
     *     {@link PickupListEntity }
     *     
     */
    public PickupListEntity getPickupList() {
        return pickupList;
    }

    /**
     * Sets the value of the pickupList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PickupListEntity }
     *     
     */
    public void setPickupList(PickupListEntity value) {
        this.pickupList = value;
    }

    /**
     * Gets the value of the totalMemberPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalMemberPrice() {
        return totalMemberPrice;
    }

    /**
     * Sets the value of the totalMemberPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalMemberPrice(Double value) {
        this.totalMemberPrice = value;
    }

    /**
     * Gets the value of the totalPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the value of the totalPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalPrice(Double value) {
        this.totalPrice = value;
    }

    /**
     * Gets the value of the transaction property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionEntity }
     *     
     */
    public TransactionEntity getTransaction() {
        return transaction;
    }

    /**
     * Sets the value of the transaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionEntity }
     *     
     */
    public void setTransaction(TransactionEntity value) {
        this.transaction = value;
    }

    /**
     * Gets the value of the transactionItemId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTransactionItemId() {
        return transactionItemId;
    }

    /**
     * Sets the value of the transactionItemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTransactionItemId(Long value) {
        this.transactionItemId = value;
    }

    /**
     * Gets the value of the unitMemberPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getUnitMemberPrice() {
        return unitMemberPrice;
    }

    /**
     * Sets the value of the unitMemberPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setUnitMemberPrice(Double value) {
        this.unitMemberPrice = value;
    }

    /**
     * Gets the value of the unitPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Sets the value of the unitPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setUnitPrice(Double value) {
        this.unitPrice = value;
    }

}
