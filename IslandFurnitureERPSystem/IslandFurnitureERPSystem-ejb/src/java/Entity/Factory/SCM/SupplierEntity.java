/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author apple
 */
@Entity
@Table(name = "Supplier")
public class SupplierEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierId;
    private String supplierName;
    private String supplierAddress;
    private Integer supplierContact;
    private Integer supplierFax;
    private String remark;
//    @OneToMany(cascade={CascadeType.PERSIST})
//    private Set<ContractEntity> ContractList=new HashSet<ContractEntity>();

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long id) {
        this.supplierId = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

//    public Set<ContractEntity> getContractList() {
//        return ContractList;
//    }
//
//    public void setContractList(Set<ContractEntity> ContractlList) {
//        this.ContractList = ContractList;
//    }
    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public Integer getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(Integer supplierContact) {
        this.supplierContact = supplierContact;
    }

    public Integer getSupplierFax() {
        return supplierFax;
    }

    public void setSupplierFax(Integer supplierFax) {
        this.supplierFax = supplierFax;
    }

    public String getremark() {
        return remark;
    }

    public void setremark(String remark) {
        this.remark = remark;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierId != null ? supplierId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierEntity)) {
            return false;
        }
        SupplierEntity other = (SupplierEntity) object;
        if ((this.supplierId == null && other.supplierId != null) || (this.supplierId != null && !this.supplierId.equals(other.supplierId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Factory.SupplierEntity[ id=" + supplierId + " ]";
    }

}
