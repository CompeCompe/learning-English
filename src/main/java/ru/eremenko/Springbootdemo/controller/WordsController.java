package ru.eremenko.Springbootdemo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import ru.eremenko.Springbootdemo.model.LearnedWords;
import ru.eremenko.Springbootdemo.model.NewWords;
import ru.eremenko.Springbootdemo.service.WordsService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WordsController {

    private final WordsService wordsService;
    public String equals;
    private int random;
    private String translate;
    private List<NewWords> wordsList;
    private List<NewWords> oneWord;
    private NewWords newWord;
    private int count;

    @Autowired
    public WordsController(WordsService wordsService) {
        this.wordsService = wordsService;
    }

    //Метод, который выдает html страницу word-list и на ней показывает рандомное слово из таблицы new_words
    @GetMapping("/words")
    public String findAll(Model model){
        newWord = getRandomWord();
        model.addAttribute("words", newWord);
        model.addAttribute("equals", equals);
        return "words-list";
    }
    //Берем рандомное слово из бд
    public NewWords getRandomWord(){
        NewWords randomWord;
        wordsList = wordsService.findAll();
        oneWord = new ArrayList<>();
        random = (int) (0 + Math.random()*wordsList.size());
        oneWord.add(wordsList.get(random));
        randomWord = oneWord.get(0);
        translate = randomWord.translate.trim().toLowerCase();
        randomWord.translate = "";
        return randomWord;
    }
//    Надо разобраться в ajax, но там вроде понятно, должны создаваться кнопки
//    с одинаковыми классами
//    Скрипт считывает значение нажатой кнопки
//    После чего делаю постмаппинг, в котором чекается значение буквы из массива
//    Для этого создаем переменную, которая изначально указывает на 0 элемент массива
//    В итоге если правильно, то newWord в перевод добавляет букву и обновляем в страницу
//    и переменную инкрементим
//    Если неправильно, то ничего
//    Еще надо бы удалять кнопки, которые оказались правильными,
//    пока что из идей это обновлять списк буковок, либо как-то добавить hidden
//    ну это через js вроде

    //Метод проверяет правильность перевода от пользовател
    @PostMapping("/words")
    public String update(NewWords newWords,Model model){
        if (translate.equals(newWords.translate.trim().toLowerCase()) && newWord.words.equals(newWords.words)){
            equals = "Правильно";
            if(newWords.count == null){
                count = 0;
            } else {
                count = newWords.count;
            }
            count++;
            if(count > 4){
                LearnedWords learnedWords = new LearnedWords();
                learnedWords.words = newWords.words;
                learnedWords.translate = newWords.translate;
                wordsService.saveWord(learnedWords);
                wordsService.deleteById(newWords.id);
            } else {
                newWords.count = count;
                wordsService.saveWord(newWords);
            }
        } else {
            equals = "Неправильно";
        }
        return "redirect:/words";
    }
    @GetMapping("/words-create")
    public String createUserForm(NewWords newWords){
        return "words-create";
    }

    @PostMapping("/words-create")
    public String createUser(NewWords newWords){
        wordsService.saveWord(newWords);
        return "redirect:/words";
    }

    @GetMapping("words-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        wordsService.deleteById(id);
        return "redirect:/words";
    }
    //Метод для заучивания при помощи собирания слов из буков
    @GetMapping("/words-update")
    public String updateUserForm(Model model){
        newWord = getRandomWord();
        char[] letters = translate.toCharArray();
        model.addAttribute("words", newWord);
        model.addAttribute("letters",letters);
        return "words-update";
    }

    @GetMapping("/words-updat")
    public NewWords updateUser(){
        newWord.translate = translate;
        return newWord;
    }

}