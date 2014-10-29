/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Member;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author dan
 */
@Named(value = "attr")
@SessionScoped
public class PredefinedAttributes implements Serializable {

    private final static String[] titles;
    private final static String[] genders;
    private final static String[] departments;
    private final static String[] country;

    static {
        titles = new String[4];        
        titles[0] = "Mr";
        titles[1] = "Ms";
        titles[2] = "Miss";
        titles[3] = "Mrs";

        genders = new String[3];
        genders[0] = "Male";
        genders[1] = "Female";
        genders[2] = "Others";

        departments = new String[3];
        departments[0] = "H";
        departments[1] = "F";
        departments[2] = "S";

        country = new String[3];
        country[0] = "Singapore";
        country[1] = "United States";
        country[2] = "China";

    }

    /**
     * Creates a new instance of PredefinedAttributes
     */
    public PredefinedAttributes() {
    }

    public List<String> getTitles() {
        return Arrays.asList(titles);
    }

    public List<String> getGenders() {
        return Arrays.asList(genders);
    }

    public List<String> getDepartments() {
        return Arrays.asList(departments);
    }

    public List<String> getCountry() {
        return Arrays.asList(country);
    }

}
