package com.innovation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innovation.blogpessoal.model.Postagem;
import com.innovation.blogpessoal.repository.PostagemRepository;

import jakarta.validation.Valid;

@RestController // define que a classe receberá requisições compostas por url, verbos, corpo da requisiçao
@RequestMapping("/postagens") // é usada para mapear as solicitações para os métodos da classe controladora
@CrossOrigin(origins = "*", allowedHeaders = "*") // indica que a Classe controladora permitirá o recebimento de requisições realizadas de fora do domínio ao qual ela pertence
public class PostagemControler {

	@Autowired //A Injeção de Dependência define quais Classes serão instanciadas e em quais lugares serão Injetadas quando houver necessidade. 
	//Classe Controladora cria um ponto de injeção da Interface PostagemRepository na Classe PostagemController.
	private PostagemRepository postagemRepository;
	
	@GetMapping //mapeia todas as Requisições HTTP GET, enviadas para um endereço específico
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findByTituloContainingIgnoreCase(titulo));
	}
	
	
	@PostMapping //indica que o Método post(Postagem postagem), responderá a todas as requisições do tipo HTTP POST
	public ResponseEntity<Postagem> post (@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
	}
	
	
	
}
