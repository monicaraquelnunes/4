package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Person;

@Repository // Anotate que faz o link da tapela Person do banco com a classe Person
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
