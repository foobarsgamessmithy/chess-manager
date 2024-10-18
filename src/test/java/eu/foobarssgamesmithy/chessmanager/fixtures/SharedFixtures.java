package eu.foobarssgamesmithy.chessmanager.fixtures;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SharedFixtures {

    public static ZonedDateTime PLAYED_AT = ZonedDateTime.of(LocalDateTime.of(2024,10,18,13,37, 42), ZoneId.of("GMT"));

    public static String formatZonedDateTimeForDto(ZonedDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        return formatter.format(dateTime);
    }
}
