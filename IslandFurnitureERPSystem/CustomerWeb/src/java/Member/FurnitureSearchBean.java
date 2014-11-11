/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

import Entity.Store.OCRM.CountryProductEntity;
import SessionBean.OCRM.CustomerWebModuleLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "furnitureSearchBean")
@ViewScoped
public class FurnitureSearchBean {

    @EJB
    private CustomerWebModuleLocal cwml;

    private List<CountryProductEntity> itemList;
    private List<CountryProductEntity> displayList;
    private String type;
    private String web;

    public FurnitureSearchBean() {
    }

    @PostConstruct
    public void init() {
        web = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("web");
        itemList = cwml.listItems(web);
        System.out.println(itemList.get(0).getProductName());
        displayList = new ArrayList<>();
        type = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("searchType");
        if (type.equals("All")) {
            for (CountryProductEntity c : itemList) {
                displayList.add(c);
            }

        } else if (type.equals("Others")) {
            for (CountryProductEntity c : itemList) {
                if (c.getType().equals("Others")) {
                    displayList.add(c);
                }
            }
        } else if (type.equals("Bed")) {
            for (CountryProductEntity c : itemList) {
                if (c.getType().equals("Bed")) {
                    displayList.add(c);
                }
            }
        } else if (type.equals("Desk")) {
            for (CountryProductEntity c : itemList) {
                if (c.getType().equals("Desk")) {
                    displayList.add(c);
                }
            }
        } else if (type.equals("Chair")) {
            for (CountryProductEntity c : itemList) {
                if (c.getType().equals("Chair")) {
                    displayList.add(c);
                }
            }
        } else if (type.equals("Light")) {
            for (CountryProductEntity c : itemList) {
                if (c.getType().equals("Light")) {
                    displayList.add(c);
                }
            }
        } else if (type.equals("Sofa")) {
            for (CountryProductEntity c : itemList) {
                if (c.getType().equals("Sofa")) {
                    displayList.add(c);
                }
            }
        } else if (type.equals("Closet")) {
            for (CountryProductEntity c : itemList) {
                if (c.getType().equals("Closet")) {
                    displayList.add(c);
                }
            }
        }

    }

    public String view(Long itemId) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("itemId", itemId);
        return "ViewItem?faces-redirect=true";
    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public List<CountryProductEntity> getItemList() {
        return itemList;
    }

    public void setItemList(List<CountryProductEntity> itemList) {
        this.itemList = itemList;
    }

    public List<CountryProductEntity> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(List<CountryProductEntity> displayList) {
        this.displayList = displayList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
