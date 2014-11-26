
package sessionbean.ocrm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for supplierEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="supplierEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contractList" type="{http://OCRM.SessionBean/}contractEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierContact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="supplierName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "supplierEntity", propOrder = {
    "contractList",
    "remark",
    "supplierAddress",
    "supplierContact",
    "supplierFax",
    "supplierId",
    "supplierName"
})
public class SupplierEntity {

    @XmlElement(nillable = true)
    protected List<ContractEntity> contractList;
    protected String remark;
    protected String supplierAddress;
    protected String supplierContact;
    protected String supplierFax;
    protected Long supplierId;
    protected String supplierName;

    /**
     * Gets the value of the contractList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contractList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContractList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContractEntity }
     * 
     * 
     */
    public List<ContractEntity> getContractList() {
        if (contractList == null) {
            contractList = new ArrayList<ContractEntity>();
        }
        return this.contractList;
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
     * Gets the value of the supplierAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierAddress() {
        return supplierAddress;
    }

    /**
     * Sets the value of the supplierAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierAddress(String value) {
        this.supplierAddress = value;
    }

    /**
     * Gets the value of the supplierContact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierContact() {
        return supplierContact;
    }

    /**
     * Sets the value of the supplierContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierContact(String value) {
        this.supplierContact = value;
    }

    /**
     * Gets the value of the supplierFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierFax() {
        return supplierFax;
    }

    /**
     * Sets the value of the supplierFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierFax(String value) {
        this.supplierFax = value;
    }

    /**
     * Gets the value of the supplierId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * Sets the value of the supplierId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSupplierId(Long value) {
        this.supplierId = value;
    }

    /**
     * Gets the value of the supplierName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Sets the value of the supplierName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierName(String value) {
        this.supplierName = value;
    }

}
