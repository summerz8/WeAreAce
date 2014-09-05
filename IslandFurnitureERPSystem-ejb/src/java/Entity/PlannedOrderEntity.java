/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Yoky
 */
@Entity
public class PlannedOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pannedOrderId;
    private ProductEntity product;
    private Date date;
    private List<PlannedOrderItemEntity> items;

    public PlannedOrderEntity() {
    }

    public Long getPannedOrderId() {
        return pannedOrderId;
    }

    public void setPannedOrderId(Long pannedOrderId) {
        this.pannedOrderId = pannedOrderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pannedOrderId != null ? pannedOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the pannedOrderId fields are not set
        if (!(object instanceof PlannedOrderEntity)) {
            return false;
        }
        PlannedOrderEntity other = (PlannedOrderEntity) object;
        if ((this.pannedOrderId == null && other.pannedOrderId != null) || (this.pannedOrderId != null && !this.pannedOrderId.equals(other.pannedOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PlannedOrderEntity[ id=" + pannedOrderId + " ]";
    }
    
}
