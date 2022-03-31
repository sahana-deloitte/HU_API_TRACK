package GET;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Assignment2
{

    @Test
    public void test_get_call(){

        given().
                baseUri("https://jsonplaceholder.typicode.com/posts").header("Content-Type","application/json").
                when().
                get("https://jsonplaceholder.typicode.com/posts").
                then().
                statusCode(200).body("userId[39]",equalTo(4)).body("title[39]",equalTo("enim quo cumque"));
    }

    @Test
    public void test_put_call(){
        File jsonData = new File("C:\\Users\\sahankh\\IdeaProjects\\Assignment2\\src\\test\\java\\PUT\\put_call.json");
        given().
                baseUri("https://reqres.in/api/users").
                body(jsonData).
                header("Content-Type","application/json").
                when().
                put("/users").
                then().
                statusCode(200).body("name",equalTo("Arun")).body("job",equalTo("Manager"));
    }

}
