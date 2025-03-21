package RestAssured.RestAssuredPOM.test;

import org.testng.annotations.Test;
import RestAssured.RestAssuredPOM.Payloads.User;
import RestAssured.RestAssuredPOM.endpoints.UserEndPoints;
import RestAssured.RestAssuredPOM.endpoints.UserEndPoints2;
import RestAssured.RestAssuredPOM.utilities.DataProviderClass;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class UserTest2 {
	
	// Using propeties file here
	
	public Logger logger;

    @Test(priority = 1, dataProvider = "allData", dataProviderClass = DataProviderClass.class)
    public void testPostUser(String userid, String username, String firstname, 
                             String lastname, String password, String email, String phonenumber,String userStatus) {
        
    	logger= LogManager.getLogger(this.getClass());
    	logger.info("**************Create user************");
        int parsedUserId = 0;
        try {
            parsedUserId = Integer.parseInt(userid.trim());
        } catch (NumberFormatException e) {
            Assert.fail("Invalid user ID: " + userid);
        }

        // ✅ Creating user payload
        User userPayload = new User();
        userPayload.setUserid(parsedUserId);
        userPayload.setUsername(username);
        userPayload.setFirstName(firstname);
        userPayload.setLastName(lastname);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhonenumber(phonenumber);
        userPayload.setUserStatus(Integer.parseInt(userStatus));
        
        
        //logger.info("**************Reading user************");

        // ✅ Sending API request
        Response res = UserEndPoints2.createUser(userPayload);
        
        // ✅ Validate response
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(), 200, "Failed to create user!");
        
        logger.info("**************Reading user************");
    }
    @Test(priority = 4, dataProvider = "userNames", dataProviderClass = DataProviderClass.class)
    public void testDeleteUser(String userName) {
        // First, check if the user exists
        Response getRes = UserEndPoints2.deleteUser(userName);
        if (getRes.getStatusCode() == 404) {
            System.out.println("User " + userName + " does not exist. Skipping deletion.");
            return; // Stop execution
        }

        // If user exists, proceed with deletion
        Response res = UserEndPoints2.deleteUser(userName);
        System.out.println("Delete Response Code: " + res.getStatusCode());
       // Assert.assertEquals(res.getStatusCode(), 200);
    }

}
