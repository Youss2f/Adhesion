// src/main/java/ma/fm6education/adhesion/controller/HomeController.java
package ma.fm6education.adhesion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        return "home"; // Ensure there's a Thymeleaf template named 'home.html' in 'src/main/resources/templates'
    }
}
