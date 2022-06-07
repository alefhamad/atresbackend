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

        //for each item in the list, if the paciente field is not null, set the isAssigned field to true
        //else, set it to false
        //return the list
        for (ItemModel item : listItems) {
            if (item.getPaciente() != null) {
                item.setIsAssigned(true);
            } else {
                item.setIsAssigned(false);
            }
            
        }
        return listItems;

    }

    @GetMapping("/api/items/count")
    public ArrayList<ItemModel> getQuantity() {
            ArrayList<ItemModel> listItems = (ArrayList<ItemModel>) itemRepo.findAll();
            //for each item in the list, count the repeated tipo field
            //then update the quantity field of the matching item with the count
            //return the list
            for (ItemModel item : listItems) {
                int count = 0;
                for (ItemModel item2 : listItems) {
                    if (item.getTipo().equals(item2.getTipo())) {
                        count++;
                    }
                }
                item.setQuantity(count);
            }
            return listItems;
    }


    @PostMapping("/api/items")
    public ResponseEntity<ItemModel> insertItem(@RequestBody ItemModel itemRecebido) {
        //if paciente field of itemRecebido is not null, set isAssigned to true
        //else, sabe it to the database
        if (itemRecebido.getPaciente() != null) {
            itemRecebido.setIsAssigned(true);
        } else {
            itemRecebido.setIsAssigned(false);
        }
        itemRepo.save(itemRecebido);
        return ResponseEntity.ok(itemRecebido);
        
    }


}