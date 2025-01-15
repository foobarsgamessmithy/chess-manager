package eu.foobarssgamesmithy.chessmanager.fixtures;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

    public static String formatZonedDateTimeForDto(ZonedDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return formatter.format(dateTime);
    }
}
