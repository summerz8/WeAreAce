/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author dan
 */
@Entity
@Table(name="HQ_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class HQUserEntity extends UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public HQUserEntity() {
    }
   
}
