package com.lex.app.domain;

import java.util.List;

public class MessageData {
	public int retcode;
	public List<SingleData> data;
	
	/**
	 * 
	 * 
	 * @author Administrator
	 *
	 */
	public class SingleData {
		public String id;
		public String username;
		public String headicon;
		public String pubdate;
		public String from;
		public String content;
		public String likecount;
		public String comment;
		@Override
		public String toString() {
			return "SingleData [id=" + id + ", username=" + username + "]";
		}
	}

	@Override
	public String toString() {
		return "MessageData [retcode=" + retcode + ", data=" + data + "]";
	}
	
}
