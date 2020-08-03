package com.javainuse.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDateTime;

/**
 * Using ConcurrentHashMap<sessionId, Session> to save all the active sessions.
 * @ClassName: SessionManager
 * @Description: Singleton class to manage all the active sessions
 * @author Yicun Hou
 */
public class SessionManager {
	private static SessionManager aliveSessions;
	
	public static ConcurrentHashMap<Integer, Session> map;
	
	private SessionManager() {
		map = new ConcurrentHashMap<>();
	}
	
	/**
	 * @Name: GetSessions
	 * @Description: return a list of Sessions to display active sessions to the client
	 * @author Yicun Hou
	 */
	public static List<Session> getSessions() {
		List<Session> result = new ArrayList<>();
		for (Map.Entry<Integer, Session> e : map.entrySet()) {
			if (e.getValue().getStopTime().compareTo(LocalDateTime.now()) > 0) {
				result.add(e.getValue());
			} else {
				map.remove(e.getKey());
			}
		}
		return result;
	}
	
	/**
	 * @Name: getInstance
	 * @Description: return a SessionManager Instance
	 * @author Yicun Hou
	 */
	public static SessionManager getInstance() {
		if (aliveSessions == null) {
			aliveSessions = new SessionManager();
		}
		return aliveSessions;
	}
}
