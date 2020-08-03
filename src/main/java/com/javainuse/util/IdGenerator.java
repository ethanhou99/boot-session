package com.javainuse.util;

/**
 * @Name: IdGenerator
 * @Description: return session id for each session
 * @author Yicun Hou
 */
public class IdGenerator {
	private static Integer curId = 0;
	
	public static int generate() {
		synchronized(curId) {
			curId++;
			return curId;
		}
	}
}
