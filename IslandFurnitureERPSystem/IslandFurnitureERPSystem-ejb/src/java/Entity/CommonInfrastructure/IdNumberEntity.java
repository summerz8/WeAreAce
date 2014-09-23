/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity.CommonInfrastructure;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author zhangshiyu
 */
@Entity
@Table(name="IdNumber")
public class IdNumberEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private final int id = 0;
    private long id_H;
    private long id_F;
    private long id_S;

    public IdNumberEntity() {
       
    }
    
    public void create(){
     this.setId_F(1000000); 
     this.setId_H(1000000);
     this.setId_S(1000000);
    }
    
    public long getId_H() {
        return id_H;
    }

    public void setId_H(long id_H) {
        this.id_H = id_H;
    }

    public long getId_F() {
        return id_F;
    }

    public void setId_F(long id_F) {
        this.id_F = id_F;
    }

    public long getId_S() {
        return id_S;
    }

    public void setId_S(long id_S) {
        this.id_S = id_S;
    }
  
}