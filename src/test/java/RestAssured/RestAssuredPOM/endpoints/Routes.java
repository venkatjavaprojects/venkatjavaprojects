package RestAssured.RestAssuredPOM.endpoints;


public class Routes{
	
	public static String baseUrl= "https://petstore.swagger.io/v2";
	public static String postUrl= baseUrl+"/user";
	public static String getUrl= "https://petstore.swagger.io/v2/user/{username}";
	public static String updateUrl= baseUrl+"/user/{username}";
	public static String deleteUrl= "https://petstore.swagger.io/v2/user/{username}";
	
}
  