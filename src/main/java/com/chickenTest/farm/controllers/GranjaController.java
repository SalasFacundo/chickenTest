package com.chickenTest.farm.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.chickenTest.farm.dao.GranjaDao;
import com.chickenTest.farm.models.Granja;



@Controller
public class GranjaController {
	
	@Autowired
	private GranjaDao granjaDao;
	
	
	
	
	@RequestMapping(value="api/granja")
	public List<Granja> getGranja()
	{				
		return granjaDao.datos();
	}
	
	
	@RequestMapping(value = "/comprar")
	public String comprar(Model model)
	{		
		return "comprar";
	}
	
	@RequestMapping(value = "/granja")
	public String granja(Model model)
	{
		return "granja";
	}
	
	@RequestMapping(value = "/index")
	public String index(Model model)
	{
		return "index";
	}
	
	@RequestMapping(value = "/modificarGranja")
	public String modificarGranja(Model model)
	{
		return "modificarGranja";
	}
	
	@RequestMapping(value = "/vender")
	public String vender(Model model)
	{
		return "vender";
	}
	
	@RequestMapping(value = "/verHuevos")
	public String verHuevos(Model model)
	{
		return "verHuevos";
	}	
	
	@RequestMapping(value = "/verPollos")
	public String verPollos(Model model)
	{
		return "verPollos";
	}
	
	

	
	@RequestMapping(value="/procesarVenta")
	public String procesarVenta(HttpServletRequest request, Model model)
	{	
		System.out.println("entra");
		String producto="";
		if(request.getParameter("cantidad") != null && request.getParameter("dias") != null && request.getParameter("producto")!=null)
		{
			int cantidad= Integer.parseInt(request.getParameter("cantidad"));
			int dias= Integer.parseInt(request.getParameter("dias"));
			 producto= request.getParameter("producto");
			
			model.addAttribute("cantidad",cantidad);
			model.addAttribute("dias",dias);
			model.addAttribute("producto",producto);
			
		
			System.out.println(cantidad);
			System.out.println(dias);
			System.out.println(producto);
		}
		
		
		return "vender";
	}
	
	
	

}






