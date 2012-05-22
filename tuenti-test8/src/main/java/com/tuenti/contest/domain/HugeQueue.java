package com.tuenti.contest.domain;

import java.io.File;
import java.io.IOException;

public class HugeQueue {
	private File file;
	
	public HugeQueue() throws IOException {
		file = File.createTempFile("tuenti", "tmp");
	}
	
	public HugeQueue(HugeQueue queue) throws IOException {
		file = new File(queue.getFile().getAbsolutePath());
	}
	
	protected File getFile() {
		return file;
	}
	
}