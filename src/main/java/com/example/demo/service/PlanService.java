package com.example.demo.service;

import java.util.List;

import com.example.demo.datamodel.Hit;

public interface PlanService {
	public List<Hit> findByPlanNameAndSponsorNameAndSponsorState(String planName, String sponsorName,
			String sponsorState);
}
