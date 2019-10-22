package net.formula97.webapps;

import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;
import java.util.Objects;

public class CityDetachableModel extends LoadableDetachableModel<CityCode> {

    private static final long serialVersionUID = 230900926972197023L;
    private String code;
    private List<CityCode> cityList;

    public CityDetachableModel(String code) {
        this.code = code;
    }

    public CityDetachableModel(CityCode object) {
        this(object.getCode());
    }

    public CityDetachableModel(CityCode cityCode, List<CityCode> wholeList) {
        this(cityCode.getCode());
        this.cityList = wholeList;
    }

    @Override
    protected CityCode load() {
        return cityList.stream().filter(code -> code.getCode().equals(this.code)).findFirst().orElse(null);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (obj instanceof CityDetachableModel) {
            CityDetachableModel other = (CityDetachableModel) obj;
            return other.code.equals(this.code);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
