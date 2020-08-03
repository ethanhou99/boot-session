package com.javainuse.service.Impl;



import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.stereotype.Service;

import com.javainuse.service.SessionService;
import com.javainuse.session.ActionType;
import com.javainuse.session.Session;
import com.javainuse.session.SessionManager;
import com.javainuse.session.SessionThread;

/**
 * @className: ServiceImpl
 * @Description: Implementation of session service
 * @author Yicun Hou
 */
@Service("Service")
public class ServiceImpl implements SessionService{
	private static ExecutorService executor = Executors.newFixedThreadPool(50);
	private final static String serverUrl = "http://localhost:8081/";
	
	/**
     * @Title: startSession
     * @Description: Start multiple sessions concurrently
     * @param num of sessions to start
     * @param lifetime of sessions
     * @return void
     */
	public void startSession(int num, int time) {
		for (int i = 0; i < num; i++) {
			Session session = new Session();
			session.setAction(ActionType.START);
			Thread thread = new Thread(new SessionThread(session, serverUrl));
			LocalDateTime startTime = session.getStartTime();
			session.setStopTime(startTime.plusMinutes(time));
			executor.submit(thread);
		}
	}
	
	/**
     * @Title: stopSessions
     * @Description: Stop a specific session
     * @param sessionId to stop the session
     * @return void
     */
	public void stopSession(int sessionId) {
		Session session = new Session();
		session.setAction(ActionType.STOP);
		session.setDeliverySessionId(sessionId);
		Thread thread = new Thread(new SessionThread(session, serverUrl));
		executor.submit(thread);
	}
	
	/**
     * @Title: stopAll
     * @Description: stop all sessions gracefully and exit program
     * @return void
     */
	public void stopAll() {
		SessionManager.getInstance();
		LocalDateTime max = LocalDateTime.now();
		if (SessionManager.map.size() > 0) {
			for (Session session : SessionManager.map.values()) {
				if (session.getStopTime().compareTo(max) > 0) {
					max = session.getStopTime();
				}
			}
		}
		while(true) {
			if (LocalDateTime.now().compareTo(max) > 0) {
				System.exit(0);
			}
		}
	}
	
	/**
     * @Title: adjustSessions
     * @Description: dynamically adjust session's lifetime
     * @param session id to adjust
     * @param minutes to add or minus
     * @return void
     */
	public void adjustSession(int id, int min) {
		SessionManager.getInstance();
		ConcurrentHashMap<Integer, Session> map = SessionManager.map;
		Session session = map.get(id);
		if (session != null) {
			LocalDateTime stopTime = session.getStopTime();
			session.setStopTime(stopTime.plusMinutes(min));
		}
	}
	
}

