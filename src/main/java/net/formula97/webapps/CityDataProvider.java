package net.formula97.webapps;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

import java.util.Iterator;
import java.util.List;

public class CityDataProvider extends SortableDataProvider<CityCode, String> {

    private static final long serialVersionUID = -6093104363074716350L;
    private List<CityCode> list;

    public CityDataProvider() {
        super();
        setSort("code", SortOrder.ASCENDING);
    }

    public CityDataProvider(List<CityCode> displayList) {
        this();
        this.list = displayList;
    }

    @Override
    public Iterator<? extends CityCode> iterator(long first, long count) {
        return sortByParam(getSort()).subList((int) first, (int) (first + count)).iterator();
    }

    @Override
    public long size() {
        return this.list.size();
    }

    @Override
    public IModel<CityCode> model(CityCode object) {
        return new CityDetachableModel(object, this.list);
    }

    /**
     * ソート方向に応じて保持しているリストを並び替える。
     *
     * @param param ソート方向に関するオブジェクト
     * @return ソート方向に応じたソート方向結果、nullや指定外の場合は保持しているものをそのまま返す
     */
    private List<CityCode> sortByParam(SortParam<String> param) {
        if (param != null) {
            switch (param.getProperty()) {
                case "code":
                    if (param.isAscending()) {
                        this.list.sort((o1, o2) -> o1.getCode().compareToIgnoreCase(o2.getCode()));
                    } else {
                        this.list.sort((o1, o2) -> o2.getCode().compareToIgnoreCase(o1.getCode()));
                    }
                    break;
                case "pref":
                    if (param.isAscending()) {
                        this.list.sort((o1, o2) -> o1.getPref().compareToIgnoreCase(o2.getPref()));
                    } else {
                        this.list.sort((o1, o2) -> o2.getPref().compareToIgnoreCase(o1.getPref()));
                    }
                    break;
                case "city":
                    if (param.isAscending()) {
                        this.list.sort((o1, o2) -> o1.getCity().compareToIgnoreCase(o2.getCity()));
                    } else {
                        this.list.sort((o1, o2) -> o2.getCity().compareToIgnoreCase(o1.getCity()));
                    }
                    break;
                default:
                    return this.list;
            }
        }
        return this.list;
    }
}
