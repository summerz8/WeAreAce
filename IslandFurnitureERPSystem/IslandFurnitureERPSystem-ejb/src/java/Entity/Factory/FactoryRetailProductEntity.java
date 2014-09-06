/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.Factory;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author zhengyuan
 */
@Entity
        @Table(name="FactoryRetailProduct")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class FactoryRetailProductEntity extends FactoryItemEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public FactoryRetailProductEntity(){
        
    }
  
}
