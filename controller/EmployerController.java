
  package com.app.controller;
  
  
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.http.HttpStatus; import
  org.springframework.http.ResponseEntity; import
  org.springframework.web.bind.annotation.GetMapping; import
  org.springframework.web.bind.annotation.PostMapping; import
  org.springframework.web.bind.annotation.RequestBody; import
  org.springframework.web.bind.annotation.RequestMapping; import
  org.springframework.web.bind.annotation.RestController;
  
  import com.app.dao.IEmployerDao; import com.app.pojos.Employer;
  
  @RestController
  
  @RequestMapping("/employer") public class EmployerController {
  
  @Autowired private IEmployerDao dao;
  
  @GetMapping("/addEmps") public String addEmp(@RequestBody Employer emps) {
  System.out.println("in show employee add form"); return "/employer/addEmps";
  }
  
  @PostMapping("/addEmps") public ResponseEntity<String>
  processRegisterMemberForm(@RequestBody Employer emps) { try { return new
  ResponseEntity<String>(dao.addEmployer(emps), HttpStatus.CREATED); } catch
  (RuntimeException e1) { return new ResponseEntity<String>(e1.getMessage(),
  HttpStatus.INTERNAL_SERVER_ERROR); } } }
 