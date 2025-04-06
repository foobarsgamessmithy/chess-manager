package eu.foobarssgamesmithy.chessmanager.core.mapper;

import eu.foobarssgamesmithy.chessmanager.core.messaging.data.AutoImportMessage;
import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    AutoImportMessage mapFromUser(UserBo user);
}
