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

	
	
	
	@GetMapping(value = "/granja")
	public String granja(Model model)
	{
		Granja granja= granjaRepository.findAll().get(0);
		
		int cantidadPollos=polloRepository.findAll().size(),
			cantidadHuevos= huevoRepository.findAll().size();
		
		model.addAttribute("cantidadPollos", cantidadPollos);
		model.addAttribute("cantidadHuevos", cantidadHuevos);
		
		model.addAttribute("granja", granja);
		return "granja";
	}
	
	@GetMapping({"/index","/","", "/home"})
	public String index(Model model)
	{
		Granja granja= granjaRepository.findAll().get(0);
		model.addAttribute("granja", granja);
		return "index";
	}
	
	@GetMapping(value = "/modificarGranja")
	public String modificarGranja(Model model)
	{
		Granja granja= granjaRepository.findAll().get(0);
		model.addAttribute("granja", granja);
		return "modificarGranja";
	}
	
	@PostMapping(value="/procesarModificarGranja")
	public String procesarModificarGranja(HttpServletRequest request, Model model)
	{	
		List<String> errores= new ArrayList<>();
		Granja granja= granjaRepository.findAll().get(0);
		
		
		String producto="";
		
		try {
			long telefono= Long.parseLong(request.getParameter("telefono"));
			
			int capacidadPollos= Integer.parseInt(request.getParameter("capacidadPollos")),
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
			
						
					
			Granja granjaNueva=new Granja(granja.getId(), nombre, telefono, direccion, dinero, capacidadHuevos, capacidadPollos, precioVentaPollo, precioVentaHuevo, precioCompraPollo, precioCompraHuevo, diasCompraPollo, diasCompraHuevo);
			
			
			
			granjaRepository.save(granjaNueva);
		} catch (Exception e) {
			model.addAttribute("errores", "Ingrese valores validos");
			model.addAttribute("granja", granja);
			return "modificarGranja";
		}
			
		
		
		return "redirect:/granja";
	
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
			String producto=request.getParameter("producto"),
				   operacion=request.getParameter("operacion");
		int i=0;
		
		Map<String,String> map=mapHuevos();
			
		
			if(producto.equals("pollo"))			
				map=mapPollos();
			
			
			if(map.get(String.valueOf(dias))==null)
				errores.add("No se encuentran "+producto+"s de "+dias+" dias de vida");
			else
			{				
				if(cantidad>Integer.valueOf(map.get(String.valueOf(dias))))				
					errores.add("Se encuentran solo "+map.get(String.valueOf(dias))+" "+ producto+ " de "+dias+" dias");				
			}
			
			
			model.addAttribute("granja",granja);
			model.addAttribute("errores", errores);
			
			
			if(errores.isEmpty())
			{
				if(producto.equals("huevo"))
				{
					for (Huevo huevo : huevoRepository.findAll()) {
						
						if(huevo.getDias()==dias && i<cantidad)
						{
							huevoRepository.delete(huevo);
							i++;
						}
					}	
				
					granja.setDinero(granja.getDinero()+granja.getPrecioVentaHuevo()*cantidad);
				}
				else
				{
					for (Pollo pollo : polloRepository.findAll()) {
						
						if(pollo.getDias()==dias && i<cantidad)
						{
							polloRepository.delete(pollo);
							i++;
						}
					}	
				
					granja.setDinero(granja.getDinero()+granja.getPrecioVentaHuevo()*cantidad);
				}
				
				
				granjaRepository.save(granja);
				
				return "redirect:/vender";
			}
				
			
			return "vender";
	
	}
	
	
	@GetMapping(value = "/comprar")
	public String comprar(Model model)
	{
		Granja granja= granjaRepository.findAll().get(0);
		model.addAttribute("granja", granja);
		return "comprar";
	}
	
	
	@PostMapping(value="/procesarCompra")
	public String procesarCompra(HttpServletRequest request, Model model)
	{	
		List<String> errores= new ArrayList<>();
		Granja granja= granjaRepository.findAll().get(0);
		int cantidad=Integer.parseInt(request.getParameter("cantidad")); 			
			String producto=request.getParameter("producto");
		int i=0;
		
		Map<String,String> map=null;
			
		
			if(producto.equals("pollo"))
			{
				map=mapPollos();
				
				if(cantidad * granja.getPrecioCompraPollo() > granja.getDinero())				
					errores.add("No dispone del dinero suficiente, puede comprar solo " +  granja.getDinero()/granja.getPrecioCompraPollo());
			}
			else
			{
				map=mapHuevos();
				
				if(cantidad * granja.getPrecioCompraHuevo() > granja.getDinero())				
					errores.add("No dispone del dinero suficiente, puede comprar solo " +  granja.getDinero()/granja.getPrecioCompraHuevo());
				
			}
			
			
			model.addAttribute("granja",granja);
			model.addAttribute("errores", errores);
			
			if(errores.isEmpty())
			{
				if(producto.equals("pollo"))
				{
					for(i=0; i<cantidad; i++)					
						polloRepository.save(new Pollo(granja.getDiasDeCompraPollo()));
					
					
					granja.setDinero(granja.getDinero()-granja.getPrecioCompraPollo()*cantidad);
					
				}
				else
				{
					for(i=0; i<cantidad; i++)					
						huevoRepository.save(new Huevo(granja.getDiasDeCompraPollo()));
					
					
					granja.setDinero(granja.getDinero()-granja.getPrecioCompraHuevo()*cantidad);
					
				}
				
				granjaRepository.save(granja);
				return "redirect:/comprar";
			
			}
				
			
			return "comprar";
	
	}
	
	@GetMapping(value = "/verHuevos")
	public String verHuevos(Model model)
	{
		Granja granja= granjaRepository.findAll().get(0);		
		model.addAttribute("granja", granja);
		model.addAttribute("huevos", mapHuevos());
		return "verHuevos";
	}	
	
	@GetMapping(value = "/verPollos")
	public String verPollos(Model model)
	{
		
		Granja granja= granjaRepository.findAll().get(0);		
		model.addAttribute("granja", granja);		 
		model.addAttribute("pollos", mapPollos());
		
		return "verPollos";
	}
	
	
	@GetMapping(value = "/pasarDia")
	public String pasarDia(Model model)
	{
				
		model.addAttribute("granja", granjaRepository.findAll().get(0));		 
		
		System.out.println("ESTOY PASANDO UN DIA");
		System.out.println("ESTOY PASANDO UN DIA");
		System.out.println("ESTOY PASANDO UN DIA");
		System.out.println("ESTOY PASANDO UN DIA");
		System.out.println("ESTOY PASANDO UN DIA");
		
		
		return "redirect:/granja";
	}
	
	
	
	private Map<String, String> mapHuevos()
	{
		Map<String, String> huevosPorDia = new HashMap<String, String>();
		
		 for (String valor : huevoRepository.cantidadDias()) {
			 
			 String[] parts = valor.split(",");
			 huevosPorDia.put(parts[0], parts[1]);
		}		 
		 
		 return huevosPorDia;
	}
	
	private Map<String, String> mapPollos()
	{
		Map<String, String> pollosPorDia = new HashMap<String, String>();
		
		 for (String valor : polloRepository.cantidadDias()) {
			 
			 String[] parts = valor.split(",");
			 pollosPorDia.put(parts[0], parts[1]);
		}		
		 
		 return pollosPorDia;
	}
	
	
	
}







