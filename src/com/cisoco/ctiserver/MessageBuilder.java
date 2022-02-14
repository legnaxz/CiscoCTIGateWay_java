package com.cisoco.ctiserver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class MessageBuilder {
	
	
	public static String buildMessage( String type, int cid, String dt, String agent_id, String agent_ip ) {
		String _current_time = CurrentDataTime();
		String _extension = dt;
		String _callkey = _current_time + "." + _extension;
		
		String result = String.format( "%-4s", "V110" ) +
				String.format( "%-6s", "490" ) +
				String.format( "%-14s", _current_time ) +
				String.format( "%-1s", "3" ) +
				String.format( "%-1s", type ) +
				String.format( "%-20s", cid ) +
				String.format( "%-30s", _callkey ) +
				String.format( "%-30s", _callkey ) +
				String.format( "%-6s", dt ) +
				String.format( "%-10s", agent_id ) +
				String.format( "%-15s", agent_ip ) +
				String.format( "%-20s", "00001111222233334444" ) +
				String.format( "%-1s", "I" ) +
				String.format( "%-20s", "55556666777788889999" ) +
				String.format( "%-1s", "Y" ) +
				String.format( "%-1s", "Y" ) +
				String.format( "%-1s", "P" ) +
				String.format( "%-319s", "" );
				
		return result;
	}
	
	public static String CurrentDataTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		
		return now.format( formatter );
		
	}
}
