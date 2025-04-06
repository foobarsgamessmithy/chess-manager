package eu.foobarssgamesmithy.chessmanager.core.importer.impl;

import eu.foobarssgamesmithy.chessmanager.core.importer.ImportMatch;
import eu.foobarssgamesmithy.chessmanager.core.importer.mapper.ImporterMapper;
import eu.foobarssgamesmithy.chessmanager.core.messaging.data.AutoImportMessage;
import eu.foobarssgamesmithy.chessmanager.core.user.exception.UserNotFoundException;
import eu.foobarssgamesmithy.chessmanager.persistence.importer.ImporterRepository;
import eu.foobarssgamesmithy.chessmanager.persistence.importer.entity.MatchImportEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static eu.foobarssgamesmithy.chessmanager.core.messaging.MessageTopics.AUTO_IMPORT_TOPIC;

@Component
public class ManageImportImpl {

    private static final Logger LOG = LoggerFactory.getLogger(ManageImportImpl.class);

    private final ImporterRepository repository;

    private final ImporterMapper mapper;

    private final ImportMatch importMatch;

    private final static Long LAST_IMPORT_TIMESTAMP_THRESHOLD = (long) (60 * 30); // every 30 minutes

    public ManageImportImpl(ImporterRepository repository, ImporterMapper mapper, ImportMatch importMatch) {
        this.repository = repository;
        this.mapper = mapper;
        this.importMatch = importMatch;
    }

    @Transactional
    @KafkaListener(topics = AUTO_IMPORT_TOPIC, groupId = "importer")
    public void listenAutoImport(AutoImportMessage message) {
        Optional<MatchImportEntity> matchImportOptional = this.repository.findByUserName(
                message.getUserName());
        LOG.debug("Received new message {}.", message);
        MatchImportEntity matchImporter;
        if (matchImportOptional.isPresent()) {
            LOG.debug("Set auto import to {} for user {}.", message.getUserName(), message.isAutoImport());
            matchImporter = matchImportOptional.get();
            matchImporter.setAutoImport(message.isAutoImport());
            matchImporter.setLichessUsername(message.getLichessUsername());
        } else {
            LOG.info("Create new auto import entry for user {}.", message.getUserName());
            matchImporter = mapper.mapMessage(message);
            matchImporter.setLastImport(new Timestamp(1));
        }
        this.repository.save(matchImporter);
    }

    @Transactional
    @Scheduled(fixedRate = 5000)
    public void checkForImport() throws UserNotFoundException {
        LOG.debug("Task started. Check for imports.");
        List<MatchImportEntity> importList = this.repository.findByAutoImportTrueAndLastImportBefore(
                Timestamp.from(Instant.now().minusSeconds(LAST_IMPORT_TIMESTAMP_THRESHOLD)));
        for (MatchImportEntity entry : importList) {
            LOG.debug("Import matches for {}.", entry.getLichessUsername());
            this.importMatch.importMatchesByLichessUser(entry.getLichessUsername());
            entry.setLastImport(Timestamp.from(Instant.now()));
            this.repository.save(entry);
        }
    }
}
