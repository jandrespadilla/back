package com.back.services;

import com.back.repositories.CategoriaRepostery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepostery categoriaRepository;

}
