package com.yespeech.tcpclient;


import com.cisoco.ctiserver.stdtypes;
import com.cisoco.ctiserver.stdtypes.OpenReq;
import com.yespeech.tcpclient.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TcpInterface implements Runnable {
	//private static final Logger logger = LogManager.getLogger( TcpInterface.class );
	
	

	static String _host;
	static int _port;
	
	private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private ObjectOutputStream object_writer;

    private Thread runningThread;
    private boolean running;
	
	public TcpInterface( String host, int port ) throws IOException {
		try {
            socket = new Socket(host, port);
            writer = new PrintWriter(socket.getOutputStream());
            object_writer = new ObjectOutputStream( socket.getOutputStream());
            
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            running = true;

            runningThread = new Thread(this);
            runningThread.start();
        }catch(Exception e){e.printStackTrace(); disconnect();}
    }
	
	public void disconnect()
    {
        running = false;
        if(runningThread != null)
            runningThread.interrupt();
        runningThread = null;

        try
        {
            reader.close();
        }catch(Exception e){}
        reader = null;

        try
        {
            writer.close();
        }catch(Exception e){}
        writer = null;
        try
        {
            socket.close();
        }catch(Exception e){}
        socket = null;
    }
	
	public void sendMessage(String message)
    {
        if(running)
        {
            writer.println(message);
            writer.flush();
        }
    }
	
	int htonl(int value) {
		  return ByteBuffer.allocate(4).putInt(value)
		    .order(ByteOrder.nativeOrder()).getInt(0);
		}
	
	public void sendMessage(Object obj) throws IOException
    {   
		if(running)
        {
        	writer.println(obj);
        }
    }
	
	public void sendMessage(byte [] msg) throws IOException
    {   
		if(running)
        {
			object_writer.write( msg );
			object_writer.flush();

        }
    }
	
	
	public void sendMessage( Integer i) throws IOException
    {   
		if(running)
        {
			object_writer.write( i );
			object_writer.flush();
        }
    }
	

    public void run()
    {
        try
        {
			String message = "";
			boolean b = reader.ready();
			char[] rDataLen = new char[4];
			reader.read(rDataLen,0, 4);
			int lenth = Integer.parseInt(new String(rDataLen));
			char[] rContentTxt = new char[lenth];
			reader.read(rContentTxt,0 , lenth);
			String response= new String(rDataLen) + new String(rContentTxt);
			System.out.println(response);
        }catch(Exception e){e.printStackTrace(); disconnect();}
    }
	
//	public void Send( String msg ) throws IOException {
//		try {
//			String str = null;
//			
//			PrintWriter out  = new PrintWriter( _socket.getOutputStream() );
//			BufferedReader in  = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
//
//			out.print( msg );
//			out.flush();
//			
//            str = in.readLine();                //Client�κ��� �����͸� �о��
//
//            System.out.println("Client�� ���� �� �޼��� : " + str);
//			
//			out.close();
//			in.close();
//			_socket.close();
//			
//		} 
//		catch( Exception e ) {
//			logger.error( "Exception : [{}]", e );
//			_socket.close();
//		}
//
//
//	}
//	
//	public String Receive( ) {
//		String data = null;
//		try {
//			Socket _socket = new Socket();
//			_socket.connect( new InetSocketAddress( _host,  _port ) );
//				ObjectInputStream input = new ObjectInputStream ( _socket.getInputStream() );
//	
//				Packet recvPacket = (Packet)input.readObject();
//				System.out.println( recvPacket.message );
//				_socket.close();
//		}
//		 catch( Exception e ) {
//				logger.error( "Exception : [{}]", e );
//			}
//		return data;
//	}
}
