package pe.edu.upc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pe.edu.upc.model.Users;
import pe.edu.upc.model.Role;
import pe.edu.upc.repository.IUserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{

	@Autowired
	private IUserRepository userDao;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		 Users usuario = userDao.findByCorreo(username);
		 List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		 
		 Role role = usuario.getRole();
		 
		 authorities.add(new SimpleGrantedAuthority(role.getNameRole()));
		 
		 return new User(usuario.getEmail(), usuario.getContra(),true ,true,true,true,authorities);
		 
	}
	
}	
