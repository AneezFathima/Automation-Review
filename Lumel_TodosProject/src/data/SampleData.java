package data;

import java.util.ArrayList;
import java.util.List;

public class SampleData {
	
	List<String> strArytodosListData;
	List<String> strArytodosCompListData;
	List<String> strArytodoDeleteListData;
	List<String> strArytodoFilterListData;
	
	
	public List<String> gettodoslistData() {
		
		strArytodosListData = new ArrayList<String>();
	
		strArytodosListData.add("Drink Water");
		strArytodosListData.add("Go for a walk");
		strArytodosListData.add("Do Laundary");
		strArytodosListData.add("EXERCISE Daily");		
		strArytodosListData.add("Read a Book");

		
		return strArytodosListData;
	}
	
	public List<String> gettodosCompletedlistData() {
		
		strArytodosCompListData = new ArrayList<String>();

		strArytodosCompListData.add("Go for a walk");
		strArytodosCompListData.add("Do Laundary");
		
		return strArytodosCompListData;
	}
	
	public List<String> gettodosDeletelistData() {
		
		strArytodoDeleteListData = new ArrayList<String>();
	
		strArytodoDeleteListData.add("Drink Water");
		strArytodoDeleteListData.add("EXERCISE Daily");
		
		return strArytodoDeleteListData;
	}
	
	public List<String> gettodosFilterlistData() {
		
		strArytodoFilterListData = new ArrayList<String>();
	
		strArytodoFilterListData.add("Drink Water");
		strArytodoFilterListData.add("Go for a walk");
		strArytodoFilterListData.add("Do Laundary");
		strArytodoFilterListData.add("EXERCISE Daily");
		strArytodoFilterListData.add("Read a book");
		
		return strArytodoFilterListData;
	}
}