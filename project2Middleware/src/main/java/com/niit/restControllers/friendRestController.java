package com.niit.restControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.FriendDao;
import com.niit.model.*;

@RestController
public class friendRestController {

	@Autowired
	FriendDao friendDao;
	
	@GetMapping("/showFriendList/{loginname}")
	public ResponseEntity<List<Friend>> showFriendList(@PathVariable("loginname")String loginname){
		System.out.println(loginname);
		List<Friend> friendList=friendDao.showFriendlist(loginname);
		System.out.println("inside show friendlist"+friendList);
		if(friendList.size()>0) {
			return new ResponseEntity<List<Friend>>(friendList, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<Friend>>(friendList, HttpStatus.NOT_FOUND);
			}
	}
	@GetMapping("/showPendingFriendRequests/{loginname}")
	public ResponseEntity<List<Friend>> showPendingFriendRequests(@PathVariable("loginname")String loginname){
		List<Friend> pendingFriendList=friendDao.showPendingFriendRequests(loginname);
		if(pendingFriendList.size()>0) {
			return new ResponseEntity<List<Friend>>(pendingFriendList, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<Friend>>(pendingFriendList, HttpStatus.NOT_FOUND);
			}
	}
	@GetMapping("/showSuggestedFriends/{loginname}")
	public ResponseEntity<List<User>> showSuggestedFriends(@PathVariable("loginname")String loginname){
		
		List<User> suggestedFriends=friendDao.showSuggestedFriends(loginname);
		System.out.println("showsuggested friends"+suggestedFriends);
		if(suggestedFriends.size()>0) {
			return new ResponseEntity<List<User>>(suggestedFriends, HttpStatus.OK);
		}else {
			return new ResponseEntity<List<User>>(suggestedFriends, HttpStatus.NOT_FOUND);
			}
	}
	@PostMapping("/sendFriendRequest")
	public ResponseEntity<String> sendFrienRequest( @RequestBody Friend friend)
	{
		if(friendDao.sendFriendRequest(friend)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("failure",HttpStatus.NOT_FOUND);
			}
	}
	@GetMapping("/deleteFriendRequest/{friendId}")
	public ResponseEntity<String> deleteFrienRequest(@PathVariable("friendId") int friendId)
	{
		if(friendDao.deleteFriendRequest(friendId)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("failure",HttpStatus.NOT_FOUND);
			}
	}
	@GetMapping("/acceptFriendRequest/{friendId}")
	public ResponseEntity<String> acceptFrienRequest(@PathVariable("friendId") int friendId)
	{
		if(friendDao.acceptFriendRequest(friendId)) {
			return new ResponseEntity<String>("success",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("failure",HttpStatus.NOT_FOUND);
			}
	}
	
}
