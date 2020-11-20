package pl.book.it.web.web;

import lombok.RequiredArgsConstructor;
import model.Dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.book.it.web.client.UserClient;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/bia/users")
public class UserController {

    private final UserClient userClient;

    @PostMapping("/create")
    public String createNewUser(@ModelAttribute("userDto") UserDto user, final Errors errors, ModelMap modelMap) {

        if (errors.hasErrors()) {
            return "users";
        }
        final ResponseEntity<UserDto> user1 = userClient.createUser(user);
        modelMap.addAttribute(user1);
        return "users";
    }
}
