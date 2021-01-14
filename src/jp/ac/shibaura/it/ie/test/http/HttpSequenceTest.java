package jp.ac.shibaura.it.ie.test.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.ac.shibaura.it.ie.test.data.*;
import jp.ac.shibaura.it.ie.test.interactor.AuthTest;
import jp.ac.shibaura.it.ie.usecases.auth.entry.AuthEntryInputData;
import jp.ac.shibaura.it.ie.usecases.auth.login.AuthLoginOutputData;
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
public class HttpSequenceTest {

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
        ResponseEntity<AuthLoginOutputTestData> loginResponseEntity = restTemplate.postForEntity(url, loginTestData, AuthLoginOutputTestData.class);
        System.out.println(loginResponseEntity.toString());
        System.out.println("login::" + loginResponseEntity.getBody().getSession());
        System.out.println("login::" + loginResponseEntity.getBody().getUserName());
        String session = loginResponseEntity.getBody().getSession();

        // header作成
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
        url = "http://localhost:8080/image/list?categoryId=1";
        HttpEntity<String> newEntiry = new HttpEntity<String>(headers);
        ResponseEntity<ListImageTestData> imageListEntiry = restTemplate.exchange(url, HttpMethod.GET, newEntiry, new ParameterizedTypeReference<ListImageTestData>() {
        });
        ResponseEntity<String> imageListEntiry1 = restTemplate.exchange(url, HttpMethod.GET, newEntiry, String.class);
        System.out.println("imageList::" + imageListEntiry.toString());
        System.out.println("imageList::" + imageListEntiry.getBody());
        System.out.println("imageList::" + imageListEntiry.getStatusCodeValue());
        System.out.println("imageList::" + imageListEntiry.getStatusCodeValue());
        System.out.println("imageList::" + imageListEntiry.getStatusCodeValue());
        System.out.println("NeoimageList::" + imageListEntiry1.getBody().toString());
        ListImageTestData tempUrl = imageListEntiry.getBody();
        for (int i = 0; i < tempUrl.getUrls().size(); i++) {
            System.out.println("imageのURLは:" + tempUrl.getUrls().get(i).getImageUrl());
        }

        // カテゴリ一覧表示
        url = "http://localhost:8080/category/list";
        ResponseEntity<String> categoryListEntry2 = restTemplate.exchange(url, HttpMethod.GET, newEntiry, String.class);
        System.out.println("categoryList::" + categoryListEntry2.toString());
        ResponseEntity<CategoryListTestData> categoryListEntry = restTemplate.exchange(url, HttpMethod.GET, newEntiry, new ParameterizedTypeReference<CategoryListTestData>() {
        });
        System.out.println("categoryList::" + categoryListEntry.toString());
        System.out.println("categoryList::" + categoryListEntry.getBody());
        System.out.println("categoryList::" + categoryListEntry.getStatusCodeValue());
        System.out.println(categoryListEntry.getBody().getCategoryList().size());
        for (int i = 0; i < categoryListEntry.getBody().getCategoryList().size(); i++) {
            System.out.println("categoryの情報は:" + categoryListEntry.getBody().getCategoryList().get(i).getCategoryId() + ":" + categoryListEntry.getBody().getCategoryList().get(i).getCategoryName());
        }

        // ルーム参加
        url = "http://localhost:8080/category/1/join";
        ResponseEntity<AuthTest.CategoryJoinTestData> categoryJoinResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, AuthTest.CategoryJoinTestData.class);
        System.out.println("categoryJoin::" + categoryJoinResponseEntity.toString());
        System.out.println("categoryJoin::" + categoryJoinResponseEntity.getBody().getRoomId());
        String roomId = categoryJoinResponseEntity.getBody().getRoomId();

        // ルーム待機
        url = "http://localhost:8080/room/" + roomId + "/wait";
        ResponseEntity<String> roomWaitTestDataResponseEntity1 = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        ResponseEntity<RoomWaitTestData> roomWaitTestDataResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, RoomWaitTestData.class);
        System.out.println("room/wait:" + roomWaitTestDataResponseEntity.getBody());
        System.out.println("room/wait:" + roomWaitTestDataResponseEntity.getStatusCodeValue());
        System.out.println("room/wait:" + roomWaitTestDataResponseEntity.getBody().getNumberOfWaitUser() + ":" + roomWaitTestDataResponseEntity.getBody().isStart());
        System.out.println("room/wait 1 :" + roomWaitTestDataResponseEntity1.toString());

        // ルーム退出
        url = "http://localhost:8080/room/" + roomId + "/exit";
        ResponseEntity<String> roomExitTestDataResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("room/exit:" + roomExitTestDataResponseEntity.getBody());

        // ユーザログアウト
        url = "http://localhost:8080/logout";
        ResponseEntity<String> logoutResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("logout::" + logoutResponseEntity.toString());


    }
}