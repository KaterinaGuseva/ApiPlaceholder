package utils.apiutils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtilsResponses {

    public static Response executeGet(String endpoint) {
        return RestAssured.given()
                .when()
                .get(endpoint);
    }

    public static Response executePost(Object requestBody, String endpoint) {
        return RestAssured.given()
                .when()
                .body(requestBody)
                .post(endpoint);
    }
}
