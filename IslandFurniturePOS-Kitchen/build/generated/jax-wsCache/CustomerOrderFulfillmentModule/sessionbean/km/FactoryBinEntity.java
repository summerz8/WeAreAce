
package sessionbean.km;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for factoryBinEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="factoryBinEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="factory" type="{http://KM.SessionBean/}factoryEntity" minOccurs="0"/>
 *         &lt;element name="factoryBinId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="factoryBinStoredProducts" type="{http://KM.SessionBean/}factoryBinStoredProductEntity" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "factoryBinEntity", propOrder = {
    "factory",
    "factoryBinId",
    "factoryBinStoredProducts"
})
public class FactoryBinEntity {

    protected FactoryEntity factory;
    protected Long factoryBinId;
    @XmlElement(nillable = true)
    protected List<FactoryBinStoredProductEntity> factoryBinStoredProducts;

    /**
     * Gets the value of the factory property.
     * 
     * @return
     *     possible object is
     *     {@link FactoryEntity }
     *     
     */
    public FactoryEntity getFactory() {
        return factory;
    }

    /**
     * Sets the value of the factory property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactoryEntity }
     *     
     */
    public void setFactory(FactoryEntity value) {
        this.factory = value;
    }

    /**
     * Gets the value of the factoryBinId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFactoryBinId() {
        return factoryBinId;
    }

    /**
     * Sets the value of the factoryBinId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFactoryBinId(Long value) {
        this.factoryBinId = value;
    }

    /**
     * Gets the value of the factoryBinStoredProducts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the factoryBinStoredProducts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFactoryBinStoredProducts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FactoryBinStoredProductEntity }
     * 
     * 
     */
    public List<FactoryBinStoredProductEntity> getFactoryBinStoredProducts() {
        if (factoryBinStoredProducts == null) {
            factoryBinStoredProducts = new ArrayList<FactoryBinStoredProductEntity>();
        }
        return this.factoryBinStoredProducts;
    }

}
