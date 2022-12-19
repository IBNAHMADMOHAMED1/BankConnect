package ma.bankconnect.api;

import lombok.RequiredArgsConstructor;
import ma.bankconnect.dto.AuthenticationRequest;
import ma.bankconnect.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationResource {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest authenticationRequest) {

        System.out.println(authenticationRequest.getEmail());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        System.out.println("hhh------------------");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        if (userDetails != null) {
            return ResponseEntity.ok().body(jwtUtils.generateToken(userDetails));
        }
        return ResponseEntity.badRequest().body("Login failed");
    }
    @GetMapping("/clients")
    public ResponseEntity<String> getAllCustomers(  ) {
        System.out.println("---------1111111111111111------");
        return ResponseEntity.ok("hhhhhhhhhhhhhhh");
    }
}
