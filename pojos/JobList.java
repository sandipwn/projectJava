package com.app.pojos;

import java.util.Date;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "joblist")
public class JobList {
	private Integer jobId;
	private String jobProfile, jobDesc;
	private Date interviewDate;
	private String interviewTime;
	private int noOfVacancies;
	private double salary;
	private String location;
	// many to one bet. joblist and employer

//	@JsonBackReference
	private Employer employer;

	private Set<Skill> skills = new HashSet<>();

//	@JsonBackReference 
	private Set<Candidate> candidates = new HashSet<>();

	public JobList() {
		System.out.println("in joblist ctor");
	}

	public JobList(String jobProfile, String jobDesc, Date interviewDate, String interviewTime, int noOfVacancies,
			double salary, String location) {
		super();
		this.jobProfile = jobProfile;
		this.jobDesc = jobDesc;
		this.interviewDate = interviewDate;
		this.interviewTime = interviewTime;
		this.noOfVacancies = noOfVacancies;
		this.salary = salary;
		this.location = location;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	@Column(length = 100)
	public String getJobProfile() {
		return jobProfile;
	}

	public void setJobProfile(String jobProfile) {
		this.jobProfile = jobProfile;
	}

	@Column(length = 2000)
	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}

	public int getNoOfVacancies() {
		return noOfVacancies;
	}

	public void setNoOfVacancies(int noOfVacancies) {
		this.noOfVacancies = noOfVacancies;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@ManyToOne
	@JoinColumn(name = "company_id")
	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	@ManyToMany(mappedBy = "joblists")
	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	@ManyToMany(mappedBy = "joblists")
	public Set<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}

	@Override
	public String toString() {
		return "JobList [jobId=" + jobId + ", jobProfile=" + jobProfile + ", jobDesc=" + jobDesc + ", interviewDate="
				+ interviewDate + ", interviewTime=" + interviewTime + ", noOfVacancies=" + noOfVacancies + ", salary="
				+ salary + ", location=" + location + "]";
	}
}
