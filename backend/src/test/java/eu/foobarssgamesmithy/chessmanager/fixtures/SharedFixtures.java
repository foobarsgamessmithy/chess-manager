package eu.foobarssgamesmithy.chessmanager.fixtures;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class SharedFixtures {

    public static final UUID SAVED_MATCH_UUID_1 = UUID.fromString("00000000-0000-0000-0001-000000000001");

    public static final UUID SAVED_MATCH_UUID_2 = UUID.fromString("00000000-0000-0000-0001-000000000002");

    public static final UUID MATCH_WITHOUT_RESULT_UUID = UUID.fromString("00000000-0000-0000-0001-000000000003");

    public static final UUID MATCH_WITH_RESULT_UUID = UUID.fromString("00000000-0000-0000-0001-000000000004");

    public static final ZoneId STANDARD_ZONE =  ZoneId.of("CET");

    public static final ZonedDateTime PLAYED_AT = LocalDateTime.of(2024,10,25,13,37, 42)
            .atOffset(ZoneOffset.of("+01:00"))
            .toZonedDateTime();

    public static final ZonedDateTime IMPORTED_MATCH_PLAYED_AT =
            LocalDateTime.of(2018,10,26,22,58, 26, 822000000)
                    .atZone(ZoneId.of("Europe/Paris"));

    public static String formatZonedDateTimeForDto(ZonedDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return formatter.format(dateTime);
    }

    public static final List<String> MOVE_LIST = List.of("e4 e5", "Bc4 Bc6", "Qh5 Nf6", "Qxf7#" );

    public static final List<String> IMPORTED_MOVE_LIST = List.of("c4 Nf6", "Nc3 e5", "d4 exd4", "Qxd4 Nc6", "Qd1 Bb4", "Bd2 O-O",
            "e3 Bxc3", "Bxc3 Ne4",  "Ne2 d6",  "Qc2 Re8", "Nf4 Bf5", "Bd3 Qg5", "O-O g6", "Rae1 Nxc3", "Qxc3 Bxd3", "Qxd3 Ne5", "Qd1 Rad8",
            "b3 c6",  "Kh1 Qf5", "Qa1 h5", "Rd1 Ng4", "h3 Nf6", "Qd4 a6", "f3 Qe5", "Rfe1 Qxd4", "exd4 Rxe1+", "Rxe1 Kf8", "h4 Re8", "Rxe8+ Kxe8",
            "Kg1 Ng8", "Kf2 Nh6", "g3 Ke7", "Ng2 Ke6", "Ne3 a5", "g4 f6", "Kg3 d5", "c5 Nf7", "Ng2 hxg4", "fxg4 Nd8", "Nf4+ Kf7", "h5 g5",
            "Ne2 Ne6", "Kf3 Kg7", "Ke3 Kh6", "Ng3 Ng7", "Nf5+ Nxf5+");

}
