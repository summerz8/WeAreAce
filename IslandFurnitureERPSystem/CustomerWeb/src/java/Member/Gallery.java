package Member;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Entity.Store.OCRM.SetEntity;
import SessionBean.OCRM.CustomerWebModuleLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "gallery")
@ViewScoped
public class Gallery {

    @EJB
    private CustomerWebModuleLocal cwml;

    
    private List<SetEntity> setList;
    public Gallery() {
    }

    @PostConstruct
    public void init() {
        setList=cwml.getSetList();
    }

    public CustomerWebModuleLocal getCwml() {
        return cwml;
    }

    public void setCwml(CustomerWebModuleLocal cwml) {
        this.cwml = cwml;
    }

    public List<SetEntity> getSetList() {
        return setList;
    }

    public void setSetList(List<SetEntity> setList) {
        this.setList = setList;
    }
    
    
    
}
