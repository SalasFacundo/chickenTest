package com.sistema.inventario.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.inventario.models.Pollo;

public interface IPolloRepository extends JpaRepository<Pollo, Integer>{

}
