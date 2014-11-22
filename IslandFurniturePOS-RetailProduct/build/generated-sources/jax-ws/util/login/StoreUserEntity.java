
package util.login;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for storeUserEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="storeUserEntity">
 *   &lt;complexContent>
 *     &lt;extension base="{http://login.util/}userEntity">
 *       &lt;sequence>
 *         &lt;element name="isCasher" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="beginCash" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="endCash" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "storeUserEntity", propOrder = {
    "isCasher",
    "beginCash",
    "endCash"
})
public class StoreUserEntity
    extends UserEntity
{

    protected boolean isCasher;
    protected Double beginCash;
    protected Double endCash;

    /**
     * Gets the value of the isCasher property.
     * 
     */
    public boolean isIsCasher() {
        return isCasher;
    }

    /**
     * Sets the value of the isCasher property.
     * 
     */
    public void setIsCasher(boolean value) {
        this.isCasher = value;
    }

    /**
     * Gets the value of the beginCash property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBeginCash() {
        return beginCash;
    }

    /**
     * Sets the value of the beginCash property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBeginCash(Double value) {
        this.beginCash = value;
    }

    /**
     * Gets the value of the endCash property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEndCash() {
        return endCash;
    }

    /**
     * Sets the value of the endCash property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEndCash(Double value) {
        this.endCash = value;
    }

}
