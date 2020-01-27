
  package com.app.controller;
  
  import java.util.List;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.http.HttpStatus; import
  org.springframework.http.ResponseEntity; import
  org.springframework.web.bind.annotation.CrossOrigin; import
  org.springframework.web.bind.annotation.DeleteMapping; import
  org.springframework.web.bind.annotation.GetMapping; import
  org.springframework.web.bind.annotation.PathVariable; import
  org.springframework.web.bind.annotation.RequestMapping; import
  org.springframework.web.bind.annotation.RestController;
  
  import com.app.dao.IAdminDao; import com.app.pojos.User;
  
  @CrossOrigin(allowedHeaders = "*")
  @RestController
  @RequestMapping("/admin")
  
  public class AdminCotroller {
  
  @Autowired private IAdminDao dao;
  
	public AdminCotroller() {
		System.out.println("in admin controller");
	}

	@GetMapping
	public List<User> getAllUsers() {
		System.out.println("in get all users");
		return dao.getAllUsers();
	}

	@DeleteMapping("/{user_id}")
	public ResponseEntity<String> delEmpDetails(@PathVariable int user_id) {

		System.out.println("in del user " + user_id);
		try {
			return new ResponseEntity<String>(dao.removeUser(user_id), HttpStatus.OK);
		} catch (RuntimeException e1) {
			e1.printStackTrace();
			return new ResponseEntity<String>(e1.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
