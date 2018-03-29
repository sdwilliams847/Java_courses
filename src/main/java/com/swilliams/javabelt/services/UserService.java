package com.swilliams.javabelt.services;

import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.swilliams.javabelt.models.User;
import com.swilliams.javabelt.repositories.UserRepository;

@Service
public class UserService {
	UserRepository uR;
	
	public UserService(UserRepository uR) {
		this.uR = uR;
	}

	public boolean isMatch(String password,String dbPassword){
		if(BCrypt.checkpw(password,dbPassword)){
			return true;
		}else{
			return false;
		}
	}

	public User create(User user){
		user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
		return this.uR.save(user);
	}

	public void login(HttpSession session, Long id) {session.setAttribute("id",id);}
	
	public void logout(HttpSession s) {s.setAttribute("id", null);}
	
	public String redirect() {return "redirect:/users/new";}
	
	public boolean isValid(HttpSession session) {
		if(session.getAttribute("id") == null) {return false;}
		else {return true;}
	}
	
	public ArrayList<User> all(){
    	return (ArrayList<User>)uR.findAll();
    }
		 
	public Optional<User> find(Long id) {
		return uR.findById(id);
	}
	
	public void update(User user) {
		uR.save(user);
	}
	
	public void destroy(Long id) {
		uR.deleteById(id);
	}
	
	public User findByEmail(String email) {
		return uR.findByEmail(email);
	}
}
