package com.company.base;

import com.company.util.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseService {

    private static final String Base_URI = ConfigurationReader.getProperty("url");

    private final RequestSpecification requestSpecification;
    private Response response;

    public BaseService() {
        requestSpecification = given().baseUri(Base_URI);
    }

    protected Response sendPostRequest(Object payload, String endpoint) {
        return requestSpecification.contentType(ContentType.JSON).body(payload).post(endpoint);
    }

    protected Response sendPutRequest(Object payload, String endpoint) {
        return requestSpecification.contentType(ContentType.JSON).body(payload).put(endpoint);
    }

    protected Response sendGetRequest(String endpoint) {
        return requestSpecification.get(endpoint);
    }

    protected Response sendDeleteRequest(String enpoint) {
        return requestSpecification.delete(enpoint);
    }

}
