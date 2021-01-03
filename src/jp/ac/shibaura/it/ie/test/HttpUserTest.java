package jp.ac.shibaura.it.ie.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.ac.shibaura.it.ie.usecases.auth.entry.AuthEntryInputData;
import jp.ac.shibaura.it.ie.usecases.auth.login.AuthLoginOutputData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HttpUserTest {

    @Test
    public void testUserUpdate() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        // ユーザエントリ
        String uuid = UUID.randomUUID().toString();
        AuthEntryInputData inputData = new AuthEntryInputData(uuid, uuid, uuid);
        String url = "http://localhost:8080/entry";
        ResponseEntity<String> response = restTemplate.postForEntity(url, inputData, String.class);
        System.out.println("entry::" + response.getStatusCodeValue());

        // ユーザログイン
        url = "http://localhost:8080/login";
        LoginTestData loginTestData = new LoginTestData(uuid, uuid);
        ResponseEntity<AuthLoginOutputData> loginResponseEntity = restTemplate.postForEntity(url, loginTestData, AuthLoginOutputData.class);
        System.out.println(loginResponseEntity.toString());
        System.out.println("login::" + loginResponseEntity.getBody().getSession());
        String session = loginResponseEntity.getBody().getSession();


        HttpHeaders headers = new HttpHeaders();

        // 名前変更
        url = "http://localhost:8080/user/update";
        headers.add("session", session);
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> body = new HashMap<String, String>();
        body.put("userId", uuid);
        body.put("userName", "test");
        String reqBodyData = new ObjectMapper().writeValueAsString(body);
        HttpEntity<String> entity = new HttpEntity<String>(reqBodyData, headers);
        ResponseEntity<String> userUpdateEntity = restTemplate.postForEntity(url, entity, String.class);
        System.out.println("User/Update");
        System.out.println(userUpdateEntity.toString());
        System.out.println("user/update::" + userUpdateEntity.getStatusCodeValue());

        // 画像一覧表示
        url = "http://localhost:8080/image/list?categoryId=test";
        HttpEntity<String> newEntiry = new HttpEntity<String>(headers);
        ResponseEntity<ListImageTestData> imageListEntiry = restTemplate.exchange(url, HttpMethod.GET, newEntiry, ListImageTestData.class);
        // ResponseEntity<String> categoryListEntiry = restTemplate.exchange(url, HttpMethod.GET, newEntiry, String.class);
        System.out.println("imageList::" + imageListEntiry.toString());
        System.out.println("imageList::" + imageListEntiry.getBody());
        System.out.println("imageList::" + imageListEntiry.getStatusCodeValue());
        ListImageTestData tempUrl = imageListEntiry.getBody();
        for (int i = 0; i < tempUrl.urls.size(); i++) {
            System.out.println("imageのURLは:" + tempUrl.urls.get(i).imageUrl);
        }

        // カテゴリ一覧表示
        url = "http://localhost:8080/category/list";
        //ResponseEntity<CategoryListTestData> categoryListEntiry = restTemplate.exchange(url, HttpMethod.GET, newEntiry, CategoryListTestData.class);
        ResponseEntity<String> categoryListEntiry = restTemplate.exchange(url, HttpMethod.GET, newEntiry, String.class);
        System.out.println("categoryList::" + categoryListEntiry.toString());
        System.out.println("categoryList::" + categoryListEntiry.getBody());
        System.out.println("categoryList::" + categoryListEntiry.getStatusCodeValue());
        // categoryListEntiry.getBody().category;
       // System.out.println(categoryListEntiry.getBody().categoryList.size());
       // for (int i = 0; i < categoryListEntiry.getBody().categoryList.size(); i++) {
       //     System.out.println("categoryの情報は:" + categoryListEntiry.getBody().categoryList.get(i).getCategoryId());
       // }

        // ルーム参加
        url = "http://localhost:8080/category/1/join";
        ResponseEntity<CategoryJoinTestData> categoryJoinResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, CategoryJoinTestData.class);
        System.out.println("categoryJoin::" + categoryJoinResponseEntity.toString());
        System.out.println("categoryJoin::" + categoryJoinResponseEntity.getBody().getRoomId());
        String roomId = categoryJoinResponseEntity.getBody().getRoomId();

        // ルーム待機
        url = "http://localhost:8080/room/" + roomId + "/wait";

        // ルーム退出
        url = "http//localhost:8080/room/" + roomId + "/exit";

        // ユーザログアウト
        url = "http://localhost:8080/logout";
        ResponseEntity<String> logoutResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("logout::" + logoutResponseEntity.toString());


    }
}
