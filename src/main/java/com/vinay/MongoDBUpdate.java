package com.vinay;

import com.vinay.domain.User;
import com.vinay.util.MongoDBCrud;

import java.util.Optional;

import static com.vinay.util.MongoDBConstants.*;

public class MongoDBUpdate {

    public static void main(String[] args) {

        MongoDBCrud crud = new MongoDBCrud(CONN_STRING, DB_NAME, COLL_NAME);

        Optional<User> OptUserObj = crud.getUserByName("Vinay Chauhan");
        if (OptUserObj.isPresent()) {
            User userObj = OptUserObj.get();
            System.out.println("Fetched Record :: " + userObj);
            userObj.setName("Vipin Chauhan");
            crud.updateUser(userObj.getId(), userObj);
        } else {
            System.out.println("No Records Found.");
        }


    }

}
