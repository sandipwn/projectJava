
  package com.app.dao;
  
  import org.hibernate.SessionFactory; import
  org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.stereotype.Repository; import
  org.springframework.transaction.annotation.Transactional;
  
  import com.app.pojos.Employer;
  
  @Repository
  
  @Transactional public class EmployerDaoImpl implements IEmployerDao {
  
  @Autowired private SessionFactory sf;
  
  @Override public String addEmployer(Employer emps) {
  System.out.println("in add method of user");
  sf.getCurrentSession().save(emps); return
  "User added with Id:"+emps.getCompanyId(); }
  
  }
 