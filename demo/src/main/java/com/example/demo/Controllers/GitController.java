package com.example.demo.Controllers;

import com.example.demo.Models.GitRepoDTO;
import com.example.demo.Services.GitService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/github")
public class GitController
{
    private final GitService gitService;


    @GetMapping("/getRepos/{username}")
    public ResponseEntity <List<GitRepoDTO>> getReposForUser(@PathVariable("username") String username)
    {
        return ResponseEntity.ok().body(gitService.getAllRepos(username));
    }

}
