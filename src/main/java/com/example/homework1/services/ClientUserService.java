package com.example.homework1.services;

import com.example.homework1.dao.ClientUserDAO;
import com.example.homework1.models.ClientUser;
import com.example.homework1.models.dto.ClientUserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@NoArgsConstructor
public class ClientUserService implements UserDetailsService {
    @Autowired
    private ClientUserDAO clientUserDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    public ClientUserService(ClientUserDAO clientUserDAO, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.clientUserDAO = clientUserDAO;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientUser byEmail = clientUserDAO.findByEmail(username);
        return byEmail;
    }

    public void saveClient(@RequestBody ClientUserDTO clientUserDTO) {
        ClientUser clientUser = new ClientUser();
        clientUser.setEmail(clientUserDTO.getUsername());
        clientUser.setPassword(passwordEncoder.encode(clientUserDTO.getPassword()));

        clientUserDAO.save(clientUser);
    }

    public List<ClientUser> getAllDefault() {
        return clientUserDAO.findAll();
    }

    public ResponseEntity<String> login(@RequestBody ClientUserDTO clientUserDTO){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                clientUserDTO.getUsername(), clientUserDTO.getPassword()
        );
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (authenticate!=null){
            String jwtToken = Jwts.builder()
                    .setSubject(authenticate.getName())
                    .signWith(SignatureAlgorithm.HS512, "secret".getBytes(StandardCharsets.UTF_8))
                    .compact();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("AUTHORIZATION","Bearer "+jwtToken);
            return new ResponseEntity<>("login",httpHeaders, HttpStatus.OK);
        }
        return new ResponseEntity<>("bad credenc",HttpStatus.FORBIDDEN);
    }
}

