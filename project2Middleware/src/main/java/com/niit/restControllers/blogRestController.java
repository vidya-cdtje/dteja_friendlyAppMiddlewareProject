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

import com.niit.dao.*;
import com.niit.model.Blog;

@RestController
public class blogRestController
{
	@Autowired
	BlogDao blogDao;

	@GetMapping("/listBlogs")
	public ResponseEntity<List<Blog>> listBlogs()
	{
		List<Blog> listBlogs=blogDao.listBlogs();
		
		if(listBlogs.size()>0)
		{
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value="/addBlog")
	public ResponseEntity<String> insertBlog(@RequestBody Blog blog)
	{
		blog.setCreateDate(new java.util.Date());
		blog.setDislikes(0);
		blog.setLikes(0);
		blog.setStatus("NA");
		if(blogDao.addBlog(blog))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getBlog/{blogid}")
	public ResponseEntity<Blog> getBlog(@PathVariable("blogid")int blogid)
	{
		Blog blog=blogDao.getBlog(blogid);
		if(blog!=null)
		{
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/approveBlog/{blogid}")
	public ResponseEntity<String> approveBlog(@PathVariable("blogid")int blogid)
	{
		if(blogDao.approveBlog(blogid))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/rejectBlog/{blogid}")
	public ResponseEntity<String> rejectBlog(@PathVariable("blogid")int blogid)
	{
		if(blogDao.rejectBlog(blogid))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/incrementLikes/{blogid}")
	public ResponseEntity<String> incrementLikes(@PathVariable("blogid")int blogid)
	{
		if(blogDao.incLikes(blogid))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/incrementDisLikes/{blogid}")
	public ResponseEntity<String> incrementDisLikes(@PathVariable("blogid")int blogid)
	{
		if(blogDao.disLikes(blogid))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/deleteBlog/{blogid}")
	public ResponseEntity<String> deleteBlog(@PathVariable("blogid")int blogid)
	{
		Blog blog=blogDao.getBlog(blogid);
		
		if(blogDao.deleteBlog(blog))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/updateBlog")
	public ResponseEntity<String> updateBlog(@RequestBody Blog blog)
	{
		if(blogDao.updateBlog(blog))
		{
			return new ResponseEntity<String>("Success",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
}
