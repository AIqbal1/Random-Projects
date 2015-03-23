package com.avaab.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

public class Service2 {

	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	@PersistenceContext
	private EntityManager entityManager;
	
	private TransactionTemplate transactionTemplate;
	
	public void persistOrder() throws Exception {
		this.transactionTemplate = new TransactionTemplate(platformTransactionManager);
		this.transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		
		try {
			final Order order = this.transactionTemplate.execute(new TransactionCallback<Order>() {
				@Override
	            public Order doInTransaction(TransactionStatus status) {
				
					Order order = new Order();
					order.setCustomer("Customer " + (new Date()).toLocaleString());				
					entityManager.persist(order);																		
					
				    TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization(){
	
						public void afterCommit() {
							System.out.println("====> AFTER SUCCESSFUL COMMIT TO DB...");						
						}
	
						public void afterCompletion(int arg0) {
							System.out.println("AFTER COMPLETION...commit or rollback...");
							
						}
	
						public void beforeCommit(boolean arg0) {
							// TODO Auto-generated method stub
							
						}
	
						public void beforeCompletion() {
							// TODO Auto-generated method stub
							
						}
	
						public void flush() {
							// TODO Auto-generated method stub
							
						}
	
						public void resume() {
							// TODO Auto-generated method stub
							
						}
	
						public void suspend() {
							// TODO Auto-generated method stub
							
						}			       
					});	
				  //  throw new IllegalAccessError();
				    return order;
				}
			});	
		}
		catch(Exception ex) {
			System.out.println("Exception ex...");
		}
		
//		TransactionSynchronizationManager.clearSynchronization();
	}

	
}

