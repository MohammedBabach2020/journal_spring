package ma.micda.journal.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ma.micda.journal.constants.Mappings;
import ma.micda.journal.dto.JournalDTO;
import ma.micda.journal.models.Journal;
import ma.micda.journal.services.JournalService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = Mappings.REQUEST_MAPPING_General)
public class JournalController {

    @Autowired
    private final JournalService journalService;
    @Autowired
    private final ModelMapper modelMapper;

    public JournalController(JournalService journalService, ModelMapper modelMapper) {
        this.journalService = journalService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/private/get/{userId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> getJournals(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(this.journalService.findByUserId(userId));
    }

    @PostMapping("/private/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> createJournal(@RequestBody JournalDTO journalDTO) {
        return ResponseEntity.ok(getJournalEntity(this.journalService.save(journalDTO)));
    }

    @PutMapping("/private/edit")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Journal> updateJournal(@RequestBody Journal journal) {
        Journal newJournal = this.journalService.EditJournalById(journal);
        return new ResponseEntity<>(newJournal, HttpStatus.OK);
    }

    @DeleteMapping("/private/delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Journal> deleteJournal(@PathVariable Long id) {
        this.journalService.deleteJournal(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private JournalDTO getJournalEntity(Journal Journal) {
        return modelMapper.map(Journal, JournalDTO.class);
    }
}
