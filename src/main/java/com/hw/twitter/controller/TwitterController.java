package com.hw.twitter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hw.twitter.service.TwitterService;

import twitter4j.TwitterException;

@RestController
public class TwitterController {

	
	private TwitterService service;
	
	@RequestMapping(method=RequestMethod.GET, path="/getTweets")
	public List<String> getTweets() throws TwitterException{
		return TwitterService.getTimeLine();
	}
	
	@PostMapping(path="/sendMessage")
	public String sendDirectMessage() throws TwitterException{
		return TwitterService.sendDirectMessage("97119265", "Test from App123");
	}
	
	@PostMapping(path="/createPost/{msg}")
	public String createPost(@PathVariable String msg) throws TwitterException{
		return TwitterService.createTweet(msg);
	}
	
	
	@PostMapping(path="/deletePost/{msg}")
	public String deletePost(@PathVariable String msg) throws TwitterException{
		//Long statusId = 1312191363803357187L;
		return TwitterService.deleteTweet(msg);
	}
	
/*	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloworldBean(){
		return new HelloWorldBean("Hello World");
	}
	

	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloworldPathVar(@PathVariable String name){
		return new HelloWorldBean(String.format("Hello World %s", name));
	}*/
	
	
}
