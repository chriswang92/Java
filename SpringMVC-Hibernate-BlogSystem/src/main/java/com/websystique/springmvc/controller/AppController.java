package com.websystique.springmvc.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.poi.hssf.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.websystique.springmvc.dao.UserDao;
import com.websystique.springmvc.model.Blog;
import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.BlogService;
import com.websystique.springmvc.service.EmployeeService;
import com.websystique.springmvc.service.UserService;

import sun.security.jgss.LoginConfigImpl;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	UserService userService;
	@Autowired
	EmployeeService service;
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/userRegister" }, method = RequestMethod.GET)
	public String userRegister(ModelMap model) {

		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		
		return "userRegistration";
	}
	
	@RequestMapping(value = { "/userRegister" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "userRegistration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [Employee].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!userService.isUsernameUnique(user.getId(), user.getUsername())){
			FieldError ssnError =new FieldError("user","username",
					messageSource.getMessage("non.unique.username", new String[]{user.getUsername()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "userRegistration";
		}
		
		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getUsername() + " registered successfully");
		return "success";
	}
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String login(ModelMap model) {
		User user = new User();
		model.addAttribute("user",user);
		return "login";
	}
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.POST)
	public String login(@Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			System.out.println("result has errors");
			return "login";
		}
		String username = user.getUsername();
		String password = user.getPassword();
		if(userService.checkLogin(username, password)){
			if(!model.containsKey("current_username")){
				
				model.addAttribute("current_username", username);
			}else {
				model.put("current_username", username);
			}
			System.out.println("checklogin successed...");
			return "userMainpage";
		}else{
			model.addAttribute("loginFailedMessage", "username/password wrong!");
			return "login";
		}
		
	}
	
	/*
	 * This method  shows the home page
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String home(ModelMap model) {

		List<Employee> employees = service.findAllEmployees();
		List<Employee> topEmployees = employees.subList(0, employees.size()-1);
		model.addAttribute("topEmployees", topEmployees);
		return "index";
	}
	
	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/userlist" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "allusers";
	}
	
	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {

		List<Employee> employees = service.findAllEmployees();
		model.addAttribute("employees", employees);
		return "allemployees";
	}

	/*
	 * This method will provide the medium to add a new employee.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newEmployee(ModelMap model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveEmployee(@Valid Employee employee, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [Employee].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
			FieldError ssnError =new FieldError("employee","ssn",
					messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "registration";
		}
		
		service.saveEmployee(employee);

		model.addAttribute("success", "Employee " + employee.getName() + " registered successfully");
		return "success";
	}


	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable String ssn, ModelMap model) {
		Employee employee = service.findEmployeeBySsn(ssn);
		model.addAttribute("employee", employee);
		model.addAttribute("edit", true);
		return "registration";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{ssn}-employee" }, method = RequestMethod.POST)
	public String updateEmployee(@Valid Employee employee, BindingResult result,
			ModelMap model, @PathVariable String ssn) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
			FieldError ssnError =new FieldError("employee","ssn",
					messageSource.getMessage("non.unique.ssn", new String[]{employee.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "registration";
		}

		service.updateEmployee(employee);

		model.addAttribute("success", "Employee " + employee.getName()	+ " updated successfully");
		return "success";
	}

	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{ssn}-employee" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String ssn) {
		service.deleteEmployeeBySsn(ssn);
		return "redirect:/list";
	}
	
	@RequestMapping(value = {"/allBlogs-{username}"}, method = RequestMethod.GET)
	public String listBlogs(@PathVariable String username, ModelMap model){
		
		if(!model.containsKey("current_username")){
			model.addAttribute("current_username", username);
		}else {
			model.put("current_username", username);
		}
		List<Blog> allBlogsUnderUsername = blogService.findAllBlogsUnderUsername(username);
		model.addAttribute("allBlogs", allBlogsUnderUsername);
		model.addAttribute("current_username",username);
		
		return "allBlogs";
		
	}
	
	
	
	@RequestMapping(value = {"/newBlog-{username}"}, method = RequestMethod.GET)
	public String newBlog(@PathVariable String username,ModelMap model){
		Blog blog = new Blog();
		blog.setUser(userService.findUserByUsername(username));
		model.addAttribute("blog", blog);
		model.addAttribute("curr_userid",userService.findUserByUsername(username).getId());
		model.addAttribute("edit",false);
		return "addBlog";
		
	}
	@RequestMapping(value = {"/newBlog-{username}"}, method = RequestMethod.POST)
	public String saveBlog(@Valid Blog blog, BindingResult result,
			ModelMap model,@PathVariable String username) {

		if (result.hasErrors()) {
			return "addBlog";
		}
		blog.setUser(userService.findUserByUsername(username));
		
		blogService.saveBlog(blog);

		model.addAttribute("current_username", blog.getUser().getUsername());
		
		model.addAttribute("success", "blog " + blog.toString() + " added successfully");
		return "success";
	}
	
	
	/*
	 * This method will provide the medium to update an existing Blog.
	 */
	@RequestMapping(value = { "/edit-{blog_id}-blog" }, method = RequestMethod.GET)
	public String editBlog(@PathVariable int blog_id, ModelMap model) {
		Blog blog = blogService.findBlogById(blog_id);
		model.addAttribute("blog", blog);
		model.addAttribute("edit", true);
		
		return "addBlog";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating Blog in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{blog_id}-blog" }, method = RequestMethod.POST)
	public String updateBlog(@Valid Blog blog, BindingResult result,
			ModelMap model, @PathVariable int blog_id) {
		
		if (result.hasErrors()) {
			return "addBlog";
		}

		Blog updatedBlog = blogService.updateBlog(blog);
		
		String user = updatedBlog.getUser().getUsername();
		model.addAttribute("current_username", user);
		model.addAttribute("success", blog.toString()	+ " updated successfully "+user);
		return "success";
	}

	
	/*
	 * This method will delete an Blog by it's blog_id value.
	 */
	@RequestMapping(value = { "/delete-{blog_id}-blog" }, method = RequestMethod.GET)
	public String deleteBlog(@PathVariable int blog_id, ModelMap model) {
		String current_username = blogService.findBlogById(blog_id).getUser().getUsername();
		blogService.deleteBlogById(blog_id);
		return "redirect:/allBlogs-" + current_username;
	}
	
	
}
