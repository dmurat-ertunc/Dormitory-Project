package com.dme.DormitoryProject.security;

import com.dme.DormitoryProject.business.services.ITokenBlackListService;
import com.dme.DormitoryProject.repository.IUserDao;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTGenerator tokenGenerator;
    @Autowired
    private CustomUserDetailService customUserDetailsService;
    @Autowired
    private ITokenBlackListService tokenBlackListService;
    @Autowired
    private IUserDao userDao;

    @Autowired
    public JWTAuthenticationFilter(IUserDao userDao){
        this.userDao=userDao;
    }

    public JWTAuthenticationFilter(){

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 2. Eğer Authentication null değil ve OAuth2 ile giriş yapılmışsa:
        if (authentication instanceof OAuth2AuthenticationToken) {
            // Bu durumda OAuth2 ile giriş yapılmış demektir.
            // Kullanıcı bilgilerini OAuth2AuthenticationToken üzerinden alabilirsiniz.
            OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
            String username = userDao.findUserNameByEmail(oauth2Token.getPrincipal().getAttribute("email"));
            // İlgili userDetails'i yükleyebilir ve Authentication oluşturabilirsiniz
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        // 3. Eğer Authorization başlığı varsa ve JWT ise:
        else {
            String bearerToken = request.getHeader("Authorization");
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
                String token = bearerToken.substring(7);

                if (StringUtils.hasText(token) && tokenGenerator.validateToken(token) && !(tokenBlackListService.isTokenBlackListed(token))) {
                    String username = tokenGenerator.getUsernameFromJWT(token);
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        // Son olarak diğer filtreleri çalıştır
        filterChain.doFilter(request, response);
//        String token = getJWTFromRequest(request);
//        if(StringUtils.hasText(token) && tokenGenerator.validateToken(token) && !(tokenBlackListService.isTokenBlackListed(token))) {
//            String username = tokenGenerator.getUsernameFromJWT(token);
//
//            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,
//                    userDetails.getAuthorities());
//            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        }
//        filterChain.doFilter(request, response);
    }

    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
