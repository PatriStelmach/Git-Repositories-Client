package com.example.demo;


import com.example.demo.Clients.GitClient;
import com.example.demo.Models.GitRepo;
import com.example.demo.Models.GitRepoDTO;
import com.example.demo.Services.GitService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GitClientIntegrationTest
{
    @Autowired
    private GitClient gitClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GitService gitService;
    @LocalServerPort
    private int port;


    //checks if expected output given form client matches the controller output
    @Test
    public void testGetReposFromGitHub() throws Exception
    {
        // given
        String username = "octocat";
        // when
        List<GitRepo> clientRepos = gitClient.getRepos(username);

        List<GitRepoDTO> expectedRepos = clientRepos.stream()
                .filter(repo -> !repo.isFork())
                .map(repo ->
                {
                    repo.setBranches(gitClient.getBranches(username, repo.getName()));
                    return new GitRepoDTO(repo.getName(), repo.getOwner(), repo.getBranches());
                })
                .toList();

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/github/getRepos/"
                + username, String.class);
        List<GitRepoDTO> actualRepos = objectMapper.readValue(response.getBody(), new TypeReference<>() {});


        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedRepos, gitService.getAllRepos(username));
        assertEquals(expectedRepos, actualRepos);

    }





}

