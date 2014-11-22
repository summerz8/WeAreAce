
package sessionbean.km;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rawMaterialEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rawMaterialEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bomList" type="{http://KM.SessionBean/}bomEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="factoryRawMaterials" type="{http://KM.SessionBean/}factoryRawMaterialEntity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="materialId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="materialName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rawMaterialEntity", propOrder = {
    "bomList",
    "description",
    "factoryRawMaterials",
    "materialId",
    "materialName",
    "unit"
})
public class RawMaterialEntity {

    @XmlElement(nillable = true)
    protected List<BomEntity> bomList;
    protected String description;
    @XmlElement(nillable = true)
    protected List<FactoryRawMaterialEntity> factoryRawMaterials;
    protected Long materialId;
    protected String materialName;
    protected String unit;

    /**
     * Gets the value of the bomList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bomList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBomList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BomEntity }
     * 
     * 
     */
    public List<BomEntity> getBomList() {
        if (bomList == null) {
            bomList = new ArrayList<BomEntity>();
        }
        return this.bomList;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the factoryRawMaterials property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the factoryRawMaterials property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFactoryRawMaterials().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FactoryRawMaterialEntity }
     * 
     * 
     */
    public List<FactoryRawMaterialEntity> getFactoryRawMaterials() {
        if (factoryRawMaterials == null) {
            factoryRawMaterials = new ArrayList<FactoryRawMaterialEntity>();
        }
        return this.factoryRawMaterials;
    }

    /**
     * Gets the value of the materialId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getMaterialId() {
        return materialId;
    }

    /**
     * Sets the value of the materialId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setMaterialId(Long value) {
        this.materialId = value;
    }

    /**
     * Gets the value of the materialName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * Sets the value of the materialName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterialName(String value) {
        this.materialName = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

}
