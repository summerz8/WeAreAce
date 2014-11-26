
package util.login;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for internalMessageReceive complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="internalMessageReceive">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="calendarTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="message" type="{http://login.util/}internalMessageEntity" minOccurs="0"/>
 *         &lt;element name="opened" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="receivedMessageid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="receiver" type="{http://login.util/}userEntity" minOccurs="0"/>
 *         &lt;element name="replied" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="sendMessage" type="{http://login.util/}internalMessageEntity" minOccurs="0"/>
 *         &lt;element name="senderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "internalMessageReceive", propOrder = {
    "calendarTime",
    "content",
    "deleted",
    "message",
    "opened",
    "receivedMessageid",
    "receiver",
    "replied",
    "sendMessage",
    "senderId",
    "title"
})
public class InternalMessageReceive {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar calendarTime;
    protected String content;
    protected boolean deleted;
    protected InternalMessageEntity message;
    protected boolean opened;
    protected Long receivedMessageid;
    protected UserEntity receiver;
    protected Boolean replied;
    protected InternalMessageEntity sendMessage;
    protected String senderId;
    protected String title;

    /**
     * Gets the value of the calendarTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCalendarTime() {
        return calendarTime;
    }

    /**
     * Sets the value of the calendarTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCalendarTime(XMLGregorianCalendar value) {
        this.calendarTime = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the deleted property.
     * 
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the value of the deleted property.
     * 
     */
    public void setDeleted(boolean value) {
        this.deleted = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link InternalMessageEntity }
     *     
     */
    public InternalMessageEntity getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link InternalMessageEntity }
     *     
     */
    public void setMessage(InternalMessageEntity value) {
        this.message = value;
    }

    /**
     * Gets the value of the opened property.
     * 
     */
    public boolean isOpened() {
        return opened;
    }

    /**
     * Sets the value of the opened property.
     * 
     */
    public void setOpened(boolean value) {
        this.opened = value;
    }

    /**
     * Gets the value of the receivedMessageid property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getReceivedMessageid() {
        return receivedMessageid;
    }

    /**
     * Sets the value of the receivedMessageid property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setReceivedMessageid(Long value) {
        this.receivedMessageid = value;
    }

    /**
     * Gets the value of the receiver property.
     * 
     * @return
     *     possible object is
     *     {@link UserEntity }
     *     
     */
    public UserEntity getReceiver() {
        return receiver;
    }

    /**
     * Sets the value of the receiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserEntity }
     *     
     */
    public void setReceiver(UserEntity value) {
        this.receiver = value;
    }

    /**
     * Gets the value of the replied property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReplied() {
        return replied;
    }

    /**
     * Sets the value of the replied property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReplied(Boolean value) {
        this.replied = value;
    }

    /**
     * Gets the value of the sendMessage property.
     * 
     * @return
     *     possible object is
     *     {@link InternalMessageEntity }
     *     
     */
    public InternalMessageEntity getSendMessage() {
        return sendMessage;
    }

    /**
     * Sets the value of the sendMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link InternalMessageEntity }
     *     
     */
    public void setSendMessage(InternalMessageEntity value) {
        this.sendMessage = value;
    }

    /**
     * Gets the value of the senderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * Sets the value of the senderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderId(String value) {
        this.senderId = value;
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

}
