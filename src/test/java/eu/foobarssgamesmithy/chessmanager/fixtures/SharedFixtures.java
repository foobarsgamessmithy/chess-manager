package eu.foobarssgamesmithy.chessmanager.fixtures;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class SharedFixtures {

    public static UUID SAVED_MATCH_UUID_1 = UUID.fromString("00000000-0000-0000-0001-000000000001");

    public static UUID SAVED_MATCH_UUID_2 = UUID.fromString("00000000-0000-0000-0001-000000000002");

    public static UUID MATCH_UUID = UUID.fromString("0e492bb1-d84f-4a78-bc66-78218521d50a");

    public static ZoneId STANDARD_ZONE =  ZoneId.of("CET");

    public static ZonedDateTime PLAYED_AT = LocalDateTime.of(2024,10,25,13,37, 42)
            .atOffset(ZoneOffset.of("+01:00"))
            .toZonedDateTime();

    public static String formatZonedDateTimeForDto(ZonedDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return formatter.format(dateTime);
    }
}
