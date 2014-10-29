/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.OCRM;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "customerWebBean")
@ViewScoped
public class CustomerWebBean {

    String SelectedWeb;
    List<SelectItem> displayList;

    public CustomerWebBean() {
    }

    @PostConstruct
    public void init() {
        displayList = new ArrayList<>();

        displayList.add(new SelectItem("Singapore"));
        displayList.add(new SelectItem("China"));

    }

    public String goNext() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("web", SelectedWeb);

        if (SelectedWeb.equals("Singapore")) {
            return "CustomerWebSingapore?faces-redirect=true";
        } else {

            return "CustomerWebChina?faces-redirect=true";
        }

    }

    public String getSelectedWeb() {
        return SelectedWeb;
    }

    public void setSelectedWeb(String SelectedWeb) {
        this.SelectedWeb = SelectedWeb;
    }

    public List<SelectItem> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(List<SelectItem> displayList) {
        this.displayList = displayList;
    }

}
