package ru.eremenko.Springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eremenko.Springbootdemo.model.LearnedWords;
import ru.eremenko.Springbootdemo.model.NewWords;
import ru.eremenko.Springbootdemo.repository.LearnedRepository;
import ru.eremenko.Springbootdemo.repository.WordsRepository;

import java.util.List;
@Service
public class WordsService {

    private final WordsRepository wordsRepository;
    private final LearnedRepository learnedRepository;

    @Autowired
    public WordsService(WordsRepository wordsRepository, LearnedRepository learnedRepository) {
        this.wordsRepository = wordsRepository;
        this.learnedRepository = learnedRepository;
    }

    public NewWords findById(Long id){
        return wordsRepository.getOne(id);
    }

    public List<NewWords> findAll(){
        return wordsRepository.findAll();
    }

    public NewWords saveWord(NewWords newWords){
        return wordsRepository.save(newWords);
    }

    public LearnedWords saveWord(LearnedWords learnedWords) {
        return learnedRepository.save(learnedWords);
    }

    public void deleteById(Long id){
        wordsRepository.deleteById(id);
    }


}
