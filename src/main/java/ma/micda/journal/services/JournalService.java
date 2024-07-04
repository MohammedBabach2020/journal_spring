package ma.micda.journal.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import ma.micda.journal.dto.JournalDTO;
import ma.micda.journal.models.Journal;
import ma.micda.journal.repository.JournalRepository;

@Service

public class JournalService {

    @Autowired
    private final JournalRepository journalRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public JournalService(JournalRepository journalRepository, ModelMapper modelMapper) {
        this.journalRepository = journalRepository;
        this.modelMapper = modelMapper;
    }

    public List<Journal> findByUserId(Long userId) {
        return journalRepository.findByUserId(userId);
    }

    public Journal save(JournalDTO journalDTO) {
        return this.journalRepository.save(getJournalEntity(journalDTO));
    }

    public Journal EditJournalById(Journal newJournal) {

        Journal existed_journal = journalRepository.findById(newJournal.getId()).orElse(null);
        if (existed_journal != null) {
            existed_journal.setText(newJournal.getText());
            existed_journal.setTitle(newJournal.getTitle());
            return this.journalRepository.save(existed_journal);
        } else {

            return existed_journal;
        }

    }

    public void deleteJournal(Long id) {

        this.journalRepository.deleteById(id);
    }

    private Journal getJournalEntity(JournalDTO journalDTO) {
        return modelMapper.map(journalDTO, Journal.class);
    }
}
