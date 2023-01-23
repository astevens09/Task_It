package com.project.taskit.config;

//import com.project.taskit.config.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/","/sign-up","/css/**","/images/**","/js/**","/about").permitAll();
                auth.anyRequest().authenticated();
            })
            .formLogin(form ->{
                form
                .loginPage("/login")
                        .defaultSuccessUrl("/tasks")
                        .permitAll();
            })
                .csrf(csrf ->{csrf
                        .disable();});
            http.logout(logout -> {logout
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//                    .logoutUrl("/perform_logout")
//                    .logoutSuccessUrl("/login");
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");
            });
//            .formLogin(Customizer.withDefaults());
        return http.build();
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//
//                .authorizeHttpRequests().anyRequest().authenticated().and()
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .httpBasic()
    /* Login configuration */
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/tasks") // user's home page, it can be any URL
//                .permitAll() // Anyone can go to the login page
//                /* Logout configuration */
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login?logout") // append a query string value
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/", "/login", "/sign-up") // anyone can see the home and the ads pages
//                .permitAll()
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/tasks")
//                .authenticated()
    /* Pages that can be viewed without having to log in */
//

    /* Pages that require authentication */
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers(
//                        "/posts/create", // only authenticated users can create ads
//                        "/posts/{id}/edit" // only authenticated users can edit ads
//                )
//                .authenticated()
//        ;
//        return http.build();
//    }

}