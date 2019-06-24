package com.gabi.backend.bikeparkend.service.implementation;

import com.gabi.backend.bikeparkend.model.User;
import com.gabi.backend.bikeparkend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsEntity loadUserByUsername(String s) throws UsernameNotFoundException {
        //System.out.println("Mortii ma-tii " + s);
        Optional<User> userUsername  = userRepository.findByUsername(s);
        Optional<User> userEmail = userRepository.findByEmail(s);
        User user = null;

        if(userUsername.isPresent()){
            user=userUsername.get();
        }
        else if(userEmail.isPresent()){
            user=userEmail.get();
        }

        //System.out.println("Da useru " + user.getEmail() + " " + user.getPassword() + " " + user.getActive());
        //System.out.println(user.getRoles().size());


        if(user==null) {
            throw new UsernameNotFoundException("The user doesn't exist");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            //System.out.println(role.getRoleString() + " " + role.getUsers());
            authorities.add(new SimpleGrantedAuthority(role.getRoleString().toString()));
        });
        //System.out.println("Lungime roluri authority " + authorities.size());

        UserDetailsEntity userDetails = new UserDetailsEntity(user);
        /*System.out.println("UserDetails " + userDetails.getUsername() + " " + userDetails.getAuthorities().size() +
                " " + userDetails.isEnabled());*/
        return userDetails;
    }
}
