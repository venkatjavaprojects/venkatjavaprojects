package RestAssured.RestAssuredPOM.endpoints;

import org.json.JSONObject;
import org.testng.annotations.Test;

import RestAssured.RestAssuredPOM.Payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;


public class UserEndPoints2 {
	static ResourceBundle getUrl(){
	ResourceBundle routes=	ResourceBundle.getBundle("routes");
	return routes;
	}
	
	public static Response createUser(User payload){
		
		String postUrl=getUrl().getString("postUrl");
		
		Response res=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
		
		.when()
		.post(postUrl);
		
		return res;
		
		
	}
public static Response readUser(String userName){
	String getUrl=getUrl().getString("getUrl");
		
		Response res=given()
		.pathParam("username", userName)
		
		.when()
		.get(getUrl);
		
		return res;
		
		
	}
public static Response updateUser(String userName,User payload){
	String updateUrl=getUrl().getString("updateUrl");

	
	Response res=given()
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.pathParam("username", userName)
	.body(payload)
	
	.when()
	.put(updateUrl);
	
	return res;
	
	
}
public static Response deleteUser(String userName){
	String deleteUrl=getUrl().getString("deleteUrl");
	
	
	Response res=given()
	.pathParam("username", userName)
	
	.when()
	.delete(deleteUrl);
	
	return res;
	
	
}
}
