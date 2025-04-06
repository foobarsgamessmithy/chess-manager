package eu.foobarssgamesmithy.chessmanager.core.importer.mapper;

import eu.foobarssgamesmithy.chessmanager.core.messaging.data.AutoImportMessage;
import eu.foobarssgamesmithy.chessmanager.persistence.importer.entity.MatchImportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImporterMapper {

    MatchImportEntity mapMessage(AutoImportMessage source);

}
