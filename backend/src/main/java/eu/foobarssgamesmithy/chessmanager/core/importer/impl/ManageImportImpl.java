package eu.foobarssgamesmithy.chessmanager.core.importer.impl;

import eu.foobarssgamesmithy.chessmanager.core.messaging.data.AutoImportMessage;
import eu.foobarssgamesmithy.chessmanager.persistence.importer.ImporterRepository;
import eu.foobarssgamesmithy.chessmanager.persistence.importer.entity.MatchImportEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static eu.foobarssgamesmithy.chessmanager.core.messaging.MessageTopics.AUTO_IMPORT_TOPIC;

@Component
public class ManageImportImpl {

    private final ImporterRepository repository;

    public ManageImportImpl(ImporterRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = AUTO_IMPORT_TOPIC, groupId = "importer")
    public void listenAutoImport(AutoImportMessage message) {
        Optional<MatchImportEntity> matchImportOptional = this.repository.findByUserName(
                message.getUserName());
        if(matchImportOptional.isPresent()) {
            MatchImportEntity matchImporter = matchImportOptional.get();
            matchImporter.setAutoImport(message.isAutoImport());
        } else {
            // map message to entity
        }

    }
}
