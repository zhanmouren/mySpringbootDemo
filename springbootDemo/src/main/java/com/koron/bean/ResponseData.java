package com.koron.bean;



public class ResponseData {
	/**
	 * 返回类封装
	 */
	
	public static final int SUCCESS_CODE = 0;
	
	public static final int FAIL_CODE = -1;

	private int code;
	
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private Object data;
	
	private int page;
	
	private long total;
	
	public final static ResponseData success(String description) {
		ResponseData data = new ResponseData();
		data.setCode(ResponseData.SUCCESS_CODE);
		data.setDescription(description);
		return data;
	}
	
	public final static ResponseData success(Object object, String description) {
		ResponseData data = new ResponseData();
		data.setCode(ResponseData.SUCCESS_CODE);
		data.setDescription(description);
		data.setData(object);
		return data;
	}
	
	public final static ResponseData success(int page, long count, Object object, String description) {
		ResponseData data = new ResponseData();
		data.setCode(ResponseData.SUCCESS_CODE);
		data.setDescription(description);
		data.setData(object);
		data.setPage(page);
		data.setTotal(count);
		return data;
	}
	
	public final static ResponseData faill(String description) {
		ResponseData data = new ResponseData();
		data.setCode(ResponseData.FAIL_CODE);
		data.setDescription(description);
		return data;
	}
	
	public final static ResponseData faill(Object object, String description) {
		ResponseData data = new ResponseData();
		data.setCode(ResponseData.FAIL_CODE);
		data.setDescription(description);
		data.setData(object);
		return data;
	}
	
	public final static ResponseData faill(int code, String description) {
		ResponseData data = new ResponseData();
		data.setCode(code);
		data.setDescription(description);
		return data;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getCode() {
		return code;
	}

	public Object getData() {
		return data;
	}

	

	public void setCode(int code) {
		this.code = code;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
