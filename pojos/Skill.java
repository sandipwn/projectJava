package com.app.pojos;

import java.util.HashSet;


import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "skillId")
@JsonIdentityReference(alwaysAsId = true)*/
@Entity
@Table(name = "skill")
public class Skill {
	private Integer skillId;
	private String skill;
    //Many to many bidirectional bet. skill and candidate 
	
	
	
	/*
	 * @JsonBackReference
	 */	  private Set<Candidate> candidates = new HashSet<>();
	
	
	  //@JsonBackReference
	 	private Set<JobList> joblists = new HashSet<>();
	
	public Skill() {
		System.out.println("in skill ctor");
	}
	public Skill(String skill) {
		super();
		this.skill = skill;
	}
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
   
	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	//owning side
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="candidateSkill",joinColumns = {@JoinColumn(name="skill_id")},inverseJoinColumns = {@JoinColumn(name="resume_id")})
	public Set<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}
     
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="jobSkill",joinColumns = {@JoinColumn(name="skill_id")},inverseJoinColumns = {@JoinColumn(name="job_id")})
	public Set<JobList> getJoblists() {
		return joblists;
	}

	public void setJoblists(Set<JobList> joblists) {
		this.joblists = joblists;
	}

	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", skill=" + skill + "]";
	}
	

}
