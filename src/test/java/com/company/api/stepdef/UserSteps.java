package com.company.api.stepdef;

import com.aventstack.extentreports.Status;
import com.company.model.request.User;
import com.company.service.UsersService;
import com.company.util.ExtentReportManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class UserSteps {

    private User user;
    private Response response;
    private String userId;

    private UsersService usersService;

    @Given("a user payload with name {string} and email {string}")
    public void createUserPayload(String name, String email) {
        user = new User(name, email);
        ExtentReportManager.scenario.log(Status.PASS, "user details " + user.toString());
    }

    @When("I send a POST request to {string}")
    public void sendPostRequest(String endpoint) {
        usersService.createUser(user, endpoint);
        if (response.statusCode() == 201) {
            userId = response.jsonPath().getString("id");
        }
    }

    @Then("the response status code should be {int}")
    public void validateStatusCode(int expectedStatus) {
        Assert.assertEquals(response.statusCode(), expectedStatus);
    }

    @Then("the response should contain the user name {string}")
    public void validateResponseName(String expectedName) {
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals(actualName, expectedName);
    }

    @Given("an existing user is created")
    public void createExistingUser() {
        createUserPayload("John Doe", "john@example.com");
        sendPostRequest("/users");
        Assert.assertEquals(response.statusCode(), 201);
    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        endpoint = endpoint.replace("{id}", userId);
        response = usersService.getUser(endpoint, userId);
    }

    @When("I send a PUT request to {string} with updated name {string}")
    public void sendPutRequest(String endpoint, String newName) {
        user.setName(newName);
        endpoint = endpoint.replace("{id}", userId);
        usersService.updateUser(user, endpoint);
    }

    @When("I send a DELETE request to {string}")
    public void sendDeleteRequest(String endpoint) {
        endpoint = endpoint.replace("{id}", userId);
        usersService.deleteUser(endpoint, userId);
    }

    @Then("the response should contain the user email {string}")
    public void validateResponseEmail(String expectedEmail) {
        String actualEmail = response.jsonPath().getString("email");
        Assert.assertEquals(actualEmail, expectedEmail);
    }
}

