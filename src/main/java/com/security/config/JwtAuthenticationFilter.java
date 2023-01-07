package com.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.security.dto.GenericResponse;
import com.security.entity.AppUser;
import com.security.enums.RoleType;
import com.security.repo.AppUserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenUtils jwtTokenUtil;

    private  AppUserRepo appUserRepo;

    JwtAuthenticationFilter(JwtTokenUtils jwtTokenUtil, AppUserRepo appUserRepo) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.appUserRepo = appUserRepo;
    }

    private static Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filter)
            throws ServletException, IOException {
        try {
            String authToken = req.getHeader("Authorization");

            String username = jwtTokenUtil.parseToken(authToken);
            AppUser user = appUserRepo.findByEmail(username);
            if (user != null) {

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(
                        new SimpleGrantedAuthority(user.getRoleType().name())));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filter.doFilter(req, res);
            }

        } catch (Exception e) {
            e.printStackTrace();

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            GenericResponse resp = new GenericResponse(HttpStatus.UNAUTHORIZED.value(), "SUCCESS");
            String jsonRespString = ow.writeValueAsString(resp);
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            PrintWriter writer = res.getWriter();
            writer.write(jsonRespString);
            System.out.println("===============================");
        }
    }

}



