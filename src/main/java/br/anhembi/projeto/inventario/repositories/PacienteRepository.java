package br.anhembi.projeto.inventario.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import br.anhembi.projeto.inventario.models.ItemModel;
import br.anhembi.projeto.inventario.models.PacienteModel;

@Repository
public interface PacienteRepository extends CrudRepository<PacienteModel, Long> {
    public PacienteModel findByCpf(String cpf);
    
    public PacienteModel findByName(String name);

    //public PacienteModel adicionaItem(ItemModel item);
}
