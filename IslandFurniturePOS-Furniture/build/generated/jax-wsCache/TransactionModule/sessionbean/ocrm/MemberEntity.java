
package sessionbean.ocrm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for memberEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="memberEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="memberId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="pwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="midName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="storeId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="birthday" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentPoints" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="totalPoints" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="pointsToUpgrade" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="deleteFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="memberlvl" type="{http://OCRM.SessionBean/}membershipLevelEntity" minOccurs="0"/>
 *         &lt;element name="rfm" type="{http://OCRM.SessionBean/}rfmEntity" minOccurs="0"/>
 *         &lt;element name="clv" type="{http://OCRM.SessionBean/}clvEntity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "memberEntity", propOrder = {
    "memberId",
    "pwd",
    "lastName",
    "midName",
    "firstName",
    "storeId",
    "birthday",
    "gender",
    "title",
    "address",
    "postalCode",
    "email",
    "createDate",
    "country",
    "currentPoints",
    "totalPoints",
    "pointsToUpgrade",
    "deleteFlag",
    "memberlvl",
    "rfm",
    "clv"
})
public class MemberEntity {

    protected Long memberId;
    protected String pwd;
    protected String lastName;
    protected String midName;
    protected String firstName;
    protected Long storeId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar birthday;
    protected String gender;
    protected String title;
    protected String address;
    protected String postalCode;
    protected String email;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    protected String country;
    protected Double currentPoints;
    protected Double totalPoints;
    protected Double pointsToUpgrade;
    protected Boolean deleteFlag;
    protected MembershipLevelEntity memberlvl;
    protected RfmEntity rfm;
    protected ClvEntity clv;

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
     * Gets the value of the pwd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Sets the value of the pwd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPwd(String value) {
        this.pwd = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the midName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMidName() {
        return midName;
    }

    /**
     * Sets the value of the midName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMidName(String value) {
        this.midName = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the storeId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * Sets the value of the storeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStoreId(Long value) {
        this.storeId = value;
    }

    /**
     * Gets the value of the birthday property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthday() {
        return birthday;
    }

    /**
     * Sets the value of the birthday property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthday(XMLGregorianCalendar value) {
        this.birthday = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the currentPoints property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCurrentPoints() {
        return currentPoints;
    }

    /**
     * Sets the value of the currentPoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCurrentPoints(Double value) {
        this.currentPoints = value;
    }

    /**
     * Gets the value of the totalPoints property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalPoints() {
        return totalPoints;
    }

    /**
     * Sets the value of the totalPoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalPoints(Double value) {
        this.totalPoints = value;
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

    /**
     * Gets the value of the deleteFlag property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDeleteFlag() {
        return deleteFlag;
    }

    /**
     * Sets the value of the deleteFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDeleteFlag(Boolean value) {
        this.deleteFlag = value;
    }

    /**
     * Gets the value of the memberlvl property.
     * 
     * @return
     *     possible object is
     *     {@link MembershipLevelEntity }
     *     
     */
    public MembershipLevelEntity getMemberlvl() {
        return memberlvl;
    }

    /**
     * Sets the value of the memberlvl property.
     * 
     * @param value
     *     allowed object is
     *     {@link MembershipLevelEntity }
     *     
     */
    public void setMemberlvl(MembershipLevelEntity value) {
        this.memberlvl = value;
    }

    /**
     * Gets the value of the rfm property.
     * 
     * @return
     *     possible object is
     *     {@link RfmEntity }
     *     
     */
    public RfmEntity getRfm() {
        return rfm;
    }

    /**
     * Sets the value of the rfm property.
     * 
     * @param value
     *     allowed object is
     *     {@link RfmEntity }
     *     
     */
    public void setRfm(RfmEntity value) {
        this.rfm = value;
    }

    /**
     * Gets the value of the clv property.
     * 
     * @return
     *     possible object is
     *     {@link ClvEntity }
     *     
     */
    public ClvEntity getClv() {
        return clv;
    }

    /**
     * Sets the value of the clv property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClvEntity }
     *     
     */
    public void setClv(ClvEntity value) {
        this.clv = value;
    }

}
