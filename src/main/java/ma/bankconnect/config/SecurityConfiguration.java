package ma.bankconnect.config;

import ma.bankconnect.ControllerAdvice.CustomAuthenticationEntryPoint;
import ma.bankconnect.dao.UserDao;
import ma.bankconnect.service.CustomerServiceImpl;
import ma.bankconnect.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration   {
    private final JwtAuthFilter jwtAuthFilter;
    private final UserDao userDao;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    private CustomerServiceImpl customerService;
    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User("ibnahmad@gmail.com", "password", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            new User("mohamed@gmail.com", "password", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));

    @Autowired
    @Lazy
    public SecurityConfiguration(JwtAuthFilter jwtAuthFilter, UserDao userDao, CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.userDao = userDao;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
        System.out.println("SecurityConfiguration");
        this.jwtAuthFilter = jwtAuthFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        try {
            http
                    .csrf().disable()
                    .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
                    .and()
                    .authorizeHttpRequests()
                    .requestMatchers("/api/v1/auth/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .authenticationProvider(authenticationProvider())
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @Bean
    public JwtUtils jwtUtils() {
        return new JwtUtils();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService  userDetailsService() {
        return email -> {
            String who = jwtAuthFilter.getWhoWantToLogin();
            if (who != null) userDao.setWho(who);
            UserDetails userDetails = userDao.findByEmail(email);
            if (userDetails != null) {
                return userDetails;
            }
            throw new UsernameNotFoundException("Email " + email + " not found");
        };
    }
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration conf) throws Exception {
        return conf.getAuthenticationManager();
    }
}
