/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Factory.SCM;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Yoky
 */
@Entity
@Table(name = "Supplier")
public class SupplierEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierId;
    private String name;
    private Integer countryCode;
    private String address;
    private String tel;
    private String email;
    private String fax;
    private String remark;
    @OneToMany(mappedBy = "supplierContract")
    private List<SupplierContractEntity> supplierContracts= new ArrayList<SupplierContractEntity>();

    public SupplierEntity() {
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SupplierContractEntity> getSupplierContracts() {
        return supplierContracts;
    }

    public void setSupplierContracts(List<SupplierContractEntity> supplierContracts) {
        this.supplierContracts = supplierContracts;
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
