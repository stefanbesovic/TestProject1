package com.practice.test1.security;

import com.practice.test1.jwt.JwtConfig;
import com.practice.test1.jwt.JwtTokenVerifier;
import com.practice.test1.jwt.JwtUsernamePasswordAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.crypto.SecretKey;

import static com.practice.test1.security.UserRole.ADMIN;
import static com.practice.test1.security.UserRole.LISTENER;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder,
                          SecretKey secretKey,
                          JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }
/* //FORM LOGIN - 1 WAY OF DOING THINGS
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index").permitAll()
                .antMatchers("/api/users/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/welcome", true)
                    .passwordParameter("password")
                    .usernameParameter("username")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/login");
    }
*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernamePasswordAuthFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernamePasswordAuthFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index").permitAll()
                .antMatchers("/api/users/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails bassUser = User.builder()
                .username("bass")
                .password(passwordEncoder.encode("bass"))
                .roles(ADMIN.name()) // ROLE_ADMIN
                .build();

        UserDetails misaUser = User.builder()
                .username("misa")
                .password(passwordEncoder.encode("misa"))
                .roles(LISTENER.name())
                .build();

        return new InMemoryUserDetailsManager(
                bassUser, misaUser
        );
    }
}
