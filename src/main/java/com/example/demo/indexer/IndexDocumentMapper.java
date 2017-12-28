package com.example.demo.indexer;

import org.springframework.stereotype.Component;

import com.example.demo.datamodel.Plan;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class IndexDocumentMapper {
	private ObjectMapper mapper;

	public IndexDocumentMapper() {
		this.mapper = new ObjectMapper();
	}

	public String getIndexMetaString(IndexMeta metaData) {
		try {
			return mapper.writer().withRootName("index").writeValueAsString(metaData);
		} catch (JsonProcessingException e) {
			log.error("error. exception={}", e);
		}
		return null;
	}

	public String getDocumentJsonString(Plan doc) {
		try {
			return mapper.writeValueAsString(doc);
		} catch (JsonProcessingException e) {
			log.error("error processing json for document. error={}", e);
		}
		return null;
	}
}
