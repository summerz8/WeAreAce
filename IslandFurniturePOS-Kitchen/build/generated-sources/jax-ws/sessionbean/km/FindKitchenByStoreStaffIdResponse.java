
package sessionbean.km;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for findKitchenByStoreStaffIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findKitchenByStoreStaffIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://KM.SessionBean/}kitchenEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findKitchenByStoreStaffIdResponse", propOrder = {
    "_return"
})
public class FindKitchenByStoreStaffIdResponse {

    @XmlElement(name = "return")
    protected KitchenEntity _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link KitchenEntity }
     *     
     */
    public KitchenEntity getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link KitchenEntity }
     *     
     */
    public void setReturn(KitchenEntity value) {
        this._return = value;
    }

}
