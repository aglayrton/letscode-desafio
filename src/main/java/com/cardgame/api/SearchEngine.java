package com.cardgame.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cardgame.repository.exceptions.DocumentNotFoundException;
import com.cardgame.service.ImdbService;

import lombok.RequiredArgsConstructor;


@Component
@EnableScheduling
@RequiredArgsConstructor
public class SearchEngine {

    @Autowired
    private ImdbService imdbService;

    private final Logger logger = LoggerFactory.getLogger(SearchEngine.class);

    @Scheduled(fixedDelay = 60000)
    public void searchImdbs() {
        logger.info("iniciando busca de imdbs");
        try {
            imdbService.scraping();
            logger.info("A busca de imdbs foi concluida com sucesso!");
        }catch (DocumentNotFoundException de){
            logger.error(de.getMessage());
        }catch (Exception ex){
            logger.error("ocorreu um erro desconhecido na busca de imdbs, detalhes: " + ex.getMessage());
        }
    }
}
