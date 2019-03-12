package com.kishore5242.dao;

import java.util.List;

import com.kishore5242.tests.bean.Stream;

public interface StreamDao {

	List<Stream> getAllStreams();

	void addStream(Stream stream);
	
	void removeStream(int id);

	Stream getStream(int id);
	
	void updateStream(Stream stream);

	List<Stream> getAllStreamsByUser(String username);

	void deleteStream(Integer stream_id);

}
