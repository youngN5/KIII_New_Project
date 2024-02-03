package mk.ukim.finki.lab03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Lab03Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab03Application.class, args);
    }

}
