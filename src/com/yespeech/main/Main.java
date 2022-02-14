package com.yespeech.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.*;

import com.cisoco.ctiserver.CiscoEvent;
import com.yespeech.tcpclient.ConnectionInfo;

public class Main implements Runnable {
	private static final Logger logger = LogManager.getLogger(Main.class);
	private static CiscoEvent ciscoEvent;
	private static String CiscoGateway_VERSION = "1.0.1";
	String logMsg = null;
	static ConnectionInfo connectionInfo;
	
	public static void main(String[] args) {
		initLogger();
		
		connectionInfo = ConnectionInfo.getInstance();
		
		
		try {
			logger.info(CiscoGateway_VERSION);

			initShutDownHook();
			
			// CTI connection
			Thread daemonThread = new Thread(new Main());
			daemonThread.setDaemon(true);
			daemonThread.start();
			while (true) {
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			printThrowable(e);
		}
	}

	@Override
	public void run() {
		try {
			// connection start
			ciscoEvent = new CiscoEvent();
			ciscoEvent.StartCiscoCTI();
		} catch (IOException e) {
			printThrowable(e);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//
	private static void initShutDownHook() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {

				} catch (Exception ex) {
					logger.warn(printThrowable(ex));
				}

				logger.info("Exit Program ...");
			}

		});
	}

	protected static String printThrowable(Throwable e) {
		if (e == null) {
			return "";
		}
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return String.format("Error happened: %s\n%s", e.getMessage(), sw.toString());
	}

	private static void initLogger() {
		try {
			Configurator.initialize(null, "log4j2.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
