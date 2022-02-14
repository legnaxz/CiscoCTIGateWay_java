package com.yespeech.tcpclient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ini4j.Ini;

public class ConnectionInfo {
	private static final Logger logger = LogManager.getLogger(ConnectionInfo.class);

	private String configPath = "./config.ini";
	private static String agentConfigPath = "./agent_config.ini";
	
	private String CTIMode;
	private String CTIBackupHost;
	private int CTIBackupPort;
	
	//CTI_host
	private String CTIName;
	private String CTIHost;
	private int CTIPort;
	private String CTIHostBackup;
	private int CTIPortBackup;
	private int deviceType;
	
	//CTI_agent
	private String[] DN;
	
	//VAS1
	private String VASHost;
	private int VASPort;
	
	//VAS_backup
	private String VASHost_backup;
	private int VASPort_backup;
		
	
	private static ConnectionInfo connectionInfo = null;
	
	public static synchronized ConnectionInfo getInstance() {
		if( connectionInfo == null ) {
			connectionInfo = new ConnectionInfo();
			connectionInfo.getConfig();
		}
		
		return connectionInfo;
	}
	
	private void getConfig(){
		try{
			File configFile = new File(configPath);
			Ini properties = new Ini(configFile);
			
			this.CTIMode = properties.get( "CTI_GW_Backup", "mode" );
			this.CTIBackupHost = properties.get( "CTI_GW_Backup", "host" );
			this.CTIBackupPort = Integer.parseInt(properties.get( "CTI_GW_Backup", "port" ) );
			
			this.CTIName = properties.get( "CTI_host", "name" );
			
			this.CTIHost = properties.get( "CTI_host", "host" );
			this.CTIPort = Integer.parseInt( properties.get( "CTI_host", "port" ) );
			
			this.CTIHostBackup = properties.get( "CTI_host", "host_backup" );
			this.CTIPortBackup = Integer.parseInt( properties.get( "CTI_host", "port_backup" ) );
			
			String tempDN = properties.get( "CTI_agent", "DN" );
			
			this.DN = tempDN.split( ","  );

			this.VASHost = properties.get( "VAS", "host" );
			this.VASPort = Integer.parseInt(properties.get( "VAS", "port" ) );
			
			this.VASHost_backup = properties.get( "VAS_backup", "host" );
			this.VASPort_backup = Integer.parseInt(properties.get( "VAS_backup", "port" ) );
			
		}catch( FileNotFoundException fex ){
			logger.error( fex.getMessage() );
		}catch(IOException iex){
			logger.error( iex.getMessage() );
		}catch(Exception ex){
			logger.error( ex.getMessage() );
		}
	}

	public static String getAgentConfig( String DN ){
		String agentHost = "";
		
		try{
			File agentConfigFile = new File( agentConfigPath );
			Ini properties = new Ini( agentConfigFile );
			
			agentHost = properties.get( "DNs", DN );
			
		}catch( FileNotFoundException fex ){
			logger.error( fex.getMessage() );
		}catch( IOException iex){
			logger.error( iex.getMessage() );
		}catch( Exception ex ){
			logger.error( ex.getMessage() );
		}
		
		return agentHost;
	}
	public String getCTIMode() {
		return CTIMode;
	}
	
	public String getCTIBackupHost() {
		return CTIBackupHost;
	}
	
	public int getCTIBackupPort() {
		return CTIBackupPort;
	}
	
	public String getCTIName() {
		return CTIName;
	}

	public void setCTIName( String CTIName ) {
		this.CTIName = CTIName;
	}

	public String getCTIHost() {
		return CTIHost;
	}
	
	public int getCTIPort() {
		return CTIPort;
	}
	
	public String getCTIHostBackup() {
		return CTIHostBackup;
	}
	
	public int getCTIPortBackup() {
		return CTIPortBackup;
	}

	public int getDeviceType() {
		return deviceType;
	}

	public String getDN( int index ) {
		return DN[index];
	}
	
	public String[] getDNs(){
		return DN;
	}

	public String getConfigpath() {
		return configPath;
	}

	public String getVASHost() {
		return VASHost;
	}

	public int getVASPort() {
		return VASPort;
	}
	
	public String getVASHostBackup() {
		return VASHost_backup;
	}

	public int getVASPortBackup() {
		return VASPort_backup;
	}

	
}
