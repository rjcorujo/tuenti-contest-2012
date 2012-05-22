package com.tuenti.contest.solver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;




public class SearchSolver {
	private static final String INDEX_DIR = "/tmp/tuenti_index";
	private static Directory indexDir;
	private static final String FILES_DIR = "/tmp/tuenti_files";
	
	private static final String FIELD_NAME = "DOC_NAME";
	private static final String FIELD_CONTENT = "DOC_CONTENT";
	private static final String FIELD_CUSTOM = "DOC_CUST_";
	
	private static final Version LUCENE_VERSION = Version.LUCENE_36;
	private static final int MAX_HITS = 800;
	
	
	static {
		createIndex();
	}
	
	private static void createIndex() {
		try {
			indexDir = new SimpleFSDirectory(new File(INDEX_DIR));
			IndexWriter indexWriter = getIndexWriter();
			
			File filesDir = new File(FILES_DIR);
			int fileNum = 0;
			for (File targetFile : filesDir.listFiles()) {
				Document document = new Document();
				document.add(new Field(FIELD_NAME, targetFile.getName(), Store.YES, Index.NO));
				Map<String, String> hits = processFile(targetFile.getAbsolutePath());
				for (String word : hits.keySet()) {
					String occurences = hits.get(word);
					String times = String.valueOf(occurences.split(",").length);
					document.add(new Field(FIELD_CUSTOM+word, occurences, Store.YES, Index.NO));
					document.add(new Field(FIELD_CUSTOM+word+"_TIMES", times, Store.YES, Index.NO));
				}
				Reader reader = new FileReader(targetFile);
				document.add(new Field(FIELD_CONTENT, reader));
				
				indexWriter.addDocument(document);
				System.out.println(fileNum++);
			}
			indexWriter.commit();
			indexWriter.close();
		} catch (Exception e) {
			throw new RuntimeException("Error indexing files");
		}
	}
	
	
	private static Map<String,String> processFile(String filename) throws Exception {
		BufferedReader reader = null;
		try {
			File file = new File(filename);
			Integer fileCode = Integer.parseInt(file.getName());
			reader = new BufferedReader(new FileReader(file));
			Map<String,String> wordsMapping = new HashMap<String, String>();
			int lineNum = 1;
			String line = reader.readLine();
			while (line != null) {
				if (!line.isEmpty()) {
					String[] words = line.split("\\s+");
					int wordIndex = 1;
					for (String word : words) {
						word = word.toLowerCase();
						if (!word.isEmpty()) {
							String descriptor = fileCode+"-"+lineNum+"-"+(wordIndex);
							String descriptors = wordsMapping.get(word);
							if (descriptors == null) {
								descriptors = descriptor;
							} else {
								descriptors = descriptors+","+descriptor;
							}
							wordsMapping.put(word, descriptors);
						}
						wordIndex++;
					}
				}
				line = reader.readLine();
				lineNum++;
			}
			return wordsMapping;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}
	
	public SearchSolver() {
		// TODO Auto-generated constructor stub
	}
	
	public String searchTerm(String term, int targetTime) {
		try {
			term = term.toLowerCase();
			IndexSearcher searcher = getIndexSearcher();
			
			Query query = buildQuery(term);
			
			TopScoreDocCollector collector = TopScoreDocCollector.create(MAX_HITS, true);
			searcher.search(query, collector);
			
			String result = getDescriptorForTime(term, targetTime, collector.topDocs().scoreDocs, searcher);
			searcher.close();
			return result;
		} catch (Exception e) {
			throw new RuntimeException("Error searching");
		}
	}
	
	private String getDescriptorForTime(String word, Integer targetTime, ScoreDoc[] scoreDocs, IndexSearcher searcher) throws Exception {
		List<Integer> docsIndexs = new ArrayList<Integer>();
		for (ScoreDoc scoreDoc : scoreDocs) {
			docsIndexs.add(scoreDoc.doc);
		}
	
		Collections.sort(docsIndexs);
		int times = 0;
		for (Integer docIndex : docsIndexs) {
			Document doc = searcher.doc(docIndex);
			int currentTime = Integer.parseInt(doc.get(FIELD_CUSTOM+word+"_TIMES"));
			if (times + currentTime >  targetTime) {
				String descriptorsStr = doc.get(FIELD_CUSTOM+word);
				String[] descriptors = descriptorsStr.split(",");
				int index = targetTime - times;
				return descriptors[index];
			}
			times += currentTime;
		}
		return null;
	}
	
	public void close() {
		try {
			indexDir.close();
		} catch (Exception e) {
			throw new RuntimeException("Error closing index directory");
			
		}
	}
	
	private IndexSearcher getIndexSearcher() throws Exception {
		IndexReader reader = IndexReader.open(indexDir);
		return new IndexSearcher(reader);
	}
	
	private static IndexWriter getIndexWriter() throws Exception {
		Analyzer analyzer = new StandardAnalyzer(LUCENE_VERSION);
		IndexWriterConfig config = new IndexWriterConfig(LUCENE_VERSION, analyzer);

		return new IndexWriter(indexDir, config);
	}
	
	private Query buildQuery(String term) throws Exception {
		Analyzer analyzer = new StandardAnalyzer(LUCENE_VERSION);
		QueryParser queryparser = new QueryParser(LUCENE_VERSION, FIELD_CONTENT, analyzer);
		Query query = queryparser.parse(term);
		
		return query;
		
		
		
	}
}
