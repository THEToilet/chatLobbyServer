package jp.ac.shibaura.it.ie.test.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.ac.shibaura.it.ie.test.data.*;
import jp.ac.shibaura.it.ie.test.interactor.AuthTest;
import jp.ac.shibaura.it.ie.usecases.auth.entry.AuthEntryInputData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HttpLoginTest {

    @Test
    public void testLogin() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // ユーザログイン
        String url = "http://localhost:8080/login";
        LoginTestData loginTestData = new LoginTestData("q","qqqqqqqq");
        ResponseEntity<AuthLoginOutputTestData> loginResponseEntity = restTemplate.postForEntity(url, loginTestData, AuthLoginOutputTestData.class);
        System.out.println(loginResponseEntity.toString());
        System.out.println("login::" + loginResponseEntity.getBody().getSession());
        System.out.println("login::" + loginResponseEntity.getBody().getUserName());
        String session = loginResponseEntity.getBody().getSession();

    }
}
