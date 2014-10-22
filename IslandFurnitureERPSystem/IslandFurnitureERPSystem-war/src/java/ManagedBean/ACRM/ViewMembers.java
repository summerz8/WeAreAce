/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean.ACRM;

import Entity.Store.OCRM.MemberEntity;
import SessionBean.ACRM.AnalyticalCRMSessionBeanLocal;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author summer
 */
@Named(value = "viewMembers")
@RequestScoped
public class ViewMembers implements Serializable {

    @EJB
    private AnalyticalCRMSessionBeanLocal acrm;

    private Collection<MemberEntity> currentMembers;

    private Collection<MemberEntity> allMembers;
    private Collection<MemberEntity> membersByAge;
    private Collection<MemberEntity> membersByGender;
    private Collection<MemberEntity> membersByCountry;
    private Collection<MemberEntity> membersByMemberLevel;

    private Collection<MemberEntity> membersByAG;
    private Collection<MemberEntity> membersByAC;
    private Collection<MemberEntity> membersByAM;
    private Collection<MemberEntity> membersByGC;
    private Collection<MemberEntity> membersByGM;
    private Collection<MemberEntity> membersByCM;

    private Collection<MemberEntity> membersByAGC;
    private Collection<MemberEntity> membersByAGM;
    private Collection<MemberEntity> membersByACM;
    private Collection<MemberEntity> membersByGCM;

    private Collection<MemberEntity> membersByAGCM;

    private Collection<MemberEntity> filteredMembers;

    private Integer memberAmount;

    private Boolean checkGender;
    private Boolean checkAge = false;
    private Boolean checkCountry = false;
    private Boolean checkMemberlvl = false;

    private Integer maxAge = 0;
    private Integer minAge = 0;

    private Map<String, String> genders;
    private String gender = null;

    private Map<String, String> countries;
    private String country = null;

    private Map<Integer, String> memberLevels;
    private Integer memberLevel = 0;

