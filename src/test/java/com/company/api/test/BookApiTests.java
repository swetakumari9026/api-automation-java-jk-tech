package com.company.api.test;

import com.aventstack.extentreports.Status;
import com.company.model.request.Book;
import com.company.service.BookService;
import com.company.util.ExtentReportManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookApiTests extends ExtentReportManager {

    private static int createdBookId;
    BookService bookService;
    Response response;

    @Test(priority = 1)
    public void testGetAllBooks() {
        extent.createTest("Get All Books");
        response = bookService.getBookAllBooks("/books");
        Assert.assertEquals(response.statusCode(), 200);
        scenario.log(Status.PASS, "Book get all" + response.getBody());
    }

    @Test(priority = 2)
    public void testCreateBook() {
        Book book = new Book("API Testing with RestAssured", "QA Author", 2023);
        bookService.createBook(book);
        Assert.assertEquals(response.statusCode(), 200);
        createdBookId = response.jsonPath().getInt("id");
        scenario.log(Status.PASS, "Book created with ID: " + createdBookId);
    }

    @Test(priority = 3, dependsOnMethods = "testCreateBook")
    public void testGetBookById() {
        extent.createTest("Get Book By ID");
        bookService.getBookById(createdBookId);

        scenario.log(Status.INFO, "Response: " + response.asString());

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "API Testing with RestAssured");
        scenario.log(Status.PASS, "Book retrieved successfully");
    }

    @Test(priority = 4, dependsOnMethods = "testCreateBook")
    public void testUpdateBook() {
        extent.createTest("Update Book");

        Book updatedBook = new Book("Updated Title", "Updated Author", 2024);

        bookService.updateBook(createdBookId, updatedBook);

        scenario.log(Status.INFO, "Payload: " + updatedBook.toString());
        scenario.log(Status.INFO, "Response: " + response.asString());

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "Updated Title");
        scenario.log(Status.PASS, "Book updated successfully");
    }

    @Test(priority = 5, dependsOnMethods = "testCreateBook")
    public void testDeleteBook() {
        extent.createTest("Delete Book");

        bookService.deleteBook(createdBookId);

        scenario.log(Status.INFO, "Response: " + response.asString());

        Assert.assertEquals(response.statusCode(), 200);
        scenario.log(Status.PASS, "Book deleted successfully");
    }

    @Test(priority = 6)
    public void testGetNonExistentBook() {
        extent.createTest("Get Non-existent Book");

        bookService.getBookById(9999);

        scenario.log(Status.INFO, "Response: " + response.asString());

        Assert.assertEquals(response.statusCode(), 404);
        scenario.log(Status.PASS, "Verified book not found scenario");
    }
}
