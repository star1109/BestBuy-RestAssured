package ui.swagger.testbase;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import ui.swagger.utils.TestUtils;

/**
 * Created by Jay Vaghani
 */
public class TestBase extends TestUtils {
    static ValidatableResponse response;
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";


    }
}
