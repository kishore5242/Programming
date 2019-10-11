package com.kishore5242.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kishore5242.bean.Stream;
import com.kishore5242.dao.StreamDao;

@Service
@Transactional
public class StreamServiceImpl implements StreamService {
	
	@Autowired
	StreamDao streamDao;

	@Override
	public List<Stream> getAllStreams() {
		List<Stream> streams = streamDao.getAllStreams();
		return streams;
	}

	@Override
	public void createStream(Stream stream) {
		streamDao.addStream(stream);
	}

	@Override
	public void removeStream(int id) {
		streamDao.removeStream(id);
	}

	@Override
	public Stream getStream(int id) {
		return streamDao.getStream(id);
	}

	@Override
	public void updateStream(Stream stream) {
		streamDao.updateStream(stream);
	}

	@Override
	public List<Stream> getAllStreamsByUser(String username) {
		List<Stream> streams = streamDao.getAllStreamsByUser(username);
		return streams;
	}

	@Override
	public void deleteStream(Integer stream_id) {
		streamDao.deleteStream(stream_id);
		
	}

}
