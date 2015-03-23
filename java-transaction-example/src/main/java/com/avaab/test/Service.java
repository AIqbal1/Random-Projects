package com.avaab.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

@Component
@Qualifier(value = "aaa, bbb")
public class Service {

	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	@PersistenceContext
	private EntityManager entityManager;
	
	private TransactionTemplate transactionTemplate;
	
	private int num = 0;
	
	private List<String> ids = new ArrayList<String>();
	
	public void persistOrder() throws Exception {
		this.transactionTemplate = new TransactionTemplate(platformTransactionManager);
		if(num == 0) {
			this.transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		} else {
			this.transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			try {
				final Order order = this.transactionTemplate.execute(new TransactionCallback<Order>() {
					@Override
		            public Order doInTransaction(TransactionStatus status) {
							Order order = new Order();
							String name = "Customer 2 " + (new Date()).toLocaleString();
							order.setCustomer(name);															
							entityManager.persist(order);
							System.out.println("CUSTOMER 2 ID REAL ID  == " + order.getId());
				
							
						    TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization(){
						    	
								public void afterCommit() {
									System.out.println("====> AFTER SUCCESSFUL COMMIT 2 TO DB...");						
								}
			
								public void afterCompletion(int arg0) {
									System.out.println("AFTER COMPLETION...commit 2 or rollback...");
									
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
							return order;
					}		
				});
			}
			catch(Exception ex) {
				System.out.println("Exception ex..." + ex);						
				throw ex;
			}
			System.out.println("TRANSACTION FOR OBJECT 2 COMPLETE..");
			return;
		}
		
		try {
			final Order order = this.transactionTemplate.execute(new TransactionCallback<Order>() {
				@Override
	            public Order doInTransaction(TransactionStatus status) {
				
					Order order = new Order();
					String name = "Customer 1 " + (new Date()).toLocaleString();
					order.setCustomer(name);
					entityManager.persist(order);									
					System.out.println("CUSTOMER 1 ID REAL ID  == " + order.getId());
					
					if(num == 0) {
						try {
							num++;
							persistOrder();							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					System.out.println("LEAVING IF STATEMENT....");
					
				    TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization(){
	
						public void afterCommit() {
							System.out.println("====> AFTER SUCCESSFUL COMMIT 1 TO DB...");						
						}
	
						public void afterCompletion(int arg0) {
							System.out.println("AFTER COMPLETION...commit 1 or rollback...");
							
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
			System.out.println("TRANSACTION FOR OBJECT 1 COMPLETE..");
		}
		catch(Exception ex) {
			System.out.println("Exception ex..." + ex);
			
			throw ex;
		}
		
		for(long i = 1 ; i<= 2 ; i++) {
			Order order = entityManager.find(Order.class, i);
			System.out.println(" retrieved id = " + order.getCustomer());
			
		}
		
		
//		TransactionSynchronizationManager.clearSynchronization();
	}

	
	
}
