package com.daw.contafin;

import com.daw.contafin.config.jwt.JwtUtils;
import com.daw.contafin.config.jwt.UserDetailsImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringSecurityForUserControllerImplTestConfig.class)
class JwtUtilsTest {

    @InjectMocks
    private JwtUtils jwtUtils;

    private String jwtSecret = "mySecretKeyForJwtTesting123456789012345656755575765757575755675757657575757"; // Simula una clave secreta
    private int jwtExpirationMs = 3600000; // 1 hora en milisegundos

    @BeforeEach
    void setUp() {
        jwtUtils.jwtSecret = jwtSecret;
        jwtUtils.jwtExpirationMs = jwtExpirationMs;
    }

    @Test
    @WithUserDetails("username")
    void testGenerateJwtToken() {
        // GIVEN
        Authentication authentication = mock(Authentication.class);
        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("username");

        // WHEN
        String token = jwtUtils.generateJwtToken(authentication);

        // THEN
        assertNotNull(token);
    }

    @Test
    @WithUserDetails("username")
    void testGetUserNameFromJwtToken() {
        // GIVEN
        Authentication authentication = mock(Authentication.class);
        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("username");
        String token = jwtUtils.generateJwtToken(authentication);

        // WHEN
        String username = jwtUtils.getUserNameFromJwtToken(token);

        // THEN
        assertEquals("username", username);
    }

    @Test
    @WithUserDetails("username")
    void testValidateJwtToken_ValidToken() {
        // GIVEN
        Authentication authentication = mock(Authentication.class);
        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("username");
        String token = jwtUtils.generateJwtToken(authentication);

        // WHEN
        boolean isValid = jwtUtils.validateJwtToken(token);

        // THEN
        assertTrue(isValid);
    }

    @Test
    @WithUserDetails("username")
    void testValidateJwtToken_InvalidToken() {
        // GIVEN
        String invalidToken = "invalidToken";

        // WHEN
        boolean isValid = jwtUtils.validateJwtToken(invalidToken);

        // THEN
        assertFalse(isValid);
    }

    @Test
    @WithUserDetails("username")
    void testValidateJwtToken_ExpiredToken() {
        // GIVEN
        jwtUtils.jwtExpirationMs = -1;
        Authentication authentication = mock(Authentication.class);
        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn("username");// Fuerza la expiración del token
        String expiredToken = jwtUtils.generateJwtToken(authentication);

        // WHEN
        boolean isValid = jwtUtils.validateJwtToken(expiredToken);

        // THEN
        assertFalse(isValid);
    }
}
