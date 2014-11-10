/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import Entity.Factory.SetEntity;
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
@Named(value = "createSet")
@ViewScoped
public class CreateSet {

    @EJB
    CustomerWebModuleLocal cwml;

    private String picture;
    private String setName;
    private String description;
    private CountrySetEntity set;
    private String name;
    private String path;
    private List<SetEntity> globalSetList;
    private List<SelectItem> displayList;
    private List<CountrySetEntity> setList;
    private String selectedWeb;
    private String selectedSet;

    public CreateSet() {
    }

    @PostConstruct
    public void init() {
        selectedWeb = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("web");

        globalSetList = cwml.getGlobalSetList();
        setList= cwml.getSetList(selectedWeb);
        for(CountrySetEntity c: setList){
            for(SetEntity s:globalSetList){
                if(s.equals(c.getSet())){
                    globalSetList.remove(s);
                    break;
                }
            }
        }
        
        displayList=new ArrayList<>();
        for (SetEntity s : globalSetList) {
            String t = s.getId() + " " + s.getName();
            displayList.add(new SelectItem(s.getId(), t));
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

    public String create() {
        Long selectedSetId=Long.valueOf(selectedSet);
        Long setId = cwml.createSet(selectedSetId, selectedWeb);
        set = cwml.getSet(setId);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("setId", setId);

        return "EditSet?faces-redirect=true";
    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CountrySetEntity getSet() {
        return set;
    }

    public void setSet(CountrySetEntity set) {
        this.set = set;
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

    public List<SetEntity> getGlobalSetList() {
        return globalSetList;
    }

    public void setGlobalSetList(List<SetEntity> globalSetList) {
        this.globalSetList = globalSetList;
    }

    public List<SelectItem> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(List<SelectItem> displayList) {
        this.displayList = displayList;
    }

    public List<CountrySetEntity> getSetList() {
        return setList;
    }

    public void setSetList(List<CountrySetEntity> setList) {
        this.setList = setList;
    }

    public String getSelectedWeb() {
        return selectedWeb;
    }

    public void setSelectedWeb(String selectedWeb) {
        this.selectedWeb = selectedWeb;
    }

    public String getSelectedSet() {
        return selectedSet;
    }

    public void setSelectedSet(String selectedSet) {
        this.selectedSet = selectedSet;
    }

}
