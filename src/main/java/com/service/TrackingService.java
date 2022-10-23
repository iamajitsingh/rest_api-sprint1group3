package com.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.controlleradvice.TrackingNotFoundException;
import com.dao.TrackingDao;
import com.model.Tracking;

@Service
public class TrackingService {
	
	@Autowired
	TrackingDao trackingDao;
	
	public void addTracking(Tracking tracking) {
        Tracking tracking1 = new Tracking();
        tracking1.setStatus(tracking.getStatus());
        tracking1.setLocation(tracking.getLocation());
        tracking1.setTracking_Id(tracking.getTracking_Id());
        trackingDao.save(tracking1);
}

	public Tracking getDetails(int tracking_id) {
		return trackingDao.findById(tracking_id).get();
	}


}

