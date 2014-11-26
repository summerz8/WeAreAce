
package sessionbean.km;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for factoryBinStoredProductEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="factoryBinStoredProductEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="factoryBinStoredProductId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="stockTypeIndicator" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "factoryBinStoredProductEntity", propOrder = {
    "factoryBinStoredProductId",
    "amount",
    "stockTypeIndicator",
    "status"
})
public class FactoryBinStoredProductEntity {

    protected Long factoryBinStoredProductId;
    protected Double amount;
    protected int stockTypeIndicator;
    protected String status;

    /**
     * Gets the value of the factoryBinStoredProductId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFactoryBinStoredProductId() {
        return factoryBinStoredProductId;
    }

    /**
     * Sets the value of the factoryBinStoredProductId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFactoryBinStoredProductId(Long value) {
        this.factoryBinStoredProductId = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAmount(Double value) {
        this.amount = value;
    }

    /**
     * Gets the value of the stockTypeIndicator property.
     * 
     */
    public int getStockTypeIndicator() {
        return stockTypeIndicator;
    }

    /**
     * Sets the value of the stockTypeIndicator property.
     * 
     */
    public void setStockTypeIndicator(int value) {
        this.stockTypeIndicator = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
