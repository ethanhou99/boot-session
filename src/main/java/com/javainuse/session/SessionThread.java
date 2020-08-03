package com.javainuse.session;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

/**
 * @ClassName: SessionThread
 * @Description: Define the session thread to support multi-threading.
 * @author Yicun Hou
 */
public class SessionThread implements Runnable {
	
	private static Logger logger = Logger.getLogger(SessionThread.class);
	private Session session;
	private String serverUrl;
	private ConcurrentHashMap<Integer, Session> aliveSessions;

	public SessionThread(Session session, String serverUrl) {
		this.session = session;
		this.serverUrl = serverUrl;
		SessionManager.getInstance();
		this.aliveSessions = SessionManager.map;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		ActionType type = session.getAction();
		int sessionId = session.getDeliverySessionId();
		
		if (type == ActionType.START) {
			int responseCode = 0;
			try {
				responseCode = sendRequest();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (responseCode == 200) {
				synchronized (aliveSessions) {
					aliveSessions.put(sessionId, session);
				}
			}
		} else {
			int responseCode = 0;
			try {
				responseCode = sendRequest();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (responseCode == 200) {
				synchronized (aliveSessions) {
					aliveSessions.remove(sessionId);
				}
			}
		}
	}
	
    private int sendRequest() throws IOException{
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(serverUrl + session.getDeliverySessionId());
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.connect();
		} catch (IOException e) {
			logger.error("can not connect!");
			e.printStackTrace();
		}
		int code = connection.getResponseCode();

		if (code == 200) {
		   logger.info(url + "nbi/deliverysession?session_id=" + session.getDeliverySessionId()  + "  -200" + "  startTime:" + session.getStartTime() + "   body:" + session.toString());
		} else {
		   logger.error(url + "nbi/deliverysession?session_id=" + session.getDeliverySessionId()  + "-" + code);
		}
        return code;
    }
}
