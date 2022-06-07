package br.anhembi.projeto.inventario.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.anhembi.projeto.inventario.models.ItemModel;


@Repository
public interface ItemRepository extends CrudRepository<ItemModel, Long> {
    public ItemModel findById(long id);

    public ItemModel findByTipo(String tipo);

}
