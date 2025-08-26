package se.verran.corsdemo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    
    @GetMapping("/")
    public String index(){
        return "CORS Demo Application is running! Use the cors-demo.html file to test CORS.";
    }

    @PostMapping("/test")
    @ResponseBody
    public String test(HttpServletRequest request){
        String origin = request.getHeader("Origin");
        System.out.println("POST /test called from origin: " + origin);
        return "Success! Request from origin: " + origin;
    }

    @GetMapping("/api/data")
    public String getData(HttpServletRequest request){
        String origin = request.getHeader("Origin");
        System.out.println("GET /api/data called from origin: " + origin);
        return "{\"message\": \"Data retrieved successfully\", \"origin\": \"" + origin + "\"}";
    }

    @PostMapping("/api/submit")
    public String submitData(HttpServletRequest request){
        String origin = request.getHeader("Origin");
        System.out.println("POST /api/submit called from origin: " + origin);
        return "{\"status\": \"Data submitted successfully\", \"origin\": \"" + origin + "\"}";
    }

    // This endpoint has specific CORS restrictions
    @PostMapping("/restricted")
    @CrossOrigin(origins = {"http://localhost:8080"}, maxAge = 3600)
    public String restricted(HttpServletRequest request){
        String origin = request.getHeader("Origin");
        System.out.println("POST /restricted called from origin: " + origin);
        return "Access granted to restricted endpoint from origin: " + origin;
    }

    // This endpoint allows all origins (not recommended for production)
    @GetMapping("/open")
    @CrossOrigin(origins = "*")
    public String openEndpoint(HttpServletRequest request){
        String origin = request.getHeader("Origin");
        System.out.println("GET /open called from origin: " + origin);
        return "Open endpoint accessible from any origin: " + origin;
    }
}
