package com.example.demo.datamodel;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PlanQueryBuilderImpl implements PlanQueryBuilder {

	@Value("${es.query.from}")
	private int FROM;

	@Value("${es.query.maxResultSize}")
	private int SIZE_OF_RESULTS;

	@Override
	public String findByPlanNameAndSponsorNameAndSponsorState(String planName, String sponsorName,
			String sponsorState) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		BoolQueryBuilder qb = new BoolQueryBuilder();

		if (StringUtils.isNotBlank(planName)) {
			qb.must(new MatchQueryBuilder("PLAN_NAME", planName));
		}
		if (StringUtils.isNotBlank(sponsorName)) {
			qb.must(new MatchQueryBuilder("SPONSOR_DFE_NAME", sponsorName));
		}
		if (StringUtils.isNotBlank(sponsorState)) {
			qb.must(new MatchQueryBuilder("SPONS_DFE_MAIL_US_STATE", sponsorState));
		}

		return searchSourceBuilder.query(qb).from(FROM).size(SIZE_OF_RESULTS).toString();
	}

}
