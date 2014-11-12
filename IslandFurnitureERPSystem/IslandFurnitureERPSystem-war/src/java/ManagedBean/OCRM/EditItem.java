/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Factory.ProductEntity;
import Entity.Store.OCRM.CountryProductEntity;
import SessionBean.OCRM.CustomerWebModuleLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author apple
 */
@Named(value = "editItem")
@ViewScoped
public class EditItem {

    @EJB
    CustomerWebModuleLocal cwml;
    private Long itemId;
    private CountryProductEntity item;
    private String description;
    private ProductEntity product;
    private Long productId;
    private String type;
    private String picture;
    private String productName;

    private List<SelectItem> displayList;
    private List<ProductEntity> productList;
    private List<SelectItem> typeList;
    private String selectedProduct;
    private String selectedType;
    private String name;
    private String path;

    public EditItem() {
    }

    @PostConstruct
    public void init() {
        itemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("itemId");
        item = cwml.getItem(itemId);
        description = item.getDescription();
        product = item.getProduct();
        type = item.getType();
        productName = item.getProductName();
        productId = product.getProductId();

        displayList = new ArrayList<>();
        typeList = new ArrayList<>();
        productList = cwml.getProductList();
        for (ProductEntity s : productList) {
            String t = s.getProductId() + " " + s.getName();
            displayList.add(new SelectItem(s.getProductId(), t));
        }

        typeList.add(new SelectItem("Desk"));
        typeList.add(new SelectItem("Bed"));
        typeList.add(new SelectItem("Closet"));
        typeList.add(new SelectItem("Sofa"));
        typeList.add(new SelectItem("Chair"));
        typeList.add(new SelectItem("Other"));
    }
    
    
    public String upDate(){
        
        productId=Long.valueOf(selectedProduct);
        cwml.editItem(itemId, productId, productName, description, selectedType,picture);
        
        return "CustomerWebFurniture?faces-redirect=true";
    
    }

    public void handleProductItemImageUpload(FileUploadEvent event) throws IOException {

        System.out.println("Enter handleProductItemImage ");

        String[] fileNameParts = event.getFile().getFileName().split("\\.");

        name = fileNameParts[0] + "." + fileNameParts[1];

        System.out.println(name);

        path = "/Users/apple/Documents/NUS/2014/Year3Sem1/IS3102/Program/IslandFurnitureERPSystem/IslandFurnitureERPSystem-war/web/resources/images/" + name;

        System.out.println("path is " + path);

        File result = new File(path);
        InputStream is;
        try (FileOutputStream out = new FileOutputStream(path)) {
            int a;
            int BUFFER_SIZE = 8192;
            byte[] buffer = new byte[BUFFER_SIZE];
            is = event.getFile().getInputstream();
            while (true) {
                a = is.read(buffer);

                if (a < 0) {
                    break;
                }

                out.write(buffer, 0, a);
                out.flush();
            }
        }

        path = "/Users/apple/Documents/NUS/2014/Year3Sem1/IS3102/Program/IslandFurnitureERPSystem/CustomerWeb/web/resources/images/" + name;

        System.out.println("path is " + path);

        File result2 = new File(path);
        InputStream is2;
        try (FileOutputStream out = new FileOutputStream(path)) {
            int a;
            int BUFFER_SIZE = 8192;
            byte[] buffer = new byte[BUFFER_SIZE];
            is = event.getFile().getInputstream();
            while (true) {
                a = is.read(buffer);

                if (a < 0) {
                    break;
                }

                out.write(buffer, 0, a);
                out.flush();
            }
        }

        is.close();
        picture = name;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Image has been uploaded", ""));
    }
    
    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public CountryProductEntity getItem() {
        return item;
    }

    public void setItem(CountryProductEntity item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<SelectItem> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(List<SelectItem> displayList) {
        this.displayList = displayList;
    }

    public List<ProductEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductEntity> productList) {
        this.productList = productList;
    }

    public List<SelectItem> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<SelectItem> typeList) {
        this.typeList = typeList;
    }

    public String getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(String selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }
    
    

}
