package com.github.drochetti.springneo4j.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.aspects.config.Neo4jAspectConfiguration;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.drochetti.springneo4j.config.Neo4jDatabaseConfig;
import com.github.drochetti.springneo4j.model.GraphEntity;

@ContextConfiguration(classes = { Neo4jDatabaseConfig.class, Neo4jAspectConfiguration.class })
//@ContextConfiguration(locations = "classpath:/spring/neo4j.xml")
@Transactional
public class GraphOnlyTest extends BaseTest {

	@Autowired
	protected Neo4jTemplate template;

	protected GraphEntity graphEntity;

	@Test
	@Transactional
	public void testLoadGraphEntity() {
		GraphEntity entity = template.findAll(GraphEntity.class).iterator().next();
		assertNotNull(entity);
		assertNotNull("[Never happens!] graph property null when no @GraphProperty", entity.getGraphProperty());
		assertNotNull(entity.getGraphToPartialRelation());
		assertNotNull(entity.getGraphToPartialRelation().getNodeId());

		// Relational properties not found as expected
		assertNull(entity.getGraphToPartialRelation().getRelationalProperty());
		assertNull(entity.getGraphToPartialRelation().getId());
	}

}
