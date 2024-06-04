package com.data.spring;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserRepository ur;
	
	
	@GetMapping
	public Page<User> getAllUsers(@PageableDefault ( sort="email" , direction= Sort.Direction.ASC)Pageable pageable){
		
		return ur.findAll(pageable);
		
	}
	
	
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable int id){
		
		return ur.findById(id);
	}
	
	@PostMapping
	public User saveUser(@RequestBody User user) {
		
		return ur.save(user);
	}
	
	
	@PutMapping("/{id}")
	public User updateUser(@PathVariable int id , @RequestBody User userdetails)
	{
		User user = ur.findById(id).orElseThrow(()->new RuntimeException("Invalid "+id));
		
		user.setEmail(userdetails.getEmail());
		user.setPassword(userdetails.getPassword());
		
		
		return ur.save(user);
		
	}
	
	
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id)
	{
		
		ur.deleteById(id);
	}
	
}
