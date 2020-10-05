package com.hw.twitter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.hw.twitter.service.TwitterService;

import twitter4j.TwitterException;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TwitterServiceTest {

	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(TwitterService.class);

	@Test
	public void testRequestManagement() {
		String createTemplateTweetMessage = "test for create tweet " + UUID.randomUUID().toString();
		try {
			Assert.assertEquals(createTemplateTweetMessage, TwitterService.createTweet(createTemplateTweetMessage));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("An error occurred while running test case:", e);
		}
	}

	@Test
	public void testGetTweets() {
		String createTemplateTweetMessage = "test for getting tweets " + UUID.randomUUID().toString();
		try {
			Assert.assertEquals(createTemplateTweetMessage,
						TwitterService.createTweet(createTemplateTweetMessage));
			List<String> userTweets = TwitterService.getTimeLine();
			System.out.println("Expected string : " + createTemplateTweetMessage);
			System.out.println("Actual string : " + userTweets.get(0));
			assertEquals(createTemplateTweetMessage, userTweets.get(0));
		} catch (TwitterException e) {
			LOGGER.error("An error occurred getting a tweet " + e.getMessage());
		}
	}

	@Test
	public void testDeleteTweets() {
		try {
			String testDelete = "test for delete tweet";
			Assert.assertEquals(testDelete, TwitterService.createTweet(testDelete));

			List<String> userTweetsToDelete = TwitterService.getTimeLine();
			String tweetToDelete = userTweetsToDelete.get(0);
			String text = TwitterService.deleteTweet(tweetToDelete);
			System.out.println("deleted status text : " + text);
			assertEquals(tweetToDelete, text);
		} catch (TwitterException e) {
			LOGGER.error("An error occurred using twitter API " + e.getMessage());
		}
	}
}
