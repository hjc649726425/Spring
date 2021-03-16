package com.hjc.controller;

import com.hjc.anno.TestParam;
import com.hjc.anno.TestReturn;
import com.hjc.model.Person;
import com.hjc.model.User;
import com.hjc.test.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	Integer age = 0;

	@GetMapping("test")
	@ResponseBody
	public String test(){
		return "test";
	}

	@GetMapping("adduser")
	@ResponseBody
	public String adduser(@RequestBody User u){
		return "test";
	}

	@GetMapping("test/{name}")
	@ResponseBody
	public String test1(@PathVariable(name = "name") String name){
		return name;
	}

	@GetMapping("test45/{age}")
	@ResponseBody
	public int test2(int age){
		return age;
	}

	@GetMapping("getPerson")
	//@ResponseBody
	@TestReturn
	public Person getTest(@TestParam(name = "test")String name){
		Person p = new Person();
		p.setName(name);
		return p;
	}

	@GetMapping("getUser")
	@ResponseBody
	public User getUserTest(){
		User u = new User();
		u.setAge(19);
		u.setName("张三");
		return u;
	}

	@GetMapping("myindex")
	public ModelAndView index(@ModelAttribute("user") User u){
		System.out.println(u);
		ModelAndView mv = new ModelAndView();
		mv.getModel().put("name", "张三");
		mv.setViewName("abc");
		return mv;
	}

	@GetMapping("myindex2")
	public String index2(@ModelAttribute("user") User u){
		//return "redirect:myindex";
		return "forward:/views/abc.html";
	}

	@ModelAttribute
	public void testModelAttribute(){
		System.out.println("testModelAttribute");
	}

	/*不指定指定属性名称，方法返回一个对象(其中键为该对对象的小写形式)，相当于model.addAttribute("user", user1)*/
	@ModelAttribute("user")
	public User getUser(){
		System.out.println("getUser");
		User u = new User();
		u.setName("张三");
		u.setAge(++age);
		return u;
	}
}
