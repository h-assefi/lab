package com.h.asefi.demo.common.helper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class LocalDateTimeHelper {

    private LocalDateTimeHelper() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    /**
     * Converts a timestamp in milliseconds (since the Unix epoch) to a
     * {@link LocalDateTime}
     * using the system default time zone.
     * <p>
     * This method is useful when you have a timestamp (e.g., from a database or
     * API)
     * and want to work with a {@code LocalDateTime} for formatting or business
     * logic.
     * </p>
     *
     * @param milliseconds the number of milliseconds since January 1, 1970,
     *                     00:00:00 GMT
     * @return the corresponding {@code LocalDateTime} in the system default time
     *         zone
     */
    public static LocalDateTime fromLong(Long milliseconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
    }

    /**
     * Converts a {@link LocalDateTime} to a timestamp in milliseconds (since the
     * Unix epoch),
     * using the system default time zone.
     * <p>
     * This is useful for persisting {@code LocalDateTime} values as epoch
     * timestamps,
     * especially when storing in databases or sending over APIs.
     * </p>
     *
     * @param localDateTime the {@code LocalDateTime} to convert
     * @return the number of milliseconds since January 1, 1970, 00:00:00 GMT
     */
    public static Long toLong(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        return zonedDateTime.toInstant().toEpochMilli();

    }

    /**
     * Converts a timestamp in milliseconds (since the Unix epoch) to a
     * {@link LocalDateTime},
     * using the specified time zone.
     * <p>
     * Useful when working with time zone–aware data sources or when consistent time
     * zone handling is required.
     * </p>
     *
     * @param milliseconds the number of milliseconds since January 1, 1970,
     *                     00:00:00 GMT
     * @param zoneId       the {@code ZoneId} to use for the conversion
     * @return the corresponding {@code LocalDateTime} in the specified time zone
     */
    public static LocalDateTime fromLong(Long milliseconds, ZoneId zoneId) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), zoneId);
    }

    /**
     * Converts a {@link LocalDateTime} to a timestamp in milliseconds (since the
     * Unix epoch),
     * using the specified time zone.
     * <p>
     * This allows conversion that respects the intended time zone context of the
     * {@code LocalDateTime}.
     * </p>
     *
     * @param localDateTime the {@code LocalDateTime} to convert
     * @param zoneId        the {@code ZoneId} to apply during conversion
     * @return the number of milliseconds since January 1, 1970, 00:00:00 GMT
     */
    public static Long toLong(LocalDateTime localDateTime, ZoneId zoneId) {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
        return zonedDateTime.toInstant().toEpochMilli();
    }

}
