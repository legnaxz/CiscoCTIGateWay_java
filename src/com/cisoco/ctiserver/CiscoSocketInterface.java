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
		    .order(ByteOrder.LITTLE_ENDIAN).getInt(0);
		}
	
	int nltoh(int value) {
		  return ByteBuffer.allocate(4).putInt(value)
		    .order(ByteOrder.BIG_ENDIAN).getInt(0);
		}
	

	
	public int CiscoCtiOpenReq( String host, int port ) throws IOException, InterruptedException
	{
		int CiscoResult = 0;
		int msg_length = 0;
		String data = null;
		TcpInterface _tcp = new TcpInterface( host, port );
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		OpenReq open_req = new OpenReq();
		
		open_req.InvokeID = ByteBuffer.allocate(4).putInt( nltoh( 1 ) ).array();
		open_req.VersionNumber = ByteBuffer.allocate(4).putInt( nltoh( 14 ) ).array();
		open_req.IdleTimeout = ByteBuffer.allocate(4).putInt( nltoh( 0xFFFFFFFF ) ).array();
		open_req.PeripherealID = ByteBuffer.allocate(4).putInt( nltoh( 0xFFFFFFFF ) ).array();
		open_req.ServiceRequested = ByteBuffer.allocate(4).putInt( nltoh( stdtypes.CTI_SERVICE_ALL_EVENTS |
				stdtypes.CTI_SERVICE_CALL_DATA_UPDATE )).array();
		open_req.CallMsgMask = ByteBuffer.allocate(4).putInt( nltoh( stdtypes.CALL_ESTABLISHED_MASK |
				stdtypes.CALL_HELD_MASK |
				stdtypes.CALL_RETRIEVED_MASK |
				stdtypes.CALL_CLEARED_MASK |
				stdtypes.CALL_CONNECTION_CLEARED_MASK |
				stdtypes.CALL_CONFERENCED_MASK |
				stdtypes.CALL_TRANSFERRED_MASK |
				stdtypes.BEGIN_CALL_MASK |
				stdtypes.END_CALL_MASK |
				stdtypes.RTP_STARTED_MASK |
				stdtypes.RTP_STOPPED_MASK ) ).array();
		open_req.AgentStateMask = ByteBuffer.allocate(4).putInt( nltoh ( stdtypes.AGENT_AVAILABLE_MASK | stdtypes.AGENT_TALKING_MASK )).array();
		open_req.ConfigMsgMask = ByteBuffer.allocate(4).putInt( nltoh( 0x1F )).array();
		open_req.Reserved1 = ByteBuffer.allocate(4).putInt( nltoh( 0 ) ).array();
		open_req.Reserved2 = ByteBuffer.allocate(4).putInt( nltoh( 0 ) ).array();
		open_req.Reserved3 = ByteBuffer.allocate(4).putInt( nltoh( 0 ) ).array();
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

		open_req.MessageLength = ByteBuffer.allocate(4).putInt(htonl(msg_length)).array();
		open_req.MessageType = ByteBuffer.allocate(4).putInt(htonl(stdtypes.MT_OPEN_REQ)).array();
		
		output.write( open_req.MessageLength );
		output.write( open_req.MessageType );
		output.write( open_req.InvokeID );
		output.write( open_req.VersionNumber );
		output.write( open_req.IdleTimeout );
		output.write( open_req.PeripherealID );
		output.write( open_req.ServiceRequested );
		output.write( open_req.CallMsgMask );
		output.write( open_req.AgentStateMask );
		output.write( open_req.ConfigMsgMask );
		output.write( open_req.Reserved1 );
		output.write( open_req.Reserved2 );
		output.write( open_req.Reserved3 );
		output.write( open_req.pFloatingAddr );
		
		
		byte[] out = output.toByteArray();

		_tcp.sendMessage(out);

			
		//_tcp.sendMessage(open_req.toString() );
		//_tcp.sendMessage(open_req);
		Thread.sleep(1000);
		
		return CiscoResult;
	}
}


