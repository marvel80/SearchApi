package com.example.demo.service;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.datamodel.AwsEsResponse;
import com.example.demo.datamodel.Hit;
import com.example.demo.datamodel.PlanQueryBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanQueryBuilder planQueryBuidler;

	@Autowired
	RestTemplate restTemplate;

	@Value("${aws.es.hostUrl}")
	private String hostUrl;

	@Value("${aws.es.index}")
	private String indexName;

	private String completeUrl;

	@PostConstruct
	public void checkAndSetUrl() {
		if (StringUtils.isBlank(completeUrl)) {
			StringBuilder url = new StringBuilder();
			url.append(hostUrl).append("/").append(indexName).append("/").append("_search");
			completeUrl = url.toString();
		}
	}

	@Override
	public List<Hit> findByPlanNameAndSponsorNameAndSponsorState(String planName, String sponsorName,
			String sponsorState) {
		String query = planQueryBuidler.findByPlanNameAndSponsorNameAndSponsorState(planName, sponsorName,
				sponsorState);
		AwsEsResponse responseFromEs = callAwsEsService(query);
		return responseFromEs.getHits().getHits();
	}

	private AwsEsResponse callAwsEsService(String query) {
		HttpHeaders headers = getHeaders();
		log.debug("aws_url={} headers={} query={} ", completeUrl, headers, query);

		HttpEntity<String> entity = new HttpEntity<String>(query, headers);
		return restTemplate.exchange(completeUrl, HttpMethod.POST, entity, AwsEsResponse.class).getBody();
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
