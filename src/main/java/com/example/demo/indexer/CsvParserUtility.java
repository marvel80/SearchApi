package com.example.demo.indexer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.datamodel.Plan;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CsvParserUtility {

	public List<Plan> getParsedBeansFromOpenCsv(String fileName) {
		List<Plan> beans = null;
		try {
			beans = new CsvToBeanBuilder<Plan>(new FileReader(fileName)).withType(Plan.class).build().parse();
		} catch (IllegalStateException | FileNotFoundException e) {
			log.error("state error", e);
		}
		return beans;
	}

}
