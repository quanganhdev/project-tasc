package com.example.projecttasc.security.filter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.projecttasc.database.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Slf4j
public class customauthenfilter extends UsernamePasswordAuthenticationFilter {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    public  customauthenfilter(UserRepository userRepository, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.authenticationManager= authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
       String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info("user name : " + username + " ; password : "+ password);
        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(username,password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
       User user =(User)authentication.getPrincipal();
        com.example.projecttasc.database.entity.User userid = userRepository.findByUserName(user.getUsername());
        if (userid == null){
            log.error("khong the tim thay user");
            throw  new UsernameNotFoundException("user not fond");
        }else {
            log.info("da tim thay user");
        }
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create().withSubject(user.getUsername()).withClaim("userid", userid.getId())
                                        .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                                        .withIssuer(request.getRequestURI().toString())
                                        .withClaim("roles",user.getAuthorities()
                                                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                                        .sign(algorithm);
        String refresh_toke = JWT.create().withSubject(user.getUsername()).withClaim("userid", userid.getId())
                .withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
                .withIssuer(request.getRequestURI().toString())
                .withClaim("roles",user.getAuthorities()
                        .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        response.setHeader( "access_token",access_token);
        response.setHeader( "refresh_toke",refresh_toke);
        Map<String,String> tokens = new HashMap<>();
        tokens.put("access_token",access_token);
        tokens.put("refresh_toke",refresh_toke);
        response.setContentType(APPLICATION_JSON_VALUE);
        new  ObjectMapper().writeValue(response.getOutputStream(),tokens);
    }
}
