package com.lead.dashboard.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtil {
    private static final ZoneId IST_ZONE = ZoneId.of("Asia/Kolkata");

    // Get current LocalDateTime in IST
    public static LocalDateTime getCurrentISTLocalDateTime() {
        return LocalDateTime.now(IST_ZONE);
    }

    // Get current Date in IST (for legacy support)
    public static Date getCurrentISTDate() {
        return Date.from(LocalDateTime.now(IST_ZONE).atZone(IST_ZONE).toInstant());
    }

    // Get current LocalDateTime in IST with seconds
    public static LocalDateTime getCurrentISTDateTimeWithSeconds() {
        return LocalDateTime.now(IST_ZONE);
    }
}
