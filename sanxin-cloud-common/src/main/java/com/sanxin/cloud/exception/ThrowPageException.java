package com.sanxin.cloud.exception;

public class ThrowPageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -100509897248249450L;
	
	
	public ThrowPageException(String arg0){
		super(arg0);
	}
	
	public ThrowPageException(){
		super();
	}
	
	public ThrowPageException(Throwable arg0){
		super(arg0);
	}

}
