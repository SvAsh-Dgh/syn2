// package com.syn.projectsyn2.bugtracker.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// import static org.springframework.security.config.Customizer.withDefaults;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//                 .authorizeHttpRequests(authorizeRequests ->
//                         authorizeRequests
//                                 .requestMatchers("/", "/landing").permitAll()
//                                 .anyRequest().authenticated()
//                 )
//                 .formLogin(formLogin ->
//                         formLogin
//                                 .loginPage("/landing")
//                                 .permitAll()
//                 )
//                 .logout(logout ->
//                         logout
//                                 .permitAll()
//                 )
//                 .httpBasic(withDefaults());  // This enables HTTP Basic authentication

//         return http.build();
//     }
// }
