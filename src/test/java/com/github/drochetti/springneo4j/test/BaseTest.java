package com.github.drochetti.springneo4j.test;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.drochetti.springneo4j.config.JpaConfig;
import com.github.drochetti.springneo4j.model.PartialEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JpaConfig.class)
//@ContextConfiguration(locations = "classpath:/spring/jpa.xml")
@TransactionConfiguration(defaultRollback = false)
public class BaseTest {

	@PersistenceContext
	protected EntityManager entityManager;

	protected PartialEntity partialEntity;

//	@Before
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	public void prepareData() {
//		assertNotNull(entityManager);
//		PartialEntity entity = new PartialEntity();
//		entity.setRelationalProperty("relationalPropertyValue");
//		entity.setGraphProperty("graphPropertyValue");
//		entityManager.persist(entity);
//		entityManager.flush();
//		assertNotNull(entity.getId());
//		this.partialEntity = entity;
//	}

	@Test
	@Transactional
	public void testCreateRelationalData() {
		assertNotNull(entityManager);
		PartialEntity entity = new PartialEntity();
		entity.setRelationalProperty("relationalPropertyValue");
		entity.setGraphProperty("graphPropertyValue");
		entityManager.persist(entity);
		entityManager.flush();
		assertNotNull(entity.getId());
		entity.persist();
		this.partialEntity = entity;
	}

//	@After
	public void dropData() {
		assertNotNull(partialEntity);
		entityManager.remove(partialEntity);
	}

}
