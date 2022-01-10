package com.sistema.inventario.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.sistema.inventario.models.Huevo;

public interface IHuevoRepository extends JpaRepository<Huevo, Integer>{

}
