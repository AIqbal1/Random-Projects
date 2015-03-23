package com.avaab.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ServiceTrans {
	
	private PlatformTransactionManager platformTransactionManager;
	private EntityManager entityManager;
	
	public void setPlatformTransactionManager(
			PlatformTransactionManager platformTransactionManager) {
		this.platformTransactionManager = platformTransactionManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Transactional
	public void testTransactional() {
		Order order = new Order();
		String name = "Customer 3 " + (new Date()).toLocaleString();
		order.setCustomer(name);															
		entityManager.persist(order);
		
		  TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization(){
		    	
				public void afterCommit() {
					System.out.println("====> AFTER SUCCESSFUL COMMIT 3 TO DB...");						
				}

				public void afterCompletion(int arg0) {
					System.out.println("AFTER COMPLETION...commit 3 or rollback...");
					
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
		
		
		
		System.out.println("CUSTOMER 3 ID REAL ID  == " + order.getId());
	}
	
}
