/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author zhangshiyu
 */
@Entity
@Table(name = "Factory")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FactoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long factoryId;
    private Integer countryCode;
    private String address;
    private String tel;
    private String manager; // should be managerEntity
   
    public FactoryEntity() {
    }
    

    
    @Override
    public String toString() {
        return "entity.UserEntity[ id=" + factoryId + " ]";
    }
    
}
