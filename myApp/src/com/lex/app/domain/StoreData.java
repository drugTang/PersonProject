package com.lex.app.domain;

import java.util.List;

public class StoreData {
	public int retcode;
	public List<SingleData> data;
	
	public class SingleData {
		public String id;
		public String title;
		public String description;
		public String price;
		public String sell;
		public String image;
		public String distance;
		@Override
		public String toString() {
			return "SingleData [title=" + title + "]";
		}
	}

	@Override
	public String toString() {
		return "StoreData [data=" + data + "]";
	}
	
}
