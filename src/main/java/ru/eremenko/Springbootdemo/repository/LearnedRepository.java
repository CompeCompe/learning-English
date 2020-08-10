package ru.eremenko.Springbootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.eremenko.Springbootdemo.model.LearnedWords;


public interface LearnedRepository extends JpaRepository<LearnedWords, Long> {
}