    @PostConstruct
    public void init() {
        genders = new HashMap<String, String>();
        genders.put("Female", "Female");
        genders.put("Male", "Male");
        genders.put("Others", "Others");

        countries = new HashMap<String, String>();
        initialCounties();

        memberLevels = new HashMap<>();
        memberLevels.put(1, "1 Basic");
        memberLevels.put(2, "2 Blue");
        memberLevels.put(3, "3 Silver");
        memberLevels.put(4, "4 Gold");
        memberLevels.put(5, "5 Diamond");

        try {
            getValuesOnSession();
            initSetting();

            System.out.println("ViewMembersBean: memberAmount: " + memberAmount);
        } catch (Exception ex) {
            Logger.getLogger(ViewMembers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ViewMembers() {
    }

    public Collection<MemberEntity> getCurrentMembers() {
        if (!checkGender && !checkAge && !checkCountry && !checkMemberlvl) {
            currentMembers = allMembers;
        } else if (checkGender && !checkAge && !checkCountry && !checkMemberlvl) {
            currentMembers = membersByGender;
        } else if (!checkGender && checkAge && !checkCountry && !checkMemberlvl) {
            currentMembers = membersByAge;
        } else if (!checkGender && !checkAge && checkCountry && !checkMemberlvl) {
            currentMembers = membersByCountry;
        } else if (!checkGender && !checkAge && !checkCountry && checkMemberlvl) {
            currentMembers = membersByMemberLevel;
        } else if (checkGender && checkAge && !checkCountry && !checkMemberlvl) {
            currentMembers = membersByAG;
        } else if (!checkGender && checkAge && checkCountry && !checkMemberlvl) {
            currentMembers = membersByAC;
        } else if (!checkGender && checkAge && !checkCountry && checkMemberlvl) {
            currentMembers = membersByAM;
        } else if (checkGender && !checkAge && checkCountry && !checkMemberlvl) {
            currentMembers = membersByGC;
        } else if (checkGender && !checkAge && !checkCountry && checkMemberlvl) {
            currentMembers = membersByGM;
        } else if (!checkGender && !checkAge && checkCountry && checkMemberlvl) {
            currentMembers = membersByCM;
        } else if (checkGender && checkAge && checkCountry && !checkMemberlvl) {
            currentMembers = membersByAGC;
        } else if (checkGender && checkAge && !checkCountry && checkMemberlvl) {
            currentMembers = membersByAGM;
        } else if (!checkGender && checkAge && checkCountry && checkMemberlvl) {
            currentMembers = membersByACM;
        } else if (checkGender && !checkAge && checkCountry && checkMemberlvl) {
            currentMembers = membersByGCM;
        } else {
            currentMembers = membersByAGCM;
        }
        return currentMembers;
    }

    public void setCurrentMembers(Collection<MemberEntity> currentMembers) {
        this.currentMembers = currentMembers;
    }

    public Collection<MemberEntity> getAllMembers() {
        return allMembers;
    }

    public void setAllMembers(Collection<MemberEntity> allMembers) {
        this.allMembers = allMembers;
    }

    public Collection<MemberEntity> getMembersByAge() {
        return membersByAge;
    }

    public void setMembersByAge(Collection<MemberEntity> membersByAge) {
        this.membersByAge = membersByAge;
    }

    public Collection<MemberEntity> getMembersByGender() {
        return membersByGender;
    }

    public void setMembersByGender(Collection<MemberEntity> membersByGender) {
        this.membersByGender = membersByGender;
    }

    public Collection<MemberEntity> getMembersByCountry() {
        return membersByCountry;
    }

    public void setMembersByCountry(Collection<MemberEntity> membersByCountry) {
        this.membersByCountry = membersByCountry;
    }

    public Collection<MemberEntity> getMembersByMemberLevel() {
        return membersByMemberLevel;
    }

    public void setMembersByMemberLevel(Collection<MemberEntity> membersByMemberLevel) {
        this.membersByMemberLevel = membersByMemberLevel;
    }

    public Integer getMemberAmount() {
        memberAmount = filteredMembers.size();
        return memberAmount;
    }

    public void setMemberAmount(Integer memberAmount) {
        this.memberAmount = memberAmount;
    }

    public Boolean getCheckGender() {
        System.out.println("getCheckGender: ===> checkGender = " + this.checkGender.toString());
        return checkGender;
    }

    public void setCheckGender(Boolean checkGender) {
        System.out.println("ViewMembersBean: setCheckGender: before");
        this.checkGender = checkGender;
        System.out.println("View MembersBean: setCheckGender: After ---> checkGender = " + this.checkGender.toString());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("checkGender", this.checkGender);

    }

    public Boolean getCheckAge() {
        return checkAge;
    }

    public void setCheckAge(Boolean checkAge) {
        this.checkAge = checkAge;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("checkAge", this.checkAge);

    }

    public Boolean getCheckCountry() {
        return checkCountry;
    }

    public void setCheckCountry(Boolean checkCountry) {
        this.checkCountry = checkCountry;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("checkCountry", this.checkCountry);

    }

    public Boolean getCheckMemberlvl() {
        return checkMemberlvl;
    }

    public void setCheckMemberlvl(Boolean checkMemberlvl) {
        this.checkMemberlvl = checkMemberlvl;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("checkMemberlvl", this.checkMemberlvl);

    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("maxAge", this.maxAge);
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("minAge", this.minAge);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("gender", this.gender);

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("country", this.country);
    }

    public Integer getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("memberLevel", this.memberLevel);
    }

    public Map<String, String> getGenders() {
        return genders;
    }

    public void setGenders(Map<String, String> genders) {
        this.genders = genders;
    }

    public Map<String, String> getCountries() {
        return countries;
    }

    public void setCountries(Map<String, String> countries) {
        this.countries = countries;
    }

    public Map<Integer, String> getMemberLevels() {
        return memberLevels;
    }

    public void setMemberLevels(Map<Integer, String> memberLevels) {
        this.memberLevels = memberLevels;
    }

    public Collection<MemberEntity> getMembersByAG() {
        return membersByAG;
    }

    public void setMembersByAG(Collection<MemberEntity> membersByAG) {
        this.membersByAG = membersByAG;
    }

    public Collection<MemberEntity> getMembersByAC() {
        return membersByAC;
    }

    public void setMembersByAC(Collection<MemberEntity> membersByAC) {
        this.membersByAC = membersByAC;
    }

    public Collection<MemberEntity> getMembersByAM() {
        return membersByAM;
    }

    public void setMembersByAM(Collection<MemberEntity> membersByAM) {
        this.membersByAM = membersByAM;
    }

    public Collection<MemberEntity> getMembersByGC() {
        return membersByGC;
    }

    public void setMembersByGC(Collection<MemberEntity> membersByGC) {
        this.membersByGC = membersByGC;
    }

    public Collection<MemberEntity> getMembersByGM() {
        return membersByGM;
    }

    public void setMembersByGM(Collection<MemberEntity> membersByGM) {
        this.membersByGM = membersByGM;
    }

    public Collection<MemberEntity> getMembersByCM() {
        return membersByCM;
    }

    public void setMembersByCM(Collection<MemberEntity> membersByCM) {
        this.membersByCM = membersByCM;
    }

    public Collection<MemberEntity> getMembersByAGC() {
        return membersByAGC;
    }

    public void setMembersByAGC(Collection<MemberEntity> membersByAGC) {
        this.membersByAGC = membersByAGC;
    }

    public Collection<MemberEntity> getMembersByAGM() {
        return membersByAGM;
    }

    public void setMembersByAGM(Collection<MemberEntity> membersByAGM) {
        this.membersByAGM = membersByAGM;
    }

    public Collection<MemberEntity> getMembersByACM() {
        return membersByACM;
    }

    public void setMembersByACM(Collection<MemberEntity> membersByACM) {
        this.membersByACM = membersByACM;
    }

    public Collection<MemberEntity> getMembersByGCM() {
        return membersByGCM;
    }

    public void setMembersByGCM(Collection<MemberEntity> membersByGCM) {
        this.membersByGCM = membersByGCM;
    }

    public Collection<MemberEntity> getMembersByAGCM() {
        return membersByAGCM;
    }

    public void setMembersByAGCM(Collection<MemberEntity> membersByAGCM) {
        this.membersByAGCM = membersByAGCM;
    }

    public Collection<MemberEntity> getFilteredMembers() {
        return filteredMembers;
    }

    public void setFilteredMembers(Collection<MemberEntity> filteredMembers) {
        this.filteredMembers = filteredMembers;
    }

    public void initialCounties() {
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

    private void initSetting() throws Exception {
        if (!checkGender && !checkAge && !checkCountry && !checkMemberlvl) {
            System.out.println("ViewMembersBean: allMembers = acrm.getAllMembers();");
            allMembers = acrm.getAllMembers();
            filteredMembers = acrm.getAllMembers();
            System.out.println("ViewMembersBean: " + allMembers.toString());
        } else if (checkGender && !checkAge && !checkCountry && !checkMemberlvl) {
            membersByGender = acrm.getMembersByGender(gender);
            filteredMembers = acrm.getMembersByGender(gender);
        } else if (!checkGender && checkAge && !checkCountry && !checkMemberlvl) {
            membersByAge = acrm.getMembersByAge(minAge, maxAge);
            filteredMembers = acrm.getMembersByAge(minAge, maxAge);
        } else if (!checkGender && !checkAge && checkCountry && !checkMemberlvl) {
            membersByCountry = acrm.getMembersByNationality(country);
            filteredMembers = acrm.getMembersByNationality(country);
        } else if (!checkGender && !checkAge && !checkCountry && checkMemberlvl) {
            membersByMemberLevel = acrm.getMembersByMemberLevel(memberLevel);
            filteredMembers = acrm.getMembersByNationality(country);
        } else if (checkGender && checkAge && !checkCountry && !checkMemberlvl) {
            membersByAG = acrm.getMembersByAG(minAge, maxAge, gender);
            filteredMembers = acrm.getMembersByAG(minAge, maxAge, gender);
        } else if (!checkGender && checkAge && checkCountry && !checkMemberlvl) {
            membersByAC = acrm.getMembersByAC(minAge, maxAge, country);
            filteredMembers = acrm.getMembersByAC(minAge, maxAge, country);
        } else if (!checkGender && checkAge && !checkCountry && checkMemberlvl) {
            membersByAM = acrm.getMembersByAM(minAge, maxAge, memberLevel);
            filteredMembers = acrm.getMembersByAM(minAge, maxAge, memberLevel);
        } else if (checkGender && !checkAge && checkCountry && !checkMemberlvl) {
            membersByGC = acrm.getMembersByGC(gender, country);
            filteredMembers = acrm.getMembersByGC(gender, country);
        } else if (checkGender && !checkAge && !checkCountry && checkMemberlvl) {
            membersByGM = acrm.getMembersByGM(gender, memberLevel);
            filteredMembers = acrm.getMembersByGM(gender, memberLevel);
        } else if (!checkGender && !checkAge && checkCountry && checkMemberlvl) {
            membersByCM = acrm.getMembersByCM(country, memberLevel);
            filteredMembers = acrm.getMembersByCM(country, memberLevel);
        } else if (checkGender && checkAge && checkCountry && !checkMemberlvl) {
            membersByAGC = acrm.getMembersByAGC(minAge, maxAge, gender, country);
            filteredMembers = acrm.getMembersByAGC(minAge, maxAge, gender, country);
        } else if (checkGender && checkAge && !checkCountry && checkMemberlvl) {
            membersByAGM = acrm.getMembersByAGM(minAge, maxAge, gender, memberLevel);
            filteredMembers = acrm.getMembersByAGM(minAge, maxAge, gender, memberLevel);
        } else if (!checkGender && checkAge && checkCountry && checkMemberlvl) {
            membersByACM = acrm.getMembersByACM(minAge, maxAge, country, memberLevel);
            filteredMembers = acrm.getMembersByACM(minAge, maxAge, country, memberLevel);
        } else if (checkGender && !checkAge && checkCountry && checkMemberlvl) {
            membersByGCM = acrm.getMembersByGCM(gender, country, memberLevel);
            filteredMembers = acrm.getMembersByGCM(gender, country, memberLevel);
        } else {
            membersByAGCM = acrm.getMembersByAGCM(minAge, maxAge, gender, country, memberLevel);
            filteredMembers = acrm.getMembersByAGCM(minAge, maxAge, gender, country, memberLevel);
        }
    }

    private void getValuesOnSession() {
        // get boolean values for checking criteria
        checkGender = (Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("checkGender");
        if (checkGender == null) {
            checkGender = false;
            System.out.println("ViewMembersBean: init() : checkGender = null ==> set to false");
        }

        checkAge = (Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("checkAge");
        if (checkAge == null) {
            checkAge = false;
            System.out.println("ViewMembersBean: init() : checkAge = null ==> set to false");
        }

        checkCountry = (Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("checkCountry");
        if (checkCountry == null) {
            checkCountry = false;
            System.out.println("ViewMembersBean: init() : checkCountry = null ==> set to false");
        }

        checkMemberlvl = (Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("checkMemberlvl");
        if (checkMemberlvl == null) {
            checkMemberlvl = false;
            System.out.println("ViewMembersBean: init() : checkMemberlvl = null ==> set to false");
        }

        //get preset attribute value
        minAge = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("minAge");
        maxAge = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("maxAge");
        gender = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("gender");
        country = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("country");
        memberLevel = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("memberLevel");

    }

}
