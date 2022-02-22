package com.cardgame.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardgame.dto.ImdbDTO;
import com.cardgame.dto.ImdbRequiredDTO;
import com.cardgame.dto.QuizDTO;
import com.cardgame.entities.Imdb;
import com.cardgame.entities.Quiz;
import com.cardgame.entities.User;
import com.cardgame.repository.ImdbRepository;
import com.cardgame.repository.QuizRepository;
import com.cardgame.repository.UserRepository;
import com.cardgame.service.exceptions.FilmSelectedException;
import com.cardgame.service.exceptions.ResourceEntityNotFoundException;

@Service
public class QuizService {

	@Autowired
	private ImdbRepository imdbRepository;

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private UserRepository userRepository;
	
	public List<ImdbDTO> findAll() {
		List<Imdb> list = imdbRepository.findAllRandom();
		return list.stream().map(x -> new ImdbDTO(x)).collect(Collectors.toList());
	}
	
	public List<QuizDTO> ranking() {
		List<Quiz> list = quizRepository.findRanking();
		return list.stream().map(x -> new QuizDTO(x)).collect(Collectors.toList());
	}

	public QuizDTO battle(List<ImdbRequiredDTO> list, Long id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceEntityNotFoundException("Not found entity"));

		Quiz quiz = quizRepository.findTopByUserAndGameOverOrderByIdDesc(user, false).orElse(new Quiz(0, user, 0, false));

		ImdbRequiredDTO selected = list.stream().filter(ImdbRequiredDTO::isselected).findFirst()
				.orElseThrow(() -> new FilmSelectedException("Tente novamente"));

		ImdbRequiredDTO noSelected = list.stream().filter(imdbFalse -> !imdbFalse.isselected()).findFirst()
				.orElseThrow(() -> new FilmSelectedException("Tente novamente"));

		if (selected.getNote().compareTo(noSelected.getNote()) == -1) {
			quiz.setError(quiz.getError() + 1);

			if (quiz.getError() == 3) {
				quiz.setGameOver(true);
			}
			quiz = quizRepository.save(quiz);
			return new QuizDTO(quiz);
		}

		quiz.setPont(quiz.getPont() + 1);
		quiz = quizRepository.save(quiz);

		return new QuizDTO(quiz);
	}

}
