package com.iavaab.xmm.example;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.muc.MultiUserChat;

public class XmppMUCTest {

    public static void main(String[] args) throws Exception {            	
    	
        ConnectionConfiguration config = new ConnectionConfiguration("d25080", 5222);
        config.setSASLAuthenticationEnabled(false);
        config.setSecurityMode(SecurityMode.disabled);
        
        XMPPConnection connection = new XMPPConnection(config);
    	connection.connect();
    	connection.login("test1", "test1");
    	
    	MultiUserChat muc = new MultiUserChat(connection, "myroom@conference.d25080");
    //	muc.create("testroom");
    	//muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
    	muc.join("myroom");
    	System.out.println("Room should be created ==> ");    	    	
    	muc.addMessageListener(new MyMessageListener("user 1"));
    	
    	boolean isRunning = true;
        while (isRunning) {
            Thread.sleep(50);
        }
    	    	
    	//connection.disconnect();
        
    }	
	
}
