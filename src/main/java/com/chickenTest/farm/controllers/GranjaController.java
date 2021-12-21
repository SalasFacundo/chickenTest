package com.chickenTest.farm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chickenTest.farm.dao.GranjaDao;
import com.chickenTest.farm.models.Granja;



@RestController
public class GranjaController {
	
	@Autowired
	private GranjaDao granjaDao;
	
	
	
	
	@RequestMapping(value="api/granja")
	public List<Granja> getGranja()
	{				
		return granjaDao.datos();
	}
	
	@RequestMapping(value="/vender")
	public String vender()
	{				
		return "vender";
	}
	
	
	
	
	

}






