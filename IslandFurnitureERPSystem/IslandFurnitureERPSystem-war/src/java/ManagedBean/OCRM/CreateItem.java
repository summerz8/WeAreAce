/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Factory.ProductEntity;
import SessionBean.OCRM.CustomerWebModuleLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Named(value = "createItem")
@ViewScoped
public class CreateItem {

    @EJB
    CustomerWebModuleLocal cwml;

    private List<SelectItem> displayList;
    private List<ProductEntity> productList;
    private List<SelectItem> typeList;
    private String selectedType;
    private String name;
    private String description;
    private String path;
    private String productName;
    private String selectedProduct;
    private String picture;

    public CreateItem() {
    }

    @PostConstruct
    public void init() {
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
        typeList.add(new SelectItem("Chiar"));
        typeList.add(new SelectItem("Other"));

    }

    public void handleProductItemImageUpload(FileUploadEvent event) throws IOException {

        System.out.println("Enter handleProductItemImage ");
        String[] fileNameParts = event.getFile().getFileName().split("\\.");
        name = fileNameParts[0] + "." + fileNameParts[1];
        System.out.println(name);
        path = "/Users/dan/Desktop/Project/IslandFurnitureERPSystem/CustomerWeb/web/resources/images/" + name;
        System.out.println("path is " + path);
        File result2 = new File(path);
        InputStream is2;
        try (FileOutputStream out = new FileOutputStream(path)) {
            int a;
            int BUFFER_SIZE = 8192;
            byte[] buffer = new byte[BUFFER_SIZE];
            is2 = event.getFile().getInputstream();
            while (true) {
                a = is2.read(buffer);
                
                if (a < 0) {
                    break;
                }
                
                out.write(buffer, 0, a);
                out.flush();
            }
        }
        String path000 = "/Users/dan/Desktop/Project/IslandFurnitureERPSystem/IslandFurnitureERPSystem-ejb/resources/" + name;
        System.out.println("path is " + path000);
        File result3 = new File(path000);
        InputStream is3;
        try (FileOutputStream out = new FileOutputStream(path000)) {
            int a;
            int BUFFER_SIZE = 8192;
            byte[] buffer = new byte[BUFFER_SIZE];
            is3 = event.getFile().getInputstream();
            while (true) {
                a = is3.read(buffer);
                
                if (a < 0) {
                    break;
                }
                
                out.write(buffer, 0, a);
                out.flush();
            }
        }
        is3.close();
        picture = name;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Image has been uploaded", ""));
    }

    public String create() {
        Long productId = Long.valueOf(selectedProduct);
        String web = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("web");

        cwml.createItem(productId, productName, description, selectedType, picture,web);

        return "CustomerWebFurniture?faces-redirect=true";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
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

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }

    public String getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(String selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
