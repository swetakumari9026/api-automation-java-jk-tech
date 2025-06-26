package com.company.service;

import com.company.base.BaseService;
import com.company.model.request.User;
import io.restassured.response.Response;

public class UsersService extends BaseService {
    public static String Base_Path = "/api/user";

    public Response createUser(User user, String endpoint) {
        return sendPostRequest(user, Base_Path + endpoint);
    }

    public Response getUser(String endpoint, String id) {
        return sendGetRequest(Base_Path + endpoint + id);
    }

    public Response deleteUser(String endpoint, String id) {
        return sendDeleteRequest(Base_Path + endpoint + id);
    }

    public Response updateUser(User user, String endpoint) {
        return sendPutRequest(user, Base_Path + endpoint);
    }

}
