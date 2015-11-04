package com.lex.app.domain;

import java.util.List;

public class ShoppingCarData {
	public int retcode;
	public List<SingleData> data;
	
	public class SingleData {
		public String id;
		public String image;
		public String title;
		public String price;
		public String count;
		@Override
		public String toString() {
			return "SingleData [title=" + title + "]";
		}
		
	}

	@Override
	public String toString() {
		return "ShoppingCarData [data=" + data + "]";
	}
	
}
