package be.devinnnie;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TheController {

    private List<String> names = new ArrayList<>();

    @GetMapping("/hello")
    public String hello(){
        return "Hello There!";
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Payload word){
        if(word.getData().equals("tasty_syndrome")){
            return ResponseEntity.badRequest().build();
        }

        names.add(word.getData());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/names")
    public List<String> listNames(){
        return names;
    }
}
