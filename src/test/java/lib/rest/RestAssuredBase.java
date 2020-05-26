package lib.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredBase extends PreAndTest {

	public static RequestSpecification setLogs() {
		return RestAssured
				.given()
				.log()
				.all()
				.accept(getContentType());
	}
	
	public static Response getWithParam(String key, String value,String key1, String value1) {
		return setLogs()
				.params(key,value)
				.and()
				.params(key1,value1)
				.when()
				.get();
	}
	
	private static ContentType getContentType() {
		return ContentType.JSON;
	}
	public static void verifyResponseCode(Response response, int code) {
		if(response.statusCode() == code) {
			reportRequest("The status code "+code+" matches the expected code", "PASS");
		}else {
			reportRequest("The status code "+code+" does not match the expected code"+response.statusCode(), "FAIL");	
		}
	}
	public static void verifyResponseTime(Response response, long time) {
		if(response.time() <= time) {
			reportRequest("The time taken "+response.time() +" with in the expected time", "PASS");
		}else {
			reportRequest("The time taken "+response.time() +" is greater than expected SLA time "+time,"FAIL");		
		}
	}
	
	public static String getContentWithKey(Response response, String key) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return  jsonPath.getString(key);			
		}else {
			return null;
		}
	}
	
}
