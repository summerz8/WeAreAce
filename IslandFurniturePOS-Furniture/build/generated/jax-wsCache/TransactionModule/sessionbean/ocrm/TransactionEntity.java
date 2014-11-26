
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for transactionEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transactionEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="generateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="totalMemberPrice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="tendered" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="moneyChange" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="POSid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="store" type="{http://OCRM.SessionBean/}storeEntity" minOccurs="0"/>
 *         &lt;element name="member" type="{http://OCRM.SessionBean/}memberEntity" minOccurs="0"/>
 *         &lt;element name="storeStaff" type="{http://OCRM.SessionBean/}storeUserEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transactionEntity", propOrder = {
    "transactionId",
    "generateTime",
    "totalPrice",
    "totalMemberPrice",
    "tendered",
    "moneyChange",
    "poSid",
    "location",
    "store",
    "member",
    "storeStaff"
})
public class TransactionEntity {

    protected Long transactionId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar generateTime;
    protected Double totalPrice;
    protected Double totalMemberPrice;
    protected Double tendered;
    protected Double moneyChange;
    @XmlElement(name = "POSid")
    protected String poSid;
    protected int location;
    protected StoreEntity store;
    protected MemberEntity member;
    protected StoreUserEntity storeStaff;

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTransactionId(Long value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the generateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getGenerateTime() {
        return generateTime;
    }

    /**
     * Sets the value of the generateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setGenerateTime(XMLGregorianCalendar value) {
        this.generateTime = value;
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
     * Gets the value of the tendered property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTendered() {
        return tendered;
    }

    /**
     * Sets the value of the tendered property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTendered(Double value) {
        this.tendered = value;
    }

    /**
     * Gets the value of the moneyChange property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMoneyChange() {
        return moneyChange;
    }

    /**
     * Sets the value of the moneyChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMoneyChange(Double value) {
        this.moneyChange = value;
    }

    /**
     * Gets the value of the poSid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPOSid() {
        return poSid;
    }

    /**
     * Sets the value of the poSid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPOSid(String value) {
        this.poSid = value;
    }

    /**
     * Gets the value of the location property.
     * 
     */
    public int getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     */
    public void setLocation(int value) {
        this.location = value;
    }

    /**
     * Gets the value of the store property.
     * 
     * @return
     *     possible object is
     *     {@link StoreEntity }
     *     
     */
    public StoreEntity getStore() {
        return store;
    }

    /**
     * Sets the value of the store property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreEntity }
     *     
     */
    public void setStore(StoreEntity value) {
        this.store = value;
    }

    /**
     * Gets the value of the member property.
     * 
     * @return
     *     possible object is
     *     {@link MemberEntity }
     *     
     */
    public MemberEntity getMember() {
        return member;
    }

    /**
     * Sets the value of the member property.
     * 
     * @param value
     *     allowed object is
     *     {@link MemberEntity }
     *     
     */
    public void setMember(MemberEntity value) {
        this.member = value;
    }

    /**
     * Gets the value of the storeStaff property.
     * 
     * @return
     *     possible object is
     *     {@link StoreUserEntity }
     *     
     */
    public StoreUserEntity getStoreStaff() {
        return storeStaff;
    }

    /**
     * Sets the value of the storeStaff property.
     * 
     * @param value
     *     allowed object is
     *     {@link StoreUserEntity }
     *     
     */
    public void setStoreStaff(StoreUserEntity value) {
        this.storeStaff = value;
    }

}
