package com.vinay;

import com.vinay.domain.User;
import com.vinay.util.MongoDBCrud;

import static com.vinay.util.MongoDBConstants.*;

public class MongoDBCreate {

    public static void main(String[] args) {
        MongoDBCrud crud = new MongoDBCrud(CONN_STRING, DB_NAME, COLL_NAME);
        User newUser = new User();
        newUser.setName("Vinay Chauhan");
        newUser.setEmail("vinay.chauhan@example.com");
        newUser.setAge(30);
        crud.createUser(newUser);
        System.out.println("User created with ID: " + newUser.getId());
    }

}
