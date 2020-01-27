package com.app.pojos;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.jmx.export.annotation.ManagedResource;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property = "resumeId")
@JsonIdentityReference(alwaysAsId = true) madhura
*/
@Entity
@Table(name = "candidate")
public class Candidate {
	private Integer resumeId;
	private String name;
	private String gender;
	private int age;
	private String email;
	private String mobile;
	private String objective, address, pTitle, pDesc;

	@JsonIgnore
	private byte[] photo;
	// from user for one to one relation with user and candidate

//	@JsonBackReference
	/* @JsonIgnore */
	private User user;
	// from education one to many bidirectional relation
	private List<Education> education = new ArrayList<>();
	// many to many bet. skill and candidate
	 @JsonIgnore 
	private Set<Skill> skills = new HashSet<>();
	 @JsonIgnore 
	private Set<JobList> joblists = new HashSet<>();

	public Candidate() {
		System.out.println("in candidate ctor");
	}

	/*
	 * public Candidate(String name, String email, String mobile, String objective,
	 * String address, String pTitle, String pDesc, byte[] photo) { super();
	 * this.name = name; this.email = email; this.mobile = mobile; this.objective =
	 * objective; this.address = address; this.pTitle = pTitle; this.pDesc = pDesc;
	 * this.photo = photo; }
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getResumeId() {
		return resumeId;
	}

	public Candidate(String name, String gender, int age, String email, String mobile, String objective, String address,
			String pTitle, String pDesc, byte[] photo) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.mobile = mobile;
		this.objective = objective;
		this.address = address;
		this.pTitle = pTitle;
		this.pDesc = pDesc;
		this.photo = photo;
	}

	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(length = 2000)
	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(length = 700)
	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	@Column(length = 2500)
	public String getpDesc() {
		return pDesc;
	}

	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}

	@Lob
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	@OneToOne
	@JoinColumn(name = "user_id") // foreign key from user
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	public List<Education> getEducation() {
		return education;
	}

	public void setEducation(List<Education> education) {
		this.education = education;
	}

	@ManyToMany(mappedBy = "candidates", fetch = FetchType.EAGER)
	@JsonManagedReference//madhura
	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "appliedJobs", joinColumns = { @JoinColumn(name = "resume_id") }, inverseJoinColumns = {
			@JoinColumn(name = "job_id") })
	public Set<JobList> getJoblists() {
		return joblists;
	}

	public void setJoblists(Set<JobList> joblists) {
		this.joblists = joblists;
	}

	@Override
	public String toString() {
		return "Candidate [resumeId=" + resumeId + ", objective=" + objective + ", address=" + address + ", pTitle="
				+ pTitle + ", pDesc=" + pDesc;
	}
}
