package Member;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author apple
 */
@Named(value = "gallery")
@ViewScoped
public class Gallery {

    private List<String> pictureList;
    private String test;
    private Integer number=0;
    public Gallery() {
    }

    @PostConstruct
    public void create() {

        pictureList = new ArrayList();
        pictureList.add("set2.png");
        pictureList.add("set2.png");
        pictureList.add("set2.png");
        pictureList.add("set2.png");
        pictureList.add("set2.png");
        pictureList.add("set2.png");
        pictureList.add("set2.png");
        pictureList.add("set2.png");

        test = "test";

    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    
}
