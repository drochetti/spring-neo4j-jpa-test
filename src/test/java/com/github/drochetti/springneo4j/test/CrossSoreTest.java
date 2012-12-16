package com.github.drochetti.springneo4j.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.cross_store.config.CrossStoreNeo4jConfiguration;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.drochetti.springneo4j.config.Neo4jDatabaseConfig;
import com.github.drochetti.springneo4j.model.GraphEntity;
import com.github.drochetti.springneo4j.model.PartialEntity;

@ContextConfiguration(classes = { Neo4jDatabaseConfig.class, CrossStoreNeo4jConfiguration.class })
//@ContextConfiguration(locations = "classpath:/spring/neo4j-cross.xml")
public class CrossSoreTest extends BaseTest {

	@Autowired
	private Neo4jTemplate template;

	@Test
	@Transactional
	public void testLoadGraphEntity() {
		GraphEntity entity = template.findAll(GraphEntity.class).iterator().next();
		assertNotNull(entity);
		assertNotNull("graph property null when no @GraphProperty", entity.getGraphProperty());

		final PartialEntity partialEntity = entity.getGraphToPartialRelation();
		assertNotNull(partialEntity);
		assertNotNull(partialEntity.getNodeId());
		assertNotNull(partialEntity.getGraphProperty());
//		assertNotNull("but why relational/partial properties are null?",
//				partialEntity.getRelationalProperty()); // getId() is null as well
	}

	@Test
	@Transactional
	public void testCreateGraphEntity() {
		super.testCreateRelationalData();
		GraphEntity entity = new GraphEntity();
		entity.setGraphProperty("propertyValue"); // FIXME not persisted when no @GraphProperty
		entity.setGraphToPartialRelation(partialEntity);
		entity.persist();

		entityManager.clear();
		PartialEntity partial = entity.getGraphToPartialRelation();
		assertNotNull(partial.getRelationalProperty());
		assertNotNull(partial.getGraphProperty());
	}

}
