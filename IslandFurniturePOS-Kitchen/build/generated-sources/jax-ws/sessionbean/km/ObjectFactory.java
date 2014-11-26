
package sessionbean.km;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the sessionbean.km package. 
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

    private final static QName _ConfirmOrder_QNAME = new QName("http://KM.SessionBean/", "confirmOrder");
    private final static QName _ConfirmOrderResponse_QNAME = new QName("http://KM.SessionBean/", "confirmOrderResponse");
    private final static QName _FindOrderByIdResponse_QNAME = new QName("http://KM.SessionBean/", "findOrderByIdResponse");
    private final static QName _CheckOut_QNAME = new QName("http://KM.SessionBean/", "checkOut");
    private final static QName _DeleteDishItemResponse_QNAME = new QName("http://KM.SessionBean/", "deleteDishItemResponse");
    private final static QName _CreateOrderResponse_QNAME = new QName("http://KM.SessionBean/", "createOrderResponse");
    private final static QName _AddDishItem_QNAME = new QName("http://KM.SessionBean/", "addDishItem");
    private final static QName _DeleteComboItemResponse_QNAME = new QName("http://KM.SessionBean/", "deleteComboItemResponse");
    private final static QName _GetComboByKitchenIdResponse_QNAME = new QName("http://KM.SessionBean/", "getComboByKitchenIdResponse");
    private final static QName _GetDishItemByOrderId_QNAME = new QName("http://KM.SessionBean/", "getDishItemByOrderId");
    private final static QName _GetComboItemByOrderIdResponse_QNAME = new QName("http://KM.SessionBean/", "getComboItemByOrderIdResponse");
    private final static QName _DeleteComboItem_QNAME = new QName("http://KM.SessionBean/", "deleteComboItem");
    private final static QName _GetDishItemByOrderIdResponse_QNAME = new QName("http://KM.SessionBean/", "getDishItemByOrderIdResponse");
    private final static QName _AddComboItemResponse_QNAME = new QName("http://KM.SessionBean/", "addComboItemResponse");
    private final static QName _CheckOutResponse_QNAME = new QName("http://KM.SessionBean/", "checkOutResponse");
    private final static QName _GetDishByKitchenIdResponse_QNAME = new QName("http://KM.SessionBean/", "getDishByKitchenIdResponse");
    private final static QName _GetComboByKitchenId_QNAME = new QName("http://KM.SessionBean/", "getComboByKitchenId");
    private final static QName _FindOrderById_QNAME = new QName("http://KM.SessionBean/", "findOrderById");
    private final static QName _FindKitchenByStoreStaffIdResponse_QNAME = new QName("http://KM.SessionBean/", "findKitchenByStoreStaffIdResponse");
    private final static QName _AddDishItemResponse_QNAME = new QName("http://KM.SessionBean/", "addDishItemResponse");
    private final static QName _DeleteDishItem_QNAME = new QName("http://KM.SessionBean/", "deleteDishItem");
    private final static QName _CancelOrder_QNAME = new QName("http://KM.SessionBean/", "cancelOrder");
    private final static QName _AddComboItem_QNAME = new QName("http://KM.SessionBean/", "addComboItem");
    private final static QName _CancelOrderResponse_QNAME = new QName("http://KM.SessionBean/", "cancelOrderResponse");
    private final static QName _CreateOrder_QNAME = new QName("http://KM.SessionBean/", "createOrder");
    private final static QName _GetComboItemByOrderId_QNAME = new QName("http://KM.SessionBean/", "getComboItemByOrderId");
    private final static QName _GetDishByKitchenId_QNAME = new QName("http://KM.SessionBean/", "getDishByKitchenId");
    private final static QName _FindKitchenByStoreStaffId_QNAME = new QName("http://KM.SessionBean/", "findKitchenByStoreStaffId");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: sessionbean.km
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateOrder }
     * 
     */
    public CreateOrder createCreateOrder() {
        return new CreateOrder();
    }

    /**
     * Create an instance of {@link GetComboItemByOrderId }
     * 
     */
    public GetComboItemByOrderId createGetComboItemByOrderId() {
        return new GetComboItemByOrderId();
    }

    /**
     * Create an instance of {@link AddComboItem }
     * 
     */
    public AddComboItem createAddComboItem() {
        return new AddComboItem();
    }

    /**
     * Create an instance of {@link CancelOrderResponse }
     * 
     */
    public CancelOrderResponse createCancelOrderResponse() {
        return new CancelOrderResponse();
    }

    /**
     * Create an instance of {@link CancelOrder }
     * 
     */
    public CancelOrder createCancelOrder() {
        return new CancelOrder();
    }

    /**
     * Create an instance of {@link DeleteDishItem }
     * 
     */
    public DeleteDishItem createDeleteDishItem() {
        return new DeleteDishItem();
    }

    /**
     * Create an instance of {@link FindKitchenByStoreStaffId }
     * 
     */
    public FindKitchenByStoreStaffId createFindKitchenByStoreStaffId() {
        return new FindKitchenByStoreStaffId();
    }

    /**
     * Create an instance of {@link GetDishByKitchenId }
     * 
     */
    public GetDishByKitchenId createGetDishByKitchenId() {
        return new GetDishByKitchenId();
    }

    /**
     * Create an instance of {@link GetComboByKitchenId }
     * 
     */
    public GetComboByKitchenId createGetComboByKitchenId() {
        return new GetComboByKitchenId();
    }

    /**
     * Create an instance of {@link GetDishByKitchenIdResponse }
     * 
     */
    public GetDishByKitchenIdResponse createGetDishByKitchenIdResponse() {
        return new GetDishByKitchenIdResponse();
    }

    /**
     * Create an instance of {@link AddDishItemResponse }
     * 
     */
    public AddDishItemResponse createAddDishItemResponse() {
        return new AddDishItemResponse();
    }

    /**
     * Create an instance of {@link FindOrderById }
     * 
     */
    public FindOrderById createFindOrderById() {
        return new FindOrderById();
    }

    /**
     * Create an instance of {@link FindKitchenByStoreStaffIdResponse }
     * 
     */
    public FindKitchenByStoreStaffIdResponse createFindKitchenByStoreStaffIdResponse() {
        return new FindKitchenByStoreStaffIdResponse();
    }

    /**
     * Create an instance of {@link GetComboByKitchenIdResponse }
     * 
     */
    public GetComboByKitchenIdResponse createGetComboByKitchenIdResponse() {
        return new GetComboByKitchenIdResponse();
    }

    /**
     * Create an instance of {@link DeleteComboItemResponse }
     * 
     */
    public DeleteComboItemResponse createDeleteComboItemResponse() {
        return new DeleteComboItemResponse();
    }

    /**
     * Create an instance of {@link AddDishItem }
     * 
     */
    public AddDishItem createAddDishItem() {
        return new AddDishItem();
    }

    /**
     * Create an instance of {@link CreateOrderResponse }
     * 
     */
    public CreateOrderResponse createCreateOrderResponse() {
        return new CreateOrderResponse();
    }

    /**
     * Create an instance of {@link CheckOutResponse }
     * 
     */
    public CheckOutResponse createCheckOutResponse() {
        return new CheckOutResponse();
    }

    /**
     * Create an instance of {@link AddComboItemResponse }
     * 
     */
    public AddComboItemResponse createAddComboItemResponse() {
        return new AddComboItemResponse();
    }

    /**
     * Create an instance of {@link DeleteComboItem }
     * 
     */
    public DeleteComboItem createDeleteComboItem() {
        return new DeleteComboItem();
    }

    /**
     * Create an instance of {@link GetDishItemByOrderIdResponse }
     * 
     */
    public GetDishItemByOrderIdResponse createGetDishItemByOrderIdResponse() {
        return new GetDishItemByOrderIdResponse();
    }

    /**
     * Create an instance of {@link GetDishItemByOrderId }
     * 
     */
    public GetDishItemByOrderId createGetDishItemByOrderId() {
        return new GetDishItemByOrderId();
    }

    /**
     * Create an instance of {@link GetComboItemByOrderIdResponse }
     * 
     */
    public GetComboItemByOrderIdResponse createGetComboItemByOrderIdResponse() {
        return new GetComboItemByOrderIdResponse();
    }

    /**
     * Create an instance of {@link ConfirmOrder }
     * 
     */
    public ConfirmOrder createConfirmOrder() {
        return new ConfirmOrder();
    }

    /**
     * Create an instance of {@link CheckOut }
     * 
     */
    public CheckOut createCheckOut() {
        return new CheckOut();
    }

    /**
     * Create an instance of {@link DeleteDishItemResponse }
     * 
     */
    public DeleteDishItemResponse createDeleteDishItemResponse() {
        return new DeleteDishItemResponse();
    }

    /**
     * Create an instance of {@link ConfirmOrderResponse }
     * 
     */
    public ConfirmOrderResponse createConfirmOrderResponse() {
        return new ConfirmOrderResponse();
    }

    /**
     * Create an instance of {@link FindOrderByIdResponse }
     * 
     */
    public FindOrderByIdResponse createFindOrderByIdResponse() {
        return new FindOrderByIdResponse();
    }

    /**
     * Create an instance of {@link ContractEntity }
     * 
     */
    public ContractEntity createContractEntity() {
        return new ContractEntity();
    }

    /**
     * Create an instance of {@link FactoryBinStoredProductEntity }
     * 
     */
    public FactoryBinStoredProductEntity createFactoryBinStoredProductEntity() {
        return new FactoryBinStoredProductEntity();
    }

    /**
     * Create an instance of {@link FactoryProductEntity }
     * 
     */
    public FactoryProductEntity createFactoryProductEntity() {
        return new FactoryProductEntity();
    }

    /**
     * Create an instance of {@link StoreBinProductEntity }
     * 
     */
    public StoreBinProductEntity createStoreBinProductEntity() {
        return new StoreBinProductEntity();
    }

    /**
     * Create an instance of {@link RawMaterialEntity }
     * 
     */
    public RawMaterialEntity createRawMaterialEntity() {
        return new RawMaterialEntity();
    }

    /**
     * Create an instance of {@link FactoryBinEntity }
     * 
     */
    public FactoryBinEntity createFactoryBinEntity() {
        return new FactoryBinEntity();
    }

    /**
     * Create an instance of {@link StoreOutboundRecordEntity }
     * 
     */
    public StoreOutboundRecordEntity createStoreOutboundRecordEntity() {
        return new StoreOutboundRecordEntity();
    }

    /**
     * Create an instance of {@link MembershipLevelEntity }
     * 
     */
    public MembershipLevelEntity createMembershipLevelEntity() {
        return new MembershipLevelEntity();
    }

    /**
     * Create an instance of {@link FactoryEntity }
     * 
     */
    public FactoryEntity createFactoryEntity() {
        return new FactoryEntity();
    }

    /**
     * Create an instance of {@link InboundMovementEntity }
     * 
     */
    public InboundMovementEntity createInboundMovementEntity() {
        return new InboundMovementEntity();
    }

    /**
     * Create an instance of {@link FactoryRetailProductEntity }
     * 
     */
    public FactoryRetailProductEntity createFactoryRetailProductEntity() {
        return new FactoryRetailProductEntity();
    }

    /**
     * Create an instance of {@link FactoryRawMaterialAmountEntity }
     * 
     */
    public FactoryRawMaterialAmountEntity createFactoryRawMaterialAmountEntity() {
        return new FactoryRawMaterialAmountEntity();
    }

    /**
     * Create an instance of {@link KitchenOrderEntity }
     * 
     */
    public KitchenOrderEntity createKitchenOrderEntity() {
        return new KitchenOrderEntity();
    }

    /**
     * Create an instance of {@link KitchenEntity }
     * 
     */
    public KitchenEntity createKitchenEntity() {
        return new KitchenEntity();
    }

    /**
     * Create an instance of {@link BomEntity }
     * 
     */
    public BomEntity createBomEntity() {
        return new BomEntity();
    }

    /**
     * Create an instance of {@link UserEntity }
     * 
     */
    public UserEntity createUserEntity() {
        return new UserEntity();
    }

    /**
     * Create an instance of {@link PlannedOrderEntity }
     * 
     */
    public PlannedOrderEntity createPlannedOrderEntity() {
        return new PlannedOrderEntity();
    }

    /**
     * Create an instance of {@link IngredientItemEntity }
     * 
     */
    public IngredientItemEntity createIngredientItemEntity() {
        return new IngredientItemEntity();
    }

    /**
     * Create an instance of {@link FactoryRawMaterialEntity }
     * 
     */
    public FactoryRawMaterialEntity createFactoryRawMaterialEntity() {
        return new FactoryRawMaterialEntity();
    }

    /**
     * Create an instance of {@link InventoryRecordEntity }
     * 
     */
    public InventoryRecordEntity createInventoryRecordEntity() {
        return new InventoryRecordEntity();
    }

    /**
     * Create an instance of {@link InternalMessageEntity }
     * 
     */
    public InternalMessageEntity createInternalMessageEntity() {
        return new InternalMessageEntity();
    }

    /**
     * Create an instance of {@link MemberEntity }
     * 
     */
    public MemberEntity createMemberEntity() {
        return new MemberEntity();
    }

    /**
     * Create an instance of {@link MenuItemForecastEntity }
     * 
     */
    public MenuItemForecastEntity createMenuItemForecastEntity() {
        return new MenuItemForecastEntity();
    }

    /**
     * Create an instance of {@link InternalMessageReceive }
     * 
     */
    public InternalMessageReceive createInternalMessageReceive() {
        return new InternalMessageReceive();
    }

    /**
     * Create an instance of {@link IngredientPurchaseOrderEntity }
     * 
     */
    public IngredientPurchaseOrderEntity createIngredientPurchaseOrderEntity() {
        return new IngredientPurchaseOrderEntity();
    }

    /**
     * Create an instance of {@link IngredientForecastEntity }
     * 
     */
    public IngredientForecastEntity createIngredientForecastEntity() {
        return new IngredientForecastEntity();
    }

    /**
     * Create an instance of {@link IngredientSupplierEntity }
     * 
     */
    public IngredientSupplierEntity createIngredientSupplierEntity() {
        return new IngredientSupplierEntity();
    }

    /**
     * Create an instance of {@link StoreWarehouseBinEntity }
     * 
     */
    public StoreWarehouseBinEntity createStoreWarehouseBinEntity() {
        return new StoreWarehouseBinEntity();
    }

    /**
     * Create an instance of {@link RetailProductEntity }
     * 
     */
    public RetailProductEntity createRetailProductEntity() {
        return new RetailProductEntity();
    }

    /**
     * Create an instance of {@link IngredientIssueEntity }
     * 
     */
    public IngredientIssueEntity createIngredientIssueEntity() {
        return new IngredientIssueEntity();
    }

    /**
     * Create an instance of {@link DailySalesEntity }
     * 
     */
    public DailySalesEntity createDailySalesEntity() {
        return new DailySalesEntity();
    }

    /**
     * Create an instance of {@link IngredientEntity }
     * 
     */
    public IngredientEntity createIngredientEntity() {
        return new IngredientEntity();
    }

    /**
     * Create an instance of {@link RfmEntity }
     * 
     */
    public RfmEntity createRfmEntity() {
        return new RfmEntity();
    }

    /**
     * Create an instance of {@link StoreBinRetailProductEntity }
     * 
     */
    public StoreBinRetailProductEntity createStoreBinRetailProductEntity() {
        return new StoreBinRetailProductEntity();
    }

    /**
     * Create an instance of {@link ComboItemEntity }
     * 
     */
    public ComboItemEntity createComboItemEntity() {
        return new ComboItemEntity();
    }

    /**
     * Create an instance of {@link ProductEntity }
     * 
     */
    public ProductEntity createProductEntity() {
        return new ProductEntity();
    }

    /**
     * Create an instance of {@link DeliveryOrderEntity }
     * 
     */
    public DeliveryOrderEntity createDeliveryOrderEntity() {
        return new DeliveryOrderEntity();
    }

    /**
     * Create an instance of {@link StoragePlaceEntity }
     * 
     */
    public StoragePlaceEntity createStoragePlaceEntity() {
        return new StoragePlaceEntity();
    }

    /**
     * Create an instance of {@link StoreInboundRecordEntity }
     * 
     */
    public StoreInboundRecordEntity createStoreInboundRecordEntity() {
        return new StoreInboundRecordEntity();
    }

    /**
     * Create an instance of {@link PurchaseOrderEntity }
     * 
     */
    public PurchaseOrderEntity createPurchaseOrderEntity() {
        return new PurchaseOrderEntity();
    }

    /**
     * Create an instance of {@link GoodsReceiptEntity }
     * 
     */
    public GoodsReceiptEntity createGoodsReceiptEntity() {
        return new GoodsReceiptEntity();
    }

    /**
     * Create an instance of {@link StoreProductEntity }
     * 
     */
    public StoreProductEntity createStoreProductEntity() {
        return new StoreProductEntity();
    }

    /**
     * Create an instance of {@link IntegratedPlannedOrderEntity }
     * 
     */
    public IntegratedPlannedOrderEntity createIntegratedPlannedOrderEntity() {
        return new IntegratedPlannedOrderEntity();
    }

    /**
     * Create an instance of {@link ComboEntity }
     * 
     */
    public ComboEntity createComboEntity() {
        return new ComboEntity();
    }

    /**
     * Create an instance of {@link ClvEntity }
     * 
     */
    public ClvEntity createClvEntity() {
        return new ClvEntity();
    }

    /**
     * Create an instance of {@link StoreRetailProductEntity }
     * 
     */
    public StoreRetailProductEntity createStoreRetailProductEntity() {
        return new StoreRetailProductEntity();
    }

    /**
     * Create an instance of {@link IngredientReceiptEntity }
     * 
     */
    public IngredientReceiptEntity createIngredientReceiptEntity() {
        return new IngredientReceiptEntity();
    }

    /**
     * Create an instance of {@link StoreEntity }
     * 
     */
    public StoreEntity createStoreEntity() {
        return new StoreEntity();
    }

    /**
     * Create an instance of {@link DishEntity }
     * 
     */
    public DishEntity createDishEntity() {
        return new DishEntity();
    }

    /**
     * Create an instance of {@link ProductionPlanEntity }
     * 
     */
    public ProductionPlanEntity createProductionPlanEntity() {
        return new ProductionPlanEntity();
    }

    /**
     * Create an instance of {@link FactoryRetailProductAmountEntity }
     * 
     */
    public FactoryRetailProductAmountEntity createFactoryRetailProductAmountEntity() {
        return new FactoryRetailProductAmountEntity();
    }

    /**
     * Create an instance of {@link WeeklyProductionPlanEntity }
     * 
     */
    public WeeklyProductionPlanEntity createWeeklyProductionPlanEntity() {
        return new WeeklyProductionPlanEntity();
    }

    /**
     * Create an instance of {@link StoreInStoreMovementRecordEntity }
     * 
     */
    public StoreInStoreMovementRecordEntity createStoreInStoreMovementRecordEntity() {
        return new StoreInStoreMovementRecordEntity();
    }

    /**
     * Create an instance of {@link IngredientPurchaseOrderToSupplierEntity }
     * 
     */
    public IngredientPurchaseOrderToSupplierEntity createIngredientPurchaseOrderToSupplierEntity() {
        return new IngredientPurchaseOrderToSupplierEntity();
    }

    /**
     * Create an instance of {@link TicketEntity }
     * 
     */
    public TicketEntity createTicketEntity() {
        return new TicketEntity();
    }

    /**
     * Create an instance of {@link SupplierEntity }
     * 
     */
    public SupplierEntity createSupplierEntity() {
        return new SupplierEntity();
    }

    /**
     * Create an instance of {@link StoreUserEntity }
     * 
     */
    public StoreUserEntity createStoreUserEntity() {
        return new StoreUserEntity();
    }

    /**
     * Create an instance of {@link DishItemEntity }
     * 
     */
    public DishItemEntity createDishItemEntity() {
        return new DishItemEntity();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "confirmOrder")
    public JAXBElement<ConfirmOrder> createConfirmOrder(ConfirmOrder value) {
        return new JAXBElement<ConfirmOrder>(_ConfirmOrder_QNAME, ConfirmOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "confirmOrderResponse")
    public JAXBElement<ConfirmOrderResponse> createConfirmOrderResponse(ConfirmOrderResponse value) {
        return new JAXBElement<ConfirmOrderResponse>(_ConfirmOrderResponse_QNAME, ConfirmOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOrderByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "findOrderByIdResponse")
    public JAXBElement<FindOrderByIdResponse> createFindOrderByIdResponse(FindOrderByIdResponse value) {
        return new JAXBElement<FindOrderByIdResponse>(_FindOrderByIdResponse_QNAME, FindOrderByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckOut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "checkOut")
    public JAXBElement<CheckOut> createCheckOut(CheckOut value) {
        return new JAXBElement<CheckOut>(_CheckOut_QNAME, CheckOut.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteDishItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "deleteDishItemResponse")
    public JAXBElement<DeleteDishItemResponse> createDeleteDishItemResponse(DeleteDishItemResponse value) {
        return new JAXBElement<DeleteDishItemResponse>(_DeleteDishItemResponse_QNAME, DeleteDishItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "createOrderResponse")
    public JAXBElement<CreateOrderResponse> createCreateOrderResponse(CreateOrderResponse value) {
        return new JAXBElement<CreateOrderResponse>(_CreateOrderResponse_QNAME, CreateOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddDishItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "addDishItem")
    public JAXBElement<AddDishItem> createAddDishItem(AddDishItem value) {
        return new JAXBElement<AddDishItem>(_AddDishItem_QNAME, AddDishItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteComboItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "deleteComboItemResponse")
    public JAXBElement<DeleteComboItemResponse> createDeleteComboItemResponse(DeleteComboItemResponse value) {
        return new JAXBElement<DeleteComboItemResponse>(_DeleteComboItemResponse_QNAME, DeleteComboItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetComboByKitchenIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "getComboByKitchenIdResponse")
    public JAXBElement<GetComboByKitchenIdResponse> createGetComboByKitchenIdResponse(GetComboByKitchenIdResponse value) {
        return new JAXBElement<GetComboByKitchenIdResponse>(_GetComboByKitchenIdResponse_QNAME, GetComboByKitchenIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDishItemByOrderId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "getDishItemByOrderId")
    public JAXBElement<GetDishItemByOrderId> createGetDishItemByOrderId(GetDishItemByOrderId value) {
        return new JAXBElement<GetDishItemByOrderId>(_GetDishItemByOrderId_QNAME, GetDishItemByOrderId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetComboItemByOrderIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "getComboItemByOrderIdResponse")
    public JAXBElement<GetComboItemByOrderIdResponse> createGetComboItemByOrderIdResponse(GetComboItemByOrderIdResponse value) {
        return new JAXBElement<GetComboItemByOrderIdResponse>(_GetComboItemByOrderIdResponse_QNAME, GetComboItemByOrderIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteComboItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "deleteComboItem")
    public JAXBElement<DeleteComboItem> createDeleteComboItem(DeleteComboItem value) {
        return new JAXBElement<DeleteComboItem>(_DeleteComboItem_QNAME, DeleteComboItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDishItemByOrderIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "getDishItemByOrderIdResponse")
    public JAXBElement<GetDishItemByOrderIdResponse> createGetDishItemByOrderIdResponse(GetDishItemByOrderIdResponse value) {
        return new JAXBElement<GetDishItemByOrderIdResponse>(_GetDishItemByOrderIdResponse_QNAME, GetDishItemByOrderIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddComboItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "addComboItemResponse")
    public JAXBElement<AddComboItemResponse> createAddComboItemResponse(AddComboItemResponse value) {
        return new JAXBElement<AddComboItemResponse>(_AddComboItemResponse_QNAME, AddComboItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckOutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "checkOutResponse")
    public JAXBElement<CheckOutResponse> createCheckOutResponse(CheckOutResponse value) {
        return new JAXBElement<CheckOutResponse>(_CheckOutResponse_QNAME, CheckOutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDishByKitchenIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "getDishByKitchenIdResponse")
    public JAXBElement<GetDishByKitchenIdResponse> createGetDishByKitchenIdResponse(GetDishByKitchenIdResponse value) {
        return new JAXBElement<GetDishByKitchenIdResponse>(_GetDishByKitchenIdResponse_QNAME, GetDishByKitchenIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetComboByKitchenId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "getComboByKitchenId")
    public JAXBElement<GetComboByKitchenId> createGetComboByKitchenId(GetComboByKitchenId value) {
        return new JAXBElement<GetComboByKitchenId>(_GetComboByKitchenId_QNAME, GetComboByKitchenId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOrderById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "findOrderById")
    public JAXBElement<FindOrderById> createFindOrderById(FindOrderById value) {
        return new JAXBElement<FindOrderById>(_FindOrderById_QNAME, FindOrderById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindKitchenByStoreStaffIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "findKitchenByStoreStaffIdResponse")
    public JAXBElement<FindKitchenByStoreStaffIdResponse> createFindKitchenByStoreStaffIdResponse(FindKitchenByStoreStaffIdResponse value) {
        return new JAXBElement<FindKitchenByStoreStaffIdResponse>(_FindKitchenByStoreStaffIdResponse_QNAME, FindKitchenByStoreStaffIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddDishItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "addDishItemResponse")
    public JAXBElement<AddDishItemResponse> createAddDishItemResponse(AddDishItemResponse value) {
        return new JAXBElement<AddDishItemResponse>(_AddDishItemResponse_QNAME, AddDishItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteDishItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "deleteDishItem")
    public JAXBElement<DeleteDishItem> createDeleteDishItem(DeleteDishItem value) {
        return new JAXBElement<DeleteDishItem>(_DeleteDishItem_QNAME, DeleteDishItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "cancelOrder")
    public JAXBElement<CancelOrder> createCancelOrder(CancelOrder value) {
        return new JAXBElement<CancelOrder>(_CancelOrder_QNAME, CancelOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddComboItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "addComboItem")
    public JAXBElement<AddComboItem> createAddComboItem(AddComboItem value) {
        return new JAXBElement<AddComboItem>(_AddComboItem_QNAME, AddComboItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "cancelOrderResponse")
    public JAXBElement<CancelOrderResponse> createCancelOrderResponse(CancelOrderResponse value) {
        return new JAXBElement<CancelOrderResponse>(_CancelOrderResponse_QNAME, CancelOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "createOrder")
    public JAXBElement<CreateOrder> createCreateOrder(CreateOrder value) {
        return new JAXBElement<CreateOrder>(_CreateOrder_QNAME, CreateOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetComboItemByOrderId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "getComboItemByOrderId")
    public JAXBElement<GetComboItemByOrderId> createGetComboItemByOrderId(GetComboItemByOrderId value) {
        return new JAXBElement<GetComboItemByOrderId>(_GetComboItemByOrderId_QNAME, GetComboItemByOrderId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDishByKitchenId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "getDishByKitchenId")
    public JAXBElement<GetDishByKitchenId> createGetDishByKitchenId(GetDishByKitchenId value) {
        return new JAXBElement<GetDishByKitchenId>(_GetDishByKitchenId_QNAME, GetDishByKitchenId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindKitchenByStoreStaffId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://KM.SessionBean/", name = "findKitchenByStoreStaffId")
    public JAXBElement<FindKitchenByStoreStaffId> createFindKitchenByStoreStaffId(FindKitchenByStoreStaffId value) {
        return new JAXBElement<FindKitchenByStoreStaffId>(_FindKitchenByStoreStaffId_QNAME, FindKitchenByStoreStaffId.class, null, value);
    }

}
