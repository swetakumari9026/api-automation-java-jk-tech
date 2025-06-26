package com.company.service;

import com.company.base.BaseService;
import com.company.model.request.Book;
import io.restassured.response.Response;

public class BookService extends BaseService {
    public static final String Base_Path = "/api/book";

    public Response createBook(Book book) {
        return sendPostRequest(book, Base_Path + "/createBook");

    }

    public Response getBookById(int id) {
        return sendGetRequest(Base_Path + "/books/" + id);
    }

    public Response updateBook(int id, Book book) {
        return sendPutRequest(book, Base_Path + "/updatebook/");
    }

    public Response deleteBook(int id) {
        return sendDeleteRequest(Base_Path + "/books/" + id);
    }

    public Response getBookAllBooks(String endpoint) {
        return sendGetRequest(Base_Path + endpoint);
    }
}
