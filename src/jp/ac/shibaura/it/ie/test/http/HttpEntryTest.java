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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HttpEntryTest {

    @Test
    public void testDubEntry() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // ユーザエントリ
        String uuid = UUID.randomUUID().toString();
        AuthEntryInputData inputData = new AuthEntryInputData("q", "q", "qqqqqqqq");
        String url = "http://localhost:8080/entry";
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, inputData, String.class);
            System.out.println("entry::" + response.getStatusCodeValue());
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            System.out.println("400");
        } catch (HttpServerErrorException e) {
            e.printStackTrace();
            System.out.println("500");
        }

    }

    @Test
    public void testDubUserIdEntry() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // ユーザエントリ
        String uuid = UUID.randomUUID().toString();
        AuthEntryInputData inputData = new AuthEntryInputData("q", "w", "w");
        String url = "http://localhost:8080/entry";
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, inputData, String.class);
            System.out.println("entry::" + response.getStatusCodeValue());
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            System.out.println("400");
        } catch (HttpServerErrorException e) {
            e.printStackTrace();
            System.out.println("500");
        }
    }
}
