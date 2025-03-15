package eu.foobarssgamesmithy.chessmanager.core.importer.data;


import lombok.*;

@Builder(access = AccessLevel.PUBLIC)
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class LichessPlayersDto {

    private LichessPlayerDto black;

    private LichessPlayerDto white;
}
