package com.niit.restControllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfilePictureDao;
import com.niit.dao.UserDao;
import com.niit.model.ProfilePicture;
import com.niit.model.User;

@RestController
public class profilePictureRestController {
	@Autowired
	ProfilePictureDao profilePictureDao;
	@Autowired
	UserDao userDao;
	
	@PostMapping(value="/doUpload/{loginname}")
	public ResponseEntity<?> uploadProfilePicture(@RequestParam(value="file") CommonsMultipartFile fileUpload, HttpSession session,@PathVariable("loginname") String username)
	{
		
//		User tempuser=userDao.getUser(username);
		User tempuser=(User) session.getAttribute("userDetails");
		System.out.println("session userdetails"+tempuser);
		if(tempuser==null)
		{
			
			return new ResponseEntity<String>("Unauthorized User",HttpStatus.NOT_FOUND);
		}
		else
		{
			ProfilePicture profilePicture=new ProfilePicture();
			profilePicture.setLoginname(tempuser.getLoginname());
			profilePicture.setImage(fileUpload.getBytes());
			System.out.println("I am in doUpload");
			profilePictureDao.save(profilePicture);
			return new ResponseEntity<String>("picture updated",HttpStatus.OK);
	}
	}
	
	@RequestMapping(value="/getImage/{loginname}",method=RequestMethod.GET)
	public @ResponseBody byte[] getProfilePicture(@PathVariable("loginname") String loginname,HttpSession session)
	{
		User userDetail=(User)session.getAttribute("userDetails");
		System.out.println("getImage user:"+userDetail);
		if(userDetail==null)
		{
			return null;
		}
		else
		{
			ProfilePicture profilePicture=profilePictureDao.getProfilePicture(userDetail.getLoginname());
			
			if(profilePicture!=null)
			{
				System.out.println("prof pic,if:"+profilePicture);
				return profilePicture.getImage();
			}
			else
			{
				return null;
			}
		}
	}
}
