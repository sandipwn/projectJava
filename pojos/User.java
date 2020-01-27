package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	private Integer userId;
	private String name, email, password, mobile;
	private UserRole type;
	private int otp;
	// from candidate for one to one relation with user and candidate
	
	/*
	 * private Candidate Candidate; // one to one bet. employer and user
	 * 
	 * private Employer employer;
	 */
	 
	public User() {
		System.out.println("in user ctor");
	}

	public User(String password, int otp) {
		super();
		this.password = password;
		this.otp = otp;
	}

	public User(String name, String email, String password, String mobile, UserRole type) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.type = type;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(length = 30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 30, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Enumerated(EnumType.STRING)
	public UserRole getType() {
		return type;
	}

	public void setType(UserRole type) {
		this.type = type;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/*
	 * @OneToOne(mappedBy = "user", cascade = CascadeType.ALL) public Candidate
	 * getCandidate() { return Candidate; }
	 * 
	 * public void setCandidate(Candidate candidate) { Candidate = candidate; }
	 * 
	 * @OneToOne(mappedBy = "user", cascade = CascadeType.ALL) public Employer
	 * getEmployer() { return employer; }
	 * 
	 * public void setEmployer(Employer employer) { this.employer = employer;
	 * 
	 * }
	 */
	@Transient
	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", mobile=" + mobile + ", type="
				+ type + "]";
	}
}
