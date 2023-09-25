import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VerificaCepTest {
    private String BASE_URL = "https://viacep.com.br/ws/";
    @BeforeEach
    void init() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Deve verificar se o CEP inserido na URL é válido, validando o Response Body de acordo com o tal schema JSON.")
    void testeVerificandoUmCepValido() {
        given()
                .accept(ContentType.JSON)
                .pathParam("cep", "58075075")
        .when()
                .get("/{cep}/json")
        .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schema/SchemaCep.json"));
    }

    @Test
    @DisplayName("Deve verificar se o CEP inserido na URL é inválido.")
    void testeVerificandoUmCepInvalido() {
        int statusCode =
                given()
                        .accept(ContentType.JSON)
                        .pathParam("cep", "aeiou123")
                .when()
                        .get("/{cep}/json")
                .then()
                        .extract()
                        .statusCode();

        assertEquals(400, statusCode);
    }
}


