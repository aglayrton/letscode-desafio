package com.cardgame.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cardgame.dto.ImdbDTO;
import com.cardgame.entities.Imdb;
import com.cardgame.entities.ImdbLog;
import com.cardgame.enu.Gender;
import com.cardgame.repository.ImdbLogRepository;
import com.cardgame.repository.ImdbRepository;
import com.cardgame.repository.exceptions.DocumentNotFoundException;
import com.cardgame.service.exceptions.ResourceEntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImdbService {

    @Autowired
    private ImdbLogRepository imdbLogRepository;

    @Autowired
    private ImdbRepository imdbRepository;

    public void scraping() throws DocumentNotFoundException {
        for(Gender gender: Gender.values()){
            fillAttributesImdb(gender);
        }
    }

    private void fillAttributesImdb(Gender gender) throws DocumentNotFoundException {
        Set<Imdb> imdbs = new HashSet<>();
        Document document;
        ImdbLog lastImdbLogSaved = imdbLogRepository.findTopByGenderOrderByIdDesc(gender).orElse(new ImdbLog(1,gender,true));
        ImdbLog imdbLogToSave = new ImdbLog(getLasItemOrderScraped(lastImdbLogSaved.getLastItemOrderScraped()),gender,true);

        try {
             document = Jsoup.connect(addUrlParaMeters(gender.getUrl(),lastImdbLogSaved)).timeout(6000).get();
        }catch (IOException ex){
            imdbLogToSave.setSucess(false);
            imdbLogRepository.save(imdbLogToSave);
            throw new DocumentNotFoundException(
                    String.format("Ocorreu um erro na busca do documento html do gênero %s na página %d",gender,lastImdbLogSaved.getLastItemOrderScraped()));
        }

        Elements body = document.select("div.lister-list");
        for (Element row : body.select("div.lister-item-content")) {
             String title = row.select("h3.lister-item-header a").text();
             BigDecimal rating = getRating(row);
             Imdb imdb = new Imdb(title, rating, gender);
             imdbs.add(imdb);
        }

        imdbRepository.saveAll(imdbs);
        imdbLogRepository.save(imdbLogToSave);
    }

    private int getLasItemOrderScraped(int lastItem){
        return lastItem > 1? lastItem +50: lastItem + 49 ;
    }

    private int getNextItemOrderToScrap(int lastItem){
        return lastItem > 1 ? lastItem + 1 : 1;
    }

    private BigDecimal getRating(Element element){
        return element.select("div.ratings-bar strong").text().isEmpty()?
                BigDecimal.ZERO : new BigDecimal(element.select("div.ratings-bar strong").text());
    }

    private String addUrlParaMeters(String url, ImdbLog imdbLog){
        return url + "&start=" + getNextItemOrderToScrap(imdbLog.getLastItemOrderScraped());
    }
    
    @Transactional(readOnly = true)
    public ImdbDTO findById(Long id) {
    	Optional<Imdb> obj = imdbRepository.findById(id);
    	Imdb imdb = obj.orElseThrow(() -> new ResourceEntityNotFoundException("Entity not found"));
    	return new ImdbDTO(imdb);
    }
     
}
