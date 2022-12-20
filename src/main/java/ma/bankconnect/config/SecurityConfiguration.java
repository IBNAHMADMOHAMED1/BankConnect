package ma.bankconnect.config;

import ma.bankconnect.entity.Customer;
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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
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
    @Autowired
    private CustomerServiceImpl customerService;
    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User("ibnahmad@gmail.com", "password", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            new User("mohamed@gmail.com", "password", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));

    @Autowired
    @Lazy
    public SecurityConfiguration(JwtAuthFilter jwtAuthFilter) {
        System.out.println("SecurityConfiguration");
        this.jwtAuthFilter = jwtAuthFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        try {
            http
                    .csrf().disable()
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
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public UserDetailsService  userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                System.out.println("loadUserByUsername");
                final Customer customer = customerService.findByEmail(email);


                if (customer != null) {
                    System.out.println("customer != null");
                    return new User(customer.getEmail(), customer.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
                }
                throw new UsernameNotFoundException("User '" + email + "' not found");
            }
        };
    }
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration conf) throws Exception {
        return conf.getAuthenticationManager();
    }
}
