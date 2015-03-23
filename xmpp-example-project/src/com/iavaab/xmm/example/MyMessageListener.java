package com.iavaab.xmm.example;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;

public class MyMessageListener implements PacketListener {

	private final String user;
	
	public MyMessageListener(String user) {
		this.user = user;
	}
	
	@Override
	public void processPacket(Packet packet) {
        if (packet instanceof Message) {
        	Message msg = (Message) packet;
        	System.out.println(this.user + " ==> " + msg.getBody());
        } else if (packet instanceof Presence) {
          // do something else
        }		
	}
}
