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
    // @Autowired
    // BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    DataSource dataSource; //pointe vers la base de donnée

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder pe = passwordEncoder();
        // Il est impératif de toujours stocké en mémoire ou en base des mots de pass crypté
        // création d'utilisateurs en mémoir avec mot de passe crypté et des rôles distincts
        // auth.inMemoryAuthentication().withUser("arthur").password(pe.encode("123")).roles("ADMIN", "USER");
        // auth.inMemoryAuthentication().withUser("mohamed").password(pe.encode("123")).roles("USER");
        // indique à Spring l'algo utilisé pour le cryptage des pwd
        // auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());

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
        // Attribution des accès aux pages en fonction des rôles
            .authorizeRequests().antMatchers("/delete", "/save", "/updateForm", "/article" , "/validateOrder" , "/toOrder" , "/CustomerForm").hasRole("ADMIN")
            .antMatchers("/validateOrder" , "/toOrder" , "/CustomerForm").hasRole("USER")
                .and()
            .exceptionHandling().accessDeniedPage("/403"); // Au cas ou un user tente d'accéder à une page non authorisée
    }

    @Bean
        //Annotation permettant à cet objet d'être inscrit dans le contexte de Spring
            // Et delors peut être utilisé ailleurs dans l'appli via @Autowired
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
