package com.example.demo.Services;

import com.example.demo.Clients.GitClient;
import com.example.demo.Models.GitRepo;

import com.example.demo.Models.GitRepoDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class GitService
{
    private final GitClient gitClient;

    public List<GitRepoDTO> getAllRepos(String username)
    {
        List<GitRepo> repos = gitClient.getRepos(username);
        repos.removeIf(GitRepo::isFork);

        for (GitRepo repo : repos)
        {
            repo.setBranches(gitClient.getBranches(username, repo.getName()));
        }
        return repos.stream()
                .map(gitRepo -> new GitRepoDTO(gitRepo.getName(), gitRepo.getOwner(), gitRepo.getBranches()))
                .toList();
    }

}
