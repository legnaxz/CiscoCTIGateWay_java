package com.yespeech.tcpclient;


import java.net.Socket;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PingCheck {
	private static final Logger logger = LogManager.getLogger( PingCheck.class );
	private static PingCheck pingCheck = null;
		
		public static synchronized PingCheck getInstance() {
			if( pingCheck == null ) {
				pingCheck = new PingCheck();
			}
			
			return pingCheck;
		}
		
		public boolean getPingCheck( String host, int port ) {
			boolean result = false;
			 
			try {
			    (new Socket(host, port)).close();
			 
			    result = true;
			}
			catch(Exception e) {
			    
			}
			    return result;
		}
}
