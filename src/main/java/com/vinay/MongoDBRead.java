package com.vinay;

import com.vinay.util.MongoDBCrud;

import static com.vinay.util.MongoDBConstants.*;

public class MongoDBRead {

    public static void main(String[] args) {
        MongoDBCrud crud = new MongoDBCrud(CONN_STRING, DB_NAME, COLL_NAME);
        System.out.println("Record Fetched :: ");
        crud.getAllUsers().forEach(System.out::println);
    }

}
