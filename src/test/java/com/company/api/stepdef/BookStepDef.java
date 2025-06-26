package com.company.api.stepdef;

import com.aventstack.extentreports.Status;
import com.company.model.request.Book;
import com.company.service.BookService;
import com.company.util.ExtentReportManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class BookStepDef {
    BookService bookService = new BookService();
    Response response;
    Book book;
    int id;

    @Given("a new book with title {string}, author {string}, and year {int}")
    public void a_new_book_with_title_author_and_year(String title, String author, Integer year) {
        book = new Book(title, author, year);
        ExtentReportManager.scenario.info("Book Creation API " + book);

    }

    @When("I send a POST request to create the book")
    public void i_send_a_post_request_to_create_the_book() {
        response = bookService.createBook(book);
        id = response.jsonPath().get("id");
        ExtentReportManager.scenario.log(Status.INFO, "Response: " + response.asString());

    }

    @Then("the book should be created with status code {int}")
    public void the_book_should_be_created_with_status_code(Integer statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode.intValue());
        ExtentReportManager.scenario.log(Status.PASS, "Book created successfully");
    }

    @When("I retrieve the book by ID")
    public void i_retrieve_the_book_by_id() {

        bookService.getBookById(id);

        ExtentReportManager.scenario.log(Status.INFO, "Fetched book by ID: " + id);
    }

    @Then("the response should contain the title {string}")
    public void response_should_contain_title(String expectedTitle) {
        String actualTitle = response.jsonPath().getString("title");
        Assert.assertEquals(actualTitle, expectedTitle);
        ExtentReportManager.scenario.log(Status.PASS, "Book title matched: " + actualTitle);
    }

    @When("I update the book title to {string}")
    public void i_update_the_book_title(String newTitle) {
        book.setTitle(newTitle);
        bookService.updateBook(id, book);
        ExtentReportManager.scenario.log(Status.INFO, "Updated book with new title: " + newTitle);
    }

    @Then("the book should be updated with status code {int}")
    public void book_should_be_updated(Integer statusCode) {
        Assert.assertEquals(response.statusCode(), statusCode.intValue());
        ExtentReportManager.scenario.log(Status.PASS, "Book updated successfully");
    }

    @When("I delete the book by ID")
    public void i_delete_book_by_id() {
        bookService.deleteBook(id);
        ExtentReportManager.scenario.log(Status.INFO, "Deleted book ID: " + id);
    }

    @Then("the book should be deleted with status code {int}")
    public void book_should_be_deleted(Integer statusCode) {
        Assert.assertEquals(response.statusCode(), statusCode.intValue());
        ExtentReportManager.scenario.log(Status.PASS, "Book deleted successfully");
    }

    @When("I try to fetch a non-existent book ID")
    public void i_try_to_fetch_nonexistent_book() {
        bookService.getBookById(999);
        ExtentReportManager.scenario.log(Status.INFO, "Attempted to fetch non-existent book ID 999");
    }
}
