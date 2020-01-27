
package com.app.dao;

import java.util.List;

import com.app.pojos.Candidate;
import com.app.pojos.Education;
import com.app.pojos.Skill;

public interface ICandidateDao {
	List<Candidate> getDetails();

	String addRCandidate(Candidate cand);

	String addR_C_Education(Education Edu,int candId);

	String addR_C_Skills(Skill skl,int candId);

	Candidate getSpecificCandidate(int cid);

}
