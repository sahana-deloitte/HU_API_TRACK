package PUT;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class put {
    @BeforeClass
    public void setup(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://reqres.in/api").
                addHeader("Content-Type","application/json");
        requestSpecification = RestAssured.with().spec(requestSpecBuilder.build());

        ResponseSpecBuilder specBuilder = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON);
        responseSpecification = specBuilder.build();
    }

    @Test
    public void test_put_call(){
        File jsonData = new File("C:\\Users\\sahankh\\IdeaProjects\\Assignment2\\src\\test\\java\\PUT\\put_call.json");
        given().spec(requestSpecification).
                body(jsonData).
                when().
                put("/users").
                then().spec(responseSpecification).
                statusCode(200).body("name",equalTo("Arun")).body("job",equalTo("Manager"));
    }
}
