//package com.library.management.controller.auth;
//
//import com.library.management.entity.role.User;
//import com.library.management.repo.UserRepository;
//import com.library.management.security.JwtUtils;
//import com.library.management.service.UserDetailsServiceImpl;
//import com.library.management.token.TokenRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//
//    private final User user;
//    private final UserDetailsServiceImpl userDetailsService;
//    private final TokenRepository tokenRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtils jwtUtils;
//    private final AuthenticationManager authenticationManager;
//
//    public AuthResponse register(RegisterRequest request) {
//        user(request.getUsername(), request.setEmail(), request.setPassword();)
//        var savedUser = userDetailsService.save(user);
//        var jwtToken = jwtUtils.generateToken(user);
//        var refreshToken = jwtUtils.generateRefreshToken(user);
//        saveUserToken(savedUser, jwtToken);
//        return AuthResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
//    }
//
//    public AuthResponse authenticate(AuthRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        var user = userDetailsService.loadUserByUsername(request.getEmail());
//        var jwtToken = jwtUtils.generateToken(user);
//        var refreshToken = jwtUtils.generateRefreshToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, jwtToken);
//        return AuthResponse.builder()
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
//                .build();
//    }
//
//    private void saveUserToken(User user, String jwtToken) {
//        var token = Token.builder()
//                .user(user)
//                .token(jwtToken)
//                .tokenType(TokenType.BEARER)
//                .expired(false)
//                .revoked(false)
//                .build();
//        tokenRepository.save(token);
//    }
//
//    private void revokeAllUserTokens(User user) {
//        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
//        if (validUserTokens.isEmpty())
//            return;
//        validUserTokens.forEach(token -> {
//            token.setExpired(true);
//            token.setRevoked(true);
//        });
//        tokenRepository.saveAll(validUserTokens);
//    }
//
//    public void refreshToken(
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) throws IOException {
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        final String refreshToken;
//        final String userEmail;
//        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//            return;
//        }
//        refreshToken = authHeader.substring(7);
//        userEmail = jwtUtils.extractUsername(refreshToken);
//        if (userEmail != null) {
//            var user = this.userDetailsService.loadUserByUsername(userEmail)
//                    .orElseThrow();
//            if (jwtUtils.isTokenValid(refreshToken, user)) {
//                var accessToken = jwtUtils.generateToken(user);
//                revokeAllUserTokens(user);
//                saveUserToken(user, accessToken);
//                var authResponse = AuthResponse.builder()
//                        .accessToken(accessToken)
//                        .refreshToken(refreshToken)
//                        .build();
//                 new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//            }
//        }
//    }
//}
