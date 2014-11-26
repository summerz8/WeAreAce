
package sessionbean.ocrm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for storeWarehouseBinEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="storeWarehouseBinEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="frominstoreMovementRecords" type="{http://OCRM.SessionBean/}storeInStoreMovementRecordEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="inboundMovementRecords" type="{http://OCRM.SessionBean/}storeInboundRecordEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="isBackHouse" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="isDeleted" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="isDisplayArea" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="isSelfCollect" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outboundMovementRecords" type="{http://OCRM.SessionBean/}storeOutboundRecordEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="store" type="{http://OCRM.SessionBean/}storeEntity" minOccurs="0"/>
 *         &lt;element name="storeBinProducts" type="{http://OCRM.SessionBean/}storeBinProductEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="storeBinRetailProducts" type="{http://OCRM.SessionBean/}storeBinRetailProductEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="toinstoreMovementRecords" type="{http://OCRM.SessionBean/}storeInStoreMovementRecordEntity" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "storeWarehouseBinEntity", propOrder = {
    "frominstoreMovementRecords",
    "id",
    "inboundMovementRecords",
    "isBackHouse",
    "isDeleted",
    "isDisplayArea",
    "isSelfCollect",
    "name",
    "outboundMovementRecords",
    "remark",
    "store",
    "storeBinProducts",
    "storeBinRetailProducts",
    "toinstoreMovementRecords"
})
public class StoreWarehouseBinEntity {

    @XmlElement(nillable = true)
    protected List<StoreInStoreMovementRecordEntity> frominstoreMovementRecords;
    protected Long id;
    @XmlElement(nillable = true)
    protected List<StoreInboundRecordEntity> inboundMovementRecords;
    protected Boolean isBackHouse;
    protected Boolean isDeleted;
    protected Boolean isDisplayArea;
    protected Boolean isSelfCollect;
    protected String name;
    @XmlElement(nillable = true)
    protected List<StoreOutboundRecordEntity> outboundMovementRecords;
    protected String remark;
    protected StoreEntity store;
    @XmlElement(nillable = true)
    protected List<StoreBinProductEntity> storeBinProducts;
    @XmlElement(nillable = true)
    protected List<StoreBinRetailProductEntity> storeBinRetailProducts;
    @XmlElement(nillable = true)
    protected List<StoreInStoreMovementRecordEntity> toinstoreMovementRecords;

    /**
     * Gets the value of the frominstoreMovementRecords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the frominstoreMovementRecords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFrominstoreMovementRecords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StoreInStoreMovementRecordEntity }
     * 
     * 
     */
    public List<StoreInStoreMovementRecordEntity> getFrominstoreMovementRecords() {
        if (frominstoreMovementRecords == null) {
            frominstoreMovementRecords = new ArrayList<StoreInStoreMovementRecordEntity>();
        }
        return this.frominstoreMovementRecords;
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
     * Gets the value of the inboundMovementRecords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inboundMovementRecords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInboundMovementRecords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StoreInboundRecordEntity }
     * 
     * 
     */
    public List<StoreInboundRecordEntity> getInboundMovementRecords() {
        if (inboundMovementRecords == null) {
            inboundMovementRecords = new ArrayList<StoreInboundRecordEntity>();
        }
        return this.inboundMovementRecords;
    }

    /**
     * Gets the value of the isBackHouse property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsBackHouse() {
        return isBackHouse;
    }

    /**
     * Sets the value of the isBackHouse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsBackHouse(Boolean value) {
        this.isBackHouse = value;
    }

    /**
     * Gets the value of the isDeleted property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsDeleted() {
        return isDeleted;
    }

    /**
     * Sets the value of the isDeleted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDeleted(Boolean value) {
        this.isDeleted = value;
    }

    /**
     * Gets the value of the isDisplayArea property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsDisplayArea() {
        return isDisplayArea;
    }

    /**
     * Sets the value of the isDisplayArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsDisplayArea(Boolean value) {
        this.isDisplayArea = value;
    }

    /**
     * Gets the value of the isSelfCollect property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSelfCollect() {
        return isSelfCollect;
    }

    /**
     * Sets the value of the isSelfCollect property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSelfCollect(Boolean value) {
        this.isSelfCollect = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the outboundMovementRecords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outboundMovementRecords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutboundMovementRecords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StoreOutboundRecordEntity }
     * 
     * 
     */
    public List<StoreOutboundRecordEntity> getOutboundMovementRecords() {
        if (outboundMovementRecords == null) {
            outboundMovementRecords = new ArrayList<StoreOutboundRecordEntity>();
        }
        return this.outboundMovementRecords;
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
     * Gets the value of the storeBinProducts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the storeBinProducts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStoreBinProducts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StoreBinProductEntity }
     * 
     * 
     */
    public List<StoreBinProductEntity> getStoreBinProducts() {
        if (storeBinProducts == null) {
            storeBinProducts = new ArrayList<StoreBinProductEntity>();
        }
        return this.storeBinProducts;
    }

    /**
     * Gets the value of the storeBinRetailProducts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the storeBinRetailProducts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStoreBinRetailProducts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StoreBinRetailProductEntity }
     * 
     * 
     */
    public List<StoreBinRetailProductEntity> getStoreBinRetailProducts() {
        if (storeBinRetailProducts == null) {
            storeBinRetailProducts = new ArrayList<StoreBinRetailProductEntity>();
        }
        return this.storeBinRetailProducts;
    }

    /**
     * Gets the value of the toinstoreMovementRecords property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the toinstoreMovementRecords property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getToinstoreMovementRecords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StoreInStoreMovementRecordEntity }
     * 
     * 
     */
    public List<StoreInStoreMovementRecordEntity> getToinstoreMovementRecords() {
        if (toinstoreMovementRecords == null) {
            toinstoreMovementRecords = new ArrayList<StoreInStoreMovementRecordEntity>();
        }
        return this.toinstoreMovementRecords;
    }

}
