
package sessionbean.km;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="kitchenId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="memberId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="storestaffId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="POSid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createOrder", propOrder = {
    "kitchenId",
    "memberId",
    "storestaffId",
    "poSid"
})
public class CreateOrder {

    protected Long kitchenId;
    protected Long memberId;
    protected String storestaffId;
    @XmlElement(name = "POSid")
    protected String poSid;

    /**
     * Gets the value of the kitchenId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getKitchenId() {
        return kitchenId;
    }

    /**
     * Sets the value of the kitchenId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setKitchenId(Long value) {
        this.kitchenId = value;
    }

    /**
     * Gets the value of the memberId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * Sets the value of the memberId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMemberId(Long value) {
        this.memberId = value;
    }

    /**
     * Gets the value of the storestaffId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStorestaffId() {
        return storestaffId;
    }

    /**
     * Sets the value of the storestaffId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStorestaffId(String value) {
        this.storestaffId = value;
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

}
