package com.kishore5242.service;

import java.util.List;

import com.kishore5242.tests.bean.Stream;

public interface StreamService {
	
	public void createStream(Stream stream);
	
	public List<Stream> getAllStreams();
	
	public void removeStream(int id);

	Stream getStream(int id);

	void updateStream(Stream stream);
	
	public List<Stream> getAllStreamsByUser(String username);

	public void deleteStream(Integer stream_id);

}
