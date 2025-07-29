package com.example.demo.Models;

import java.util.List;


public record GitRepoDTO(String repositoryName, Owner owner, List<Branch>branches) {
}
