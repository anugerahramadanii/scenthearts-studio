//package com.scentheartsstudio.scentheartsstudio.restcontrollers;
//
//import com.scentheartsstudio.scentheartsstudio.dto.RegisterDTO;
//import com.scentheartsstudio.scentheartsstudio.services.RegisterService;
//import com.scentheartsstudio.scentheartsstudio.utils.CustomException;
//import com.scentheartsstudio.scentheartsstudio.utils.Resp;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//
//public class RegisterRestControllerV1 {
//
//    @Autowired
//    private RegisterService rs;
//
//    @PostMapping("/register")
//    public Resp<String> registerUser(@RequestBody RegisterDTO registerDTO) {
//        Resp<String> response = new Resp<>();
//        response.setCode(200);
//        response.setMessage("OK");
//        try {
//            rs.registerUser(registerDTO);
//            return response;
//        } catch (CustomException e) {
//            response.setCode(e.getCode());
//            response.setMessage(e.getMessage());
//            return response;
//        }
//    }
//
//    @PostMapping("/register/verifyEmail")
//    public Resp<String> verifyEmail(@RequestParam("email") String email) {
//        Resp<String> response = new Resp<>();
//        response.setCode(200);
//        response.setMessage("OK");
//        try {
//            rs.sendRegisterEmailOTP(email);
//            return response;
//        } catch (CustomException e) {
//            response.setCode(e.getCode());
//            response.setMessage(e.getMessage());
//            return response;
//        }
//    }
//
//}
