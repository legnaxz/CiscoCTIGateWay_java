package com.cisoco.ctiserver;

import java.io.IOException;

import com.yespeech.tcpclient.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cisoco.ctiserver.MessageBuilder;
import com.cisoco.ctiserver.CiscoSocketInterface;

@SuppressWarnings("unused")
public class CiscoEvent{
	private static final Logger logger = LogManager.getLogger( CiscoEvent.class );
	
	static ConnectionInfo connectionInfo;
	static CiscoSocketInterface cisco_socket;
	static String _host;
	static int _port;
	static String _host_backup;
	static int _port_backup;
	static String _appname;
	static String[] _DNs;
	static int _devicetype;
	static boolean _doEvent = false;
	
	public CiscoEvent() {}
	
	public void StartCiscoCTI() throws IOException, InterruptedException {
		int CiscoResult = 0;
		
		cisco_socket = new CiscoSocketInterface();
		connectionInfo = ConnectionInfo.getInstance();
		boolean is_cti_opened = false;
		boolean is_cti_primary_second = false;
		_appname = connectionInfo.getCTIName();
		_host = connectionInfo.getCTIHost();
		_port = connectionInfo.getCTIPort();
		_host_backup = connectionInfo.getCTIHostBackup();
		_port_backup = connectionInfo.getCTIBackupPort();
		_DNs = connectionInfo.getDNs();
		
		logger.debug("DNs counts : [{}]", _DNs.length );
		
		
		//while ( false == is_cti_opened  ) {
			Thread.sleep( 1000 );
			if ( false == is_cti_primary_second )
			{
				CiscoResult = cisco_socket.CiscoCtiOpenReq( _host, _port );
			}
			
//			if ( false == is_cti_primary_second ) {
//				
//				NXResult = NXHandle.getInstance().api().openServer(_host + ":" + _port, _DNs[0], _appname );
//				if( NXResult != NXDef.Success ) {
//					logger.error("Openserver [{}] NXResult [{}] DN [[]} failed. ", _host + ":" + _port, NXResult, _DNs[0] );
//					is_cti_primary_second = true;
//					continue;
//				} else {
//					is_cti_opened = true;
//					break;
//				}
//			} else {
//				NXResult = NXHandle.getInstance().api().openServer( _host_backup + ":" + _port_backup, _DNs[0], _appname );
//				if( NXResult != NXDef.Success ) {
//					logger.error("Openserver [{}] NXResult [{}] DN [[]} failed. ", _host_backup + ":" + _port_backup, NXResult, _DNs[0] );
//					is_cti_primary_second = false;
//					continue;
//				} else {
//					is_cti_opened = true;
//					break;
//				}
//			}
			 
		//}
		
//		NXResult = NXHandle.getInstance().api().monitorStart();
//		logger.debug( "Monitor start NXResult [{}]", NXResult );
//		if( NXResult == NXDef.Success ) {
//			getEventThread = new NXGetEventThread();
//			getEventThread.start();
//		}
//		
//		NXResult = NXHandle.getInstance().api().addDevices( _DNs );
	
}
	
	static class NXGetEventThread extends Thread {
		private static final Logger looger = LogManager.getLogger( NXGetEventThread.class );
		
		private boolean onEvent = true;
		
