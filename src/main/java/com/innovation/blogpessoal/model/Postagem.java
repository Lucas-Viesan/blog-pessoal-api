package com.innovation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity // indica que esta classe será utilizada para gerar uma tabela no banco de dados
@Table(name = "tb_postagens") // indica o nome da tabela no banco de dados
public class Postagem {
	
	@Id // indica que o atributo é chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // indica que a chave será  gerada no banco dados
	private Long id;
	
	@NotBlank(message = "O atributo é obrigatório") // não permite que o atributo seja nulo
	@Size(min = 5, max = 100, message = "O atributo deve conter no minímo 05 e no máximo 100 caracters") //defini valor min e max dos caracteres
	private String titulo;
	
	@NotBlank(message = "O atributo é obrigatório")
	@Size(min = 10, max = 1000, message = "O atributo deve conter no minímo 10 e no máximo 1000 caracters")
	private String texto;
	
	@UpdateTimestamp // o spring se encarrecará de obter a data e hora do sistema operacional
	private LocalDateTime data;
	
	@ManyToOne // indica que a Classe Postagem será o lado N:1 e terá um Objeto da Classe Tema, que no modelo Relacional será a Chave Estrangeira
	@JsonIgnoreProperties("postagem") //Exibi o Objeto da Classe Postagem apenas uma vez, impedindo o looping infinito e o travamento da nossa aplicação
	private Tema tema;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	
	
	
	
	
}
