package com.github.drochetti.springneo4j.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Neo4jDatabaseConfig {

	@Bean(destroyMethod = "shutdown")
	public GraphDatabaseService graphDatabaseService() {
		return new EmbeddedGraphDatabase("data/spring_neo4j_jpa_graph");
	}

}
