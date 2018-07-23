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
import com.niit.model.Job;

@RestController
public class jobRestController {
@Autowired
 JobDao jobDao;
@Autowired
 UserDao userDao;

/*public boolean addJob(Job job);
public boolean deleteJob(Job job);
public boolean updateJob(Job job);
public List<Job> listAllJobs();
public Job getJob(int jobId);*/

@PostMapping("/addJob")
public ResponseEntity<String> addJob(@RequestBody Job job){
	job.setJobDate(new java.util.Date());
	job.setActive(true);
	if(jobDao.addJob(job))
	{
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
	}
 }
@GetMapping("/deleteJob/{jobId}")
public ResponseEntity<String> deleteJob(@PathVariable("jobId")int jobId)
{
	Job job=jobDao.getJob(jobId);
	
	if(jobDao.deleteJob(job ))
	{
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
	}
}
@PostMapping("/updateJob")
public ResponseEntity<String> updateJob(@RequestBody Job job)
{
	if(jobDao.updateJob(job))
	{
		return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
	}
}
@GetMapping("/listAllJobs")
public ResponseEntity<List<Job>> listAllJobs()
{
	List<Job> listAllJobs=jobDao.listAllJobs();
	
	if(listAllJobs.size()>0)
	{
		return new ResponseEntity<List<Job>>(listAllJobs,HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<List<Job>>(listAllJobs,HttpStatus.NOT_FOUND);
	}
}
@GetMapping("/getJob/{jobId}")
public ResponseEntity<Job> getBlog(@PathVariable("jobId")int jobId)
{
	Job job=jobDao.getJob(jobId);
	if(job!=null)
	{
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<Job>(job,HttpStatus.NOT_FOUND);
	}
	
}
}



