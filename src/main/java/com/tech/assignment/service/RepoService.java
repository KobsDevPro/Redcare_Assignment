package com.tech.assignment.service;

import com.tech.assignment.pojo.Item;

import java.util.List;

public interface RepoService {
    List<Item> sortByStars();

    List<Item> sortByTopNumber(int number);

    List<Item> filterByLanguage(String language);

    List<Item> filterByDate(String date);
}
