package fr.ldnr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder pe = passwordEncoder();

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal , password as credentials , active from T_User where username=?")
                .authoritiesByUsernameQuery("select username as principal , role as role from T_Users_Roles where username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder());
    }
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.formLogin();
        http.authorizeRequests().antMatchers("/delete" , "/save" , "/article" , "/updateForm").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/loggin").hasRole("USER");

        http.exceptionHandling().accessDeniedPage("/403");
    }
}
