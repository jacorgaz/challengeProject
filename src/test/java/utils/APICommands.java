package utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class APICommands {

    public static Response apiGetInfo (String baseURI) {
        return given().
                contentType("application/json").
                header("Accept-Language","es-ES").
                when().get(baseURI).
                then().extract().response();

    }


    public static Response apiGetInfo2 (Response baseURI) {
       return baseURI;
    }
}
