package eu.foobarssgamesmithy.chessmanager.core.importer.data;

import lombok.*;

@Builder(access = AccessLevel.PUBLIC)
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class LichessPlayerDto {

    private Integer rating;

    private Integer ratingDiff;

    private LichessUserDto user;

}
