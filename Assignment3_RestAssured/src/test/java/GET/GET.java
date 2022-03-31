package GET;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GET
{
    @BeforeClass
    public void setup(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://jsonplaceholder.typicode.com/posts").
                addHeader("Content-Type","application/json");
        requestSpecification = RestAssured.with().spec(requestSpecBuilder.build());

        ResponseSpecBuilder specBuilder = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON);
        responseSpecification = specBuilder.build();
    }
    @Test
    public void test_get_call(){

        given().
                spec(requestSpecification).header("Content-Type","application/json").
                when().
                get("https://jsonplaceholder.typicode.com/posts").
                then().spec(responseSpecification).
                statusCode(200).body("userId[39]",equalTo(4)).body("title[39]",equalTo("enim quo cumque"));
    }

}
