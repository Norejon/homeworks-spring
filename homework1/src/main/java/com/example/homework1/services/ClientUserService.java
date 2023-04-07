package com.example.homework1.services;

import com.example.homework1.dao.ClientUserDAO;
import com.example.homework1.models.ClientUser;
import com.example.homework1.models.dto.ClientUserDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClientUserService implements UserDetailsService {
    private ClientUserDAO clientUserDAO;
    private PasswordEncoder passwordEncoder;

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

    public List<ClientUser> getAllDefault(){
        return clientUserDAO.findAll();
    }
}
