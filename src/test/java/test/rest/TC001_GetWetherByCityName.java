package test.rest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.RestAssuredBase;

public class TC001_GetWetherByCityName extends RestAssuredBase {

	@BeforeTest
	public void setValues() {

		testCaseName = "Get weather";
		testDescription = "Get weather by city name and verify it";
		nodes = "Open weather";
		authors = "Sanjay";
		category = "REST";
		dataFileName = "TC001";
		dataFileType = "excel";
	}
	
	@Test(dataProvider = "fetchData")
	public void getWeather(String cityName) {
		Response response = getWithParam("q", cityName,"appid", "df2e98029ce5450ae566fc404de48bba");
		verifyResponseCode(response, 200);
		verifyResponseTime(response, 5000);
		
		System.out.println("Wind speed is: " + getContentWithKey(response, "wind.speed"));
		System.out.println("Maximum Temp: " + getContentWithKey(response, "main.temp_max"));
		System.out.println("Sunset Time: " + getContentWithKey(response, "sys.sunset"));
	}
	
	
}
