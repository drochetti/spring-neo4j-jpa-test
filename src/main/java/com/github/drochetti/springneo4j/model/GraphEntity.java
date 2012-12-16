package com.github.drochetti.springneo4j.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class GraphEntity {

	@GraphId
	private Long id;

	@GraphProperty // causes a bug when missing (using CrossStore support)
	private String graphProperty;

	@RelatedTo(type = "PARTIAL_RELATED_TO_GRAPH")
	private PartialEntity graphToPartialRelation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGraphProperty() {
		return graphProperty;
	}

	public void setGraphProperty(String graphProperty) {
		this.graphProperty = graphProperty;
	}

	public PartialEntity getGraphToPartialRelation() {
		return graphToPartialRelation;
	}

	public void setGraphToPartialRelation(PartialEntity graphToPartialRelation) {
		this.graphToPartialRelation = graphToPartialRelation;
	}

}
