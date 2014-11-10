/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Store.OCRM.CountryProductEntity;
import Entity.Store.OCRM.CountrySetEntity;
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
@Named(value = "editSet")
@ViewScoped
public class EditSet {

    @EJB
    CustomerWebModuleLocal cwml;
    private Long setId;
    private CountrySetEntity set;
    private String description;
    private String setName;
    private String picture;
    private List<CountryProductEntity> itemList;
    private List<CountryProductEntity> allitems;
    private String selectedItem;
    private List<SelectItem> displayList;
    private String name;
    private String path;
    private String selectedWeb;

    public EditSet() {
    }

    @PostConstruct
    public void init() {
        setId = (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("setId");
        selectedWeb = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("web");

        set = cwml.getSet(setId);

        description = set.getDescription();
        setName = set.getName();
        picture = set.getPicture();
        itemList = set.getUnitList();
        allitems = cwml.listItems(selectedWeb);
        displayList = new ArrayList<>();
        for (CountryProductEntity s : allitems) {
            String t = s.getId() + " " + s.getProductName();
            displayList.add(new SelectItem(s.getId(), t));
        }
    }

    public String upDate() {

        cwml.editSet(setId, setName, description, picture);

        return "CustomerWebSet?faces-redirect=true";
    }

    public String addItem() {
        Long itemId = Long.valueOf(selectedItem);
        cwml.addItem(setId, itemId);
        return "EditSet?faces-redirect=true";
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

    
    public String deleteItem(Long productId){
        cwml.deleteItemInSet(set.getId(),productId);
        return "EditSet?faces-redirect=true";
    }
    
    public String editItem(Long productId){
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemId", productId);
        return "EditItem?faces-redirect=true";
    }
            
    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public Long getSetId() {
        return setId;
    }

    public void setSetId(Long setId) {
        this.setId = setId;
    }

    public CountrySetEntity getSet() {
        return set;
    }

    public void setSet(CountrySetEntity set) {
        this.set = set;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<CountryProductEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<CountryProductEntity> itemList) {
        this.itemList = itemList;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<SelectItem> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(List<SelectItem> displayList) {
        this.displayList = displayList;
    }

    public List<CountryProductEntity> getAllitems() {
        return allitems;
    }

    public void setAllitems(List<CountryProductEntity> allitems) {
        this.allitems = allitems;
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

}
