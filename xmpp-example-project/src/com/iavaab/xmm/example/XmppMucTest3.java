package com.iavaab.xmm.example;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;

public class XmppMucTest3 {
	
	
    @SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
        
    	
        ConnectionConfiguration config = new ConnectionConfiguration("d25080", 5222);
        config.setSASLAuthenticationEnabled(false);
        config.setSecurityMode(SecurityMode.disabled);
        
        XMPPConnection user3conn = new XMPPConnection(config);
    	user3conn.connect();
    	user3conn.login("test3", "test3");
    	
    	System.out.println("Invitation for user 3 should be coming...");    	
    	
    	MultiUserChat.addInvitationListener(user3conn, new InvitationListener() {            
            @SuppressWarnings("unused")
			public void invitationReceived(Connection conn, String room, String inviter, String reason, String password) {
                // Reject the invitation
            	System.out.println("Invitation arrived for user 3 - rejected " + room);            	
            	MultiUserChat.decline(conn, room, inviter, "I'm busy right now");
            }

			@Override
			public void invitationReceived(Connection conn, String room, String inviter, String reason, String password, Message arg5) {				
				System.out.println("Invitation arrived for user 3 - rejected " + room);
				MultiUserChat.decline(conn, room, inviter, "I'm busy right now");
			}
        });     	    	    	    	   
    	    	
    	
    	boolean isRunning = true;
    	 
        while (isRunning) {
            Thread.sleep(50);
        }    	
    	
    	//connection.disconnect();
        
    }		
}

