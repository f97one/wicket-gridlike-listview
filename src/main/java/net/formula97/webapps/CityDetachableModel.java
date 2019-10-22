package net.formula97.webapps;

import org.apache.wicket.model.LoadableDetachableModel;

import java.util.List;

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
}
