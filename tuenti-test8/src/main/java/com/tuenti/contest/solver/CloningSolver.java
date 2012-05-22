package com.tuenti.contest.solver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.tuenti.contest.domain.HugeReaderQueue;
import com.tuenti.contest.domain.HugeWriterQueue;



public class CloningSolver {
	private static final int MAX_PERSONS = 100000;
	private HugeReaderQueue queue;
	private List<Map<Character,String>> series;
	
	
	public CloningSolver(List<Character> persons, List<Map<Character,String>> series) throws IOException {
		if (persons.size() <= MAX_PERSONS) {
			this.series = series;
			HugeWriterQueue queueWriter = new HugeWriterQueue();
			for (Character person : persons) {
				queueWriter.add(person.charValue());
			}
			queueWriter.close();
			this.queue = new HugeReaderQueue(queueWriter);
		} else {
			throw new IllegalArgumentException("Too many persons specified, max: "+MAX_PERSONS);
		}
	}
	
	public String solveProblem() throws IOException {
		for (Map<Character,String> transformation : series) {
			HugeWriterQueue tempQueue = new HugeWriterQueue();
			char[] personBuf = new char[1];
			int read = queue.getNext(personBuf, 1);
			while (read > 0) {
				char person = personBuf[0];
				String clones = transformation.get(person);
				if (clones != null) {
					for (int j = 0; j < clones.length(); j++) {
						tempQueue.add(clones.charAt(j));
					}
				} else {
					tempQueue.add(person);
				}
				read = queue.getNext(personBuf, 1);
			}
			tempQueue.close();
			queue.deleteAndClose();
			queue = new HugeReaderQueue(tempQueue);
		}
		String md5 = queue.getMd5Sum();
		queue.deleteAndClose();
		return md5;
	}

}
