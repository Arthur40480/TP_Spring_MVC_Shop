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
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder pe = passwordEncoder();

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username AS principal, password AS credentials, active FROM T_Users where username=?")
                .authoritiesByUsernameQuery("SELECT username AS principal, role AS role FROM T_Users_Roles where username=?")
                .rolePrefix("ROLE_") //Ajout d'un prefix, par exemple si le role est ADMIN => ROLE_ADMIN
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
            .permitAll()
            .and()

            .authorizeRequests().antMatchers("/delete", "/save", "/updateForm", "/article" , "/validateOrder" , "/toOrder" , "/CustomerForm").hasRole("ADMIN")
            .antMatchers("/validateOrder" , "/toOrder" , "/CustomerForm").hasRole("USER");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
