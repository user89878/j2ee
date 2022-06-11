package com.ensah.core.dao;
import com.ensah.core.bo.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Imatiere extends JpaRepository<Element,Long> {


    List<Element> getAllByModule_IdModule(long id);
    Element findByNom(String nom);
    boolean existsByNom(String nom);
}
