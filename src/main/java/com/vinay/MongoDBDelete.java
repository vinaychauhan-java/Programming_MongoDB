package com.vinay;

import com.vinay.domain.User;
import com.vinay.util.MongoDBCrud;

import java.util.Optional;

import static com.vinay.util.MongoDBConstants.*;

public class MongoDBDelete {

    public static void main(String[] args) {
        MongoDBCrud crud = new MongoDBCrud(CONN_STRING, DB_NAME, COLL_NAME);
        Optional<User> OptUserObj = crud.getUserByName("Vipin Chauhan");
        if (OptUserObj.isPresent()) {
            User userObj = OptUserObj.get();
            System.out.println("Record Fetched :: " + userObj);
            crud.deleteUser(userObj.getId());
            System.out.println("User Deleted !!");
        } else {
            System.out.println("No Records Found.");
        }
    }

}
