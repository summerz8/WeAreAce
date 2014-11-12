/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SessionBean.OCRM;

import Entity.Store.OCRM.MemberEntity;
import Entity.Store.StoreEntity;
import Entity.Store.StoreEventEntity;
import java.io.File;
import java.util.List;
import javax.ejb.Local;
import javax.jws.WebParam;

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

    public List<String[]> getWishListItem(String email, Long store);

    public List<StoreEntity> getStoreList();

    public String getItemPicture(File imageFile);

    public String[] viewItemDetail(Long storeProductId, String email);

    public List<String[]> getSetItemList(String email, Long setId);

    public List<StoreEventEntity> getEventList(Long storeId);

}
