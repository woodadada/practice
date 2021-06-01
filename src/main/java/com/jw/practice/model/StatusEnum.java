package com.jw.practice.model;

/**
 * 에러 코드 Enum 
 * @author wjddn
 *
 */
public enum StatusEnum {
	OK(200, "OK"),
	BAD_REQUEST(400, "BAD_REQUEST"),
	NOT_FOUNT(404, "NOT_FOUND"),
	INTERNAL_SERER_ERROR(500, "INTERNAL_SERVER_ERROR");
	
	int statusCode;
	String code;
	
	StatusEnum(int statusCode, String code){
		this.statusCode = statusCode;
		this.code = code;
	}
}
