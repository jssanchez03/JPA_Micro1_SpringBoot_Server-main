package edu.espe.proyectou1.Service;

import edu.espe.proyectou1.Model.WorkTeam;
import edu.espe.proyectou1.Repository.WorkTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkTeamService {

    @Autowired
    private WorkTeamRepository workTeamRepository;

    public List<WorkTeam> findAll() {
        return workTeamRepository.findAll();
    }

    public ResponseEntity<?> save(WorkTeam workTeam) {
        return new ResponseEntity<>(workTeamRepository.save(workTeam), HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<WorkTeam> workTeamOptional = workTeamRepository.findById(uuid);
        if (workTeamOptional.isPresent()) {
            workTeamRepository.deleteById(uuid);
            return new ResponseEntity<>(workTeamOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("WorkTeam not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> findById(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<WorkTeam> workTeamOptional = workTeamRepository.findById(uuid);
        if (workTeamOptional.isPresent()) {
            return new ResponseEntity<>(workTeamOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("WorkTeam not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> updateById(String id, WorkTeam workTeam) {
        UUID uuid = UUID.fromString(id);
        Optional<WorkTeam> workTeamOptional = workTeamRepository.findById(uuid);
        if (workTeamOptional.isPresent()) {
            WorkTeam existingWorkTeam = workTeamOptional.get();
            existingWorkTeam.setName(workTeam.getName());
            existingWorkTeam.setDescription(workTeam.getDescription());
            workTeamRepository.save(existingWorkTeam);
            return new ResponseEntity<>("WorkTeam updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("WorkTeam not found", HttpStatus.NOT_FOUND);
        }
    }
}