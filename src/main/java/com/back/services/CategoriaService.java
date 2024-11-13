package com.back.services;

import com.back.models.Categoria;
import com.back.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
        }
     public Categoria findBiID(Long id){
            return categoriaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada"));
     }
    public boolean existsById(Long id) {
        return categoriaRepository.existsById(id);
    }
@Transactional
    public  Categoria createCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
     }
     @Transactional
    public Categoria updateCategoria(Long id, Categoria categoriaDetail){
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Categoria no encontrada"));
        if (categoriaDetail.getNombre()!=null){
            categoria.setNombre(categoriaDetail.getNombre());

        }else {
            throw new IllegalArgumentException("El nombre de la categoria no puede estar vacio");
        }
        categoria.setDescripcion(categoriaDetail.getDescripcion());

        return categoriaRepository.save(categoria);
    }
    public void deleteCategoria(long id){
        if (!categoriaRepository.existsById(id)){
            throw new IllegalArgumentException("Categoria no encontrada");
        }
        categoriaRepository.deleteById(id);
    }

}