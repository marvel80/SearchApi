package com.example.demo.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Plan {

	@CsvBindByName(column = "ACK_ID")
	@JsonProperty(value = "ACK_ID")
	private String ackId;

	@CsvBindByName(column = "PLAN_NAME")
	@JsonProperty(value = "PLAN_NAME")
	private String planName;

	@CsvBindByName(column = "SPONSOR_DFE_NAME")
	@JsonProperty(value = "SPONSOR_DFE_NAME")
	private String sponsorName;

	@CsvBindByName(column = "SPONS_DFE_MAIL_US_STATE")
	@JsonProperty(value = "SPONS_DFE_MAIL_US_STATE")
	private String sponsorState;

}
