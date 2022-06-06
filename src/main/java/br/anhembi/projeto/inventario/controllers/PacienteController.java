package br.anhembi.projeto.inventario.controllers;

import java.util.ArrayList;
//import java.util.Optional;

//import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import br.anhembi.projeto.inventario.models.AdminModel;
import br.anhembi.projeto.inventario.models.PacienteModel;
//import br.anhembi.projeto.inventario.repositories.ItemRepository;
//import br.anhembi.projeto.inventario.repositories.AdminRepository;
import br.anhembi.projeto.inventario.repositories.PacienteRepository;

@RestController
@CrossOrigin("*")

@RequestMapping("/api")
public class PacienteController {
    
    @Autowired
    private PacienteRepository pacienteRepo;

    @GetMapping("/pacientes")
    //get pacientes from database and return it if it the received token is valid
    public ArrayList<PacienteModel> getPacientes() {
        ArrayList<PacienteModel> listPacientes = (ArrayList<PacienteModel>) pacienteRepo.findAll();
        return listPacientes;
    }

    @GetMapping("/pacientes/{cpf}")
    //get paciente from database and return it if it the received token is valid
    public ResponseEntity<PacienteModel> getPaciente(@PathVariable String cpf) {
        
        //return a paciente if it exists
        PacienteModel paciente = pacienteRepo.findByCpf(cpf);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paciente);

    }

    @PostMapping("/pacientes")
    //create a new paciente and return it if it the received token is valid
    public ResponseEntity<PacienteModel> createPaciente(@RequestBody PacienteModel paciente) {

        //check if cpf already exists
        PacienteModel pacienteExists = pacienteRepo.findByCpf(paciente.getCpf());
        if (pacienteExists != null) {
            return ResponseEntity.badRequest().build();
        }

        //check if name is empty
        if (paciente.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        PacienteModel newPaciente = pacienteRepo.save(paciente);
        return ResponseEntity.ok(newPaciente);
    }

    @PutMapping("/pacientes/{cpf}")
    //add item to a paciente 
    public ResponseEntity<PacienteModel> updatePaciente(@PathVariable String cpf, @RequestBody PacienteModel paciente) {

        //check if cpf already exists
        PacienteModel pacienteExists = pacienteRepo.findByCpf(cpf);
        if (pacienteExists == null) {
            return ResponseEntity.notFound().build();
        }

        //check if name is empty
        if (paciente.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        PacienteModel updatedPaciente = pacienteRepo.save(paciente);
        return ResponseEntity.ok(updatedPaciente);
    }

}
