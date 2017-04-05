package com.nmangal.tropo.conference;

import static com.voxeo.tropo.Key.ID;
import static com.voxeo.tropo.Key.NAME;
import static com.voxeo.tropo.Key.EVENT;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.voxeo.tropo.Tropo;
import com.voxeo.tropo.actions.ConferenceAction;
import com.voxeo.tropo.actions.Do;

@RestController
public class ConferenceController {

	@RequestMapping(value = "/conference/{conferenceID}", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public String makeConference(@PathVariable(value = "conferenceID") final String conferenceID) {
		// Put the call in conference
		Tropo tropo = new Tropo();
		ConferenceAction confAction = tropo.conference(NAME("NeerajMangal"), ID(conferenceID));
		confAction.and(Do.on(EVENT("join")).and(Do.say("Welcome to Conference")));
		return tropo.text();
	}

}
