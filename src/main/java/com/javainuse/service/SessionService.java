package com.javainuse.service;


/**
 * @InterfaceName: SessionService
 * @Description: Define the methods of session services
 * @author Yicun Hou
 */
public interface SessionService {

		void startSession(int num, int time);
		
		void stopSession(int sessionId);
		
		void stopAll();
		
		void adjustSession(int id, int min);
}


