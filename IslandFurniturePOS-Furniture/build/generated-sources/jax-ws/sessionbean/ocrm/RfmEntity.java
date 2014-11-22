
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rfmEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rfmEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="frequency" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="member" type="{http://OCRM.SessionBean/}memberEntity" minOccurs="0"/>
 *         &lt;element name="monetary" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="recency" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rfmEntity", propOrder = {
    "frequency",
    "id",
    "member",
    "monetary",
    "recency"
})
public class RfmEntity {

    protected Integer frequency;
    protected Long id;
    protected MemberEntity member;
    protected Double monetary;
    protected Integer recency;

    /**
     * Gets the value of the frequency property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFrequency(Integer value) {
        this.frequency = value;
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
     * Gets the value of the monetary property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMonetary() {
        return monetary;
    }

    /**
     * Sets the value of the monetary property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMonetary(Double value) {
        this.monetary = value;
    }

    /**
     * Gets the value of the recency property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRecency() {
        return recency;
    }

    /**
     * Sets the value of the recency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRecency(Integer value) {
        this.recency = value;
    }

}
