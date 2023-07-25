package com.example.demo.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;

@RestController // Anotate que expõe os endpoints para acesso externo
@RequestMapping(path="/persons") // Anotate que fornece um caminho para acessar
public class PersonResource { // Refere-se ao início da classe Pessoa
	
	private PersonRepository personRepository; // Criando o atributo personRepository
	public PersonResource(PersonRepository personRepository) { // Construtor servindo para iniciar de forma automática o atributo personRepository
		super(); // Invocando o contrutor da Classe Mãe
		this.personRepository = personRepository; // Alimentando o atributo personRepository com o argumento personRepository
	}
	
	@PostMapping // Anotate que serve para cadastrar(criar) uma pessoa
	public ResponseEntity<Person> save(@RequestBody Person person){ // Método save com argumento person
		personRepository.save(person); // Método save está processando o atributo person no banco de dados
		return new ResponseEntity<>(person, HttpStatus.OK); // Está retornando o código Http 200
	}
	
	@GetMapping // Anotate que serve para consultar(ler) pessoas
	public ResponseEntity<List<Person>> getAll(){ // Método getAll
		List<Person> persons = new ArrayList<>(); // Criando um objeto person
		persons = personRepository.findAll(); // O Objeto persons recebe a consulta do banco através do método findAll
		return new ResponseEntity<>(persons, HttpStatus.OK); // Retorna o objeto persons e o código Http 200
	}
	
	@GetMapping(path="/{id}") // Anotate que serve para consultar(ler) uma pessoa
	public ResponseEntity<Optional<Person>> getById(@PathVariable Integer id){ // Método getById com argumento id
		Optional<Person> person; // Atributo person
		try { // Tentando fazer algo
			person = personRepository.findById(id); // Atributo person está recebendo a consulta pelo id
			return new ResponseEntity<Optional<Person>>(person, HttpStatus.OK); // Está retornando person e o código Http 200
		}catch (NoSuchElementException nsee) { // Lançando exceção
			return new ResponseEntity<Optional<Person>>(HttpStatus.NOT_FOUND); // Está retornando o código do Http 404
		}
	}
	
	@DeleteMapping(path="/{id}") // Anotate que serve para excluir(deletar) uma pessoa
	public ResponseEntity<Optional<Person>> deleteById(@PathVariable Integer id){ // Método deleteById com argumento id
		try { // Tentando fazer algo
			personRepository.deleteById(id); // Executando o método para excluir um registro do banco
			return new ResponseEntity<Optional<Person>>(HttpStatus.OK); // Está retornando o código Http 200
		}catch (NoSuchElementException nsee) { // Lançando exceção
			return new ResponseEntity<Optional<Person>>(HttpStatus.NOT_FOUND); // Está retornando o código do Http 404
		}
	}
	
	@PutMapping(value="/{id}") // Anotate que serve para atualizar(update) uma pessoa
	public ResponseEntity<Person> update(@PathVariable Integer id, @RequestBody Person newPerson){ // Método update com o argumento id e o argumento newPerson 
		return personRepository.findById(id) // Buscando a pessoa pelo id
				.map(person -> { // Resultado da busca no banco
					person.setName(newPerson.getName()); // Atualizando a busca do banco pelo informado no newPerson
					person.setAge(newPerson.getAge()); // Atualizando a busca do banco pelo informado no newPerson
					Person personoUpdated = personRepository.save(person); // Salvando a atualização no banco
					return ResponseEntity.ok().body(personoUpdated); // Retornando Http 200 e pessoa atualizada
		}).orElse(ResponseEntity.notFound().build()); // Se não, retorna Http 404
	}

}
