/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Factory.ProductEntity;
import Entity.Store.OCRM.VoucherEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dan
 */
@Stateless
public class VoucherManagementModule implements VoucherManagementModuleLocal {

    @PersistenceContext
    private EntityManager em;

    public VoucherManagementModule() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void AddVoucher(String name, String description, Double value) {
        System.out.println("VoucherManagementModule: AddVoucher(): ");
        Calendar currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());
        System.out.println("Now the date is :=>  " + dateNow);

        VoucherEntity ve = new VoucherEntity(name, description, value,currentDate, null, false, false);
        em.persist(ve);
        em.flush();
    }

    @Override
    public void DeleteVoucher(Long voucherId) {
        System.out.println("VoucherManagementModule: DeleteVoucher(): " + voucherId);
        VoucherEntity ve = em.find(VoucherEntity.class, voucherId);

        ve.setDeleteFlag(Boolean.TRUE);
        em.persist(ve);
        em.flush();

    }

    @Override
    public void FulfillVoucher(Long voucherId) {
        System.out.println("VoucherManagementModule: DeleteVoucher(): " + voucherId);
        VoucherEntity ve = em.find(VoucherEntity.class, voucherId);

        Calendar currentDate = Calendar.getInstance(); //Get the current date
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); //format it as per your requirement
        String dateNow = formatter.format(currentDate.getTime());
        System.out.println("Now the date is :=>  " + dateNow);
        
        ve.setFulfillFlag(Boolean.TRUE);
        ve.setUsageDate(currentDate);
        em.persist(ve);
        em.flush();

    }
    @Override
    public void ModifyVoucher(Long voucherId, String name, String description,
            Double value) {
        System.out.println("VoucherManagementModule: ModifyVoucher(): " + voucherId);
        VoucherEntity ve = em.find(VoucherEntity.class, voucherId);
        ve.setName(name);
        ve.setDescription(description);
        ve.setValue(value);
        
        em.persist(ve);
        em.flush();

    }

    @Override
    public List<VoucherEntity> ListVoucher() {
        System.out.println("VoucherManagementModule: ListVoucher(): ");
        Query q = em.createQuery("SELECT t FROM VoucherEntity t");
        List requiredVoucherList = new ArrayList();
        for (Object o : q.getResultList()) {
            VoucherEntity u = (VoucherEntity) o;
            if (!u.isDeleteFlag()) {
                requiredVoucherList.add(u);
            }
        }
        return requiredVoucherList;
    }
}