		static ConnectionInfo connectionInfo = null;
		static PingCheck pingCheck = null;
		static MessageBuilder messageBuilder = new MessageBuilder();
		static String _VASHost = "";
		static int _VASPort = 0;
		static String _VASHostBackup = "";
		static int _VASPortBackup = 0;
		static String _NCTIMode = "";
		
		
		public void run() {
			
			connectionInfo = ConnectionInfo.getInstance();
			pingCheck = PingCheck.getInstance();
			_VASHost = connectionInfo.getVASHost();
			_VASPort = connectionInfo.getVASPort();
			_NCTIMode = connectionInfo.getCTIMode();
		
//			EventData _event = null;
//			
//			int NXResult = 0;
//			
//			
//			while ( onEvent ) {
//				if ( false != _NCTIMode.equals( "Active" ) ) {
//					_event = new EventData();
//					NXResult = NXHandle.getInstance().api().getEvent( _event );
//					
//					if ( NXResult == NXDef.cubeNoEvent ) {
//						try {
//							Thread.sleep( 100 );
//						} catch ( InterruptedException ie ) {
//							
//						}
//						continue;
//					}
//					
//					if ( NXResult != NXDef.Success ) {
//						logger.error( "NXGetEventThread Error .. ");
//						break;
//					}
//										
//					logger.debug( "NX Event id: [{}], Monitor : [{}]", _event.id, _event.monitor );
//					
//					String _event_type;
//					int _cid = _event.call.c1;
//					String _dt = _event.monitor;
//					String _agent_id = _event.agent.id;
//					String _agent_ip = connectionInfo.getAgentConfig( _event.monitor.toString() );
//					
//					switch ( _event.id ) {
//					case NXDef.cubeEK_Established:
//						_event_type = "S";
//						
//						Thread callStartThread = new Thread( new Runnable() {
//							@Override
//							public void run() {
//								String _build = messageBuilder.buildMessage( _event_type, _cid, _dt, _agent_id, _agent_ip );
//								new TcpClient( _build );
//								logger.info( "[CALL START] [{}]", _build );
//							}
//						});
//						callStartThread.start();
//						
//						break;
//					case NXDef.cubeEK_ConnectionCleared:
//						_event_type = "E";
//						
//						if( _event.monitor.equals(_event.call.other) ) {
//						Thread callEndThread = new Thread( new Runnable() {
//							@Override
//							public void run() {
//								String _build = messageBuilder.buildMessage( _event_type, _cid, _dt, _agent_id, _agent_ip );
//								new TcpClient( _build );
//								logger.info( "[CALL END] [{}]", _build );
//							}
//						});
//						callEndThread.start();
//						}
//						break;
//					case NXDef.cubeEK_Held:
//						_event_type = "H";
//						
//						Thread callHoldThread = new Thread( new Runnable() {
//							@Override
//							public void run() {
//								String _build = messageBuilder.buildMessage( _event_type, _cid, _dt, _agent_id, _agent_ip );
//								new TcpClient( _build );
//								logger.info( "[CALL HOLD] [{}]", _build );
//							}
//						});
//						callHoldThread.start();
//						
//						break;
//					case NXDef.cubeEK_Retrieved:
//						_event_type = "R";
//						
//						Thread callResumeThread = new Thread( new Runnable() {
//							@Override
//							public void run() {
//								String _build = messageBuilder.buildMessage( _event_type, _cid, _dt, _agent_id,_agent_ip );
//								new TcpClient( _build );
//								logger.info( "[CALL RESUME] [{}]", _build );
//							}
//						});
//						callResumeThread.start();
//						
//						break;
//					}
//				} else {
//					if(  false != pingCheck.getPingCheck ( connectionInfo.getCTIBackupHost(), connectionInfo.getCTIBackupPort() ) ) {
//						continue;
//					} else {
//						_event = new EventData();
//						NXResult = NXHandle.getInstance().api().getEvent( _event );
//						
//						if ( NXResult == NXDef.cubeNoEvent ) {
//							try {
//								Thread.sleep( 100 );
//							} catch ( InterruptedException ie ) {
//								
//							}
//							continue;
//						}
//						
//						if ( NXResult != NXDef.Success ) {
//							logger.error( "NXGetEventThread Error .. ");
//							break;
//						}
//						
//						logger.debug( "NX Event id: [{}], Monitor : [{}]", _event.id, _event.monitor );
//						
//						String _event_type;
//						int _cid = _event.call.c1;
//						String _dt = _event.monitor;
//						String _agent_id = _event.agent.id;
//						String _agent_ip = connectionInfo.getAgentConfig( _event.monitor.toString() );
//						
//						switch ( _event.id ) {
//						case NXDef.cubeEK_Established:
//							_event_type = "S";
//							
//							Thread callStartThread = new Thread( new Runnable() {
//								@Override
//								public void run() {
//									String _build = messageBuilder.buildMessage( _event_type, _cid, _dt, _agent_id, _agent_ip );
//									new TcpClient( _build );
//									logger.info( "[CALL START] [{}]", _build );
//								}
//							});
//							callStartThread.start();
//							
//							break;
//						case NXDef.cubeEK_ConnectionCleared:
//							_event_type = "E";
//							
//							if( _event.monitor.equals(_event.call.other) ) {
//							Thread callEndThread = new Thread( new Runnable() {
//								@Override
//								public void run() {
//									String _build = messageBuilder.buildMessage( _event_type, _cid, _dt, _agent_id, _agent_ip );
//									new TcpClient( _build );
//									logger.info( "[CALL END] [{}]", _build );
//								}
//							});
//							callEndThread.start();
//							}
//							break;
//						case NXDef.cubeEK_Held:
//							_event_type = "H";
//							
//							Thread callHoldThread = new Thread( new Runnable() {
//								@Override
//								public void run() {
//									String _build = messageBuilder.buildMessage( _event_type, _cid, _dt, _agent_id, _agent_ip );
//									new TcpClient( _build );
//									logger.info( "[CALL HOLD] [{}]", _build );
//								}
//							});
//							callHoldThread.start();
//							
//							break;
//						case NXDef.cubeEK_Retrieved:
//							_event_type = "R";
//							
//							Thread callResumeThread = new Thread( new Runnable() {
//								@Override
//								public void run() {
//									String _build = messageBuilder.buildMessage( _event_type, _cid, _dt, _agent_id, _agent_ip );
//									new TcpClient( _build );
//									logger.info( "[CALL RESUME] [{}]", _build );
//								}
//							});
//							callResumeThread.start();
//							
//							break;
//						}
//					} 
//				}
//			}
		}
	}
}