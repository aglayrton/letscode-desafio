package com.cardgame.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cardgame.dto.ImdbDTO;
import com.cardgame.dto.ImdbListDTO;
import com.cardgame.dto.QuizDTO;
import com.cardgame.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizResource {

	@Autowired
	private QuizService quizService;
	/*
	@Autowired
	private UserService userService;

	@Autowired
	private ImdbService imdbService;*/

	@GetMapping
	public ResponseEntity<List<ImdbDTO>> quiz() {
		List<ImdbDTO> listDTO = quizService.findAll();
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping("/ranking")
	public ResponseEntity<List<QuizDTO>> ranking() {
		List<QuizDTO> quizDTO = quizService.ranking();
		return ResponseEntity.ok().body(quizDTO);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<QuizDTO> play(@RequestBody ImdbListDTO imdb, @PathVariable("id") Long idUser){
		
		QuizDTO newDto = quizService.battle(imdb.getImdb(), idUser);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	
}
