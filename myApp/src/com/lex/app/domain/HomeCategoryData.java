package com.lex.app.domain;

import java.util.List;

public class HomeCategoryData {
	
	public List<Data> data;
	
	public class Data {
		public String title;
		public String url;
		public List<Children> children;
		@Override
		public String toString() {
			return "Data [title=" + title + ", children=" + children + "]";
		}
	}
	
	public class Children {
		public String id;
		public String title;
		public String url;
		@Override
		public String toString() {
			return "Children [title=" + title + "]";
		}
	}

	@Override
	public String toString() {
		return "CategoryData [data=" + data + "]";
	}

}
