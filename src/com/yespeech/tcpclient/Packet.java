package com.yespeech.tcpclient;

import java.io.Serializable;

public class Packet implements Serializable {
	String message = null;
	
	public Packet( String message ) {
		this.message = message;
	}

}
