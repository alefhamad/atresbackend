package br.anhembi.projeto.inventario.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.anhembi.projeto.inventario.models.ItemModel;
import br.anhembi.projeto.inventario.repositories.ItemRepository;

@RestController
@CrossOrigin("*")
@RequestMapping
public class ItemController {
    
    @Autowired
    private ItemRepository itemRepo;

    @GetMapping("/api/items")
    public ArrayList<ItemModel> getItems() {
        ArrayList<ItemModel> listItems = (ArrayList<ItemModel>) itemRepo.findAll();
        return listItems;
    }

    @PostMapping("/api/items")
    public ResponseEntity<ItemModel> insertItem(@RequestBody ItemModel itemRecebido) {
        ItemModel newItem = itemRepo.save(itemRecebido);
        return ResponseEntity.ok(newItem);
    }


}
