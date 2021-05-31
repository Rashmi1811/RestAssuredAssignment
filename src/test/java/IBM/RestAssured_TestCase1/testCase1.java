package IBM.RestAssured_TestCase1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import util.utility;

import static io.restassured.RestAssured.given;

import java.io.IOException;

public class testCase1 {
	
 

	
	@Test(enabled = true, dataProvider = "utility")
	public void util (String rootbodyName, String rootbodyStatus, String categoryname, String tagsname, String photpel1,String photpel2) {
		RestAssured.baseURI = "http://localhost:3000/";
		JSONObject rootbodyobject = new JSONObject();
		JSONObject categoryobject = new JSONObject();
		JSONObject tagsobject = new JSONObject();

		rootbodyobject.put("id", 0);
		rootbodyobject.put("name", rootbodyName);
		rootbodyobject.put("status", rootbodyStatus);
		
		categoryobject.put("id",0);
		categoryobject.put("name", categoryname);
		
		tagsobject.put("id", 0);
		tagsobject.put("name", tagsname);
		
		//Adding the Category and Tags object into the Rootbody
		rootbodyobject.put("category", categoryobject);
		rootbodyobject.put("tags", tagsobject);
		
		
		//JSON Array Body 
		JSONArray arraybody = new JSONArray();
		arraybody.add(photpel1);
		arraybody.add(photpel2);
		
		//Adding the Arrayobject into Root body
		rootbodyobject.put("photoUrls", arraybody);
		
		
		
		
		
		System.out.println(rootbodyobject);
	

		given().body(rootbodyobject.toJSONString()).headers("content-type", "Application/JSON").when().post("students")
				.then().statusCode(201).log().all();
		
	}
	
	

	@DataProvider(name = "utility")
	public Object[][] exceldata() throws IOException {
		Object[][] data = utility.gettestdata();

		return data; 

	}


}
