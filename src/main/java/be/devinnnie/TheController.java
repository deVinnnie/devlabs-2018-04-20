package be.devinnnie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello There!";
    }
}
