
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for membershipLevelEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="membershipLevelEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cle" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="discount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="expressCheckout" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="freeDelivery" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="freeParking" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="inviteOnlyEvent" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="levelId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="levelName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pointsToUpgrade" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "membershipLevelEntity", propOrder = {
    "cle",
    "discount",
    "expressCheckout",
    "freeDelivery",
    "freeParking",
    "inviteOnlyEvent",
    "levelId",
    "levelName",
    "pointsToUpgrade"
})
public class MembershipLevelEntity {

    protected Integer cle;
    protected Double discount;
    protected Boolean expressCheckout;
    protected Boolean freeDelivery;
    protected Boolean freeParking;
    protected Boolean inviteOnlyEvent;
    protected Integer levelId;
    protected String levelName;
    protected Double pointsToUpgrade;

    /**
     * Gets the value of the cle property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCle() {
        return cle;
    }

    /**
     * Sets the value of the cle property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCle(Integer value) {
        this.cle = value;
    }

    /**
     * Gets the value of the discount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * Sets the value of the discount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDiscount(Double value) {
        this.discount = value;
    }

    /**
     * Gets the value of the expressCheckout property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExpressCheckout() {
        return expressCheckout;
    }

    /**
     * Sets the value of the expressCheckout property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExpressCheckout(Boolean value) {
        this.expressCheckout = value;
    }

    /**
     * Gets the value of the freeDelivery property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFreeDelivery() {
        return freeDelivery;
    }

    /**
     * Sets the value of the freeDelivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFreeDelivery(Boolean value) {
        this.freeDelivery = value;
    }

    /**
     * Gets the value of the freeParking property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFreeParking() {
        return freeParking;
    }

    /**
     * Sets the value of the freeParking property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFreeParking(Boolean value) {
        this.freeParking = value;
    }

    /**
     * Gets the value of the inviteOnlyEvent property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInviteOnlyEvent() {
        return inviteOnlyEvent;
    }

    /**
     * Sets the value of the inviteOnlyEvent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInviteOnlyEvent(Boolean value) {
        this.inviteOnlyEvent = value;
    }

    /**
     * Gets the value of the levelId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLevelId() {
        return levelId;
    }

    /**
     * Sets the value of the levelId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLevelId(Integer value) {
        this.levelId = value;
    }

    /**
     * Gets the value of the levelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Sets the value of the levelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLevelName(String value) {
        this.levelName = value;
    }

    /**
     * Gets the value of the pointsToUpgrade property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPointsToUpgrade() {
        return pointsToUpgrade;
    }

    /**
     * Sets the value of the pointsToUpgrade property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPointsToUpgrade(Double value) {
        this.pointsToUpgrade = value;
    }

}
