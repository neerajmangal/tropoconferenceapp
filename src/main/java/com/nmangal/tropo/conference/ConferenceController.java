package com.nmangal.tropo.conference;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConferenceController {

	@RequestMapping(value = "/conference/{conferenceID}", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public @ResponseBody UserStory getConference(@PathVariable(value = "conferenceID") final String storyNumber) {
		UserStory userStory = new UserStory(storyNumber);
		return userStory;
	}

	@RequestMapping(value = "/conference/{conferenceID}", consumes = { "application/json" }, produces = {
	"application/json" }, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<UserStory> makeConference(@PathVariable(value = "conferenceID") final String conferenceID,
			@Valid @RequestBody final Map<String, String> request) {
		RallyUpdateUserStory updateStoryState = new RallyUpdateUserStory();
		
		if (updateStoryState.RallyUpdateStoryState(storyNumber, request.get("state"))){
			UserStory userStory = new UserStory(storyNumber);
			return new ResponseEntity<UserStory>(userStory, HttpStatus.OK);
		}
		return new ResponseEntity<UserStory>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
