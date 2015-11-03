package com.lex.love.utils;

public interface HttpCallbackListener {
	void onSuccess(String result);
	void onFailure(Exception e,String msg);
}
