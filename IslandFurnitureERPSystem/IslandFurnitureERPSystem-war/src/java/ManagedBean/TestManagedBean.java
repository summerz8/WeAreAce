/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManagedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author zhengyuan
 */
@Named(value = "testManagedBean")
@ViewScoped
public class TestManagedBean implements Serializable {
    
    private List<SelectItem> firstSelectItems;
    private List<SelectItem> secondSelectItems;
    private String firstSelectedValue;
    private String secondSelectedValue;

    /**
     * Creates a new instance of TestManagedBean
     */
    public TestManagedBean() {
    }
    
    
    @PostConstruct
    public void init()
    {
        firstSelectItems = new ArrayList<>();
        firstSelectItems.add(new SelectItem("One", "One"));
        firstSelectItems.add(new SelectItem("Two", "Two"));
        firstSelectItems.add(new SelectItem("Three", "Three"));
        
        secondSelectItems = new ArrayList<>();
    }
    
    
    
    public void changeListener()
    {
        System.err.println("testManagedBean.changeListener(): " + firstSelectedValue);
        
        if(firstSelectedValue.equals("One"))
        {
            System.err.println("One");
            secondSelectItems = new ArrayList<>();
            secondSelectItems.add(new SelectItem("One One", "One One"));
            secondSelectItems.add(new SelectItem("One Two", "One Two"));
            secondSelectItems.add(new SelectItem("One Three", "One Three"));
        }
        else if(firstSelectedValue.equals("Two"))
        {
            System.err.println("Two");
            secondSelectItems = new ArrayList<>();
            secondSelectItems.add(new SelectItem("Two One", "Two One"));
            secondSelectItems.add(new SelectItem("Two Two", "Two Two"));
            secondSelectItems.add(new SelectItem("Two Three", "Two Three"));
        }
        else if(firstSelectedValue.equals("Three"))
        {
            
        }
    }
    
    

    public List<SelectItem> getFirstSelectItems() {
        return firstSelectItems;
    }

    public void setFirstSelectItems(List<SelectItem> firstSelectItems) {
        this.firstSelectItems = firstSelectItems;
    }

    public List<SelectItem> getSecondSelectItems() {
        return secondSelectItems;
    }

    public void setSecondSelectItems(List<SelectItem> secondSelectItems) {
        this.secondSelectItems = secondSelectItems;
    }

    public String getFirstSelectedValue() {
        return firstSelectedValue;
    }

    public void setFirstSelectedValue(String firstSelectedValue) {
        this.firstSelectedValue = firstSelectedValue;
    }

    public String getSecondSelectedValue() {
        return secondSelectedValue;
    }

    public void setSecondSelectedValue(String secondSelectedValue) {
        this.secondSelectedValue = secondSelectedValue;
    }
    
}
