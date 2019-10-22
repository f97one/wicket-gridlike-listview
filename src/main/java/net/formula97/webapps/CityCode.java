package net.formula97.webapps;

import java.io.Serializable;
import java.util.Objects;

public class CityCode implements Serializable {

    private static final long serialVersionUID = -2184195590447212879L;

    public CityCode(String code, String pref, String city) {
        this.code = code;
        this.pref = pref;
        this.city = city;
    }

    public CityCode() {
    }

    private String code;
    private String pref;
    private String city;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPref() {
        return pref;
    }

    public void setPref(String pref) {
        this.pref = pref;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CityCode{" +
                "code='" + code + '\'' +
                ", pref='" + pref + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityCode cityCode = (CityCode) o;
        return Objects.equals(code, cityCode.code) &&
                Objects.equals(pref, cityCode.pref) &&
                Objects.equals(city, cityCode.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, pref, city);
    }
}
