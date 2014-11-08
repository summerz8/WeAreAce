/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Factory.RetailProductEntity;
import Entity.Store.OCRM.CustomerWebRetailItemEntity;
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
@Named(value = "editRetailItemBean")
@ViewScoped
public class EditRetailItemBean {

    
    @EJB
    CustomerWebModuleLocal cwml;
    private Long retailItemId;
    private CustomerWebRetailItemEntity item;
    private String description;
    private String productName;
    private List<SelectItem> displayList;
    private List<RetailProductEntity> productList;
    private String name;
    private String path;
    private String picture;
    private Long productId;
    private String selectedProduct;
    
    public EditRetailItemBean() {
    }
    
    @PostConstruct
    public void init() {
        retailItemId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("retailItemId");
        item = cwml.getRetailItem(retailItemId);
        description = item.getDescription();
        productName = item.getProductName();

        displayList = new ArrayList<>();
        productList = cwml.getRetailProductList();
        for (RetailProductEntity s : productList) {
            String t = s.getRetailProductId() + " " + s.getName();
            displayList.add(new SelectItem(s.getRetailProductId(), t));
        }

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
     
      public String upDate(){
        
        productId=Long.valueOf(selectedProduct);
        cwml.editRetailItem(item.getId(), productId, productName, description,picture);
        
        return "CustomerWebRetail?faces-redirect=true";
    
    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public Long getRetailItemId() {
        return retailItemId;
    }

    public void setRetailItemId(Long retailItemId) {
        this.retailItemId = retailItemId;
    }

    public CustomerWebRetailItemEntity getItem() {
        return item;
    }

    public void setItem(CustomerWebRetailItemEntity item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<RetailProductEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<RetailProductEntity> productList) {
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(String selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
      
      
}
