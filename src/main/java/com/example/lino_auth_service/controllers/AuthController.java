package com.example.lino_auth_service.controllers;
import com.example.lino_auth_service.DTO.ApiResponse;
import com.example.lino_auth_service.DTO.AuthDTO;
import com.example.lino_auth_service.entity.Auth;
import com.example.lino_auth_service.service.email.MailService;
import com.example.lino_auth_service.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.lino_auth_service.service.AuthService;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
        private final AuthService authService;
    private final MailService mailService;

    public AuthController(AuthService authService, MailService mailService) {
        this.authService = authService;
        this.mailService = mailService;
    }


    @PostMapping("/otp")
    public ResponseEntity<?> otp(@RequestParam String email) {
        try {
//            String existingEmail = authService.findByEmail(email);
//            if (existingEmail != null) {
                int otpCode = (int)(Math.random() * 900000) + 100000;

                mailService.sendOtpEmail(email, otpCode);

                return ResponseEntity.ok(ApiResponse.email_success(otpCode, email, "OTP verification code"));
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(ApiResponse.error(404, "EMAIL_NOT_FOUND", "Email not found."));
//            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(500, "INTERNAL_ERROR", "Something went wrong"));
        }
    }

//    @PostMapping("/signup")
//    public ResponseEntity<ApiResponse<?>> signup(@Valid @RequestBody SignupRequest signupRequest) {
//        if (signupRequest == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        try {
//            User user = new User();
//            user.setName(signupRequest.getName());
//            user.setSurname(signupRequest.getSurname());
//            user.setFinKod(signupRequest.getFinKod());
//            user.setFatherName(signupRequest.getFatherName());
//            user.setEmail(signupRequest.getEmail());
//            user.setPhoneNumber(signupRequest.getPhoneNumber());
//            user.setPasswordHash(signupRequest.getPasswordHash());
//            user.setRole(signupRequest.getRole());
//            user.setAuthProvider(signupRequest.getAuthProvider());
//            user.setIsFrozen(0);
//            userService.save(user);
//            return ResponseEntity.ok(ApiResponse.created(user));
//        } catch (CustomException e) {
//            return ResponseEntity
//                    .status(e.getStatus())
//                    .body(ApiResponse.error(e.getStatus(), e.getErrorCode(), e.getMessage()));
//        } catch (Exception e) {
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(ApiResponse.error(500, "INTERNAL_ERROR", "Something went wrong"));
//        }
//    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<?>> signin(@RequestBody Auth auth) {

        try {
            Optional<Auth> existingUser = authService.authenticate(auth.getEmail(), auth.getPassword());

            if (existingUser.isPresent()) {
                Auth authenticatedUser = existingUser.get();

                if (authenticatedUser.getFrozen()) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
                            .body(ApiResponse.error(403, "ACCOUNT_FROZEN", "Your account is frozen. Please contact with support."));
                }

                String token = JwtUtil.generateToken(
                        authenticatedUser.getEmail(),
                        authenticatedUser.getRole(),
                        authenticatedUser.getFrozen()
                );

                AuthDTO authDTO = new AuthDTO();

                return ResponseEntity.status(HttpStatus.OK)
                        .body(ApiResponse.success(new SignInResponse(authDTO, token)));

            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(ApiResponse.error(401, "INVALID_CREDENTIALS", "Invalid email or password"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(500, "INTERNAL_ERROR", "Something went wrong"));
        }
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    public static class SignInResponse {
        private AuthDTO authDTO;
        private String token;

        public SignInResponse(AuthDTO authDTO, String token) {
            this.authDTO = authDTO;
            this.token = token;
        }

        public AuthDTO getAuthDTO() {
            return authDTO;
        }

        public void setAuthDTO(AuthDTO authDTO) {
            this.authDTO = authDTO;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

}