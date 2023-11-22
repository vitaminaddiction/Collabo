package com.collabo.taskmanagement.sec;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http    .csrf(csrf -> csrf.disable())
                .authorizeRequests(req -> req
                                        .requestMatchers("/css/**", "/js/**", "/img/**", "/assets/**", "/main/**", "/projectlist/**", "/scss/**", "/vendor/**").permitAll()
                                        .requestMatchers("/auth/**").permitAll()
                                        .requestMatchers("/").permitAll()
//                                     .requestMatchers("/member").hasRole("ADMIN")
                                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                                .loginPage("/auth/login")
                                .defaultSuccessUrl("/", true)
                                .usernameParameter("email")
                                .failureUrl("/auth/login/error")
                                .permitAll())
                .logout(logout -> logout.logoutUrl("/auth/logout"))
//
//                .userDetailsService((email)->{
//                    Member dbMember = memberRepository.findByEmail(email);
//
//                    if(dbMember == null)
//                        throw new UsernameNotFoundException(email);
//
//                    return User.builder()
//                            .username(dbMember.getEmail())
//                            .password(dbMember.getPassword())
//                            .roles("ADMIN")
//                            .build();
//                })
        ;

        return http.build();
    }
}
