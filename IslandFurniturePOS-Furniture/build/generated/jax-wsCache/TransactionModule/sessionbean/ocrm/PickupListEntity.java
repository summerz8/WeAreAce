
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pickupListEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pickupListEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PickupListId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Picked" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Distributed" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pickupListEntity", propOrder = {
    "pickupListId",
    "picked",
    "distributed"
})
public class PickupListEntity {

    @XmlElement(name = "PickupListId")
    protected Long pickupListId;
    @XmlElement(name = "Picked")
    protected Boolean picked;
    @XmlElement(name = "Distributed")
    protected Boolean distributed;

    /**
     * Gets the value of the pickupListId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPickupListId() {
        return pickupListId;
    }

    /**
     * Sets the value of the pickupListId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPickupListId(Long value) {
        this.pickupListId = value;
    }

    /**
     * Gets the value of the picked property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPicked() {
        return picked;
    }

    /**
     * Sets the value of the picked property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPicked(Boolean value) {
        this.picked = value;
    }

    /**
     * Gets the value of the distributed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDistributed() {
        return distributed;
    }

    /**
     * Sets the value of the distributed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDistributed(Boolean value) {
        this.distributed = value;
    }

}
