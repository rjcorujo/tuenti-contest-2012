package com.tuenti.contest.domain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class HugeReaderQueue extends HugeQueue {
	private BufferedReader reader;
	
	public HugeReaderQueue() throws IOException {
		super();
		initReader();
	}
	
	public HugeReaderQueue(HugeQueue queue) throws IOException {
		super(queue);
		initReader();
	}
	
	private void initReader() throws IOException {
		reader = new BufferedReader(new FileReader(getFile()));
	}

	public int getNext(char[] buffers, int size) throws IOException {
		return reader.read(buffers, 0, size);
	}

	public void close() throws IOException {
		if (reader != null) {
			reader.close();
		}
	}
	
	public void deleteAndClose() throws IOException {
		getFile().delete();
		close();
	}
	
	public String getMd5Sum() throws IOException {
		try {
			InputStream fis =  new FileInputStream(getFile().getAbsolutePath());
	
			byte[] buffer = new byte[10024];
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			int numRead;
	
	       do {
	           numRead = fis.read(buffer);
	           if (numRead > 0) {
	               md5.update(buffer, 0, numRead);
	           }
	       } while (numRead != -1);
	
	       fis.close();
	       BigInteger bigInt = new BigInteger(1,md5.digest());
	       return bigInt.toString(16);
		} catch (Exception e) {
			throw new RuntimeException("Error calculating md5 sum");
		}
	}

}
