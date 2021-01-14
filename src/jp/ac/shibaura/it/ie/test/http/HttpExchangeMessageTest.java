package jp.ac.shibaura.it.ie.test.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.internal.org.objectweb.asm.TypeReference;
import jp.ac.shibaura.it.ie.test.data.*;
import jp.ac.shibaura.it.ie.test.interactor.AuthTest;
import jp.ac.shibaura.it.ie.usecases.auth.login.AuthLoginOutputData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


/**
 * ルームに4人参加してちゃんとアプリケーションサーバに情報が送れるかをTestします
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HttpExchangeMessageTest {

    private RestTemplate restTemplate = new RestTemplate();
    private String url;
    private List<String> sessions = new ArrayList<String>();
    private Map<String, String> users = new LinkedHashMap<>();
    private String roomId;

    /**
     * ユーザ4人がログインします
     */
    @Before
    public void testMake4User() {

        users.put("22", "test");
        users.put("24c0446e-9cdb-490f-876d-20e1b898bdda", "24c0446e-9cdb-490f-876d-20e1b898bdda");
        users.put("001d9e79-b0c6-4c0f-bd32-ed9ebf7ed28b", "001d9e79-b0c6-4c0f-bd32-ed9ebf7ed28b");
        users.put("01c83709-b95f-4d57-b419-e537f763d248", "01c83709-b95f-4d57-b419-e537f763d248");
        url = "http://localhost:8080/login";

        users.forEach((userName, userPassword) -> {
            LoginTestData loginTestData = new LoginTestData(userName, userPassword);
            ResponseEntity<AuthLoginOutputTestData> loginResponseEntity = restTemplate.postForEntity(url, loginTestData, AuthLoginOutputTestData.class);
            sessions.add(loginResponseEntity.getBody().getSession());
        });

        sessions.forEach(s -> {
            HttpHeaders headers = new HttpHeaders();
            headers.add("session", s);

            HttpEntity<String> entity = new HttpEntity<String>(headers);

            // ルーム参加
            url = "http://localhost:8080/category/1/join";
            ResponseEntity<AuthTest.CategoryJoinTestData> categoryJoinResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, AuthTest.CategoryJoinTestData.class);
            System.out.println("categoryJoin:" + categoryJoinResponseEntity.toString());
            System.out.println("categoryJoin:" + categoryJoinResponseEntity.getBody().getRoomId());
            roomId = categoryJoinResponseEntity.getBody().getRoomId();

            // ルーム待機
            url = "http://localhost:8080/room/" + roomId + "/wait";
            ResponseEntity<RoomWaitTestData> roomWaitTestDataResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, RoomWaitTestData.class);
            System.out.println("roomWait:" + roomWaitTestDataResponseEntity.toString());
        });

    }

    /**
     * メッセージの送受信をする、あとスタンプもおす
     */
    @Test
    public void testExchangeMessage() {
        sessions.forEach(s -> {
            HttpHeaders headers = new HttpHeaders();
            headers.add("session", s);

            // メッセージ送信API
            url = "http://localhost:8081/chat/" + roomId + "/message/post";
            headers.setContentType(MediaType.APPLICATION_JSON);
            String reqBodyData = null;
            try {
                reqBodyData = new ObjectMapper().writeValueAsString(new ImagePostTestData(ImageTestData.image, "unko", "png"));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            HttpEntity<String> newEntity = new HttpEntity<String>(reqBodyData, headers);
            // HttpEntity<String> roomStart =  restTemplate.postForEntity(url, new ImagePostTestData(ImageTestData.image, "unko", "png"), String.class);
            HttpEntity<String> roomStart = restTemplate.postForEntity(url, newEntity, String.class);
            System.out.println("message/post:" + roomStart.toString());
            HttpEntity<String> roomStart1 = restTemplate.postForEntity(url, newEntity, String.class);
            System.out.println("message/post:" + roomStart1.toString());


            // メッセージ受信API
            url = "http://localhost:8081/chat/" + roomId + "/message/update";
            HttpEntity<String> entity = new HttpEntity<>(headers);
            // ResponseEntity<Map<String, MessageTest>> categoryJoinResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<Map<String, MessageTest>>() {});
            ResponseEntity<MessageMap> categoryJoinResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<MessageMap>() {
            });
            //ResponseEntity<String> categoryJoinResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("message/update: " + categoryJoinResponseEntity.toString());
            System.out.println("message/update: " + categoryJoinResponseEntity.getBody());


            categoryJoinResponseEntity.getBody().getMessageList().forEach((k, v) -> {
                System.out.println("messageIdは : " + k);
                System.out.println("messageIdは : " + v.getMessageId());

                // スタンプ送信API
                url = "http://localhost:8081/chat/" + roomId + "/" + k + "/stamp/post";
                String request = null;
                try {
                    request = new ObjectMapper().writeValueAsString(new StampPostTestData("imanaka", "2"));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                HttpEntity<String> superEntity = new HttpEntity<String>(request, headers);
                // HttpEntity<String> roomStart =  restTemplate.postForEntity(url, new ImagePostTestData(ImageTestData.image, "unko", "png"), String.class);
                HttpEntity<String> messageUpdateEntity = restTemplate.postForEntity(url, superEntity, String.class);
                System.out.println("message/post:" + messageUpdateEntity.toString());
                HttpEntity<String> messageUpdateEntity1 = restTemplate.postForEntity(url, superEntity, String.class);
                System.out.println("message/post:" + messageUpdateEntity1.toString());
                HttpEntity<String> messageUpdateEntity2 = restTemplate.postForEntity(url, superEntity, String.class);
                System.out.println("message/post:" + messageUpdateEntity2.toString());

                // もう一回取得
                url = "http://localhost:8081/chat/" + roomId + "/message/update";
                ResponseEntity<String> SuperCategoryJoinResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
                System.out.println("Super message/update: " + SuperCategoryJoinResponseEntity.toString());
                System.out.println("Super message/update: " + SuperCategoryJoinResponseEntity.getBody());

            });

        });


    }


    /**
     * 4人ログアウトする
     */
    @After
    public void testLogout() {

        // みんなで部屋をでる 退出API
        sessions.forEach(s -> {

            HttpHeaders headers = new HttpHeaders();
            headers.add("session", s);
            url = "http://localhost:8081/chat/" + roomId + "/exit";
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> chatExitResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("message/update: " + chatExitResponseEntity.toString());
        });


        url = "http://localhost:8080/logout";
        sessions.forEach(s -> {
            HttpHeaders headers = new HttpHeaders();
            headers.add("session", s);

            HttpEntity<String> entity = new HttpEntity<String>(headers);

            ResponseEntity<String> logoutResponseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println("logout::" + logoutResponseEntity.toString());
        });
    }
}
