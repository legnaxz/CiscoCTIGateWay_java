package com.cisoco.ctiserver;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class stdtypes {

	public static final int  CTI_SERVICE_DEBUG					=0x80000000;	// Causes all messages exchanged during the current session to be captured to a file for later analysis.
	public static final int  CTI_SERVICE_CLIENT_EVENTS			=0x00000001;	// Client receives call and agent state change events associated with a specific ACD phone.
	public static final int  CTI_SERVICE_CALL_DATA_UPDATE		=0x00000002;	// Client may modify call context data.
	public static final int  CTI_SERVICE_CLIENT_CONTROL			=0x00000004;	// Client may control calls and agent states associated with a specific ACD phone.
	public static final int  CTI_SERVICE_CONNECTION_MONITOR		=0x00000008;	// Establishment and termination of this session cause corresponding Unified ICM Alarm events to be generated.
	public static final int  CTI_SERVICE_ALL_EVENTS				=0x00000010;	// Client receives all call and agent state change events (associated with any ACD phone).
	public static final int  CTI_SERVICE_PERIPHERAL_MONITOR		=0x00000020;	// Client may dynamically add and remove devices and/or calls that it wishes to receive call and agent state events for.
	public static final int  CTI_SERVICE_CLIENT_MONITOR			=0x00000040;	// Client receives notification when all other CTI client sessions are opened and closed, and may monitor the activity of other CTI client sessions.
	public static final int  CTI_SERVICE_SUPERVISOR				=0x00000080;	// Client may request supervisor services.
	public static final int  CTI_SERVICE_SERVER					=0x00000100;	// Client identify itself as server application.
	public static final int  CTI_SERVICE_AGENT_REPORTING		=0x00000400;	// Client may reporting/routing ARM(Agent Reporting And Management) messages.
	public static final int  CTI_SERVICE_ALL_TASK_EVENTS		=0x00000800;	// Client receives all task events.
	public static final int  CTI_SERVICE_TASK_MONITOR			=0x00001000;	// Client receives monitored task events.
	public static final int  CTI_AGENT_STATE_CONTROL_ONLY		=0x00002000;	// Client can change agent state only. Call control is not allowed. If a client requests for CTI_SERVICE_ CLIENT_CONTROL, the server may grant this flag to indicate that only agent state change is allowed.
	
	// Unused	=0x00004000;
	
	public static final int  CTI_DEVICE_STATE_CONTROL			=0x00008000;	// The client/server wishes to register and get resource state change requests
	public static final int  CTI_SERVICE_UPDATE_EVENTS			=0x00080000;	// Requests that this client receive update notification events. (No data)
	public static final int  CTI_SERVICE_IGNORE_DUPLICATE_AGENT_EVENTS	=0x00100000;	// Request to suppress duplicate agent state events.
	public static final int  CTI_SERVICE_IGNORE_CONF			=0x00200000;	// Do not send confirmations for third party requests.
	public static final int  CTI_SERVICE_ACD_LINE_ONLY			=0x00400000;	// Request that events for non-ACD lines not be sent. (Unified CCE only)
	
	
	/////////////////////////////////////////////////////////////////
	// Unsolicited Call Event Message Mask
	/////////////////////////////////////////////////////////////////
	
	public static final int  CALL_DELIVERED_MASK				=0x00000001;	// Set when client wishes to receive CALL_DELIVERED_MASK EVENT messages.
	public static final int  CALL_QUEUED_MASK					=0x00000002;	// Set when client wishes to receive CALL_QUEUED_MASK EVENT messages.
	public static final int  CALL_ESTABLISHED_MASK				=0x00000004;	// Set when client wishes to receive CALL_ESTABLISHED_MASK EVENT messages.
	public static final int  CALL_HELD_MASK						=0x00000008;	// Set when client wishes to receive CALL_HELD_MASK EVENT messages.
	public static final int  CALL_RETRIEVED_MASK				=0x00000010;	// Set when client wishes to receive CALL_RETRIEVED_MASK EVENT messages.
	public static final int  CALL_CLEARED_MASK					=0x00000020;	// Set when client wishes to receive CALL_CLEARED_MASK EVENT messages.
	public static final int  CALL_CONNECTION_CLEARED_MASK		=0x00000040;	// Set when client wishes to receive CALL_CONNECTION_CLEARED_MASK EVENT messages.
	public static final int  CALL_ORIGINATED_MASK				=0x00000080;	// Set when client wishes to receive CALL_ ORIGINATED_MASK EVENT messages.
	public static final int  CALL_CONFERENCED_MASK				=0x00000100;	// Set when client wishes to receive CALL_CONFERENCED_MASK EVENT messages.
	public static final int  CALL_TRANSFERRED_MASK				=0x00000200;	// Set when client wishes to receive CALL_ TRANSFERRED_MASK EVENT messages.
	public static final int  CALL_DIVERTED_MASK					=0x00000400;	// Set when client wishes to receive CALL_DIVERTED_MASK EVENT messages.
	public static final int  CALL_SERVICE_INITIATED_MASK		=0x00000800;	// Set when client wishes to receive CALL_SERVICE_INITIATED_MASK EVENT messages.
	public static final int  CALL_TRANSLATION_ROUTE_MASK		=0x00001000;	// Set when client wishes to receive CALL_ TRANSLATION_ROUTE_MASK EVENT messages.
	public static final int  BEGIN_CALL_MASK					=0x00002000;	// Set when client wishes to receive BEGIN_CALL_MASK EVENT messages.
	public static final int  END_CALL_MASK						=0x00004000;	// Set when client wishes to receive END_CALL_MASK EVENT messages.
	public static final int  CALL_DATA_UPDATE_MASK				=0x00008000;	// Set when client wishes to receive CALL_DATA_UPDATE_MASK EVENT messages.
	public static final int  CALL_FAILED_MASK					=0x00010000;	// Set when client wishes to receive CALL_FAILED_MASK EVENT messages.
	public static final int  CALL_REACHED_NETWORK_MASK			=0x00020000;	// Set when client wishes to receive CALL_REACHED_NETWORK_MASK EVENT messages.
	public static final int  CALL_DEQUEUED_MASK					=0x00040000;	// Set when client wished to receive CALL_DEQUEUED_MASK EVENT messages.
	public static final int  AGENT_PRE_CALL_MASK				=0x00080000;	// Set when client wished to receive AGENT_PRE_CALL_MASK EVENT messages.
	public static final int  AGENT_PRE_CALL_ABORT_MASK			=0x00100000;	// Set when client wished to receive AGENT_PRE_CALL_ABORT_MASK EVENT messages.
	public static final int  RTP_STARTED_MASK					=0x00200000;	// Set when client wished to receive RTP_STARTED_MASK EVENT messages.
	public static final int  RTP_STOPPED_MASK					=0x00400000;	// Set when client wished to receive RTP_STOPPED_MASK EVENT messages.
	public static final int  AGENT_TEAM_CONFIG_MASK				=0x00800000;	// Set when client wished to receive AGENT_TEAM_CONFIG_MASK EVENT messages.
	public static final int  AGENT_LEGACY_PRE_CALL_MASK			=0x01000000;	// Set when client wished to receive AGENT_LEGACY_PRE_CALL_MASK EVENT messages.
	public static final int  CALL_ATTRIBUTE_CHANGE_MASK			=0x02000000;	// CALL_ATTRIBUTE_CHANGE_MASK EVENT messages.
	
	
	/////////////////////////////////////////////////////////////////
	// Agent State Mask
	/////////////////////////////////////////////////////////////////
	
	public static final int  AGENT_LOGIN_MASK					=0x00000001;	// Set when client wishes to receive ��login�� AGENT_STATE_MASK EVENT messages.
	public static final int  AGENT_LOGOUT_MASK					=0x00000002;	// Set when client wishes to receive ��logout�� AGENT_STATE_MASK EVENT messages.
	public static final int  AGENT_NOT_READY_MASK				=0x00000004;	// Set when client wishes to receive ��not ready�� AGENT_STATE_MASK EVENT messages.
	public static final int  AGENT_AVAILABLE_MASK				=0x00000008;	// Set when client wishes to receive ��available�� AGENT_STATE_MASK EVENT messages.
	public static final int  AGENT_TALKING_MASK					=0x00000010;	// Set when client wishes to receive ��talking�� AGENT_STATE_MASK EVENT messages.
	public static final int  AGENT_WORK_NOT_READY_MASK			=0x00000020;	// Set when client wishes to receive ��work not ready�� AGENT_STATE_MASK EVENT messages.
	public static final int  AGENT_WORK_READY_MASK				=0x00000040;	// Set when client wishes to receive ��work ready�� AGENT_STATE_MASK EVENT messages.
	public static final int  AGENT_BUSY_OTHER_MASK				=0x00000080;	// Set when client wishes to receive ��busy other�� AGENT_STATE_MASK EVENT messages.
	public static final int  AGENT_RESERVED_MASK				=0x00000100;	// Set when client wishes to receive ��reserved�� AGENT_STATE_MASK EVENT messages.
	public static final int  AGENT_HOLD_MASK					=0x00000200;	// Set when client wishes to receive ��hold�� AGENT_STATE_MASK EVENT messages.


	public static final int  MT_FAILURE_CONF					=1;	// Negative confirmation; may be sent in response to any request.
	public static final int  MT_FAILURE_EVENT					=2;	// Unsolicited notification of a failure or error.
	public static final int  MT_OPEN_REQ						=3;	// Communication session establishment request.
	public static final int  MT_OPEN_CONF						=4;	// Communication session establishment confirmation.
	public static final int  MT_HEARTBEAT_REQ					=5;	// Communication session maintenance request.
	public static final int  MT_HEARTBEAT_CONF					=6;	// Communication session maintenance confirmation.
	public static final int  MT_CLOSE_REQ						=7;	// Communication session termination request.
	public static final int  MT_CLOSE_CONF						=8;	// Communication session termination confirmation.
	public static final int  MT_CALL_DELIVERED_EVENT			=9;	// Notification of inbound call arrival.
	public static final int  MT_CALL_ESTABLISHED_EVENT			=10;	// Notification of answering of inbound call.
	public static final int  MT_CALL_HELD_EVENT					=11;	// Notification of call placed on hold.
	public static final int  MT_CALL_RETRIEVED_EVENT			=12;	// Notification of call taken off hold.
	public static final int  MT_CALL_CLEARED_EVENT				=13;	// Notification of call termination.
	public static final int  MT_CALL_CONNECTION_CLEARED_EVENT	=14;	// Notification of the termination of a conference party connection.
	public static final int  MT_CALL_ORIGINATED_EVENT			=15;	// Notification of outbound call initiation.
	public static final int  MT_CALL_FAILED_EVENT				=16;	// Notification of inability to complete call.
	public static final int  MT_CALL_CONFERENCED_EVENT			=17;	// Notification of tandem connection of two calls.
	public static final int  MT_CALL_TRANSFERRED_EVENT			=18;	// Notification of call transfer.
	public static final int  MT_CALL_DIVERTED_EVENT				=19;	// Notification of call changing to a different service.
	public static final int  MT_CALL_SERVICE_INITIATED_EVENT	=20;	// Notification of the initiation of telecommunications service at a device (��dial-tone��).
	public static final int  MT_CALL_QUEUED_EVENT				=21;	// Notification of call being placed in a queue pending the availability of some resource.
	public static final int  MT_CALL_TRANSLATION_ROUTE_EVENT	=22;	// Notification of call context data for a call that has been routed to the peripheral via a translation route.
	public static final int  MT_BEGIN_CALL_EVENT				=23;	// Notification that a call has been associated with the CTI client.
	public static final int  MT_END_CALL_EVENT					=24;	// Notification that a call is no longer associated with a CTI client.
	public static final int  MT_CALL_DATA_UPDATE_EVENT			=25;	// Notification of a change in a call��s context data.
	public static final int  MT_SET_CALL_DATA_REQ				=26;	// Request to update one or more call variables or call wrap-up data.
	public static final int  MT_SET_CALL_DATA_CONF				=27;	// Response confirming a previous SET_CALL_DATA request.
	public static final int  MT_RELEASE_CALL_REQ				=28;	// Notification that all call data updates are complete.
	public static final int  MT_RELEASE_CALL_CONF				=29;	// Response confirming a previous RELEASE_CALL request.
	public static final int  MT_AGENT_STATE_EVENT				=30;	// Notification of new agent state.
	public static final int  MT_SYSTEM_EVENT					=31;	// Notification of a PG Status change.
	public static final int  MT_CLIENT_EVENT_REPORT_REQ			=32;	// Request to report a CTI client event.
	public static final int  MT_CLIENT_EVENT_REPORT_CONF		=33;	// Response confirming a previous CLIENT_EVENT_REPORT request.
	public static final int  MT_CALL_REACHED_NETWORK_EVENT		=34;	// Notification of outbound call being connected to the network.
	public static final int  MT_CONTROL_FAILURE_CONF			=35;	// Response indicating the failure of a proceeding control request.
	public static final int  MT_QUERY_AGENT_STATE_REQ			=36;	// Request to obtain the current state of an agent position.
	public static final int  MT_QUERY_AGENT_STATE_CONF			=37;	// Response to a QUERY_AGENT_ STATE request.
	public static final int  MT_SET_AGENT_STATE_REQ				=38;	// Request to alter the current state of an agent position.
	public static final int  MT_SET_AGENT_STATE_CONF			=39;	// Response confirming a previous SET_AGENT_STATE request.
	public static final int  MT_ALTERNATE_CALL_REQ				=40;	// Request to alternate between a held and an active call.
	public static final int  MT_ALTERNATE_CALL_CONF				=41;	// Response confirming a previous ALTERNATE_CALL request.
	public static final int  MT_ANSWER_CALL_REQ					=42;	// Request to answer an alerting call.
	public static final int  MT_ANSWER_CALL_CONF				=43;	// Response confirming a previous ANSWER_CALL request.
	public static final int  MT_CLEAR_CALL_REQ					=44;	// Request to release all devices from a call.
	public static final int  MT_CLEAR_CALL_CONF					=45;	// Response confirming a previous CLEAR_CALL request.
	public static final int  MT_CLEAR_CONNECTION_REQ			=46;	// Request to release a single device from a call.
	public static final int  MT_CLEAR_CONNECTION_CONF			=47;	// Response confirming a previous CLEAR_CONNECTION request.
	public static final int  MT_CONFERENCE_CALL_REQ				=48;	// Request to conference a held call with an active call.
	public static final int  MT_CONFERENCE_CALL_CONF			=49;	// Response confirming a previous CONFERENCE_CALL request.
	public static final int  MT_CONSULTATION_CALL_REQ			=50;	// Request to hold an active call and initiate a new call.
	public static final int  MT_CONSULTATION_CALL_CONF			=51;	// Response confirming a previous CONSULTATION_CALL request.
	public static final int  MT_DEFLECT_CALL_REQ				=52;	// Request to move an alerting call to a different device.
	public static final int  MT_DEFLECT_CALL_CONF				=53;	// Response confirming a previous DEFLECT_CALL request.
	public static final int  MT_HOLD_CALL_REQ					=54;	// Request to place a call connection in the held state.
	public static final int  MT_HOLD_CALL_CONF					=55;	// Response confirming a previous HOLD_CALL request.
	public static final int  MT_MAKE_CALL_REQ					=56;	// Request to initiate a new call between two devices.
	public static final int  MT_MAKE_CALL_CONF					=57;	// Response confirming a previous MAKE_CALL request.
	public static final int  MT_MAKE_PREDICTIVE_CALL_REQ		=58;	// Request to initiate a new predictive call.
	public static final int  MT_MAKE_PREDICTIVE_CALL_CONF		=59;	// Response confirming a previous MAKE_PREDICTIVE_CALL request.
	public static final int  MT_RECONNECT_CALL_REQ				=60;	// Request to clear a connection and retrieve a held call.
	public static final int  MT_RECONNECT_CALL_CONF				=61;	// Response confirming a previous RECONNECT_CALL request.
	public static final int  MT_RETRIEVE_CALL_REQ				=62;	// Request to reconnect a held call.
	public static final int  MT_RETRIEVE_CALL_CONF				=63;	// Response confirming a previous RETRIEVE_CALL request.
	public static final int  MT_TRANSFER_CALL_REQ				=64;	// Request to transfer a held call to an active call.
	public static final int  MT_TRANSFER_CALL_CONF				=65;	// Response confirming a previous TRANSFER_CALL request.
	
	// (reserved)	=66;- =77;
	
	public static final int  MT_QUERY_DEVICE_INFO_REQ			=78;	// Request to obtain general device information.
	public static final int  MT_QUERY_DEVICE_INFO_CONF			=79;	// Response to a previous QUERY_DEVICE_INFO request.
	
	// (reserved)	=80;-=81;
	
	public static final int  MT_SNAPSHOT_CALL_REQ				=82;	// Request to obtain information about a specified call.
	public static final int  MT_SNAPSHOT_CALL_CONF				=83;	// Response to a previous SNAPSHOT_CALL request.
	public static final int  MT_SNAPSHOT_DEVICE_REQ				=84;	// Request to obtain information about a specified device.
	public static final int  MT_SNAPSHOT_DEVICE_CONF			=85;	// Response to a previous SNAPSHOT_DEVICE request.
	public static final int  MT_CALL_DEQUEUED_EVENT				=86;	// Notification of call being removed from a queue.
	
	// (reserved)	=87;- =90;
	
	public static final int  MT_SEND_DTMF_SIGNAL_REQ			=91;	// Request to transmit a sequence of DTMF tones.
	public static final int  MT_SEND_DTMF_SIGNAL_CONF			=92;	// Response to a previous SEND_ DTMF_SIGNAL_REQ request.
	public static final int  MT_MONITOR_START_REQ				=93;	// Request to initiate monitoring of a given call or device.
	public static final int  MT_MONITOR_START_CONF				=94;	// Response to a previous MONITOR_START request.
	public static final int  MT_MONITOR_STOP_REQ				=95;	// Request to terminate monitoring of a given call or device.
	public static final int  MT_MONITOR_STOP_CONF				=96;	// Response to a previous MONITOR_STOP request.
	public static final int  MT_CHANGE_MONITOR_MASK_REQ			=97;	// Request to change the message masks of a given call or device monitor.
	public static final int  MT_CHANGE_MONITOR_MASK_CONF		=98;	// Response to a previous CHANGE_ MONITOR_MASK request.
	public static final int  MT_CLIENT_SESSION_OPENED_EVENT		=99;	// Notification that a new CTI Client session has been opened.
	public static final int  MT_CLIENT_SESSION_CLOSED_EVENT		=100;	// Notification that a CTI Client session has been terminated.
	public static final int  MT_SESSION_MONITOR_START_REQ		=101;	// Request to initiate monitoring of a given CTI Client session.
	public static final int  MT_SESSION_MONITOR_START_CONF		=102;	// Response to a previous SESSION_ MONITOR_START request.
	public static final int  MT_SESSION_MONITOR_STOP_REQ		=103;	// Request to terminate monitoring of a given CTI Client session.
	public static final int  MT_SESSION_MONITOR_STOP_CONF		=104;	// Response to a previous SESSION_ MONITOR_STOP request.
	public static final int  MT_AGENT_PRE_CALL_EVENT			=105;	// Advance notification of a call routed to an Enterprise Agent.
	public static final int  MT_AGENT_PRE_CALL_ABORT_EVENT		=106;	// Cancellation of advance notification of a call routed to an Enterprise Agent.
	public static final int  MT_USER_MESSAGE_REQ				=107;	// Request to send a message to other CTI Server client(s).
	public static final int  MT_USER_MESSAGE_CONF				=108;	// Response to a previous USER_MESSAGE_REQ request.
	public static final int  MT_USER_MESSAGE_EVENT				=109;	// Notification of a message sent by another CTI Server client.
	public static final int  MT_REGISTER_VARIABLES_REQ			=110;	// Request to register call context variables used by application.
	public static final int  MT_REGISTER_VARIABLES_CONF			=111;	// Response to a previous REGISTER_VARIABLES_REQ request.
	public static final int  MT_QUERY_AGENT_STATISTICS_REQ		=112;	// Request for current agent call handling statistics.
	public static final int  MT_QUERY_AGENT_STATISTICS_CONF		=113;	// Response to a previous QUERY_AGENT_STATISTICS_REQ request.
	public static final int  MT_QUERY_SKILL_GROUP_STATISTICS_REQ	=114;	// Request for current skill group call handling statistics.
	public static final int  MT_QUERY_SKILL_GROUP_STATISTICS_CONF	=115;	// Response to a previous QUERY_SKILL_GROUP_ STATISTICS_REQ request.
	public static final int  MT_RTP_STARTED_EVENT				=116;	// Indicates that a RTP input has been started
	public static final int  MT_RTP_STOPPED_EVENT				=117;	// Indicates that a RTP input has been stopped
	public static final int  MT_SUPERVISOR_ASSIST_REQ			=118;	// An agent requests for assistance to their supervisor.
	public static final int  MT_SUPERVISOR_ASSIST_CONF			=119;	// Response to a previous SUPERVISOR_ASSIST_REQ request.
	public static final int  MT_SUPERVISOR_ASSIST_EVENT			=120;	// Notification of a supervisor assist request sent by a CTI Server client.
	public static final int  MT_EMERGENCY_CALL_REQ				=121;	// An agent declaring an emergency situation to their supervisor.
	public static final int  MT_EMERGENCY_CALL_CONF				=122;	// Response to a previous EMERGENCY_CALL_REQ request.
	public static final int  MT_EMERGENCY_CALL_EVENT			=123;	// Notification of an emergency call request sent by a CTI Server client.
	public static final int  MT_SUPERVISE_CALL_REQ				=124;	// A supervisor request to perform monitor or barge-in operations.
	public static final int  MT_SUPERVISE_CALL_CONF				=125;	// Response to a previous SUPERVISE_CALL_REQ request.
	public static final int  MT_AGENT_TEAM_CONFIG_REQ			=126;	// Request to change temporary agent team configuration.
	public static final int  MT_AGENT_TEAM_CONFIG_CONF			=127;	// Response to a previous AGENT_TEAM_CONFIG_REQ request.
	public static final int  MT_AGENT_TEAM_CONFIG_EVENT			=128;	// Notification of passing the team member list
	public static final int  MT_SET_APP_DATA_REQ				=129;	// Request to update one or more application variables.
	public static final int  MT_SET_APP_DATA_CONF				=130;	// Response confirming a previous SET_APP_DATA request.
	public static final int  MT_AGENT_DESK_SETTINGS_REQ			=131;	// Request to obtain Agent Desk Settings.
	public static final int  MT_AGENT_DESK_SETTINGS_CONF		=132;	// Response to a previous AGENT_DESK_SETTINGS_REQ request.
	public static final int  MT_LIST_AGENT_TEAM_REQ				=133;	// Request to obtain a list of Agent Teams.
	public static final int  MT_LIST_AGENT_TEAM_CONF			=134;	// Response to a previous LIST_AGENT_TEAM_REQ request.
	public static final int  MT_MONITOR_AGENT_TEAM_START_REQ	=135;	// Request to start monitoring an Agent Team.
	public static final int  MT_MONITOR_AGENT_TEAM_START_CONF	=136;	// Response to a previous MONITOR_ AGENT_TEAM_START_REQ request.
	public static final int  MT_MONITOR_AGENT_TEAM_STOP_REQ		=137;	// Request to stop monitoring an Agent Team.
	public static final int  MT_MONITOR_AGENT_TEAM_STOP_CONF	=138;	// Response to a previous MONITOR_ AGENT_TEAM_STOP_REQ request.
	public static final int  MT_BAD_CALL_REQ					=139;	// Request to mark a call as having poor voice quality.
	public static final int  MT_BAD_CALL_CONF					=140;	// Response to a previous BAD_CALL_REQ request.
	public static final int  MT_SET_DEVICE_ATTRIBUTES_REQ		=141;	// Request to set the default attributes of a calling device.
	public static final int  MT_SET_DEVICE_ATTRIBUTES_CONF		=142;	// Response to a previous SET_DEVICE_ ATTRIBUTES_REQ request.
	public static final int  MT_REGISTER_SERVICE_REQ			=143;	// Request to register service for the server application.
	public static final int  MT_REGISTER_SERVICE_CONF			=144;	// Response to a previous REGISTER_SERVICE_REQ request.
	public static final int  MT_UNREGISTER_SERVICE_REQ			=145;	// Request to unregister service for the server application.
	public static final int  MT_UNREGISTER_SERVICE_CONF			=146;	// Response to a previous UNREGISTER_SERVICE_REQ request.
	public static final int  MT_START_RECORDING_REQ				=147;	// Request to start recording.
	public static final int  MT_START_RECORDING_CONF			=148;	// Response to a previous START_RECORDING_REQ request.
	public static final int  MT_STOP_RECORDING_REQ				=149;	// Request to stop recording
	public static final int  MT_STOP_RECORDING_CONF				=150;	// Response to a previous STOP_RECORDING_REQ request.
	public static final int  MT_MEDIA_LOGIN_REQ					=151;	// Report agent login to MRD.
	public static final int  MT_MEDIA_LOGIN_RESP				=152;	// Response to MEDIA_LOGIN_REQ.
	public static final int  MT_MEDIA_LOGOUT_IND				=153;	// Report agent logout from MRD.
	public static final int  MT_MAKE_AGENT_ROUTABLE_IND			=154;	// Make agent routable for MRD request.
	public static final int  MT_MAKE_AGENT_NOT_ROUTABLE_REQ		=155;	// Make agent not routable for MRD request.
	public static final int  MT_MAKE_AGENT_NOT_ROUTABLE_RESP	=156;	// Response to MAKE_AGENT_NOT_ROUTABLE_REQ.
	public static final int  MT_MAKE_AGENT_READY_IND			=157;	// Report agent made ready.
	public static final int  MT_MAKE_AGENT_NOT_READY_REQ		=158;	// Report agent made not ready.
	public static final int  MT_MAKE_AGENT_NOT_READY_RESP		=159;	// Response to MAKE_AGENT_NOT_READY_REQ.
	public static final int  MT_OFFER_TASK_IND					=160;	// Report agent has been offered task, agent selected by Unified ICM.
	public static final int  MT_OFFER_APPLICATION_TASK_REQ		=161;	// Report agent has been offered task, agent not selected by Unified ICM.
	public static final int  MT_OFFER_APPLICATION_TASK_RESP		=162;	// Response to OFFER_APPLICATION_TASK_REQ.
	public static final int  MT_START_TASK_IND					=163;	// Report agent has begun task, agent selected by Unified ICM.
	public static final int  MT_START_APPLICATION_TASK_REQ		=164;	// Report agent has begun task, agent not selected by Unified ICM.
	public static final int  MT_START_APPLICATION_TASK_RESP		=165;	// Response to START_APPLICATION_TASK_REQ.
	public static final int  MT_PAUSE_TASK_IND					=166;	// Report agent has paused task.
	public static final int  MT_RESUME_TASK_IND					=167;	// Report agent has resumed task.
	public static final int  MT_WRAPUP_TASK_IND					=168;	// Report agent has entered wrapup for task.
	public static final int  MT_END_TASK_IND					=169;	// Report agent has ended task.
	public static final int  MT_AGENT_MADE_NOT_ROUTABLE_EVENT	=170;	// Notify client that agent made not routable for MRD.
	public static final int  MT_AGENT_INTERRUPT_ADVISORY_EVENT	=171;	// Notify client that agent has been interrupted by non-interruptible task.
	public static final int  MT_AGENT_INTERRUPT_ACCEPTED_IND	=172;	// Report acceptance of the interrupt.
	public static final int  MT_AGENT_INTERRUPT_UNACCEPTED_IND	=173;	// Report non-acceptance of the interrupt.
	public static final int  MT_AGENT_INTERRUPT_DONE_ADVISORY_EVENT	=174;	// Notify client that interrupt has been ended.
	public static final int  MT_AGENT_INTERRUPT_DONE_ACCEPTED_IND	=175;	// Report acceptance of interrupt end.
	public static final int  MT_CHANGE_MAX_TASK_LIMIT_REQ		=176;	// Change the maximum number of simultaneous tasks for the agent MRD combination.
	public static final int  MT_CHANGE_MAX_TASK_LIMIT_RESP		=177;	// Response to CHANGE_MAX_TASK_LIMIT_REQ.
	public static final int  MT_OVERRIDE_LIMIT_REQ				=178;	// Request a task assignment even though it would exceed agent��s maximum number of simultaneous tasks for the MRD.
	public static final int  MT_OVERRIDE_LIMIT_RESP				=179;	// Response to OVERRIDE_LIMIT_REQ.
	public static final int  MT_UPDATE_TASK_CONTEXT_IND			=180;	// Update Unified ICM task context.
	public static final int  MT_BEGIN_AGENT_INIT_IND			=181;	// Report begin agent and task resynchronization.
	public static final int  MT_AGENT_INIT_REQ					=182;	// Report agent��s current state.
	public static final int  MT_AGENT_INIT_RESP					=183;	// Response to AGENT_INIT_REQ.
	public static final int  MT_END_AGENT_INIT_IND				=184;	// Report end of agent and task resynchronization.
	public static final int  MT_TASK_INIT_IND					=185;	// Report task��s state.
	public static final int  MT_AGENT_INIT_READY_EVENT			=186;	// Notify client that Unified ICM is ready to receive agent and task resynchronization messages.
	public static final int  MT_GET_PRECALL_MESSAGES_REQ		=187;	// Request any pending PRE-CALL messages.
	public static final int  MT_GET_PRECALL_MESSAGES_RESP		=188;	// Response to GET_PRECALL_MESSAGES_REQ.
	public static final int  MT_AGENT_LEGACY_PRE_CALL_EVENT		=189;	// Current task context.
	public static final int  MT_FAILURE_RESP					=190;	// Failure response to ARM indication messages.
	public static final int  MT_BEGIN_TASK_EVENT				=191;	// Indicates the specified task has entered the system, either queued, offered, or begun.
	public static final int  MT_QUEUED_TASK_EVENT				=192;	// Indicate the specified task has been queued in the router.
	public static final int  MT_DEQUEUED_TASK_EVENT				=193;	// Indicate the specified task has been dequeued from the router.
	public static final int  MT_OFFER_TASK_EVENT				=194;	// Indicates the specified agent has been reserved to handle the specified task
	public static final int  MT_START_TASK_EVENT				=195;	// Indicates the specified agent has started handling the task.
	public static final int  MT_PAUSE_TASK_EVENT				=196;	// Indicates the specified agent has temporarily suspended handling of the specified task.
	public static final int  MT_RESUME_TASK_EVENT				=197;	// Indicates the specified agent has resumed handling of the specified task after having previously sent a Pause Task message.
	public static final int  MT_WRAPUP_TASK_EVENT				=198;	// Indicates the specified agent is no longer actively handling the task but is doing followup work related to the task.
	public static final int  MT_END_TASK_EVENT					=199;	// Indicates the specified agent has ended handling of the specified task.
	public static final int  MT_TASK_DATA_UPDATE_EVENT			=200;	// Update task context for the specified task.
	public static final int  MT_TASK_MONITOR_START_REQ			=201;	// Request to start the task monitor with the task mask in the request message.
	public static final int  MT_TASK_MONITOR_START_CONF			=202;	// Response to TASK_ MONITOR_START_REQ.
	public static final int  MT_TASK_MONITOR_STOP_REQ			=203;	// Request to stop the task monitor with the monitor ID in the request message.
	public static final int  MT_TASK_MONITOR_STOP_CONF			=204;	// Response to TASK_MONITOR_STOP_REQ.
	public static final int  MT_CHANGE_TASK_MONITOR_MASK_REQ	=205;	// Request to change the task monitor mask with the new mask in the request message.
	public static final int  MT_CHANGE_TASK_MONITOR_MASK_CONF	=206;	// Response to CHANGE_ TASK_MONITOR_MASK_REQ.
	public static final int  MT_MAX_TASK_LIFETIME_EXCEEDED_EVENT	=207;	// Unified ICM terminated a task which had exceeded its configured maximum lifetime ? the result is equivalent to the task ending due to an end task but with a special reason code in the Termination Call Detail record.
	public static final int  MT_SET_APP_PATH_DATA_IND			=208;	// Set or update the application path-specific data variables available to routing scripts.
	public static final int  MT_TASK_INIT_REQ					=209;	// Report task��s state ? used when an Unified ICM taskID has not yet been assigned to the task because the task began when the ARM client interface was down.
	public static final int  MT_TASK_INIT_RESP					=210;	// Response to the TASK_INIT_REQ message.
	public static final int  MT_ROUTE_REGISTER_EVENT			=211;	// Register to receive route requests
	public static final int  MT_ROUTE_REGISTER_REPLY_EVENT		=212;	// Reply to registration message
	public static final int  MT_ROUTE_REQUEST_EVENT				=213;	// Route request for a destination for a call
	public static final int  MT_ROUTE_SELECT					=214;	// Supplies a route destination for a route request
	public static final int  MT_ROUTE_END						=215;	// End Routing dialog
	
	// Reserved	=216; - =235;
	
	public static final int  MT_TEAM_CONFIG_REQ					=236;
	public static final int  MT_TEAM_CONFIG_EVENT				=237;
	public static final int  MT_TEAM_CONFIG_CONF				=238;
	
	// Reserved	=239;-=247;   Register to receive route requests
	
	public static final int  MT_CALL_ATTRIBUTE_CHANGE_EVENT		=240;	// Reply to registration message
	
	// Reserved	=241;- =246;
	
	public static final int  MT_CALL_TERMINATION_EVENT			=247;	// Call Termination Summary Info (not supported by CTI-Server)
	
	public static class NamedVar {}		
	
	public static class NaemdArray {}
	
	public static class TaskId {}
	
	public static class AppPathId {}
	
	
	public static class FailureConf {}
	
	public static class FailureEvent {}
	
	public static class OpenReq implements Serializable {
		public int	MessageLength;		// excludes this header size, 8 bytes
		public int	MessageType;
		public int InvokeID;
		public int VersionNumber;
		public int IdleTimeout;
		public int PeripherealID;
		public int ServiceRequested;
		public int CallMsgMask;
		public int	AgentStateMask;
		public int	ConfigMsgMask;
		public int	Reserved1;
		public int	Reserved2;
		public int	Reserved3;

		// Floating
		public byte [] pFloatingAddr = new byte[9];
		
		public String toString()
	    {
	       
	        String str = new String(this.pFloatingAddr);
	        String result = String.format( "%4s", this.MessageLength)
	        		+ String.format( "%4s", this.MessageType)
	        		+ String.format( "%4s", this.InvokeID)
	        		+ String.format( "%4s", this.VersionNumber)
	        		+ String.format( "%4s", this.IdleTimeout)
	        		+ String.format( "%4s", this.PeripherealID)
	        		+ String.format( "%4s", this.ServiceRequested)
	        		+ String.format( "%4s", this.CallMsgMask)
	        		+ String.format( "%4s", this.AgentStateMask)
	        		+ String.format( "%4s", this.ConfigMsgMask)
	        		+ String.format( "%4s", this.Reserved1)
	        		+ String.format( "%4s", this.Reserved2)
	        		+ String.format( "%4s", this.Reserved3)
	        		+ String.format( "%9s", str);
	        return result;
	    }
		
		public int getLength()
		{
			return 52 + 9;
		}
	}
	
	public static class SetCallDataReq{}
	
	public static class OpenConf {
		int	InvokeID;
		int	ServicesGranted;
		int	MonitorID;
		int	PGStatus;
		LocalDateTime ICMCentralControllerTime;
		boolean	PeripheralOnline;
		int PeripheralType;
		int	agentState;

		// Floating
		String	pFloatingAddr;
	}
	
	public static class SetCallDataConf {}
	
	public static class HeartbeatReq {}
	
	public static class HeartbeatConf {}
	
	public static class CloseReq {}
	
	public static class AgentPreCallEvent {}
	
	public static class BeginCallEvent {}
	
	public static class EndCallEvent {}
	
	public static class CallDataUpdateEvent {}
	
	public static class CallEstablishedEvent {}
	
	public static class CallOriginatedEvent {}
	
	public static class CallTransferredEvent {}
	
	public static class CallHeldEvent {}
	
	public static class CallConnectionClear {}
	
	public static class AgentStateEvent {}
	
	public static class RtpStartedEvent {}
	
	public static class RtpStoppedEvent {}
	
	public static class SystemEvent {}
	
		
}
