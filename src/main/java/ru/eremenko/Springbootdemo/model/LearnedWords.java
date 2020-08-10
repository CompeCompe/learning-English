package ru.eremenko.Springbootdemo.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "learned_words")
public class LearnedWords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "word")
    public String words;
    @Column(name = "translate")
    public String translate;
}
