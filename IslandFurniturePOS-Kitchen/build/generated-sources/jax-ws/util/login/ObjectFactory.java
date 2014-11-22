
package util.login;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the util.login package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ShopLogin_QNAME = new QName("http://login.util/", "shopLogin");
    private final static QName _GetUserInfo_QNAME = new QName("http://login.util/", "getUserInfo");
    private final static QName _UpdateEndCashResponse_QNAME = new QName("http://login.util/", "updateEndCashResponse");
    private final static QName _ValidateUser_QNAME = new QName("http://login.util/", "validateUser");
    private final static QName _Logout_QNAME = new QName("http://login.util/", "logout");
    private final static QName _ShopLoginResponse_QNAME = new QName("http://login.util/", "shopLoginResponse");
    private final static QName _GetFullNameByIdResponse_QNAME = new QName("http://login.util/", "getFullNameByIdResponse");
    private final static QName _GetCasherById_QNAME = new QName("http://login.util/", "getCasherById");
    private final static QName _GetUserInfoResponse_QNAME = new QName("http://login.util/", "getUserInfoResponse");
    private final static QName _ValidateUserResponse_QNAME = new QName("http://login.util/", "validateUserResponse");
    private final static QName _UpdateEndCash_QNAME = new QName("http://login.util/", "updateEndCash");
    private final static QName _GetCasherByIdResponse_QNAME = new QName("http://login.util/", "getCasherByIdResponse");
    private final static QName _LogoutResponse_QNAME = new QName("http://login.util/", "logoutResponse");
    private final static QName _GetFullNameById_QNAME = new QName("http://login.util/", "getFullNameById");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: util.login
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetFullNameById }
     * 
     */
    public GetFullNameById createGetFullNameById() {
        return new GetFullNameById();
    }

    /**
     * Create an instance of {@link GetCasherByIdResponse }
     * 
     */
    public GetCasherByIdResponse createGetCasherByIdResponse() {
        return new GetCasherByIdResponse();
    }

    /**
     * Create an instance of {@link LogoutResponse }
     * 
     */
    public LogoutResponse createLogoutResponse() {
        return new LogoutResponse();
    }

    /**
     * Create an instance of {@link UpdateEndCash }
     * 
     */
    public UpdateEndCash createUpdateEndCash() {
        return new UpdateEndCash();
    }

    /**
     * Create an instance of {@link ValidateUserResponse }
     * 
     */
    public ValidateUserResponse createValidateUserResponse() {
        return new ValidateUserResponse();
    }

    /**
     * Create an instance of {@link GetUserInfoResponse }
     * 
     */
    public GetUserInfoResponse createGetUserInfoResponse() {
        return new GetUserInfoResponse();
    }

    /**
     * Create an instance of {@link GetCasherById }
     * 
     */
    public GetCasherById createGetCasherById() {
        return new GetCasherById();
    }

    /**
     * Create an instance of {@link GetFullNameByIdResponse }
     * 
     */
    public GetFullNameByIdResponse createGetFullNameByIdResponse() {
        return new GetFullNameByIdResponse();
    }

    /**
     * Create an instance of {@link ShopLoginResponse }
     * 
     */
    public ShopLoginResponse createShopLoginResponse() {
        return new ShopLoginResponse();
    }

    /**
     * Create an instance of {@link Logout }
     * 
     */
    public Logout createLogout() {
        return new Logout();
    }

    /**
     * Create an instance of {@link ValidateUser }
     * 
     */
    public ValidateUser createValidateUser() {
        return new ValidateUser();
    }

    /**
     * Create an instance of {@link UpdateEndCashResponse }
     * 
     */
    public UpdateEndCashResponse createUpdateEndCashResponse() {
        return new UpdateEndCashResponse();
    }

    /**
     * Create an instance of {@link GetUserInfo }
     * 
     */
    public GetUserInfo createGetUserInfo() {
        return new GetUserInfo();
    }

    /**
     * Create an instance of {@link ShopLogin }
     * 
     */
    public ShopLogin createShopLogin() {
        return new ShopLogin();
    }

    /**
     * Create an instance of {@link UserEntity }
     * 
     */
    public UserEntity createUserEntity() {
        return new UserEntity();
    }

    /**
     * Create an instance of {@link InternalMessageEntity }
     * 
     */
    public InternalMessageEntity createInternalMessageEntity() {
        return new InternalMessageEntity();
    }

    /**
     * Create an instance of {@link InternalMessageReceive }
     * 
     */
    public InternalMessageReceive createInternalMessageReceive() {
        return new InternalMessageReceive();
    }

    /**
     * Create an instance of {@link TicketEntity }
     * 
     */
    public TicketEntity createTicketEntity() {
        return new TicketEntity();
    }

    /**
     * Create an instance of {@link StoreUserEntity }
     * 
     */
    public StoreUserEntity createStoreUserEntity() {
        return new StoreUserEntity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShopLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.util/", name = "shopLogin")
    public JAXBElement<ShopLogin> createShopLogin(ShopLogin value) {
        return new JAXBElement<ShopLogin>(_ShopLogin_QNAME, ShopLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.util/", name = "getUserInfo")
    public JAXBElement<GetUserInfo> createGetUserInfo(GetUserInfo value) {
        return new JAXBElement<GetUserInfo>(_GetUserInfo_QNAME, GetUserInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateEndCashResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.util/", name = "updateEndCashResponse")
    public JAXBElement<UpdateEndCashResponse> createUpdateEndCashResponse(UpdateEndCashResponse value) {
        return new JAXBElement<UpdateEndCashResponse>(_UpdateEndCashResponse_QNAME, UpdateEndCashResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.util/", name = "validateUser")
    public JAXBElement<ValidateUser> createValidateUser(ValidateUser value) {
        return new JAXBElement<ValidateUser>(_ValidateUser_QNAME, ValidateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Logout }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.util/", name = "logout")
    public JAXBElement<Logout> createLogout(Logout value) {
        return new JAXBElement<Logout>(_Logout_QNAME, Logout.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShopLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.util/", name = "shopLoginResponse")
    public JAXBElement<ShopLoginResponse> createShopLoginResponse(ShopLoginResponse value) {
        return new JAXBElement<ShopLoginResponse>(_ShopLoginResponse_QNAME, ShopLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFullNameByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.util/", name = "getFullNameByIdResponse")
    public JAXBElement<GetFullNameByIdResponse> createGetFullNameByIdResponse(GetFullNameByIdResponse value) {
        return new JAXBElement<GetFullNameByIdResponse>(_GetFullNameByIdResponse_QNAME, GetFullNameByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCasherById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.util/", name = "getCasherById")
    public JAXBElement<GetCasherById> createGetCasherById(GetCasherById value) {
        return new JAXBElement<GetCasherById>(_GetCasherById_QNAME, GetCasherById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.util/", name = "getUserInfoResponse")
    public JAXBElement<GetUserInfoResponse> createGetUserInfoResponse(GetUserInfoResponse value) {
        return new JAXBElement<GetUserInfoResponse>(_GetUserInfoResponse_QNAME, GetUserInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.util/", name = "validateUserResponse")
    public JAXBElement<ValidateUserResponse> createValidateUserResponse(ValidateUserResponse value) {
        return new JAXBElement<ValidateUserResponse>(_ValidateUserResponse_QNAME, ValidateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateEndCash }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.util/", name = "updateEndCash")
    public JAXBElement<UpdateEndCash> createUpdateEndCash(UpdateEndCash value) {
        return new JAXBElement<UpdateEndCash>(_UpdateEndCash_QNAME, UpdateEndCash.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCasherByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.util/", name = "getCasherByIdResponse")
    public JAXBElement<GetCasherByIdResponse> createGetCasherByIdResponse(GetCasherByIdResponse value) {
        return new JAXBElement<GetCasherByIdResponse>(_GetCasherByIdResponse_QNAME, GetCasherByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogoutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.util/", name = "logoutResponse")
    public JAXBElement<LogoutResponse> createLogoutResponse(LogoutResponse value) {
        return new JAXBElement<LogoutResponse>(_LogoutResponse_QNAME, LogoutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFullNameById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://login.util/", name = "getFullNameById")
    public JAXBElement<GetFullNameById> createGetFullNameById(GetFullNameById value) {
        return new JAXBElement<GetFullNameById>(_GetFullNameById_QNAME, GetFullNameById.class, null, value);
    }

}
