package com.springproject.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	public void setId(long id) {
		this.id = id;
	}
	public void setAbstractdocsize(long abstractdocsize) {
		this.abstractdocsize = abstractdocsize;
	}
	@Column(name="title")
	private String title;
	@Column(name="batch")
	private String batch;
	@Column(name="guide")
	private String guide;
	@Column(name="abstractdocname")
	private String abstractdocname;
	@Column(name="abstractdocsize")
	private long abstractdocsize;
	@Lob
	@Column(name="abstractdoc",columnDefinition = "MEDIUMBLOB")
	private byte[] abstractdoc;
	public long getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getGuide() {
		return guide;
	}
	public void setGuide(String guide) {
		this.guide = guide;
	}
	public byte[] getAbstractdoc() {
		return abstractdoc;
	}
	public void setAbstractdoc(byte[] abstractdoc) {
		this.abstractdoc = abstractdoc;
	}
	public String getAbstractdocname() {
		return abstractdocname;
	}
	public void setAbstractdocname(String abstractdocname) {
		this.abstractdocname = abstractdocname;
	}
	public long getAbstractdocsize() {
		return abstractdocsize;
	}
	
	
	

}
