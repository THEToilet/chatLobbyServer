package jp.ac.shibaura.it.ie;

import jp.ac.shibaura.it.ie.contorollers.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
  public static void main(String... args){
    SpringApplication.run(AuthController.class, args);
    SpringApplication.run(CategoryContoroller.class, args);
    SpringApplication.run(ImageContoroller.class, args);
    SpringApplication.run(RoomController.class, args);
    SpringApplication.run(UserController.class, args);

  }
}