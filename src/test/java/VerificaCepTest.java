import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class VerificaCepTest {
    private String URL = "https://viacep.com.br/ws/";

    @BeforeAll
    void init(){
        RestAssured.baseURI = URL;
    }
}


