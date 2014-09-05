/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author apple
 */
@Entity
public class SupplierEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long SupplierEntity;
    private String Name;
    @ManyToMany(cascade={CascadeType.ALL},mappedBy="supplier")
    private Set<RawMaterialEntity> MaterialList=new HashSet<RawMaterialEntity>();
    
    public Long getId() {
        return SupplierEntity;
    }

    public void setId(Long id) {
        this.SupplierEntity = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (SupplierEntity != null ? SupplierEntity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierEntity)) {
            return false;
        }
        SupplierEntity other = (SupplierEntity) object;
        if ((this.SupplierEntity == null && other.SupplierEntity != null) || (this.SupplierEntity != null && !this.SupplierEntity.equals(other.SupplierEntity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.SupplierEntity[ id=" + SupplierEntity + " ]";
    }
    
}
