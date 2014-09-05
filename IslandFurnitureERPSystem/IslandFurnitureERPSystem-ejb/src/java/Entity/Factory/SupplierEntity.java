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
import javax.persistence.OneToMany;

/**
 *
 * @author apple
 */
@Entity
public class SupplierEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long SupplierID;
    private String Name;
    private String Address;
    private Integer Contact;
    private Integer fax;
    private String Remark;
    @OneToMany(cascade={CascadeType.PERSIST})
    private Set<ContractEntity> ContractList=new HashSet<ContractEntity>();
    
    public Long getSupplierId() {
        return SupplierID;
    }

    public void setSupplierId(Long id) {
        this.SupplierID = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Set<ContractEntity> getContractList() {
        return ContractList;
    }

    public void setContractList(Set<ContractEntity> ContractlList) {
        this.ContractList = ContractList;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public Integer getContact() {
        return Contact;
    }

    public void setContact(Integer Contact) {
        this.Contact = Contact;
    }

    public Integer getFax() {
        return fax;
    }

    public void setFax(Integer fax) {
        this.fax = fax;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (SupplierID != null ? SupplierID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierEntity)) {
            return false;
        }
        SupplierEntity other = (SupplierEntity) object;
        if ((this.SupplierID == null && other.SupplierID != null) || (this.SupplierID != null && !this.SupplierID.equals(other.SupplierID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.SupplierEntity[ id=" + SupplierID + " ]";
    }
    
}
