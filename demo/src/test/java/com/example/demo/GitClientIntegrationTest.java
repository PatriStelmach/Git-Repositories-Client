package com.example.demo;


import com.example.demo.Clients.GitClient;
import com.example.demo.Controllers.GitController;
import com.example.demo.Models.Branch;
import com.example.demo.Models.GitRepo;
import com.example.demo.Models.GitRepoDTO;
import com.example.demo.Services.GitService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
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


    //tests if endpoint works for default "octocat" user
    @Test
    public void testGetReposFromGitHub() throws Exception
    {
        // given
        String username = "octocat";
        // when
        List<GitRepo> clientRepos = gitClient.getRepos(username);
        List<Branch> branches = gitClient.getBranches(username, "hello-world");

        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/github/getRepos/"
                + username, String.class);
        List<GitRepoDTO> controllerRepos = objectMapper.readValue(response.getBody(), new TypeReference<>() {});

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(controllerRepos, gitService.getAllRepos(username));
        assertNotNull(clientRepos, "List of repos can't be null");
        assertNotNull(controllerRepos, "List of repos can't be null");
        assertNotNull(branches, "List of branches can't be null");

    }





}

