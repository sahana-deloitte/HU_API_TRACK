package RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CreateTasks {
    @Test(priority = 1)
    //validating the data
    public void add_tasks() throws IOException {
        excelData Data=new excelData();         //fetching data from ExcelData and store it in Data object
        String token=Data.getToken(0,1,4);  //getToken and store in token
        for(int i=1;i<=20;i++){
            String task=Data.getToken(1,i,0);   //get token into tasks
            data dt=new data(task);   //Parametaric constructor,
            Response response = given().
                    body(dt).
                    headers(
                            "Authorization",
                            "Bearer " + token,  //Bearer tokens enable requests to authenticate using an access key
                            "Content-Type",
                            ContentType.JSON,     //token will be in string hence converted it to json
                            "Accept",
                            ContentType.JSON).
                            when().
                    post("https://api-nodejs-todolist.herokuapp.com/task").
                    then().
                    log().body().
                    statusCode(HttpStatus.SC_CREATED).extract().response(); //authentication of the api request sent in an http
        }
    }
    @Test(priority = 2)
    public void pagination() throws IOException {
        excelData Data=new excelData();
        String token=Data.getToken(0,1,4);    //In sheet0 in row1 get 4th Column
        for(int i=0;i<3;i++) {
            int pagi;  //pagination
            if(i==0){
                pagi=2;
            }
            else if(i==1){
                pagi=5;
            }
            else
                pagi=10;
            Response response = given().param("limit", pagi).  //not more than 10
                    headers(
                            "Authorization",
                            "Bearer " + token,  //Bearer tokens enable requests to authenticate using an access key
                            "Content-Type",
                            ContentType.JSON,
                            "Accept",
                            ContentType.JSON).
                            when().
                    get("https://api-nodejs-todolist.herokuapp.com/task").
                    then().
                    log().body().
                    statusCode(HttpStatus.SC_OK).extract().response(); //it's an inbuilt
            JSONObject jsonObject = new JSONObject(response.asString());   //convert json to string
            Object ObjToken = jsonObject.get("count");  //count number of pagination
            assertThat(ObjToken, is(pagi));    //check if a specific value match to an expected one
        }
    }
}

