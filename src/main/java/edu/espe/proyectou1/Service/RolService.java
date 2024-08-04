package edu.espe.proyectou1.Service;

import edu.espe.proyectou1.Model.Rol;
import edu.espe.proyectou1.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    public ResponseEntity<?> save(Rol rol) {
        return new ResponseEntity<>(rolRepository.save(rol), HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Rol> rolOptional = rolRepository.findById(uuid);
        if (rolOptional.isPresent()) {
            rolRepository.deleteById(uuid);
            return new ResponseEntity<>(rolOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Rol not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> findById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Rol> rolOptional = rolRepository.findById(uuid);
        if (rolOptional.isPresent()) {
            return new ResponseEntity<>(rolOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Rol not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateById(String id, Rol rol) {
        UUID uuid = UUID.fromString(id);
        Optional<Rol> rolOptional = rolRepository.findById(uuid);
        if (rolOptional.isPresent()) {
            Rol existingRol = rolOptional.get();
            existingRol.setName(rol.getName());
            existingRol.setDescription(rol.getDescription());
            rolRepository.save(existingRol);
            return new ResponseEntity<>("Rol updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Rol not found", HttpStatus.NOT_FOUND);
        }
    }
}

