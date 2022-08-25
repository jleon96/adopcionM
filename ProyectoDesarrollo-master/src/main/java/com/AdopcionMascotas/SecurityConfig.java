package com.AdopcionMascotas;

import com.AdopcionMascotas.Service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.util.UrlPathHelper;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserService getUserService() {
        return new UserService();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(getUserService());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationSuccessHandler appAuthenticationSuccessHandler() {
        return new AppAuthenticationSuccessHandler();
    }

    public SecurityConfig(UserService userPrincipalDetailsService) {
        this.userDetailsService = userPrincipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    //El siguiente metodo funciona para hacer la autenticacion del usuraio
  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/leerusuarios","/leerusuarioU", "/usuarioNI", "/login", "/leergatos","/comentariosLista", "/leerperros", "/creargato", "/creargatoN", "/crearperro", "/crearperroN","/index.html", "/leerdonaciones","/leerAlimentos")
                .hasRole("ADMIN")
                .antMatchers("/leercomentarios","/leerusuarioU",  "/index2", "/leergatosU", "/login", "/donaciones", "/alimentos.html", "/acercaDe.html", "/gatos.html", "/perros.html", "/index.html", "/donacionesN")
                .hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll().defaultSuccessUrl("/index.html", true)
                .and()
                .logout()
                .logoutSuccessHandler((HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
                    System.out.println("El usuario" + authentication.getName() + " ha salido.");
                    UrlPathHelper helper = new UrlPathHelper();
                    String context1 = helper.getContextPath(request);
                    response.sendRedirect(context1 + "/login");
                });
    }
}