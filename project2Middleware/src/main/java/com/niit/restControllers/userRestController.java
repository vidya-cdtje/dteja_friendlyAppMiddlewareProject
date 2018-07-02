package com.niit.restControllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserDao;
import com.niit.model.User;
@RestController
public class userRestController {
	
	@Autowired
	UserDao userDao;
    
	@PostMapping("/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody User userDetail)
	{
	 userDetail.setRole("ROLE_USER");
	 
	 if (userDao.registerUser(userDetail))
	 {
		 return new ResponseEntity<String> ("success", HttpStatus.OK);
	 }
	 else
	 {
		 return new ResponseEntity<String> ("failure", HttpStatus.NOT_FOUND);

	 }
	}
	
	@PostMapping("/checkLogin")
	public ResponseEntity<User> checkLogin(@RequestBody User userDetail, HttpSession session)
	{
		User tempUser=userDao.getUser(userDetail.getLoginname());
		System.out.println("inside checklogin");
		if(tempUser!=null)
		{
			session.setAttribute("userDetails", tempUser);
			return new ResponseEntity<User> (tempUser, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<User>(tempUser, HttpStatus.NOT_FOUND);
		}
	}
}
