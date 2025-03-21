package RestAssured.RestAssuredPOM.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import RestAssured.RestAssuredPOM.Payloads.User;
import RestAssured.RestAssuredPOM.endpoints.UserEndPoints;
import io.restassured.response.Response;
import org.testng.Assert;

    public class UserTest {
    	
    	//Using Faker

        User userPayload = new User();
        String username1 = "testUser123";
        
        Faker faker;
       // User userPayload1;
        String username; // ✅ Store a fixed username for all tests

        @BeforeClass
        void setUp() {
            faker = new Faker();
            userPayload = new User();

            userPayload.setUserid(faker.idNumber().hashCode());
            username1 = faker.name().username(); // ✅ Generate once and use everywhere
            userPayload.setUsername(username1);
            userPayload.setFirstName(faker.name().firstName());
            userPayload.setLastName(faker.name().lastName());
            userPayload.setEmail(faker.internet().safeEmailAddress());
            userPayload.setPassword(faker.internet().password(5, 10));
            userPayload.setPhonenumber(faker.phoneNumber().cellPhone());
        }

        @Test(priority = 1, groups = {"smoke", "sanity"})
        public void testPostUser() {
            Response res = UserEndPoints.createUser(userPayload);
            res.then().log().all();
            Assert.assertEquals(res.getStatusCode(), 200);
        }

        @Test(priority = 2, groups = {"sanity"})
        public void testGetUserByName() {
            Response res = UserEndPoints.readUser(username1);
            res.then().log().all();
            Assert.assertEquals(res.getStatusCode(), 200);
        }

        @Test(priority = 3, groups = {"sanity"})
        public void testUpdateUser() {
            userPayload.setFirstName("UpdatedName");
            Response res = UserEndPoints.updateUser(username1, userPayload);
            res.then().log().all();
            Assert.assertEquals(res.getStatusCode(), 200);
        }

        @Test(priority = 4, groups = {"smoke"})
        public void testDeleteUser() {
            Response res = UserEndPoints.deleteUser(username1);
            res.then().log().all();
            Assert.assertEquals(res.getStatusCode(), 200);
        }
    }


