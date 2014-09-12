/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private String supplierContact;
    private String supplierFax;
    private String remark;

    //contract entity -- supplier entity: M<-->1
    @OneToMany(cascade = {CascadeType.PERSIST})
    private Collection<ContractEntity> ContractList = new ArrayList<ContractEntity>();

    public SupplierEntity() {
        //may be changed later
        setSupplierId(System.nanoTime());
    }

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

    public Collection<ContractEntity> getContractList() {
        return ContractList;
    }

    public void setContractList(Collection<ContractEntity> ContractlList) {
        this.ContractList = ContractList;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(String supplierContact) {
        this.supplierContact = supplierContact;
    }

    public String getSupplierFax() {
        return supplierFax;
    }

    public void setSupplierFax(String supplierFax) {
        this.supplierFax = supplierFax;
    }

    public String getremark() {
        return remark;
    }

    public void setremark(String remark) {
        this.remark = remark;
    }

    //create a new supplier entity with attributes
    public void create(String name, String address, String contact, String fax, String remark) {
        this.supplierName = name;
        this.supplierAddress = address;
        this.supplierContact = contact;
        this.supplierFax = fax;
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
