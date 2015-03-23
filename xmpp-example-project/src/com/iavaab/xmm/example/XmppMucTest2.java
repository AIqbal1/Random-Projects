package com.iavaab.xmm.example;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.muc.InvitationRejectionListener;
import org.jivesoftware.smackx.muc.MultiUserChat;

public class XmppMucTest2 {
	
	
    @SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
        
    	
//        ConnectionConfiguration config = new ConnectionConfiguration("d25080", 5222);
//        config.setSASLAuthenticationEnabled(false);
//        config.setSecurityMode(SecurityMode.disabled);
        
        XMPPConnection user2conn = new XMPPConnection("d25080");
    	user2conn.connect();
    	user2conn.login("test2", "test2");
    	
    	MultiUserChat muc = new MultiUserChat(user2conn, "myroom@conference.d25080");    	    
    	muc.join("myroom2");    	    	  
    	muc.addMessageListener(new MyMessageListener("user 2"));
    
    	System.out.println("user supports MUC ==> " + MultiUserChat.isServiceEnabled(user2conn, "test3@d25080"));
    	
    	muc.addInvitationRejectionListener(new InvitationRejectionListener() {			
			@Override
			public void invitationDeclined(String arg0, String arg1) {				
				System.out.println("User has rejected my invitiation");
			}
		});       
    	
    	muc.invite("test3@d25080", "Meet me in this excellent room");
    	muc.invite("test4@d25080", "Meet me in this excellent room");
    	
    	muc.sendMessage("Text message22");
    	
    	boolean isRunning = true;    	
    	
        while (isRunning) {
            Thread.sleep(50);
        }
    	
    	
    	//connection.disconnect();
        
    }		
}
