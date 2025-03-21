package RestAssured.RestAssuredPOM.endpoints;

import org.json.JSONObject;
import org.testng.annotations.Test;

import RestAssured.RestAssuredPOM.Payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class UserEndPoints {
	
	public static Response createUser(User payload){
		
		Response res=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(Routes.postUrl);
		
		return res;
		
		
	}
public static Response readUser(String userName){
		
		Response res=given()
		.pathParam("username", userName)
		
		.when()
		.get(Routes.getUrl);
		
		return res;
		
		
	}
public static Response updateUser(String userName,User payload){
	
	Response res=given()
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.pathParam("username", userName)
	.body(payload)
	
	.when()
	.put(Routes.updateUrl);
	
	return res;
	
	
}
public static Response deleteUser(String userName){
	
	Response res=given()
	.pathParam("username", userName)
	
	.when()
	.delete(Routes.deleteUrl);
	
	return res;
	
	
}
}
