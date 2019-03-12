package com.kishore5242.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kishore5242.tests.bean.Stream;
import com.kishore5242.util.SecurityUtil;

@Repository
public class StreamDaoImpl implements StreamDao {

	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public List<Stream> getAllStreams() {
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
        List<Stream> list= session.createCriteria(Stream.class)
        	.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
        	.list();
        return list;
	}

	@Override
	public void addStream(Stream stream) {
		Session session = sessionFactory.getCurrentSession();
		SecurityUtil.authenticateUser(stream.getUsername());
		session.save(stream);
	}

	@Override
	public void removeStream(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Stream p = (Stream) session.load(Stream.class, new Integer(id));
		SecurityUtil.authenticateUser(p.getUsername());
		if(null != p){
			session.delete(p);
		}
	}

	@Override
	public Stream getStream(int id) {
		Session session = sessionFactory.getCurrentSession();
		Stream stream = session.get(Stream.class, id);
		SecurityUtil.authenticateUser(stream.getUsername());
		return stream;
	}

	@Override
	public void updateStream(Stream stream) {
		Session session = sessionFactory.getCurrentSession();
		Stream s = session.get(Stream.class, stream.getStream_id());
		s.setStream_name(stream.getStream_name());
		SecurityUtil.authenticateUser(s.getUsername());
		session.update(s);
	}

	@Override
	public List<Stream> getAllStreamsByUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        
        Criteria criteria = session.createCriteria(Stream.class)
        		.add(Restrictions.eq("username", username))
            	.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
        
        @SuppressWarnings("unchecked")
        List<Stream> list= criteria.list();
        
        if(list.size()>0){
        	SecurityUtil.authenticateUser(list.get(0).getUsername());
        }
        
        return list;
	}

	@Override
	public void deleteStream(Integer stream_id) {
		Session session = this.sessionFactory.getCurrentSession();
		Stream t = (Stream) session.load(Stream.class, stream_id);
		SecurityUtil.authenticateUser(t.getUsername());
		if(null != t){
			session.delete(t);
		}
	}

}
