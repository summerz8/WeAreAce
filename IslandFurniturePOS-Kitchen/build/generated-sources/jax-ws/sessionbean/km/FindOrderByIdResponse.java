
package sessionbean.km;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for findOrderByIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findOrderByIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://KM.SessionBean/}kitchenOrderEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findOrderByIdResponse", propOrder = {
    "_return"
})
public class FindOrderByIdResponse {

    @XmlElement(name = "return")
    protected KitchenOrderEntity _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link KitchenOrderEntity }
     *     
     */
    public KitchenOrderEntity getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link KitchenOrderEntity }
     *     
     */
    public void setReturn(KitchenOrderEntity value) {
        this._return = value;
    }

}
