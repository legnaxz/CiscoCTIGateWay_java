# -*- coding: utf-8 -*-
import time
import socket

CTI_SERVICE_DEBUG					=0x80000000	
CTI_SERVICE_CLIENT_EVENTS			=0x00000001	
CTI_SERVICE_CALL_DATA_UPDATE		=0x00000002	
CTI_SERVICE_CLIENT_CONTROL			=0x00000004	
CTI_SERVICE_CONNECTION_MONITOR		=0x00000008	
CTI_SERVICE_ALL_EVENTS				=0x00000010	
CTI_SERVICE_PERIPHERAL_MONITOR		=0x00000020	
CTI_SERVICE_CLIENT_MONITOR			=0x00000040	
CTI_SERVICE_SUPERVISOR				=0x00000080	
CTI_SERVICE_SERVER					=0x00000100	
CTI_SERVICE_AGENT_REPORTING		    =0x00000400	
CTI_SERVICE_ALL_TASK_EVENTS		    =0x00000800	
CTI_SERVICE_TASK_MONITOR			=0x00001000	
CTI_AGENT_STATE_CONTROL_ONLY		=0x00002000	
CTI_DEVICE_STATE_CONTROL			=0x00008000	
CTI_SERVICE_UPDATE_EVENTS			=0x00080000	
CTI_SERVICE_IGNORE_DUPLICATE_AGENT_EVENTS	=0x00100000	
CTI_SERVICE_IGNORE_CONF			    =0x00200000
CTI_SERVICE_ACD_LINE_ONLY			=0x00400000	
CALL_DELIVERED_MASK				    =0x00000001	
CALL_QUEUED_MASK					=0x00000002	
CALL_ESTABLISHED_MASK				=0x00000004	
CALL_HELD_MASK						=0x00000008	
CALL_RETRIEVED_MASK				    =0x00000010	
CALL_CLEARED_MASK					=0x00000020	
CALL_CONNECTION_CLEARED_MASK		=0x00000040	
CALL_ORIGINATED_MASK				=0x00000080	
CALL_CONFERENCED_MASK				=0x00000100	
CALL_TRANSFERRED_MASK				=0x00000200	
CALL_DIVERTED_MASK					=0x00000400	
CALL_SERVICE_INITIATED_MASK		    =0x00000800	
CALL_TRANSLATION_ROUTE_MASK		    =0x00001000	
BEGIN_CALL_MASK					    =0x00002000	
END_CALL_MASK						=0x00004000	
CALL_DATA_UPDATE_MASK				=0x00008000	
CALL_FAILED_MASK					=0x00010000	
CALL_REACHED_NETWORK_MASK			=0x00020000	
CALL_DEQUEUED_MASK					=0x00040000	
AGENT_PRE_CALL_MASK				    =0x00080000	
AGENT_PRE_CALL_ABORT_MASK			=0x00100000	
RTP_STARTED_MASK					=0x00200000	
RTP_STOPPED_MASK					=0x00400000	
AGENT_TEAM_CONFIG_MASK				=0x00800000	
AGENT_LEGACY_PRE_CALL_MASK			=0x01000000	
CALL_ATTRIBUTE_CHANGE_MASK			=0x02000000	
AGENT_LOGIN_MASK					=0x00000001	
AGENT_LOGOUT_MASK					=0x00000002	
AGENT_NOT_READY_MASK				=0x00000004	
AGENT_AVAILABLE_MASK				=0x00000008	
AGENT_TALKING_MASK					=0x00000010	
AGENT_WORK_NOT_READY_MASK			=0x00000020	
AGENT_WORK_READY_MASK				=0x00000040	
AGENT_BUSY_OTHER_MASK				=0x00000080	
AGENT_RESERVED_MASK				    =0x00000100	
AGENT_HOLD_MASK					    =0x00000200	

TARGET_IP = '127.0.0.1'
TARGET_PORT = 5555


def send_message(host, port, send_msg):
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

    sock.connect((host, port))

    sock.send(send_msg.encode('utf-8'))
    print('RECV: [%s]' % sock.recv(1024))

    sock.close()


def main():
    print('start Cisco CTI')
    print('connect to %s:%d' % (TARGET_IP, TARGET_PORT))

    InvokeID = '%4s' % socket.htonl(1)
    VersionNumber = '%4s' % socket.htonl(14)
    IdleTimeout = '%4s' % 0xFFFFFFFF
    PeripherealID = '%4s' % 0xFFFFFFFF
    ServiceRequested = '%4s' % socket.htonl( CTI_SERVICE_ALL_EVENTS | CTI_SERVICE_CALL_DATA_UPDATE )
    CallMsgMask = '%4s' % socket.htonl( CALL_ESTABLISHED_MASK |
            CALL_HELD_MASK |
            CALL_RETRIEVED_MASK |
            CALL_CLEARED_MASK |
            CALL_CONNECTION_CLEARED_MASK |
            CALL_CONFERENCED_MASK |
            CALL_TRANSFERRED_MASK |
            BEGIN_CALL_MASK |
            END_CALL_MASK |
            RTP_STARTED_MASK |
            RTP_STOPPED_MASK )
    AgentStateMask = '%4s' % socket.htonl ( AGENT_AVAILABLE_MASK | AGENT_TALKING_MASK )
    ConfigMsgMask = '%4s' % socket.htonl( 0x1F )
    Reserved1 = '%4s' % 0
    Reserved2 = '%4s' % 0
    Reserved3 = '%4s' % 0
    CtiId = '%4s' % "%-6s" % '14RTP'
    CtiType = '%4s' % "%-3s" % '21'
  
    send_msg = InvokeID + VersionNumber + IdleTimeout + PeripherealID + ServiceRequested + CallMsgMask + AgentStateMask + ConfigMsgMask + Reserved1 + Reserved2 + Reserved3 + CtiId + CtiType
    print('[', send_msg, ']')
    send_message(TARGET_IP, TARGET_PORT, send_msg)


if __name__ == '__main__':
    main()
