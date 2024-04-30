package com.scentheartsstudio.scentheartsstudio.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scentheartsstudio.scentheartsstudio.DTO.LoginDTO;
import com.scentheartsstudio.scentheartsstudio.Services.UserService;
import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
import com.scentheartsstudio.scentheartsstudio.utils.Resp;

@RestController
@RequestMapping("/api")
public class LoginRestController {

    @Autowired
    private UserService us;

    @PostMapping("/login")
    public Resp<LoginDTO> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        Resp<LoginDTO> response = new Resp<>();
        response.setCode(200);
        response.setMessage("OK");
        try {
            LoginDTO loginDTO = us.loginService(email, password);
            response.setData(loginDTO);
            return response;
        } catch (CustomException e) {
            response.setCode(e.getCode());
            response.setMessage(e.getMessage());
            return response;
        }
    }

}
