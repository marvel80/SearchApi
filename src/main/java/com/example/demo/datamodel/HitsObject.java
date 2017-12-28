package com.example.demo.datamodel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "hits")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HitsObject {
	private long total;
	
	private List<Hit> hits;

}
