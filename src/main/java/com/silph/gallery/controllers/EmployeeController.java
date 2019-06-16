package com.silph.gallery.controllers;

import javax.validation.Valid;

import com.silph.gallery.model.Cart;
import com.silph.gallery.model.Employee;
import com.silph.gallery.validators.EmployeeValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * EmployeeController
 */
@Controller
@SessionAttributes({"cart"})
public class EmployeeController {

	@Autowired
    private EmployeeValidator employeeValidator;
    
    @ModelAttribute("cart")
	public Cart cart() {
			return new Cart();
	}

    @GetMapping("/login")
    public String login (@ModelAttribute ("cart") Cart cart, Model model) {
        model.addAttribute("employee", new Employee());
      return "pages/login.html";
    }
      
    @RequestMapping(value = "/loginEmployee", method = RequestMethod.POST)
    public String loginEmployee(@ModelAttribute ("cart") Cart cart, @Valid @ModelAttribute("employee") Employee employee, Model model, BindingResult br) {
        this.employeeValidator.validate(employee, br);
        if (!br.hasErrors()) {
          model.addAttribute("currentEmployee", employee);
            return "pages/employeeDashboard.html";
        }
      else
            return "pages/login.html";
    }
    
}