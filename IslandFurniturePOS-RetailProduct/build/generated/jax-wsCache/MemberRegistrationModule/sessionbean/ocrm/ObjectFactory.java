
package sessionbean.ocrm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the sessionbean.ocrm package. 
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

    private final static QName _GetMemberByIdResponse_QNAME = new QName("http://OCRM.SessionBean/", "getMemberByIdResponse");
    private final static QName _Redemption_QNAME = new QName("http://OCRM.SessionBean/", "redemption");
    private final static QName _AddNewPointsForMember_QNAME = new QName("http://OCRM.SessionBean/", "addNewPointsForMember");
    private final static QName _GetMemberCardIdById_QNAME = new QName("http://OCRM.SessionBean/", "getMemberCardIdById");
    private final static QName _CheckMember_QNAME = new QName("http://OCRM.SessionBean/", "checkMember");
    private final static QName _CheckEmail_QNAME = new QName("http://OCRM.SessionBean/", "checkEmail");
    private final static QName _AddNewPointsForMemberResponse_QNAME = new QName("http://OCRM.SessionBean/", "addNewPointsForMemberResponse");
    private final static QName _GetMemberIdByCardId_QNAME = new QName("http://OCRM.SessionBean/", "getMemberIdByCardId");
    private final static QName _CheckEmailResponse_QNAME = new QName("http://OCRM.SessionBean/", "checkEmailResponse");
    private final static QName _CheckMemberResponse_QNAME = new QName("http://OCRM.SessionBean/", "checkMemberResponse");
    private final static QName _GetMemberById_QNAME = new QName("http://OCRM.SessionBean/", "getMemberById");
    private final static QName _GetMemberIdByCardIdResponse_QNAME = new QName("http://OCRM.SessionBean/", "getMemberIdByCardIdResponse");
    private final static QName _RedemptionResponse_QNAME = new QName("http://OCRM.SessionBean/", "redemptionResponse");
    private final static QName _GetMemberCardIdByIdResponse_QNAME = new QName("http://OCRM.SessionBean/", "getMemberCardIdByIdResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sessionbean.ocrm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CheckMemberResponse }
     * 
     */
    public CheckMemberResponse createCheckMemberResponse() {
        return new CheckMemberResponse();
    }

    /**
     * Create an instance of {@link CheckEmailResponse }
     * 
     */
    public CheckEmailResponse createCheckEmailResponse() {
        return new CheckEmailResponse();
    }

    /**
     * Create an instance of {@link GetMemberById }
     * 
     */
    public GetMemberById createGetMemberById() {
        return new GetMemberById();
    }

    /**
     * Create an instance of {@link GetMemberIdByCardIdResponse }
     * 
     */
    public GetMemberIdByCardIdResponse createGetMemberIdByCardIdResponse() {
        return new GetMemberIdByCardIdResponse();
    }

    /**
     * Create an instance of {@link RedemptionResponse }
     * 
     */
    public RedemptionResponse createRedemptionResponse() {
        return new RedemptionResponse();
    }

    /**
     * Create an instance of {@link GetMemberCardIdByIdResponse }
     * 
     */
    public GetMemberCardIdByIdResponse createGetMemberCardIdByIdResponse() {
        return new GetMemberCardIdByIdResponse();
    }

    /**
     * Create an instance of {@link Redemption }
     * 
     */
    public Redemption createRedemption() {
        return new Redemption();
    }

    /**
     * Create an instance of {@link AddNewPointsForMember }
     * 
     */
    public AddNewPointsForMember createAddNewPointsForMember() {
        return new AddNewPointsForMember();
    }

    /**
     * Create an instance of {@link GetMemberCardIdById }
     * 
     */
    public GetMemberCardIdById createGetMemberCardIdById() {
        return new GetMemberCardIdById();
    }

    /**
     * Create an instance of {@link GetMemberByIdResponse }
     * 
     */
    public GetMemberByIdResponse createGetMemberByIdResponse() {
        return new GetMemberByIdResponse();
    }

    /**
     * Create an instance of {@link AddNewPointsForMemberResponse }
     * 
     */
    public AddNewPointsForMemberResponse createAddNewPointsForMemberResponse() {
        return new AddNewPointsForMemberResponse();
    }

    /**
     * Create an instance of {@link GetMemberIdByCardId }
     * 
     */
    public GetMemberIdByCardId createGetMemberIdByCardId() {
        return new GetMemberIdByCardId();
    }

    /**
     * Create an instance of {@link CheckEmail }
     * 
     */
    public CheckEmail createCheckEmail() {
        return new CheckEmail();
    }

    /**
     * Create an instance of {@link CheckMember }
     * 
     */
    public CheckMember createCheckMember() {
        return new CheckMember();
    }

    /**
     * Create an instance of {@link RfmEntity }
     * 
     */
    public RfmEntity createRfmEntity() {
        return new RfmEntity();
    }

    /**
     * Create an instance of {@link ClvEntity }
     * 
     */
    public ClvEntity createClvEntity() {
        return new ClvEntity();
    }

    /**
     * Create an instance of {@link MemberEntity }
     * 
     */
    public MemberEntity createMemberEntity() {
        return new MemberEntity();
    }

    /**
     * Create an instance of {@link MembershipLevelEntity }
     * 
     */
    public MembershipLevelEntity createMembershipLevelEntity() {
        return new MembershipLevelEntity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMemberByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://OCRM.SessionBean/", name = "getMemberByIdResponse")
    public JAXBElement<GetMemberByIdResponse> createGetMemberByIdResponse(GetMemberByIdResponse value) {
        return new JAXBElement<GetMemberByIdResponse>(_GetMemberByIdResponse_QNAME, GetMemberByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Redemption }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://OCRM.SessionBean/", name = "redemption")
    public JAXBElement<Redemption> createRedemption(Redemption value) {
        return new JAXBElement<Redemption>(_Redemption_QNAME, Redemption.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewPointsForMember }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://OCRM.SessionBean/", name = "addNewPointsForMember")
    public JAXBElement<AddNewPointsForMember> createAddNewPointsForMember(AddNewPointsForMember value) {
        return new JAXBElement<AddNewPointsForMember>(_AddNewPointsForMember_QNAME, AddNewPointsForMember.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMemberCardIdById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://OCRM.SessionBean/", name = "getMemberCardIdById")
    public JAXBElement<GetMemberCardIdById> createGetMemberCardIdById(GetMemberCardIdById value) {
        return new JAXBElement<GetMemberCardIdById>(_GetMemberCardIdById_QNAME, GetMemberCardIdById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckMember }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://OCRM.SessionBean/", name = "checkMember")
    public JAXBElement<CheckMember> createCheckMember(CheckMember value) {
        return new JAXBElement<CheckMember>(_CheckMember_QNAME, CheckMember.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckEmail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://OCRM.SessionBean/", name = "checkEmail")
    public JAXBElement<CheckEmail> createCheckEmail(CheckEmail value) {
        return new JAXBElement<CheckEmail>(_CheckEmail_QNAME, CheckEmail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewPointsForMemberResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://OCRM.SessionBean/", name = "addNewPointsForMemberResponse")
    public JAXBElement<AddNewPointsForMemberResponse> createAddNewPointsForMemberResponse(AddNewPointsForMemberResponse value) {
        return new JAXBElement<AddNewPointsForMemberResponse>(_AddNewPointsForMemberResponse_QNAME, AddNewPointsForMemberResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMemberIdByCardId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://OCRM.SessionBean/", name = "getMemberIdByCardId")
    public JAXBElement<GetMemberIdByCardId> createGetMemberIdByCardId(GetMemberIdByCardId value) {
        return new JAXBElement<GetMemberIdByCardId>(_GetMemberIdByCardId_QNAME, GetMemberIdByCardId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckEmailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://OCRM.SessionBean/", name = "checkEmailResponse")
    public JAXBElement<CheckEmailResponse> createCheckEmailResponse(CheckEmailResponse value) {
        return new JAXBElement<CheckEmailResponse>(_CheckEmailResponse_QNAME, CheckEmailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckMemberResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://OCRM.SessionBean/", name = "checkMemberResponse")
    public JAXBElement<CheckMemberResponse> createCheckMemberResponse(CheckMemberResponse value) {
        return new JAXBElement<CheckMemberResponse>(_CheckMemberResponse_QNAME, CheckMemberResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMemberById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://OCRM.SessionBean/", name = "getMemberById")
    public JAXBElement<GetMemberById> createGetMemberById(GetMemberById value) {
        return new JAXBElement<GetMemberById>(_GetMemberById_QNAME, GetMemberById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMemberIdByCardIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://OCRM.SessionBean/", name = "getMemberIdByCardIdResponse")
    public JAXBElement<GetMemberIdByCardIdResponse> createGetMemberIdByCardIdResponse(GetMemberIdByCardIdResponse value) {
        return new JAXBElement<GetMemberIdByCardIdResponse>(_GetMemberIdByCardIdResponse_QNAME, GetMemberIdByCardIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RedemptionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://OCRM.SessionBean/", name = "redemptionResponse")
    public JAXBElement<RedemptionResponse> createRedemptionResponse(RedemptionResponse value) {
        return new JAXBElement<RedemptionResponse>(_RedemptionResponse_QNAME, RedemptionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMemberCardIdByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://OCRM.SessionBean/", name = "getMemberCardIdByIdResponse")
    public JAXBElement<GetMemberCardIdByIdResponse> createGetMemberCardIdByIdResponse(GetMemberCardIdByIdResponse value) {
        return new JAXBElement<GetMemberCardIdByIdResponse>(_GetMemberCardIdByIdResponse_QNAME, GetMemberCardIdByIdResponse.class, null, value);
    }

}
