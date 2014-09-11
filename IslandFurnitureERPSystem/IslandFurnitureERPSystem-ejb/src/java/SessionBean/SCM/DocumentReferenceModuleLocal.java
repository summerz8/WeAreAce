/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.SCM;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Yoky
 */
@Local
public interface DocumentReferenceModuleLocal {

    List ViewPlannedOrder(Long id);

    List<ArrayList> ViewProductionPlan(Long id);

    List viewBlockedStock();

    List viewReturnedProduct();

    List viewPurchaseOrder(Long id);

    List viewGoodsReceipt(Long id);

    List viewContract(Long id);

    List viewSupplier(Long id);
    
}
