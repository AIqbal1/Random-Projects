package com.iavaab.xmm.example;

public class XmppTest {
    
    public static void main(String[] args) throws Exception {
        
        String username = "test1";
        String password = "test1";
        
        XmppManager xmppManager = new XmppManager("d25080", 5222);
        
        xmppManager.init();
        xmppManager.performLogin(username, password);
        xmppManager.setStatus(true, "Hello everyone");
        
        String buddyJID = "test2";
        String buddyName = "test2";
        xmppManager.createEntry(buddyJID, buddyName);
        
        xmppManager.sendMessage("Hello mate", "test2@d25080");
        
        boolean isRunning = true;
        
        while (isRunning) {
            Thread.sleep(50);
        }
        
        xmppManager.destroy();
        
    }

}
