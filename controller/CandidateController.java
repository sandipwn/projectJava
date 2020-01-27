
package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.ICandidateDao;
import com.app.pojos.Candidate;
import com.app.pojos.Education;
import com.app.pojos.Skill;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController

@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	private ICandidateDao dao;

	public CandidateController() {
		System.out.println("in candidate controller");
	}

	@GetMapping("/list")
	public List<Candidate> getAll() {
		System.out.println("in get all users");
		List<Candidate> details = dao.getDetails();
		// System.out.println(details);
		details.forEach(c -> {
			System.out.println("Email "+c.getEmail());
			System.out.println("Jobs");
			c.getJoblists().forEach(System.out::println);
			System.out.println("Skills");
			c.getSkills().forEach(System.out::println);
		});
		return details;
	}

	// for adding candidate personal details seperately in table/pojo candidate
	@GetMapping("/addCand")
	public String add(Candidate c) {
		System.out.println("in show register form");
		return "/candidate/addCand";
	}

	@PostMapping("/addCand")
	public ResponseEntity<String> processCandidateForm(@RequestBody Candidate cnd) {
		try {
			return new ResponseEntity<String>(dao.addRCandidate(cnd), HttpStatus.CREATED);
		} catch (RuntimeException e1) {
			return new ResponseEntity<String>(e1.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// for adding candidate education details seperately in table/pojo education

	@GetMapping("/addEdu")
	public String add(Education edu) {
		System.out.println("in show register form");
		return "/candidate/addEdu";
	}

	@PostMapping("/addEdu/{candId}")
	public ResponseEntity<String> processEducationForm(@RequestBody Education educ, @PathVariable int candId) {
		try {
			return new ResponseEntity<String>(dao.addR_C_Education(educ, candId), HttpStatus.CREATED);
		} catch (RuntimeException e1) {
			return new ResponseEntity<String>(e1.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// for adding candidate skills details seperately in table/pojo skill
	@GetMapping("/addSkills")
	public String add(Skill skl) {
		System.out.println("in show register form");
		return "/candidate/addSkills";
	}

	@PostMapping("/addSkills/{candId}")
	public ResponseEntity<String> processEducationForm(@RequestBody Skill skill, @PathVariable int candId) {
		try {
			return new ResponseEntity<String>(dao.addR_C_Skills(skill, candId), HttpStatus.CREATED);
		} catch (RuntimeException e1) {
			return new ResponseEntity<String>(e1.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// display entire data of candidate -->{ candidate, education, skills }
	@GetMapping("/{cand_id}")
	public ResponseEntity<?> getCandDtls(@PathVariable int cand_id) {
		System.out.println("in get cand dtls " + cand_id);
		Candidate candDetails = dao.getSpecificCandidate(cand_id);
		ObjectMapper om = new ObjectMapper();
		try {
			String s = om.writeValueAsString(candDetails);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (candDetails == null)
			return new ResponseEntity<String>("Candidate not found...", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Candidate>(candDetails, HttpStatus.OK);
	}
}
