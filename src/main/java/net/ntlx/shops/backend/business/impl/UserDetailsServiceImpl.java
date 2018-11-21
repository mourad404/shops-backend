package net.ntlx.shops.backend.business.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.ntlx.shops.backend.model.Costumer;
import net.ntlx.shops.backend.repository.CostumerRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CostumerRepository costumerRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Costumer c = costumerRepository.getByUsername(username);
		if (c == null)
			throw new UsernameNotFoundException(username);
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		UserDetails spUser = new User(c.getUsername(), c.getPassword(), authorities);
		return spUser;
	}

}
