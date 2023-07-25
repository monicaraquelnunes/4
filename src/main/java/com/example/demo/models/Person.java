package com.example.demo.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity // Anotate que liga(mapeia) essa classe com a tabela no banco
public class Person { // Refere-se ao início da classe Pessoa
	
	@GeneratedValue // Anotate que gera uma numeração automática no banco
	@Id // Anotate que mapeia a coluna Id do banco com o atributo id da classe Person
	private int id; // Atributo id
	
	@Column(name = "name") // Anotate que mapeia a coluna name do banco com o atributo name da classe Person
	private String name; // Atributo name
	
	@Column(name = "age") // Anotate que mapeia a coluna age do banco com o atributo age da classe Person
	private int age; // Atributo age
	
	public Person() { // Construtor vazio
		}
	
	public Person(String name, int age) { // Construtor servindo para iniciar de forma automática os atributos name e age
		super(); // Invocando o construtor da classe mãe
		this.name = name; // Alimentando o atributo name com o argumento name
		this.age = age; // Alimentando o atributo age com o argumento age
	}
	
	public int getId() { // Método getId
		return id; // Retornando o atributo id
		
	}
	
	public String getName() { // Método getName
		return name; // Retornando o atributo name
		
	}
	
	public void setName(String name) { // Método setName com argumento name
		this.name = name; // Alimentando o atributo name com argumento name
		
	}
	
	public int getAge() { // Método getAge
		return age; // Retornando o atributo age
		
	}
	
	public void setAge(int age) { // Método setAge com argumento age
		this.age = age; // Alimentando o atributo age com argumento age
		
	}

	@Override // Anotate da classe Mãe
	public String toString() { // Método toString
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]"; // Retornando uma String com todo o conteúdo da classe
	}

}
