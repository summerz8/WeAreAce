/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.CommonInFrastructure;

import Entity.Factory.RawMaterialEntity;
import java.util.ArrayList;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dan zy
 */
@Stateful
public class EnterpriseInventoryManagementModule_RawMaterial implements EnterpriseInventoryManagementModule_RawMaterialLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext
    private EntityManager em;
    public EnterpriseInventoryManagementModule_RawMaterial(){
    }
    
    @Override
    public void addRawMaterial(String name, String description){
       System.out.println("EnterpriseInventoryManagementModule_RawMaterial: add Raw Material()");
       RawMaterialEntity rawMaterial = new RawMaterialEntity();
       rawMaterial.setMaterialName(name);
       rawMaterial.setDescription(description);
       em.persist(rawMaterial);
    }
    @Override
    public void deleteRawMaterial(long rawMaterialId) throws Exception{
       System.out.println("EnterpriseInventoryManagementModule_RawMaterial: delete Raw Material()");
       RawMaterialEntity rawMaterial = em.find(RawMaterialEntity.class, rawMaterialId);
       if(rawMaterial == null){
           throw new Exception ("Raw Material is not found.");
       }
       else{
       
             
       
       }
       
       em.persist(rawMaterial);
    }
    
    @Override
    public void modifyRawMaterial(long rawMaterialId, String name, String description) throws Exception{    
       System.out.println("EnterpriseInventoryManagementModule_RawMaterial: modify Raw Material()");
       RawMaterialEntity rawMaterial = em.find(RawMaterialEntity.class,rawMaterialId);
       if (rawMaterial == null) {
           throw new Exception ("Raw Material is not found.");
       }
       else{
           rawMaterial.setMaterialName(name);
           rawMaterial.setDescription(description);
           
       }
       
       em.persist(rawMaterial);
    }
    
 
    
    @Override
    public ArrayList<RawMaterialEntity> listRawMaterial(){   
        System.out.println("EnterpriseInventoryManagementModule_RawMaterial: list Material()");
        ArrayList<RawMaterialEntity> rmList = new ArrayList<RawMaterialEntity>();
        Query q = em.createQuery("Select rm From RawMaterialEntity rm");
        for( Object o: q.getResultList()){
            RawMaterialEntity rm = (RawMaterialEntity) o;
            rmList.add(rm);
        } 
        return rmList;
    }
    
    
    
    
    
   
}
