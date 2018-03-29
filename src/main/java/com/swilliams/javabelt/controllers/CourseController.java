package com.swilliams.javabelt.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.swilliams.javabelt.models.Course;
import com.swilliams.javabelt.models.User;
import com.swilliams.javabelt.models.courses_users;
import com.swilliams.javabelt.services.CUService;
import com.swilliams.javabelt.services.CourseService;
import com.swilliams.javabelt.services.UserService;

@Controller
@RequestMapping("/courses")
public class CourseController {
	private UserService uS;
	private CourseService cS;
	private CUService CUS;
	
	public CourseController(UserService uS,CourseService cS,CUService CUS) {
		this.uS = uS;
		this.cS = cS;
		this.CUS = CUS;
	}

	
	@RequestMapping("")
	public String courses(Model model, HttpSession session) {
		if(session.getAttribute("id") != null) {
			Optional<User> user = uS.find((Long) session.getAttribute("id"));
			User u = user.get();
			
			model.addAttribute("user",u);
			List<Course> allCourse = cS.all();
			
			model.addAttribute("courses",allCourse);
			
			return "courses.jsp";
		}
		return "redirect:/users/new";
	}
	
	@RequestMapping("/new")
	public String newCourse(Model model, HttpSession session, @ModelAttribute("course") Course course) {
		if(session.getAttribute("id") == null) {
			return "redirect:/courses";
		}	
		return "newCourse.jsp";
	}
	
	@PostMapping("/new")
	public String createCourse(@Valid @ModelAttribute("course") Course course, BindingResult res, RedirectAttributes flash) {
		if (res.hasErrors()) {
			flash.addFlashAttribute("errors", res.getAllErrors());
			return "redirect:/courses/new";
		}
		
		cS.create(course);

		return "redirect:/courses";

	}
	
	@RequestMapping("/add/{course_id}")
	public String addCourse(@PathVariable("course_id") Long course_id,HttpSession session) {
		if(session.getAttribute("id") == null) {
			return "redirect:/users/new";
		}
		Optional<User> user = uS.find((Long) session.getAttribute("id"));
		User u = user.get();
		
		Optional<Course> course = cS.find(course_id);
		Course c = course.get();
		
		int cLimit = c.getClassLimit();
		int signups = c.getSignups();
		
		if(cLimit < (signups+1)) {
			return "redirect:/courses/add/"+course_id;
		}else {
			
			courses_users cuse = new courses_users(u,c);

			CUS.create(cuse);
			
			signups++;
			c.setSignups(signups);
			cS.update(c);
			
			return "redirect:/courses/view/"+course_id;		
		}
	}
	
	@RequestMapping("/view/{course_id}")
	public String viewCourse(@PathVariable("course_id")Long course_id, Model model, HttpSession session) {
		if(session.getAttribute("id") == null) {
			return "redirect:/users/new";
		}
		Optional<User> user = uS.find((Long) session.getAttribute("id"));
		User u = user.get();
		
		Optional<Course> course = cS.find(course_id);
		Course c = course.get();
		model.addAttribute("course",c);
		model.addAttribute("user",u);
		
		return "course.jsp";
	}
	
	@RequestMapping("/remove/{course_id}")
	public String removeCourse(@PathVariable("course_id")Long course_id,Model model, HttpSession session) {
		Optional<User> user = uS.find((Long) session.getAttribute("id"));
		User u = user.get();
		
		Optional<Course> course = cS.find(course_id);
		Course c = course.get();
		
		int signups = c.getSignups();
		signups--;
		c.setSignups(signups);
		
		CUS.deletefrom(c.getId(), u.getId());
		
		return "redirect:/courses/view/"+course_id;
	}
	
