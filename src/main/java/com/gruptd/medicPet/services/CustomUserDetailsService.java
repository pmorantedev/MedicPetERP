package com.gruptd.medicPet.services;

import com.gruptd.medicPet.dao.UsuariDAO;
import com.gruptd.medicPet.models.Usuari;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Aquesta classe és una implementació personalitzada de la UserDetailsService 
 * utilitzada per retornar els detalls d'un usuari.
 *
 * @author pmorante
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuariDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuari user = userDAO.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuari no trobat amb aquest username: " + username));
        
        var rols= new ArrayList<GrantedAuthority>();
        
        rols.add(new SimpleGrantedAuthority(user.getRol_id().getNom()));  
        
        return new User(user.getUsername(), user.getContrasenya(), rols);
    }

}
