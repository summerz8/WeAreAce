/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.OCRM;

import Entity.Store.OCRM.VoucherEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dan
 */
@Local
public interface VoucherManagementModuleLocal {

    public void AddVoucher(String name, String description, Double value);

    public void DeleteVoucher(Long voucherId);

    public void FulfillVoucher(Long voucherId);

    public void ModifyVoucher(Long voucherId, String name, String description, Double value);

    public List<VoucherEntity> ListVoucher();
    
}
