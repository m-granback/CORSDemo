package se.verran.corsdemo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    @GetMapping("/")
    public String index(){
        return "main";
    }

    @PostMapping("/test")
    @ResponseBody
//    @CrossOrigin("http://127.0.0.1:8080") // * rekommenderas ej!
    public String test(HttpServletRequest request){
        System.out.println("Metoden körs på begäran av " + request.getHeader("Origin"));
        return "Retursträng från metoden test";
    }
}
