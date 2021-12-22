package com.sistema.inventario.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.inventario.dao.IGranjaRepository;
import com.sistema.inventario.models.Granja;


@Controller
public class GranjaController {
	
	@Autowired
	private IGranjaRepository granjaRepository;

	
	@GetMapping(value = "/comprar")
	public String comprar(Model model)
	{		
		Granja granja1= new Granja("Pollos Hermanos", 123456789, 20, 10, 500, 100 );
		
		//granjaRepository.save(granja1);
		List<Granja> listaGranja= granjaRepository.findAll();
		System.out.println(listaGranja);
		
		
		return "comprar";
	}
	
	@GetMapping(value = "/granja")
	public String granja(Model model)
	{
		return "granja";
	}
	
	@GetMapping(value = "/index")
	public String index(Model model)
	{
		return "index";
	}
	
	@GetMapping(value = "/modificarGranja")
	public String modificarGranja(Model model)
	{
		return "modificarGranja";
	}
	
	@GetMapping(value = "/vender")
	public String vender(Model model)
	{
		return "vender";
	}
	
	@GetMapping(value = "/verHuevos")
	public String verHuevos(Model model)
	{
		return "verHuevos";
	}	
	
	@GetMapping(value = "/verPollos")
	public String verPollos(Model model)
	{
		return "verPollos";
	}
	
	

	
	@PostMapping(value="/procesarVenta")
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
		
		return "redirect:/vender";
	
	}
	
	
	

}







