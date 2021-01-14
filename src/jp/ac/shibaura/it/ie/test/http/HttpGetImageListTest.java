package jp.ac.shibaura.it.ie.test.http;

import jp.ac.shibaura.it.ie.test.data.ListImageTestData;
import jp.ac.shibaura.it.ie.test.data.LoginTestData;
import jp.ac.shibaura.it.ie.usecases.auth.login.AuthLoginOutputData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HttpGetImageListTest {
    private String url;
    private RestTemplate restTemplate = new RestTemplate();
    private HttpEntity<String> entity;

    @Before
    public void login() {
        url = "http://localhost:8080/login";
        LoginTestData loginTestData = new LoginTestData("22", "test");
        ResponseEntity<AuthLoginOutputData> loginResponseEntity = restTemplate.postForEntity(url, loginTestData, AuthLoginOutputData.class);
        String session = loginResponseEntity.getBody().getSession();

        // header作成
        HttpHeaders headers = new HttpHeaders();
        headers.add("session", session);
        entity = new HttpEntity<String>(headers);
    }

    @Test
    public void testGetListImage() {
        url = "http://localhost:8080/image/list?categoryId=test";
        ResponseEntity<ListImageTestData> imageListEntry = restTemplate.exchange(url, HttpMethod.GET, entity, ListImageTestData.class);
        System.out.println("imageList::" + imageListEntry.toString());
        System.out.println("imageList::" + imageListEntry.getBody());
        System.out.println("imageList::" + imageListEntry.getStatusCodeValue());
        ListImageTestData tempUrl = imageListEntry.getBody();
        for (int i = 0; i < tempUrl.getUrls().size(); i++) {
            System.out.println("imageのURLは:" + tempUrl.getUrls().get(i).getImageUrl());
        }
    }

    @After
    public void logout() {
        url = "http://localhost:8080/logout";
        ResponseEntity<String> logoutResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }
}
