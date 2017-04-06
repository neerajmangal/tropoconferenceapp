package com.nmangal.tropo.conference;

import static com.voxeo.tropo.Key.ID;
import static com.voxeo.tropo.Key.NAME;
import static com.voxeo.tropo.Key.EVENT;
import static com.voxeo.tropo.Key.JOIN_PROMPT;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.voxeo.tropo.Tropo;
import com.voxeo.tropo.actions.ConferenceAction;
import com.voxeo.tropo.actions.Do;

@RestController
public class ConferenceController {

	@RequestMapping(value = "/conference/", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public String makeConference(@Valid @RequestBody TropoConferenceSession request) {
		
		String conferenceID = request.getSession().getParameters().getConferenceID();
		String name = request.getSession().getParameters().getName();
		Tropo tropo = new Tropo();
		ConferenceAction confAction = tropo.conference(NAME(name), ID(conferenceID), JOIN_PROMPT("Welcome to Conference"));
		confAction.and(Do.on(EVENT("join")).and(Do.say("Welcome to Conference")));
		return tropo.text();
	}

}
