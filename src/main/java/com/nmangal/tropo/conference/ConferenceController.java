package com.nmangal.tropo.conference;

import static com.voxeo.tropo.Key.ID;
import static com.voxeo.tropo.Key.NAME;
import static com.voxeo.tropo.Key.EVENT;
import static com.voxeo.tropo.Key.JOIN_PROMPT;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.voxeo.tropo.Tropo;
import com.voxeo.tropo.actions.ConferenceAction;
import com.voxeo.tropo.actions.Do;

@RestController
public class ConferenceController {
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	@RequestMapping(value = "/conference/", consumes = { "application/json" }, produces = {
			"application/json" }, method = RequestMethod.POST)
	public String makeConference(@Valid @RequestBody TropoConferenceSession request) {
		
		String conferenceID = request.getSession().getParameters().getConferenceID();
		String name = request.getSession().getParameters().getName();
		System.out.println("ConferenceID="+conferenceID);
		System.out.println("ConferenceName="+name);
		System.out.println("requestURL="+httpServletRequest.getRequestURL());
		
		Tropo tropo = new Tropo();
		ConferenceAction confAction = tropo.conference(NAME(name), ID(conferenceID), JOIN_PROMPT("Welcome to Conference"));
		confAction.and(Do.on(EVENT("join")).and(Do.say("Welcome to Conference")));
		System.out.println("Tropo text="+tropo.text());
		return tropo.text();
	}

}
