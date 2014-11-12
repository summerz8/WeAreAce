/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import javax.ejb.Local;

/**
 *
 * @author hangsun
 */
@Local
public interface TransactionModuleLocal {

    public Long createNewTransaction(String staffId, Long memberId,int location,String POSid);

    public void createTransactionItem(Long itemId, int amount, Long transactionId);

    public void caculateChange(Long transactionId, Double moneyChange);

    public Double caculateTotalPrice(Long transactionId);
    
}
