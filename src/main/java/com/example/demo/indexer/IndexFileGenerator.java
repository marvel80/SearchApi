package com.example.demo.indexer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.example.demo.datamodel.Plan;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IndexFileGenerator {

	private static CsvParserUtility parserUtil;

	private static IndexDocumentMapper documentMapper;

	private static String FILE_IN = "/Users/ng/Documents/pcl/search-data/latest.csv";
	private static String FILE_OUT = "/Users/ng/Documents/pc/search-data/out-large";
	private static String FORMAT = ".json";

	public static void main(String[] args) {
		documentMapper = new IndexDocumentMapper();
		parserUtil = new CsvParserUtility();

		generateBulkIndexFile();
	}

	private static void generateBulkIndexFile() {
		List<Plan> documents = parserUtil.getParsedBeansFromOpenCsv(FILE_IN);
		try (BufferedWriter wr = new BufferedWriter(new FileWriter(FILE_OUT + FORMAT))) {
			for (Plan doc : documents) {
				wr.append(documentMapper.getIndexMetaString(new IndexMeta("pc-plan-data", "plan", doc.getAckId())))
						.append("\n").append(documentMapper.getDocumentJsonString(doc)).append("\n");
			}
			wr.write("\n");
			log.info("completed write for indexed documents");
		} catch (IOException e) {
			log.error("IO exception occured. error={}", e);
		}

	}

}
