/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SessionBean.OCRM;

import Entity.CommonInfrastructure.UserEntity;
import Entity.Store.OCRM.MemberEntity;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author dan
 */
@Local
public interface MemberRegistrationModuleLocal {
    
    public MemberEntity getMember(String Email);

    public void AddMember(String lastName, String midName, String firstName, Calendar birthday, String gender, String title, String address, String postalCode, String email);

    public void DeleteMember(Long userId);

    public void ModifyMember(Long userId, String lastName, String midName, String firstName, Calendar birthday, String gender, String title, String address, String postalCode, String email);

    public List<MemberEntity> ListMember();
    
    public void AddMemberWithPassword(String lastName, String midName, String firstName, Calendar birthday, String gender, String title, String address, String postalCode, String email, String password);
    
    public MemberEntity memberLogin(String email, String pwd);
}
