package com.lex.app.domain;

import java.util.List;

public class HomeListData {
	public Data data;
	
	public class Data {
		public String countcommenturl;
		public String more;
		public List<NewsData> news;
		public List<TopNewsData> topnews;
		@Override
		public String toString() {
			return "Data [topnews=" + topnews + "]";
		}
		
		
	}
	
	
	public class NewsData {
		public boolean comment;
		public String commentlist;
		public String commenturl;
		public String id;
		public String listimage;
		public String pubdate;
		public String title;
		public String url;
		@Override
		public String toString() {
			return "NewsData [title=" + title + "]";
		}
		
	}
	
	public class TopNewsData {
		public boolean comment;
		public String commentlist;
		public String commenturl;
		public String id;
		public String pubdate;
		public String title;
		public String topimage;
		public String url;
		@Override
		public String toString() {
			return "TopNewsData [title=" + title + "]";
		}
	}

	@Override
	public String toString() {
		return "HomeListData [data=" + data + "]";
	}

}
