package com.ingg.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

import com.avaab.test.Service;
import com.avaab.test.ServiceTrans;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderPersistenceTests {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private Service service;
	
	@Autowired 
	private ApplicationContext applicationContext;

	@Autowired
	private PlatformTransactionManager platformTransactionManager;	
	

	
	@Test
	public void test() {
		System.out.println("out");
		try {
			
			
			service.persistOrder();
			
			service.persistOrder();
		} catch(Exception ex) {
			System.out.println("in Test Exception ... " + ex);
		}
	}
	
	@Test
	public void test2() {
		System.out.println("out");
		try {
			service.persistOrder();			
			//service.persistOrder();
		} catch(Exception ex) {
			System.out.println("in Test Exception ... " + ex);
		}
	}	
	
	
	@Test
	public void test3() {		
		try {			
			Class<ServiceTrans> clazz = (Class<ServiceTrans>) Class.forName("com.ingg.test.ServiceTrans"); 
			ServiceTrans table = applicationContext.getAutowireCapableBeanFactory().createBean(clazz);
			
//			table = applicationContext.getAutowireCapableBeanFactory().applyBeanPostProcessorsAfterInitialization(table, "roulette");			
			ServiceTrans table2 = applicationContext.getAutowireCapableBeanFactory().createBean(clazz);
			
			System.out.println(table2 == table);
			
			System.out.println(table2.equals(table));
			
			table.setEntityManager(entityManager);
			table.setPlatformTransactionManager(platformTransactionManager);
			
			table.testTransactional();
			
			Service service  = (Service) applicationContext.getBean("aaa");
			
			Service service2  = (Service) applicationContext.getBean("bbb");
			
			System.out.println(service == service2);
			
			System.out.println(service2.equals(service));
			
		} catch(Exception ex) {
			System.out.println("in Test Exception ... " + ex);
		}
	}

}
