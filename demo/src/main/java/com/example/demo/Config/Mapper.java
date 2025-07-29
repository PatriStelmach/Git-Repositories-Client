package com.example.demo.Config;

import com.example.demo.Models.GitRepo;
import com.example.demo.Models.GitRepoDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Mapper
{
    private final ModelMapper modelMapper;

    @PostConstruct
    public void init()
    {
        modelMapper.createTypeMap(GitRepo.class, GitRepoDTO.class).setPropertyCondition(Conditions.isNotNull());
    }

    public List<GitRepoDTO> GitEntityToDto(List<GitRepo> gitRepos)
    {
       return gitRepos.stream()
               .map(repo -> modelMapper.map(repo, GitRepoDTO.class))
               .toList();
    }
}

