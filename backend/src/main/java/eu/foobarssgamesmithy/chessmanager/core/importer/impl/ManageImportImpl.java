package eu.foobarssgamesmithy.chessmanager.core.importer.impl;

import eu.foobarssgamesmithy.chessmanager.core.importer.mapper.ImporterMapper;
import eu.foobarssgamesmithy.chessmanager.core.messaging.data.AutoImportMessage;
import eu.foobarssgamesmithy.chessmanager.persistence.importer.ImporterRepository;
import eu.foobarssgamesmithy.chessmanager.persistence.importer.entity.MatchImportEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static eu.foobarssgamesmithy.chessmanager.core.messaging.MessageTopics.AUTO_IMPORT_TOPIC;

@Component
public class ManageImportImpl {

    private final ImporterRepository repository;

    private final ImporterMapper mapper;

    public ManageImportImpl(ImporterRepository repository, ImporterMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @KafkaListener(topics = AUTO_IMPORT_TOPIC, groupId = "importer")
    public void listenAutoImport(AutoImportMessage message) {
        Optional<MatchImportEntity> matchImportOptional = this.repository.findByUserName(
                message.getUserName());
        MatchImportEntity matchImporter;
        if (matchImportOptional.isPresent()) {
            matchImporter = matchImportOptional.get();
            matchImporter.setAutoImport(message.isAutoImport());
        } else {
            matchImporter = mapper.mapMessage(message);
        }
        this.repository.save(matchImporter);
    }
}
