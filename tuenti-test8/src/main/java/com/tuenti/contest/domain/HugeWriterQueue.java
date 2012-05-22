package com.tuenti.contest.domain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HugeWriterQueue extends HugeQueue {
	private static final int BUFFER_SIZE = 100000;
	private BufferedWriter writer;
	
	public HugeWriterQueue() throws IOException {
		super();
		initWriter();
	}
	
	public HugeWriterQueue(HugeQueue queue) throws IOException {
		super(queue);
		initWriter();
	}
	
	private void initWriter() throws IOException {
		writer = new BufferedWriter(new FileWriter(getFile()),BUFFER_SIZE);
	}
	
	public void add(char data) throws IOException {
		writer.write(data);
	}
	
	public void add(char[] data, int size) throws IOException {
		writer.write(data,0,size);
	}
	
	public void close() throws IOException {
		if (writer != null) {
			writer.close();
		}
	}
	
	public void deleteAndClose() throws IOException {
		getFile().delete();
		close();
	}
}
