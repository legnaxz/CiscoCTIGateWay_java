package com.yespeech.tcpclient;

import com.cisoco.ctiserver.stdtypes;
import com.cisoco.ctiserver.stdtypes.*;
import com.yespeech.tcpclient.*;

import java.io.OutputStream;
import java.io.DataOutputStream;

import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TcpInterface implements Runnable {
	private static final Logger logger = LogManager.getLogger( TcpInterface.class );

	static String _host;
	static int _port;
	
	private Socket socket;
	
    private InputStream reader;
    private OutputStream writer;

    private Thread runningThread;
    private boolean running;
    
    int htonl(int value) {
  	  return ByteBuffer.allocate(4).putInt(value)
  	    .order(ByteOrder.LITTLE_ENDIAN).getInt(0);
  	}
    
    int htonl(short value) {
  	  return ByteBuffer.allocate(2).putShort(value)
  	    .order(ByteOrder.LITTLE_ENDIAN).getShort(0);
  	}
    
    int nltoh(int value) {
  	  return ByteBuffer.allocate(4).putInt(value)
  	    .order(ByteOrder.BIG_ENDIAN).getInt(0);
  	}
    
    int nltoh(short value) {
  	  return ByteBuffer.allocate(2).putShort(value)
  	    .order(ByteOrder.BIG_ENDIAN).getShort(0);
  	}
    
    public static int convertByteArrayToInt(byte[] bytes) {
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8) |
                ((bytes[3] & 0xFF) << 0);
    }
    
    public static short convertByteArrayToShort(byte[] bytes) {
        return (short)(((bytes[0] & 0xFF) << 8) |((bytes[1] & 0xFF) << 0));
    }
  	
	public TcpInterface( String host, int port ) throws IOException {
		try {
            socket = new Socket(host, port);
            
            reader = new DataInputStream( socket.getInputStream() );
            writer = new DataOutputStream( socket.getOutputStream() );
            
            running = true;

            runningThread = new Thread(this);
            runningThread.start();
            
        } catch ( Exception e ) {
        	e.printStackTrace();
        	disconnect();
    	}
    }
	
	public void disconnect()
    {
        running = false;
        if( runningThread != null ) {
            runningThread.interrupt();
        }
        runningThread = null;

        try
        {
            reader.close();
        } catch ( Exception e ) {
        	
        }
        reader = null;

        try
        {
        	writer.close();
        } catch ( Exception e ){
        	
        }
        writer = null;
        try
        {
            socket.close();
        } catch ( Exception e ) {
        	
        }
        socket = null;
    }
	
	public void sendMessage(byte [] msg) throws IOException
    {   
		logger.info( "Send : [{}]", msg );
		if(running)
        {
			writer.write(msg);
			writer.flush();

        }
    }
	
	
    public void run()
    {
        try
        {
			String message = "";
			
			int mhdr_length = 4;
			int mhdr_type = 4;
			int body_size = 0;
			int body_type = 0;
			        
			byte[] recv_mhdr_length = new byte[mhdr_length];
			byte[] recv_mhdr_type = new byte[mhdr_type];
			byte[] recv_body;
			
	        reader.read( recv_mhdr_length, 0 , 4 );
	        logger.info("Mhdr length [{}]", convertByteArrayToInt(recv_mhdr_length ));
	        reader.read( recv_mhdr_type, 0 , 4 );
	        logger.info("Mhdr type [{}]", convertByteArrayToInt(recv_mhdr_type ));
	        body_size = convertByteArrayToInt( recv_mhdr_length );
	        body_type = convertByteArrayToInt( recv_mhdr_type );
	        recv_body = new byte[body_size];
	        reader.read( recv_body, 0 , body_size );
			logger.info( "Receive body : [{}], length [{}]", recv_body, body_size );

	        
	        if ( stdtypes.MT_OPEN_CONF == body_type ) {
	        	logger.info("OPEN_CONF is received! \n");
	    		OnOpenConf( recv_body, body_size );
	    
	        } else {
	        	if ( stdtypes.MT_FAILURE_CONF == body_type ) {
	        		logger.error("FAILURE_CONF is received! \n");
	        		OnFailureConf( recv_body, body_size );
	        	} else {
	        		logger.info("MHDR : MessageType [{}-{}] with length, {} is received! ", stdtypes.GetStringMessage(body_type), body_type, body_size );
	        	}
	        }
	         
        } catch(Exception e) {
        	e.printStackTrace();
        	disconnect();
        }
    }
    
    public void OnOpenConf( byte [] data, int size ) throws IOException {
    	
    	int start = 0;
    	int end = 4;
    	byte [] temp = Arrays.copyOfRange(data, start, end);
    	logger.info( "[{}]", temp);
    	int InvokeID = convertByteArrayToInt( temp );
    	start +=4;
    	end +=4;
    	int ServicesGranted= convertByteArrayToInt( Arrays.copyOfRange(data, start, end) );
    	start +=4;
    	end +=4;
    	int MonitorID= convertByteArrayToInt( Arrays.copyOfRange(data, start, end) );
    	start +=4;
    	end +=4;
    	int PGStatus= convertByteArrayToInt( Arrays.copyOfRange(data, start, end) );
    	start +=4;
    	end +=4;
    	int ICMCentralControllerTime= convertByteArrayToInt( Arrays.copyOfRange(data, start, end) );
    	start +=4;
    	end +=4;
    	short PeripheralOnline = convertByteArrayToShort( Arrays.copyOfRange(data, start, end) );
    	start +=2;
    	end +=2;
    	short PeripheralType= convertByteArrayToShort( Arrays.copyOfRange(data, start, end) );
    	start +=2;
    	end +=2;
    	short agentState= convertByteArrayToShort( Arrays.copyOfRange(data, start, end) );
    	start +=2;
    	end +=2;
    	int DepartmentID = convertByteArrayToInt( Arrays.copyOfRange(data, start, end) );
    	start +=4;
    	end +=4;
		// Floating
		byte [] pFloatingAddr = Arrays.copyOfRange(data, start, size - 1);
		
		logger.info( "{}/{}/{}/{}/{}/{}/{}/{}/{}/{}", InvokeID, ServicesGranted, MonitorID, PGStatus, ICMCentralControllerTime, PeripheralOnline, PeripheralType, agentState, DepartmentID, pFloatingAddr );
	}
    
	public void OnFailureConf( byte [] data, int size ) throws IOException {
		
	}
	
}
