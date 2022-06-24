package com.shop.projectlion.global.config;


//import com.shop.projectlion.global.error.exception.GlobalExceptionHandler;
import com.shop.projectlion.domain.member.constant.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final FormLoginServiceImpl formLoginService;
    //private final AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{

        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }



    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.csrf().ignoringAntMatchers("/api/**", "/auth/**", "/oauth/**");

        http.httpBasic().and()
                .authorizeRequests()
                .antMatchers("/",
                        "/login",
                        "/register",
                        "/images/**",
                        "/kakaologin",
                        "/auth/**",
                        "/oauth/**",
                        "https://kauth.kakao.com/**",
                        "https://kapi.kakao.com/**",
                        "/health-check",
                        "/swagger-ui/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/api/oauth-login").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();


        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
                //.failureHandler(authenticationFailureHandler)
                .failureUrl("/login?isError=true")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");



        //강사님이 보여주신 예외핸들링
        http.exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPointImpl());
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(formLoginService).passwordEncoder(passwordEncoder());
    }

}
