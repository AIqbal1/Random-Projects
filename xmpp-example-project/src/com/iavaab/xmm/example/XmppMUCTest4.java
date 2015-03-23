package com.iavaab.xmm.example;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;

public class XmppMUCTest4 {

	
    @SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
        
    	
        ConnectionConfiguration config = new ConnectionConfiguration("d25080", 5222);
        config.setSASLAuthenticationEnabled(false);
        config.setSecurityMode(SecurityMode.disabled);
        
        XMPPConnection user4conn = new XMPPConnection(config);
    	user4conn.connect();
    	user4conn.login("test4", "test4");
    	
    	System.out.println("Invitation for user 4 should be coming...");    	
    	
    	MultiUserChat.addInvitationListener(user4conn, new InvitationListener() {            
            @SuppressWarnings("unused")
			public void invitationReceived(Connection conn, String room, String inviter, String reason, String password) {
                // Reject the invitation
            	System.out.println("Invitation arrived user 4 - accepted ");            	
            	MultiUserChat muc = new MultiUserChat(conn, room);    	    
            	try {            		
					muc.join(room);					
					muc.sendMessage("Text message from user 4 " + room);
					
                    Presence.Type type = Type.available;
                    Presence presence = new Presence(type);                    
                    presence.setStatus("Yes im available..");
                    conn.sendPacket(presence);       
                    					
				} catch (XMPPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    	            	
            	
            }

			@Override
			public void invitationReceived(Connection conn, String room, String inviter, String reason, String password, Message arg5) {				
				System.out.println("Invitation arrived user 4 - accepted ");
				
            	MultiUserChat muc = new MultiUserChat(conn, room);    	    
            	try {
					muc.join(room);
					muc.sendMessage("Text message from user 4 " + room);
					
                    Presence.Type type = Type.available;
                    Presence presence = new Presence(type);                    
                    presence.setStatus("Yes im available..");
                    conn.sendPacket(presence);       
                    					
				} catch (XMPPException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  				
			}
        });     	    	    	    	   
    	    	
    	
    	boolean isRunning = true;
    	 
        while (isRunning) {
            Thread.sleep(50);
        }    	
    	
    	//connection.disconnect();
        
    }		
}
