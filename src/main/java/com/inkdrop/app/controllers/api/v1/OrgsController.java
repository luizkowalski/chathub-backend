package com.inkdrop.app.controllers.api.v1;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.inkdrop.app.domain.formatter.FormatterFactory;
import com.inkdrop.app.domain.models.Organization;
import com.inkdrop.app.domain.repositories.OrganizationRepository;

@RestController
@EnableAutoConfiguration
public class OrgsController extends BasicController {
	
	private Logger log = LogManager.getLogger(OrgsController.class);
	
	@Autowired OrganizationRepository organizatioRepository;

	@RequestMapping(method = RequestMethod.GET, path="/v1/orgs/{login}")
	public ResponseEntity<?> getOrgInfo(@PathVariable String login, @RequestHeader("Auth-Token") String token){
		try{
			Organization org = organizatioRepository.findByLoginIgnoreCase(login);
			String resp = FormatterFactory.getFormatter(Organization.class).toJson(org);
			
			return createSuccessfulResponse(resp);
		} catch(Exception e) {
			log.error(e);
			return createErrorResponse(e);
		}
	}
}