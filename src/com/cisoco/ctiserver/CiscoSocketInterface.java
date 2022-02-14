package com.cisoco.ctiserver;

import com.yespeech.tcpclient.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.cisoco.ctiserver.stdtypes.*;

public class CiscoSocketInterface {

	int htonl(int value) {
		  return ByteBuffer.allocate(4).putInt(value)
		    .order(ByteOrder.nativeOrder()).getInt(0);
		}
	

	
	public int CiscoCtiOpenReq( String host, int port ) throws IOException, InterruptedException
	{
		int CiscoResult = 0;
		int msg_length = 0;
		String data = null;
		TcpInterface _tcp = new TcpInterface( host, port );
		
		OpenReq open_req = new OpenReq();
		open_req.InvokeID = htonl(1);
		open_req.VersionNumber = htonl(14);
		open_req.IdleTimeout = 0xFFFFFFFF;
		open_req.PeripherealID = 0xFFFFFFFF;
		open_req.ServiceRequested = htonl( stdtypes.CTI_SERVICE_ALL_EVENTS | stdtypes.CTI_SERVICE_CALL_DATA_UPDATE );
		open_req.CallMsgMask = htonl( stdtypes.CALL_ESTABLISHED_MASK |
				stdtypes.CALL_HELD_MASK |
				stdtypes.CALL_RETRIEVED_MASK |
				stdtypes.CALL_CLEARED_MASK |
				stdtypes.CALL_CONNECTION_CLEARED_MASK |
				stdtypes.CALL_CONFERENCED_MASK |
				stdtypes.CALL_TRANSFERRED_MASK |
				stdtypes.BEGIN_CALL_MASK |
				stdtypes.END_CALL_MASK |
				stdtypes.RTP_STARTED_MASK |
				stdtypes.RTP_STOPPED_MASK );
		open_req.AgentStateMask = htonl ( stdtypes.AGENT_AVAILABLE_MASK | stdtypes.AGENT_TALKING_MASK );
		open_req.ConfigMsgMask = htonl( 0x1F );
		open_req.Reserved1 = 0;
		open_req.Reserved2 = 0;
		open_req.Reserved3 = 0;
		open_req.pFloatingAddr[0] = 1;
		open_req.pFloatingAddr[1] = 4;
		open_req.pFloatingAddr[2] = 'R';
		open_req.pFloatingAddr[3] = 'T';
		open_req.pFloatingAddr[4] = 'P';
		open_req.pFloatingAddr[5] = 0x00;
		open_req.pFloatingAddr[6] = 2;
		open_req.pFloatingAddr[7] = 1;
		open_req.pFloatingAddr[8] = 0x00;
		
		msg_length = open_req.getLength();
		System.out.println("-----------");

		open_req.MessageLength = htonl(msg_length);
		open_req.MessageType = htonl(stdtypes.MT_OPEN_REQ);
			
		_tcp.sendMessage(open_req.toString() );
		//_tcp.sendMessage(open_req);
		Thread.sleep(1000);
		
		return CiscoResult;
	}
}


