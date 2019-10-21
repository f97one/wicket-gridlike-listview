package net.formula97.webapps;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		String[] h = {"アイテム1", "アイテム2", "アイテム3"};
		final List<String> headers = Arrays.asList(h);
		ListView<String> headerLV = new ListView<String>("tableHeader", headers) {
			@Override
			protected void populateItem(ListItem<String> item) {
				item.add(new Label("headerItem", item.getModel()));
			}
		};
		add(headerLV);

		List<ArrayList<Integer>> internalItems = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			ArrayList<Integer> rowitems = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				rowitems.add(i * 1000 + j * 100);
			}
			internalItems.add(rowitems);
		}

		ListDataProvider<ArrayList<Integer>> provider = new ListDataProvider<>(internalItems);
		DataView<ArrayList<Integer>> dataView = new DataView<ArrayList<Integer>>("rows", provider) {
			@Override
			protected void populateItem(Item<ArrayList<Integer>> item) {
				List<Integer> row = item.getModelObject();
				RepeatingView repeatingView = new RepeatingView("cols");
				for (int i : row) {
					repeatingView.add(new Label(repeatingView.newChildId(), i));
				}
				item.add(repeatingView);
			}
		};
		add(dataView);
	}

}
