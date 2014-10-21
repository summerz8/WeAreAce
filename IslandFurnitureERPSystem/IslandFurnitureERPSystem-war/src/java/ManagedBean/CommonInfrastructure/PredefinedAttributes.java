/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.CommonInfrastructure;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dan
 */
@Named(value = "attr")
@SessionScoped
public class PredefinedAttributes implements Serializable {

    private final static Map<String, String> titles;
    private final static Map<String, String> departments;
    private final static Map<String, String> genders;    
    private final static Map<String, String> countries;
    private final static Map<Integer, String> memberLevels;

    static {
        titles = new HashMap<String, String>();
        titles.put("Mr", "Mr");
        titles.put("Ms", "Ms");
        titles.put("Miss", "Miss");
        titles.put("Mrs", "Mrs");
        
        genders = new HashMap<String, String>();
        genders.put("Female", "Female");
        genders.put("Male", "Male");
        genders.put("Others", "Others");
        
        departments = new HashMap<String, String>();
        departments.put("H", "Head Quarter");
        departments.put("F", "Factory");
        departments.put("S", "Store");
        
        countries = new HashMap<String, String>();
        initialCounties();

        memberLevels = new HashMap<Integer, String>();
        memberLevels.put(1, "1 Basic");
        memberLevels.put(2, "2 Blue");
        memberLevels.put(3, "3 Silver");
        memberLevels.put(4, "4 Gold");
        memberLevels.put(5, "5 Diamond");

    }
    public static void initialCounties() {
        countries.put("Afghanistan", "Afghanistan");
        countries.put("Albania", "Albania");
        countries.put("Algeria", "Algeria");
        countries.put("Andorra", "Andorra");
        countries.put("Angola", "Angola");
        countries.put("Antigua & Deps", "Antigua & Deps");
        countries.put("Argentina", "Argentina");
        countries.put("Armenia", "Armenia");
        countries.put("Australia", "Australia");
        countries.put("Austria", "Austria");
        countries.put("Azerbaijan", "Azerbaijan");
        countries.put("Bahamas", "Bahamas");
        countries.put("Bahrain", "Bahrain");
        countries.put("Bangladesh", "Bangladesh");
        countries.put("Barbados", "Barbados");
        countries.put("Belarus", "Belarus");
        countries.put("Belgium", "Belgium");
        countries.put("Belize", "Belize");
        countries.put("Benin", "Benin");
        countries.put("Bhutan", "Bhutan");
        countries.put("Bolivia", "Bolivia");
        countries.put("Bosnia Herzegovina", "Bosnia Herzegovina");
        countries.put("Botswana", "Botswana");
        countries.put("Brazil", "Brazil");
        countries.put("Brunei", "Brunei");
        countries.put("Bulgaria", "Bulgaria");
        countries.put("Burkina", "Burkina");
        countries.put("Burundi", "Burundi");
        countries.put("Cambodia", "Cambodia");
        countries.put("Cameroon", "Cameroon");
        countries.put("Cape Verde", "Cape Verde");
        countries.put("Central African Rep", "Central African Rep");
        countries.put("Chad", "Chad");
        countries.put("Chile", "Chile");
        countries.put("China", "China");
        countries.put("Colombia", "Colombia");
        countries.put("Comoros", "Comoros");
        countries.put("Congo", "Congo");
        countries.put("Congo {Democratic Rep}", "Congo {Democratic Rep}");
        countries.put("Costa Rica", "Costa Rica");
        countries.put("Croatia", "Croatia");
        countries.put("Cuba", "Cuba");
        countries.put("Cyprus", "Cyprus");
        countries.put("Czech Republic", "Czech Republic");
        countries.put("Denmark", "Denmark");
        countries.put("Djibouti", "Djibouti");
        countries.put("Dominica", "Dominica");
        countries.put("Dominican Republic", "Dominican Republic");
        countries.put("East Timor", "East Timor");
        countries.put("Ecuador", "Ecuador");
        countries.put("Egypt", "Egypt");
        countries.put("El Salvador", "El Salvador");
        countries.put("Equatorial Guinea", "Equatorial Guinea");
        countries.put("Eritrea", "Eritrea");
        countries.put("Estonia", "Estonia");
        countries.put("Ethiopia", "Ethiopia");
        countries.put("Fiji", "Fiji");
        countries.put("Finland", "Finland");
        countries.put("France", "France");
        countries.put("Gabon", "Gabon");
        countries.put("Gambia", "Gambia");
        countries.put("Georgia", "Georgia");
        countries.put("Germany", "Germany");
        countries.put("Ghana", "Ghana");
        countries.put("Greece", "Greece");
        countries.put("Grenada", "Grenada");
        countries.put("Guatemala", "Guatemala");
        countries.put("Guinea", "Guinea");
        countries.put("Guinea-Bissau", "Guinea-Bissau");
        countries.put("Guyana", "Guyana");
        countries.put("Haiti", "Haiti");
        countries.put("Honduras", "Honduras");
        countries.put("Hungary", "Hungary");
        countries.put("Iceland", "Iceland");
        countries.put("India", "India");
        countries.put("Indonesia", "Indonesia");
        countries.put("Iran", "Iran");
        countries.put("Iraq", "Iraq");
        countries.put("Ireland {Republic}", "Ireland {Republic}");
        countries.put("Israel", "Israel");
        countries.put("Italy", "Italy");
        countries.put("Ivory Coast", "Ivory Coast");
        countries.put("Jamaica", "Jamaica");
        countries.put("Japan", "Japan");
        countries.put("Jordan", "Jordan");
        countries.put("Kazakhstan", "Kazakhstan");
        countries.put("Kenya", "Kenya");
        countries.put("Kiribati", "Kiribati");
        countries.put("Korea North", "Korea North");
        countries.put("Korea South", "Korea South");
        countries.put("Kosovo", "Kosovo");
        countries.put("Kuwait", "Kuwait");
        countries.put("Kyrgyzstan", "Kyrgyzstan");
        countries.put("Laos", "Laos");
        countries.put("Latvia", "Latvia");
        countries.put("Lebanon", "Lebanon");
        countries.put("Lesotho", "Lesotho");
        countries.put("Liberia", "Liberia");
        countries.put("Libya", "Libya");
        countries.put("Liechtenstein", "Liechtenstein");
        countries.put("Lithuania", "Lithuania");
        countries.put("Luxembourg", "Luxembourg");
        countries.put("Macedonia", "Macedonia");
        countries.put("Madagascar", "Madagascar");
        countries.put("Malawi", "Malawi");
        countries.put("Malaysia", "Malaysia");
        countries.put("Maldives", "Maldives");
        countries.put("Mali", "Mali");
        countries.put("Malta", "Malta");
        countries.put("Marshall Islands", "Marshall Islands");
        countries.put("Mauritania", "Mauritania");
        countries.put("Mauritius", "Mauritius");
        countries.put("Mexico", "Mexico");
        countries.put("Micronesia", "Micronesia");
        countries.put("Moldova", "Moldova");
        countries.put("Monaco", "Monaco");
        countries.put("Mongolia", "Mongolia");
        countries.put("Montenegro", "Montenegro");
        countries.put("Morocco", "Morocco");
        countries.put("Mozambique", "Mozambique");
        countries.put("Myanmar", "Myanmar");
        countries.put("Namibia", "Namibia");
        countries.put("Nauru", "Nauru");
        countries.put("Nepal", "Nepal");
        countries.put("Netherlands", "Netherlands");
        countries.put("New Zealand", "New Zealand");
        countries.put("Nicaragua", "Nicaragua");
        countries.put("Niger", "Niger");
        countries.put("Nigeria", "Nigeria");
        countries.put("Norway", "Norway");
        countries.put("Oman", "Oman");
        countries.put("Pakistan", "Pakistan");
        countries.put("Palau", "Palau");
        countries.put("Panama", "Panama");
        countries.put("Papua New Guinea", "Papua New Guinea");
        countries.put("Paraguay", "Paraguay");
        countries.put("Peru", "Peru");
        countries.put("Philippines", "Philippines");
        countries.put("Poland", "Poland");
        countries.put("Portugal", "Portugal");
        countries.put("Qatar", "Qatar");
        countries.put("Romania", "Romania");
        countries.put("Russia", "Russia");
        countries.put("Rwanda", "Rwanda");
        countries.put("St Kitts & Nevis", "St Kitts & Nevis");
        countries.put("St Lucia", "St Lucia");
        countries.put("Saint Vincent & the Grenadines", "Saint Vincent & the Grenadines");
        countries.put("Samoa", "Samoa");
        countries.put("San Marino", "San Marino");
        countries.put("Sao Tome & Principe", "Sao Tome & Principe");
        countries.put("Saudi Arabia", "Saudi Arabia");
        countries.put("Senegal", "Senegal");
        countries.put("Serbia", "Serbia");
        countries.put("Seychelles", "Seychelles");
        countries.put("Sierra Leone", "Sierra Leone");
        countries.put("Singapore", "Singapore");
        countries.put("Slovakia", "Slovakia");
        countries.put("Slovenia", "Slovenia");
        countries.put("Solomon Islands", "Solomon Islands");
        countries.put("Somalia", "Somalia");
        countries.put("South Africa", "South Africa");
        countries.put("South Sudan", "South Sudan");
        countries.put("Spain", "Spain");
        countries.put("Sri Lanka", "Sri Lanka");
        countries.put("Sudan", "Sudan");
        countries.put("Suriname", "Suriname");
        countries.put("Swaziland", "Swaziland");
        countries.put("Sweden", "Sweden");
        countries.put("Switzerland", "Switzerland");
        countries.put("Syria", "Syria");
        countries.put("Taiwan, China", "Taiwan, China");
        countries.put("Tajikistan", "Tajikistan");
        countries.put("Tanzania", "Tanzania");
        countries.put("Thailand", "Thailand");
        countries.put("Togo", "Togo");
        countries.put("Tonga", "Tonga");
        countries.put("Trinidad & Tobago", "Trinidad & Tobago");
        countries.put("Tunisia", "Tunisia");
        countries.put("Turkey", "Turkey");
        countries.put("Turkmenistan", "Turkmenistan");
        countries.put("Tuvalu", "Tuvalu");
        countries.put("Uganda", "Uganda");
        countries.put("Ukraine", "Ukraine");
        countries.put("United Arab Emirates", "United Arab Emirates");
        countries.put("United Kingdom", "United Kingdom");
        countries.put("United States", "United States");
        countries.put("Uruguay", "Uruguay");
        countries.put("Uzbekistan", "Uzbekistan");
        countries.put("Vanuatu", "Vanuatu");
        countries.put("Vatican City", "Vatican City");
        countries.put("Venezuela", "Venezuela");
        countries.put("Vietnam", "Vietnam");
        countries.put("Yemen", "Yemen");
        countries.put("Zambia", "Zambia");
        countries.put("Zimbabwe", "Zimbabwe");
    }
    
    /**
     * Creates a new instance of PredefinedAttributes
     */
    public PredefinedAttributes() {
    }

    public static Map<String, String> getTitles() {
        return titles;
    }

    public static Map<String, String> getDepartments() {
        return departments;
    }

    public static Map<String, String> getGenders() {
        return genders;
    }

    public static Map<String, String> getCountries() {
        return countries;
    }

    public static Map<Integer, String> getMemberLevels() {
        return memberLevels;
    }



}
