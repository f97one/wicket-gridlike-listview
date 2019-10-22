package net.formula97.webapps;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
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
        return list.subList((int) first, (int) (first + count)).iterator();
    }

    @Override
    public long size() {
        return this.list.size();
    }

    @Override
    public IModel<CityCode> model(CityCode object) {
        return new CityDetachableModel(object, this.list);
    }
}
