package com.github.drochetti.springneo4j.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.NodeEntity;

@Entity
@NodeEntity(partial = true)
public class PartialEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String relationalProperty;

	@GraphProperty
	private String graphProperty;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRelationalProperty() {
		return relationalProperty;
	}

	public void setRelationalProperty(String relationalProperty) {
		this.relationalProperty = relationalProperty;
	}

	public String getGraphProperty() {
		return graphProperty;
	}

	public void setGraphProperty(String graphProperty) {
		this.graphProperty = graphProperty;
	}

}
