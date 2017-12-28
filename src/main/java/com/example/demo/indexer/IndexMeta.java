package com.example.demo.indexer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * { "index" : { "_index": "movies", "_type" : "movie", "_id" : "2" } }
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value = "index")
public class IndexMeta {
	@JsonProperty(value = "_index")
	private String index;

	@JsonProperty(value = "_type")
	private String type;

	@JsonProperty(value = "_id")
	private String id;
}
