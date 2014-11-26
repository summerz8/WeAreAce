
package sessionbean.ocrm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for storeEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="storeEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="storeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="manager" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deleteFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="factoryList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="storeBins" type="{http://OCRM.SessionBean/}storeWarehouseBinEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="goodReceipts" type="{http://OCRM.SessionBean/}storeGoodReceiptEntity" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "storeEntity", propOrder = {
    "storeId",
    "address",
    "country",
    "contact",
    "manager",
    "deleteFlag",
    "factoryList",
    "storeBins",
    "goodReceipts"
})
public class StoreEntity {

    protected Long storeId;
    protected String address;
    protected String country;
    protected String contact;
    protected String manager;
    protected Boolean deleteFlag;
    @XmlElement(nillable = true)
    protected List<Long> factoryList;
    @XmlElement(nillable = true)
    protected List<StoreWarehouseBinEntity> storeBins;
    @XmlElement(nillable = true)
    protected List<StoreGoodReceiptEntity> goodReceipts;

    /**
     * Gets the value of the storeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * Sets the value of the storeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStoreId(Long value) {
        this.storeId = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the contact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the value of the contact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContact(String value) {
        this.contact = value;
    }

    /**
     * Gets the value of the manager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManager() {
        return manager;
    }

    /**
     * Sets the value of the manager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManager(String value) {
        this.manager = value;
    }

    /**
     * Gets the value of the deleteFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDeleteFlag() {
        return deleteFlag;
    }

    /**
     * Sets the value of the deleteFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDeleteFlag(Boolean value) {
        this.deleteFlag = value;
    }

    /**
     * Gets the value of the factoryList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the factoryList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFactoryList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getFactoryList() {
        if (factoryList == null) {
            factoryList = new ArrayList<Long>();
        }
        return this.factoryList;
    }

    /**
     * Gets the value of the storeBins property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the storeBins property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStoreBins().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StoreWarehouseBinEntity }
     * 
     * 
     */
    public List<StoreWarehouseBinEntity> getStoreBins() {
        if (storeBins == null) {
            storeBins = new ArrayList<StoreWarehouseBinEntity>();
        }
        return this.storeBins;
    }

    /**
     * Gets the value of the goodReceipts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the goodReceipts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGoodReceipts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StoreGoodReceiptEntity }
     * 
     * 
     */
    public List<StoreGoodReceiptEntity> getGoodReceipts() {
        if (goodReceipts == null) {
            goodReceipts = new ArrayList<StoreGoodReceiptEntity>();
        }
        return this.goodReceipts;
    }

}
