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

		Granja granja= granjaRepository.findAll().get(0);		//Recibo solo el primer objeto de la lista que recibe la base de datos, ya que siempre habra una sola granja

		
		int cantidadPollos=polloRepository.findAll().size(),
			cantidadHuevos= huevoRepository.findAll().size();	
		
		model.addAttribute("cantidadPollos", cantidadPollos);	//cantidad de pollos y huevos los adjunto separado a granja, ya que son tablas distintas.
		model.addAttribute("cantidadHuevos", cantidadHuevos);
		

		model.addAttribute("granja", granja);					//adjunto el objeto de granja con todos sus datos para poder usarlos en el template
		return "granja";										//llamo al template granja.html

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
	public String procesarModificarGranja(HttpServletRequest request, Model model)			//uso HttpServletRequest para recibir los datos del formulario
	{	
		
		Granja granja= granjaRepository.findAll().get(0);
		
		
		String producto="";
		
		try {
			long telefono= Long.parseLong(request.getParameter("telefono"));							//Recibo los datos del formulario
			
			int capacidadPollos= Integer.parseInt(request.getParameter("capacidadPollos")),
				capacidadHuevos= Integer.parseInt(request.getParameter("capacidadHuevos")),
				diasCompraPollo= Integer.parseInt(request.getParameter("diasDeCompraPollo")),
				diasCompraHuevo= Integer.parseInt(request.getParameter("diasDeCompraHuevo")),
				diasPolloEnMorir= Integer.parseInt(request.getParameter("diasPolloEnMorir")),
				diasPolloEnPonerHuevo= Integer.parseInt(request.getParameter("diasPolloEnPonerHuevo")),
				diasHuevoEnDarPollo= Integer.parseInt(request.getParameter("diasHuevoEnDarPollo"));
			
			
			String nombre= request.getParameter("nombre"),
					direccion= request.getParameter("direccion");
			
			double dinero=Double.parseDouble((request.getParameter("dinero"))), 
					precioVentaPollo=Double.parseDouble((request.getParameter("precioVentaPollo"))),
					precioVentaHuevo=Double.parseDouble((request.getParameter("precioVentaHuevo"))), 
					precioCompraPollo=Double.parseDouble((request.getParameter("precioCompraPollo"))),
					precioCompraHuevo=Double.parseDouble((request.getParameter("precioCompraHuevo")));
			
						
																												//Creo el objeto nuevo de granja
			Granja granjaNueva=new Granja(granja.getId(), nombre, telefono, direccion, dinero, capacidadHuevos, capacidadPollos, precioVentaPollo, precioVentaHuevo, precioCompraPollo, precioCompraHuevo, diasCompraPollo, diasCompraHuevo,diasPolloEnMorir,diasPolloEnPonerHuevo,diasHuevoEnDarPollo);
			
			
			
			granjaRepository.save(granjaNueva);																	//Actualizo en la base de datos
		} catch (Exception e) {
			model.addAttribute("errores", "Ingrese valores validos");											//Si sucede un error a pesar de las validaciones html, mostrata el error sin romper
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
			   peracion=request.getParameter("operacion");
		
		int cantidadDisponibleParaVender, i=0;
		
		final double limiteDinero=9999999.99;												//Uso constante para limite de dinero recaudado en granja
		
		
		
		Map<String,String> map=mapHuevos();													//llamo al metodo que devuelve el mapa de huevos organizado por dias y cantidad
			
		
			if(producto.equals("pollo"))			
				map=mapPollos();
			
			
			if(map.get(String.valueOf(dias))==null)											//Si al buscar en el map por la key, devuelve null entonces no se encuentran pollos de esos dias de vida
				errores.add("No se encuentran "+producto+"s de "+dias+" dias de vida");
			else 
			{				
				if(cantidad>Integer.valueOf(map.get(String.valueOf(dias))))					//Si la cantidad solicitada es mayor de la disponible muestra un mensaje diciendo cuantos hay			
					errores.add("Se encuentran solo "+map.get(String.valueOf(dias))+" "+ producto+ " de "+dias+" dias");				
			}
			
				if(producto.equals("pollo"))
				{
					
					if(cantidad*granja.getPrecioVentaPollo()+granja.getDinero()>limiteDinero)		//calculo el valor de lo que recaudaria la venta para saber si supera el limite
					{						
					
					cantidadDisponibleParaVender=(int) ( (limiteDinero-granja.getDinero())/granja.getPrecioVentaPollo());		//calculo cuantos productos se pueden vender sin superar el limite de dinero
					
				
					if(cantidadDisponibleParaVender==1)
						errores.add("El monto supera el limite de recaudacion, puede vender "+ cantidadDisponibleParaVender + " pollo");
					else
						errores.add("El monto supera el limite de recaudacion, puede vender "+ cantidadDisponibleParaVender + " pollos");
					
					}
				}
					
				else	
				{
					if(cantidad*granja.getPrecioVentaHuevo()+granja.getDinero()>limiteDinero)
					{
						
					
						cantidadDisponibleParaVender=(int) ( (limiteDinero-granja.getDinero())/granja.getPrecioVentaHuevo());
						
						if(cantidadDisponibleParaVender==1)
							errores.add("El monto supera el limite de recaudacion, puede vender "+ cantidadDisponibleParaVender + " huevo");
						else
							errores.add("El monto supera el limite de recaudacion, puede vender "+ cantidadDisponibleParaVender + " huevos");
					}
				}				
					
			
				
			
			
			
			model.addAttribute("granja",granja);
			model.addAttribute("errores", errores);
			
			
			if(errores.isEmpty())																					//Si la lista donde se almacenan los errores esta vacia entonces se procede a hacer la operacion
			{
				if(producto.equals("huevo"))
				{
					for (Huevo huevo : huevoRepository.findAll()) {
						
						if(huevo.getDias()==dias && i<cantidad)														//Elimino los huevos que se venden segun el dia elegido
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
				
					granja.setDinero(granja.getDinero()+granja.getPrecioVentaPollo()*cantidad);
				}
				
				
				granjaRepository.save(granja);
				
				return "redirect:/granja";
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
		int cantidadParaComprar, i=0;
		
		Map<String,String> map=null;
			
		
			if(producto.equals("pollo"))
			{
				 cantidadParaComprar=(int) ( granja.getDinero()/granja.getPrecioCompraPollo());
				
				map=mapPollos();
				
				if(cantidad * granja.getPrecioCompraPollo() > granja.getDinero())	
				{
					if(cantidadParaComprar==1)					
						errores.add("No dispone del dinero suficiente, puede comprar solo " + cantidadParaComprar + " pollo" );
					else
						errores.add("No dispone del dinero suficiente, puede comprar solo " + cantidadParaComprar + " pollos" );
					
				}
				if(cantidad+polloRepository.findAll().size()>granja.getCapacidadPollos())
				{
					errores.add("La cantidad deseada supera el limite de almacenaje");
				}
					
			}
			else
			{
				 cantidadParaComprar=(int) ( granja.getDinero()/granja.getPrecioCompraHuevo());
				map=mapHuevos();
				
				if(cantidad+huevoRepository.findAll().size()>granja.getCapacidadHuevos())
				{
					errores.add("La cantidad deseada supera el limite de almacenaje");
				}
				
				else if(cantidad * granja.getPrecioCompraHuevo() > granja.getDinero())
				{
					if(cantidadParaComprar==1)					
						errores.add("No dispone del dinero suficiente, puede comprar solo " + cantidadParaComprar + " "+producto );
					else
						errores.add("No dispone del dinero suficiente, puede comprar solo " + cantidadParaComprar + " "+producto+"s" );
				}
					
								
				
				
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
				return "redirect:/granja";
			
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
		List<Huevo> huevos= huevoRepository.findAll();
		List<Pollo> pollos= polloRepository.findAll();		
		Granja granja=granjaRepository.findAll().get(0);
		
		int huevosDisponibles=granja.getCapacidadHuevos()-huevos.size();	//calculo el espacio disponible
		int pollosDisponibles=granja.getCapacidadPollos()-pollos.size();
		int i=0;
			 
			
		for (Pollo pollo : pollos) {										//primero recorro la lista de los pollos para saber cual tiene que morir, y cual poner un huevo
			
			
			
			if(pollo.getDias()>=granja.getDiasPolloEnMorir())
				polloRepository.delete(pollo);
			else
			{
				pollo.setDias(pollo.getDias()+1);							//a todos los pollos se les incrementa un dia de vida
				polloRepository.save(pollo);
			}
			if(pollo.getDias()%granja.getDiasPolloEnPonerHuevo()==0 && i<huevosDisponibles)				//usé el porcentaje para saber cada cuantos dias pone un huevo, por ejemplo, si pone huevo cada 5 dias
			{																							//entonces va a poner un huevo siempre que el dia sea multiplo de 5, y no supere la capacidad
				huevoRepository.save(new Huevo(0));
				i++;
			}
				
			
					
		}
		
		i=0;
		for (Huevo huevo : huevos) {									//segundo recorro la lista de los huevos para saber cual tiene que dar un pollo, sin superar el limite de pollos
			
			
			if(huevo.getDias()>=granja.getDiasHuevoEnDarPollo() && i<pollosDisponibles)
			{				
				polloRepository.save(new Pollo(0));
				huevoRepository.delete(huevo);
				i++;
			}
			
			else
			{
				huevo.setDias(huevo.getDias()+1);
				huevoRepository.save(huevo);
			}
			
				
		}
	
		
		model.addAttribute("granja", granja);
		return "redirect:/granja";
	}
	
	
	
	private Map<String, String> mapHuevos()
	{
		Map<String, String> huevosPorDia = new HashMap<String, String>();
		
		 for (String valor : huevoRepository.cantidadDias()) {					//la query usada en la interfaz retorna una lista de String de formato "diasDeVida,cantidad"
			 
			 String[] parts = valor.split(",");									//divido el string en 2, usando la coma como divisor
			 huevosPorDia.put(parts[0], parts[1]);								//la parte de la izquierda (diasDeVida) la uso como key, y la parde la derecha (cantidad) la uso como value
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







