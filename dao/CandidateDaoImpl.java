
package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Candidate;
import com.app.pojos.Education;
import com.app.pojos.Skill;

@Repository

@Transactional
public class CandidateDaoImpl implements ICandidateDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public List<Candidate> getDetails() {
		System.out.println();
		String jpql = "select c from Candidate c";
		return sf.getCurrentSession().createQuery(jpql, Candidate.class).getResultList();
	}

	@Override
	public String addRCandidate(Candidate cand) {
		System.out.println("in add method of user");
		sf.getCurrentSession().save(cand);
		return "candidate added with resumeId:" + cand.getResumeId();
	}

	@Override
	public String addR_C_Education(Education Edu,int candId) {
		System.out.println("in add method of user");
		sf.getCurrentSession().save(Edu);
		return "Education details added";
	}

	@Override
	public String addR_C_Skills(Skill skl,int candId) {
		System.out.println("in add method of user");
		sf.getCurrentSession().save(skl);
		return "Skills added";
	}

	@Override
	public Candidate getSpecificCandidate(int cid) 
	{
		String jpql = "select c from Candidate c where c.resumeId=:cid";
		return sf.getCurrentSession().createQuery(jpql, Candidate.class).setParameter("cid", cid).getSingleResult();	}

}
