package eu.foobarssgamesmithy.chessmanager.core.importer.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

// Ignore properties form the Json result of lichess.org response which are not part of this class
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(access = AccessLevel.PUBLIC)
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class LichessPlayersDto {

    private LichessPlayerDto black;

    private LichessPlayerDto white;
}
