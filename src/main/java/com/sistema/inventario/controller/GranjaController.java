package com.sistema.inventario.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.inventario.dao.IGranjaRepository;
import com.sistema.inventario.dao.IHuevoRepository;
import com.sistema.inventario.dao.IPolloRepository;
import com.sistema.inventario.models.Granja;
import com.sistema.inventario.models.Huevo;
import com.sistema.inventario.models.Pollo;


@Controller
public class GranjaController {
	
	@Autowired
	private IGranjaRepository granjaRepository;
	
	@Autowired
	private IHuevoRepository huevoRepository;
	
	@Autowired
	private IPolloRepository polloRepository;

	
	@GetMapping(value = "/comprar")
	public String comprar(Model model)
	{		

		
		
		Granja granja= granjaRepository.findAll().get(0);
		model.addAttribute("granja", granja);
		
		
		return "comprar";
	}
	
	
	
	
	
	@GetMapping(value = "/granja")
	public String granja(Model model)
	{
		Granja granja= granjaRepository.findAll().get(0);
		model.addAttribute("granja", granja);
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
		Granja granja= granjaRepository.findAll().get(0);
		model.addAttribute("granja", granja);
		return "modificarGranja";
	}
	
	@GetMapping(value = "/vender")
	public String vender(Model model)
	{
		Granja granja= granjaRepository.findAll().get(0);
		model.addAttribute("granja", granja);
		return "vender";
	}
	
	@PostMapping(value="/procesarVenta")
	public String procesarVenta(HttpServletRequest request, Model model)
	{	
		List<String> errores= new ArrayList<>();
		Granja granja= granjaRepository.findAll().get(0);
		int cantidad=Integer.parseInt(request.getParameter("cantidad")), 
			dias=Integer.parseInt(request.getParameter("dias"));	
			String producto=request.getParameter("producto").replaceAll("\"", "");				
		Map<String,String> map=mapHuevos();
			
			if(producto.equals("pollo"))			
				map=mapPollos();
			
			if(map.get(String.valueOf(dias))==null)
				errores.add("No se encuentran "+producto+"s de "+dias+" dias de vida");
			else
			{
				
				
				
				if(cantidad>Integer.valueOf(map.get(String.valueOf(dias))))
				{
					errores.add("Se encuentran solo "+map.get(String.valueOf(dias))+" unidades de "+dias+" dias");
				}
			}
			
			
			model.addAttribute("granja",granja);
			model.addAttribute("errores", errores);
			System.out.println(map);
			
			System.out.println(cantidad);
			System.out.println(dias);
			System.out.println(producto);
			
				 
				
		
		return "vender";
	
	}
	
	@GetMapping(value = "/verHuevos")
	public String verHuevos(Model model)
	{
				
		
		model.addAttribute("huevos", mapHuevos());
		return "verHuevos";
	}	
	
	@GetMapping(value = "/verPollos")
	public String verPollos(Model model)
	{
		
				 
		model.addAttribute("pollos", mapPollos());
		
		return "verPollos";
	}
	
	

	
	
	
	
	@PostMapping(value="/procesarModificarGranja")
	public String procesarModificarGranja(HttpServletRequest request, Model model)
	{	
		List<String> errores= new ArrayList<>();
		Granja granja= granjaRepository.findAll().get(0);
		
		
		String producto="";
		if(request.getParameter("telefono") != null && request.getParameter("capacidadPollos") != null && request.getParameter("capacidadHuevos")!=null && request.getParameter("nombre")!=null && request.getParameter("direccion")!=null && request.getParameter("dinero")!=null && request.getParameter("precioVentaHuevo")!=null && request.getParameter("precioVentaPollo")!=null)
		{
			int telefono= Integer.parseInt(request.getParameter("telefono")),
			capacidadPollos= Integer.parseInt(request.getParameter("capacidadPollos")),
			capacidadHuevos= Integer.parseInt(request.getParameter("capacidadHuevos")),
					diasCompraPollo= Integer.parseInt(request.getParameter("diasDeCompraPollo")),
							diasCompraHuevo= Integer.parseInt(request.getParameter("diasDeCompraHuevo"));
			
			
			String nombre= request.getParameter("nombre"),
					direccion= request.getParameter("direccion");
			
			double dinero=Double.parseDouble((request.getParameter("dinero"))), 
					precioVentaPollo=Double.parseDouble((request.getParameter("precioVentaPollo"))),
					precioVentaHuevo=Double.parseDouble((request.getParameter("precioVentaHuevo"))), 
					precioCompraPollo=Double.parseDouble((request.getParameter("precioCompraPollo"))),
					precioCompraHuevo=Double.parseDouble((request.getParameter("precioCompraHuevo")));
					
					
			Granja granjaNueva=new Granja(nombre, telefono, direccion, dinero, capacidadHuevos, capacidadPollos, precioVentaPollo, precioVentaHuevo, precioCompraPollo, precioCompraHuevo, diasCompraPollo, diasCompraHuevo);
			
				
			granjaRepository.deleteAll();
			granjaRepository.save(granjaNueva);
		
		
		}
		
		return "redirect:/granja";
	
	}
	
	
	
	
	
	private Map<String, String> mapHuevos()
	{
		Map<String, String> huevosPorDia = new HashMap<String, String>();
		
		 for (String valor : huevoRepository.cantidadDias()) {
			 
			 huevosPorDia.put(String.valueOf(valor.charAt(0)), String.valueOf(valor.charAt(2)));
		}		 
		 
		 return huevosPorDia;
	}
	
	private Map<String, String> mapPollos()
	{
		Map<String, String> huevosPorDia = new HashMap<String, String>();
		
		 for (String valor : polloRepository.cantidadDias()) {
			 
			 huevosPorDia.put(String.valueOf(valor.charAt(0)), String.valueOf(valor.charAt(2)));
		}		 
		 
		 return huevosPorDia;
	}
	
	
	
}







