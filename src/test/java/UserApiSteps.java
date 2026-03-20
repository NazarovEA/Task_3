import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.User;

import static io.restassured.RestAssured.given;

public class UserApiSteps {
    private static final String BASE_URL = "https://stellarburgers.education-services.ru";

    @Step("Создание пользователя")
    public Response userCreate(User user) {
       return given()
                .log().all()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON)
                .body(user)
                .post(BASE_URL + "/api/auth/register")
               .then()
                .log().all()
                .extract().response();
    }
    @Step("Удаление пользователя")
    public void userDelete(String token) {
        given()
                .log().all()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .delete(BASE_URL + "/api/auth/user")
                .then()
                .statusCode(202)
                .log().all()
                .extract().response();
    }

    @Step("Логин пользователем")
    public Response userLogin(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(BASE_URL + "/api/auth/login");
    }
}
