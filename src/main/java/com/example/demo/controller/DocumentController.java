package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.datamodel.Hit;
import com.example.demo.datamodel.HitsObject;
import com.example.demo.service.PlanService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DocumentController {

	@Autowired
	private PlanService planService;

	@GetMapping("/plan")
	public HitsObject getByAll(@RequestParam(name = "planName", required = false) String planName,
			@RequestParam(name = "sponsor", required = false) String sponsor,
			@RequestParam(name = "state", required = false) String state) {
		if (StringUtils.isBlank(planName) && StringUtils.isBlank(sponsor) && StringUtils.isBlank(state)) {
			throw new InvalidRequestException("At least one search parameter is required");
		}
		String decodedPlanName = StringUtils.isNotBlank(planName) ? getDecodedValue("planName", planName)
				: StringUtils.EMPTY;
		String decodedSponsorName = StringUtils.isNotBlank(sponsor) ? getDecodedValue("sponsor", sponsor)
				: StringUtils.EMPTY;
		String decodedStateName = StringUtils.isNotBlank(sponsor) ? getDecodedValue("state", state) : StringUtils.EMPTY;

		List<Hit> searchResults = planService.findByPlanNameAndSponsorNameAndSponsorState(decodedPlanName,
				decodedSponsorName, decodedStateName);
		return new HitsObject(searchResults.size(), searchResults);
	}

	private String getDecodedValue(String key, String value) {
		try {
			return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			log.error("error decoding value={} for key={}", value, key);
			return null;
		}
	}

}
