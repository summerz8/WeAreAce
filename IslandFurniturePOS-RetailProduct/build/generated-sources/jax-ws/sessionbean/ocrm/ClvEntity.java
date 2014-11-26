
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for clvEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="clvEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aveExp" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="clv" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="member" type="{http://OCRM.SessionBean/}memberEntity" minOccurs="0"/>
 *         &lt;element name="visitThisMonth" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clvEntity", propOrder = {
    "aveExp",
    "clv",
    "id",
    "member",
    "visitThisMonth"
})
public class ClvEntity {

    protected Double aveExp;
    protected Double clv;
    protected Long id;
    protected MemberEntity member;
    protected Integer visitThisMonth;

    /**
     * Gets the value of the aveExp property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAveExp() {
        return aveExp;
    }

    /**
     * Sets the value of the aveExp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAveExp(Double value) {
        this.aveExp = value;
    }

    /**
     * Gets the value of the clv property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getClv() {
        return clv;
    }

    /**
     * Sets the value of the clv property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setClv(Double value) {
        this.clv = value;
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
     * Gets the value of the visitThisMonth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getVisitThisMonth() {
        return visitThisMonth;
    }

    /**
     * Sets the value of the visitThisMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setVisitThisMonth(Integer value) {
        this.visitThisMonth = value;
    }

}
