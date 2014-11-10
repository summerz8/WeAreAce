/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.OCRM.MemberEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dan
 */
@Local
public interface CustomerMobileAppModuleLocal {

//    public List<CountryProductEntity> getWishListItem() ;
//
//    public List<CountrySetEntity> getWishListSet() ;
//
//    public CountryProductEntity viewItemDetail() ;
//
//    public List<CountryProductEntity> getSetItem() ;
//
//    public List<CountryProductEntity> getWishListItemByCountry() ;
//
//    public List<CountryProductEntity> getWishListItemByStore() ;
//
//    public List<CountrySetEntity> getWishListSetByCountry() ;
//
//    public List<CountrySetEntity> getWishListSetByStore() ;

    public int MemberLogin(String email, String pwd);

    public MemberEntity getMember(String email);

    public Integer checkSurprise(String QR, String email);

}
