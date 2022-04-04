package RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class CreateUser {
    @Test(priority = 1)
    public void userRegister() throws IOException {
        for (int i = 1; i <= 20; i++) {       //taking 20 inputs

            excelData ed = new excelData();  //default constructor call
            String name = ed.getString(i, 0);  //get input of name in column 0
            String email = ed.getString(i, 1); //get input of email in column1
            String pass = ed.getString(i, 2); //get input of password in column2
            int age = ed.getAge(i, 3);        //get input of age in column 3
            data dt = new data(name, email, pass, age);  //creating parametaric constructor using object
            given().
                    body(dt).
                    contentType(ContentType.JSON).   //contentType
                    when().
                    post("https://api-nodejs-todolist.herokuapp.com/user/register").
                    then().
                    log().body().
                    statusCode(HttpStatus.SC_CREATED);  //201 data has been created successfully
        }
    }
    @Test(priority = 2)
    public void userLogin() throws IOException {
        excelData ed = new excelData();                  //from excelData.java class
        String email = ed.getString(1, 1);  //Login with email
        String pass = ed.getString(1, 2);  //Login with password
        data dt = new data(email, pass);               //from data.java class
        Response response = given().                   //Response Specification
                body(dt).
                contentType(ContentType.JSON).
                when().
                post("https://api-nodejs-todolist.herokuapp.com/user/login").
                then().
                log().body().
                statusCode(HttpStatus.SC_OK).extract().response();  //200 get request
        JSONObject jsonObject = new JSONObject(response.asString()); //Json object is created in name of jsonObject
        Object obj = jsonObject.getJSONObject("user").get("email");
        assertThat(obj, is(email));              //check if a specific value match to an expected one
        Object ObjToken = jsonObject.get("token");  //generate random token
        excelData excelData = new excelData();
        excelData.writeToken(ObjToken);         //To writeThe token on excel
        }
    }

