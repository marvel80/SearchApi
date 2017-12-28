package com.example.demo.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class AwsEsResponse {
	private HitsObject hits;
	private long took;
	
}
