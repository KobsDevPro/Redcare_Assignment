package com.tech.assignment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.assignment.pojo.Item;
import com.tech.assignment.pojo.Root;
import com.tech.assignment.service.RepoService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class RepoServiceImpl implements RepoService {

    private final String PARTIAL_GIT_API_URL = "https://api.github.com/search/repositories?";

    /**
     * Here the json from github is fetched depending upon the url passed.
     * then object mapper is used to parse and generate the Pojo to show as output in api.
     *
     * @param url
     * @return
     */
    private Root processGitApiResponseByDate(String url) {
        Root result;
        ObjectMapper mapper = new ObjectMapper();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            result = mapper.readValue(response.body(), Root.class);
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Item> sortByStars() {
        String url = PARTIAL_GIT_API_URL+"q=created:2019-01-10&sort=stars&order=desc";
        Root root = processGitApiResponseByDate(url);
        return root.items;
    }

    @Override
    public List<Item> sortByTopNumber(int number) {
        String url = PARTIAL_GIT_API_URL + "q=created:2019-01-10&sort=stars&order=desc&per_page="+number;
        if ( number>0) {
            Root root = processGitApiResponseByDate(url);
            return  root.items.stream().limit(number).toList();
        } else {
            return null;
        }
    }

    @Override
    public List<Item> filterByLanguage(String language) {
        String url = PARTIAL_GIT_API_URL + "q=language:"+language+"&sort=stars&order=desc";
        if (!language.isEmpty()) {
            Root root = processGitApiResponseByDate(url);
            return root.items.stream().filter(i -> language.equalsIgnoreCase(i.language)).toList();
        } else {
            return null;
        }
    }


    @Override
    public List<Item> filterByDate(String date) {
        try {
            /*
             * Url encoding is used in order to encode ">" sign and send in
             * as url otherwise it will end up with IllegalUrlException
             */
            date = URLEncoder.encode(">"+date, "UTF-8");
            String url = PARTIAL_GIT_API_URL + "q=created:"+date+"&sort=stars&order=desc";
            if (!date.isEmpty()) {
                Root root = processGitApiResponseByDate(url);
                return root.items;
            } else {
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

}
