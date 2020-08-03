package com.javainuse.session;

import java.time.LocalDateTime;

import com.javainuse.util.IdGenerator;

/**
 * @ClassName: Session
 * @Description: Define a Session class
 * @author Yicun Hou
 */
public class Session {
	private int deliverySessionId = IdGenerator.generate();
	private ActionType action;
	private String tmgiPool = "";
	private String tmgi = "";
	private LocalDateTime startTime = LocalDateTime.now();
	private LocalDateTime stopTime = LocalDateTime.now().plusMinutes(1);

	private String version = "v1.0";

	public Session() {

	}

	public Session(int deliverySessionId, ActionType action, String tmgiPool,
					  String tmgi, LocalDateTime startTime, LocalDateTime stopTime, String version) {
		this.deliverySessionId = deliverySessionId;
		this.action = action;
		this.tmgiPool = tmgiPool;
		this.tmgi = tmgi;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.version = version;
	}

	public int getDeliverySessionId() {
		return deliverySessionId;
	}

	public void setDeliverySessionId(int deliverySessionId) {
		this.deliverySessionId = deliverySessionId;
	}

	public ActionType getAction() {
		return action;
	}

	public void setAction(ActionType action) {
		this.action = action;
	}

	public String getTMGIPool() {
		return tmgiPool;
	}

	public void setTMGIPool(String tmgiPool) {
		this.tmgiPool = tmgiPool;
	}

	public String getTMGI() {
		return tmgi;
	}

	public void setTMGI(String tmgi) {
		this.tmgi = tmgi;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getStopTime() {
		return stopTime;
	}

	public void setStopTime(LocalDateTime stopTime) {
		this.stopTime = stopTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "Session{" +
				"deliverySessionId=" + deliverySessionId +
				", action=" + action +
				", tMGIPool='" + tmgiPool + '\'' +
				", tMGI='" + tmgi + '\'' +
				", startTime=" + startTime +
				", stopTime=" + stopTime +
				", version='" + version + '\'' +
				'}';
	}
}
