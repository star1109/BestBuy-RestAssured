package ui.swagger.storesinfo;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static io.restassured.RestAssured.given;

public class extractResponse {


    static ValidatableResponse response;

    @BeforeClass

    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        RestAssured.basePath = "/stores";
        response = given()
                .when()
                .get()
                .then().statusCode(200);
    }
    @Test
    public void test001() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("limit : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    @Test
    public void test002() {
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the name of 5th store
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the names of all the store
    @Test
    public void test004() {
        ArrayList<String> listOfNames = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the names of all the store = " + listOfNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //Extract the storeId of all the store
    @Test
    public void test005() {

        List<Integer> listOfStoreIds = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the storeId of all the store = " + listOfStoreIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //Print the size of the data list
    @Test
    public void test006() {
        List<Integer> dataList = response.extract().path("data.length");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the size of the data list = " + dataList.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name=='St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("all the value of the store where store name = St Cloud = " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        List<String> list = response.extract().path("data.findAll{it.name=='Rochester'}.address");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the address of the store where store name = Rochester = " + list);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the services of 8th store
    @Test
    public void test009() {
        List<Object> services = response.extract().path("data[7].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("all the services of 8th store = " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        // List<Object> storeServices = response.extract().path("data.findAll{it.services.name=='Windows Store'}");
       List<?> storeServices = response.extract().path("data.find{it.services}.services.findAll{it.name='Windows Store'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("storeservices of the store where service name = Windows Store = " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get all the storeId of all the store
    @Test
    public void test011() {
        List<?> listOfStoreIds = response.extract().path("data.services.storeservices.findAll{it.storeId}.storeId");
        Iterator<?>itr = listOfStoreIds.iterator();
        while (itr.hasNext()){
            List<?> check = (List<?>) itr.next();
            System.out.println(check.get(0));
        }

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the storeId of all the store = " + listOfStoreIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //Get ID of all the stores
    @Test
    public void test012() {
        List<Integer> listOfIds = response.extract().path("data.services.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The ID's of all the store = " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the store names Where state = ND
    @Test
    public void test013() {
        List<String> listOfStores = response.extract().path("data.findAll{it.state=='ND'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store names, where the state begins with ND = " + listOfStores);
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<?> listOfStores = response.extract().path("data.findAll{it.name=='Rochester'}.services.find{it.id}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of services for the store where store name is Rochester = " + listOfStores.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        Object createdAt = response.extract().path("data.findAll{it.name='Windows Store'}.services.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all services whose name is “Windows Store” : " + createdAt);
        System.out.println("------------------End of Test---------------------------");

    }

    // Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<?> services = response.extract().path("data.findAll{it.services}.findAll{it.name=='Fargo'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all services where store name = “Fargo” : " + services);
        System.out.println("------------------End of Test---------------------------");

    }

    // Find the zip of all the store
    @Test
    public void test017() {
        List<?> zipcodes = response.extract().path("data.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store : " + zipcodes);
        System.out.println("------------------End of Test---------------------------");

    }

    //  Find the zip of store name = Roseville
    @Test
    public void test018() {
        List<?> rosevilleZipcode = response.extract().path("data.findAll{it.name=='Roseville'}.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of store name = Roseville :" + rosevilleZipcode);
        System.out.println("------------------End of Test---------------------------");

    }

    //Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<HashMap<String,?>> storeServices = response.extract().path("data.find{it.services}.services.findAll{it.name='Magnolia Home Theater'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices details of the service name = Magnolia Home Theater :" + storeServices);
        System.out.println("------------------End of Test---------------------------");

    }

    //Find the lat of all the stores
    @Test
    public void test020() {
        List<?> latitudeStores = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all the stores :" + latitudeStores);
        System.out.println("------------------End of Test---------------------------");

    }
    }