package net.formula97.webapps;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		// ソート機能のないやつ
		// 各配列のインデックスを一致させることで、データをグリッド的に並べる

		// ヘッダ部分
		String[] h = {"アイテム1", "アイテム2", "アイテム3"};
		final List<String> headers = Arrays.asList(h);
		ListView<String> headerLV = new ListView<String>("tableHeader", headers) {
			@Override
			protected void populateItem(ListItem<String> item) {
				item.add(new Label("headerItem", item.getModel()));
			}
		};
		add(headerLV);

		// 本体部分
		List<ArrayList<Integer>> internalItems = new ArrayList<>();

		for (int i = 1; i <= 5; i++) {
			ArrayList<Integer> rowitems = new ArrayList<>();
			for (int j = 0; j < 3; j++) {
				rowitems.add(i * 1000 + j * 100);
			}
			internalItems.add(rowitems);
		}

		// 本体部分にデータを張る処理
		ListDataProvider<ArrayList<Integer>> provider = new ListDataProvider<>(internalItems);
		DataView<ArrayList<Integer>> dataView = new DataView<ArrayList<Integer>>("rows", provider) {
			private static final long serialVersionUID = -7388444179966849311L;

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

		// ソート機能のあるやつ
		List<CityCode> cityCodeList = loadFromCsv("city_code.csv");
		CityDataProvider cityDataProvider = new CityDataProvider(cityCodeList);
		DataView<CityCode> cityCodeDataView = new DataView<CityCode>("sortedRow", cityDataProvider) {
			private static final long serialVersionUID = -804351522780441495L;

			@Override
			protected void populateItem(Item<CityCode> item) {
				CityCode cityCode = item.getModelObject();

				item.add(new Label("code", Model.of(cityCode.getCode())));
				item.add(new Label("pref", Model.of(cityCode.getPref())));
				item.add(new Label("city", Model.of(cityCode.getCity())));
			}
		};
		cityCodeDataView.setItemsPerPage(10L);

		add(cityCodeDataView);
		add(new PagingNavigator("navigator", cityCodeDataView));
	}

	/**
	 * CSVを型のリストに組み立てなおす。
	 *
	 * @param filename 読み込み対象のファイル名
	 * @return CSVを組み立てなおしたもの
	 */
	@NotNull
	private List<CityCode> loadFromCsv(String filename) {
		InputStream countriesStream = HomePage.class.getResourceAsStream(filename);
		Scanner scanner = new Scanner(countriesStream);
		List<CityCode> countries = new ArrayList<>();

		while(scanner.hasNext()){
			String curLine = scanner.nextLine();
			String[] row = curLine.split(",");

			CityCode cityCode = new CityCode(row[0], row[1], row[2]);

			countries.add(cityCode);
		}

		scanner.close();

		return countries;
	}


}
