/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.CommonInFrastructure;

import Entity.CommonInfrastructure.EnterpriseManagementModuleLocal;
import Entity.CommonInfrastructure.FactoryUserEntity;
import Entity.CommonInfrastructure.HQUserEntity;
import Entity.CommonInfrastructure.IdNumberEntity;
import Entity.CommonInfrastructure.StoreUserEntity;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;

/**
 *
 * @author dan
 */
@Stateful
public class EnterpriseManagementModule implements EnterpriseManagementModuleLocal {

    private EntityManager em;

    public EnterpriseManagementModule() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void AddStaff(String department, Integer userLevel, String lastName, String firstName, String position, String gender) {
        System.out.println("EnterpriseManagementModule: addManager():");

        Integer idNumber = 0;
        FactoryUserEntity Fuser;
        StoreUserEntity Suser;
        HQUserEntity HQuser;

        IdNumberEntity idNum = em.find(IdNumberEntity.class, 0);

        switch (department) {
            case "H":
                idNumber = idNum.getId_H().intValue() + 1;
                idNum.setId_H((long) idNumber);
                HQuser = new HQUserEntity(department, idNumber.toString(), userLevel, lastName, firstName, position, gender);
                em.persist(HQuser);
                System.out.println("User H"+idNumber.toString()+"created!");
                break;
            case "F":
                idNumber = idNum.getId_F().intValue() + 1;
                idNum.setId_H((long) idNumber);
                Fuser = new FactoryUserEntity(department, idNumber.toString(), userLevel, lastName, firstName, position, gender);
                em.persist(Fuser);
                System.out.println("User F"+idNumber.toString()+"created!");
                break;
            case "S":
                idNumber = idNum.getId_S().intValue() + 1;
                idNum.setId_H((long) idNumber);
                Suser = new StoreUserEntity(department, idNumber.toString(), userLevel, lastName, firstName, position, gender);
                em.persist(Suser);
                System.out.println("User S"+idNumber.toString()+"created!");
                break;
        }

        em.flush();    
    }

    @Override
    public void DeleteStaff() {
    }

    @Override
    public void ModifyStaff() {
    }

    @Override
    public void AddFactory() {
    }

    @Override
    public void DeleteFactory() {
    }

    @Override
    public void ModifyFactory() {
    }

    @Override
    public void AddStore() {
    }

    @Override
    public void DeleteStore() {
    }

    @Override
    public void ModifyStore() {
    }

    @Override
    public void AddProduct() {
    }

    @Override
    public void DeleteProduct() {
    }

    @Override
    public void ModifyProduct() {
    }

    @Override
    public void AddRetailProduct() {
    }

    @Override
    public void DeleteRetailProduct() {
    }

    @Override
    public void ModifyRetailProduct() {
    }
}