	@RequestMapping("/delete/{course_id}")
	public String deleteCourse(@PathVariable("course_id")Long course_id) {
		cS.destroy(course_id);
		return "redirect:/courses";
	}
	
	
	@RequestMapping("/edit/{course_id}")
	public String editCourse(@PathVariable("course_id")Long course_id, @ModelAttribute("course") Course course,Model model) {

		Optional<Course> thisCourse = cS.find(course_id);
		model.addAttribute("course",thisCourse.get());
		return "editCourse.jsp";
	}
	
	
	
	@PostMapping("/edit/{course_id}")
	public String updateCourse(@Valid @ModelAttribute("course") Course course, BindingResult res, RedirectAttributes flash, @PathVariable("course_id")Long course_id) {
		if (res.hasErrors()) {
			System.out.println("Made it to the edit postmapping");
			flash.addFlashAttribute("errors", res.getAllErrors());
			return "redirect:/courses/edit/"+course_id;
		}
		System.out.println("Made it to the edit postmapping");
		cS.update(course);
		
		return "redirect:/courses";
	}

	@RequestMapping("/low")
	public String sortLow(Model model,HttpSession session) {
		List<Course> allCourse = cS.all();
		
        Course temp;
        for(int i=0; i<allCourse.size(); i++) {
            for(int j=1; j<(allCourse.size()-i); j++) {
                if(allCourse.get(j-1).getSignups() >= allCourse.get(j).getSignups() ) {
                    temp = allCourse.get(j-1);
                    allCourse.set(j-1, allCourse.get(j));
                    allCourse.set(j, temp);
                }
            }
        }
		model.addAttribute("courses",allCourse);
		Optional<User> user = uS.find((Long) session.getAttribute("id"));
		User u = user.get();
		
		model.addAttribute("user",u);
		return "courses.jsp";
	}
	
	@RequestMapping("/high")
	public String sortHigh(Model model,HttpSession session) {
		List<Course> allCourse = cS.all();
		
        Course temp;
        for(int i=0; i<allCourse.size(); i++) {
            for(int j=1; j<(allCourse.size()-i); j++) {
                if(allCourse.get(j-1).getSignups() < allCourse.get(j).getSignups() ) {
                    temp = allCourse.get(j-1);
                    allCourse.set(j-1, allCourse.get(j));
                    allCourse.set(j, temp);
                }
            }
        }
		model.addAttribute("courses",allCourse);
		Optional<User> user = uS.find((Long) session.getAttribute("id"));
		User u = user.get();
		
		model.addAttribute("user",u);
		return "courses.jsp";
	}
	
	@RequestMapping("/lowDate/{course_id}")
	public String sortDateLow(@PathVariable("course_id")Long course_id, Model model) {
		Optional<Course> course = cS.find(course_id);
		Course c = course.get();
		
        courses_users temp;
        for(int i=0; i<c.getCourses_users().size(); i++) {
            for(int j=1; j<(c.getCourses_users().size()-i); j++) {
                if(c.getCourses_users().get(j-1).getCreatedAt().after(c.getCourses_users().get(j).getCreatedAt())) {
                    temp = c.getCourses_users().get(j-1);
                    c.getCourses_users().set(j-1, c.getCourses_users().get(j));
                    c.getCourses_users().set(j, temp);
                }
            }
        }
		model.addAttribute("course",c);

		return "course.jsp";
	}
	
	@RequestMapping("/highDate/{course_id}")
	public String sortDateHigh(@PathVariable("course_id")Long course_id, Model model) {
		Optional<Course> course = cS.find(course_id);
		Course c = course.get();
		
        courses_users temp;
        for(int i=0; i<c.getCourses_users().size(); i++) {
            for(int j=1; j<(c.getCourses_users().size()-i); j++) {
                if(c.getCourses_users().get(j-1).getCreatedAt().before(c.getCourses_users().get(j).getCreatedAt())) {
                    temp = c.getCourses_users().get(j-1);
                    c.getCourses_users().set(j-1, c.getCourses_users().get(j));
                    c.getCourses_users().set(j, temp);
                }
            }
        }
		model.addAttribute("course",c);
		
		return "course.jsp";
	}
}
