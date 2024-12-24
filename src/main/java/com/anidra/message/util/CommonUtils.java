package com.anidra.message.util;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class CommonUtils {

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    static final String CHARACTER_SET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    static SecureRandom rnd = new SecureRandom();

    private static char getRandomCharacter() {
        return CHARACTER_SET.charAt(rnd.nextInt(CHARACTER_SET.length()));
    }

    public static boolean isValidJson(String incomingJson) {
        try {
            JsonParser.parseString(incomingJson);
        } catch (JsonSyntaxException e) {
            log.error("CommonUtils::isValidJson exception with parsing the incoming json {}", e.getMessage());
            return false;
        }
        return true;
    }

    public static LocalDateTime localTimeToUtc(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }

    public static String timeInUtcString(LocalDateTime localDateTime) {
        LocalDateTime utcDateTime = localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
        return DATE_TIME_FORMAT.format(utcDateTime);
    }

    public static String localDateTimeFromString() {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(1); // or parse a string
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime utcZdt = zdt.withZoneSameInstant(ZoneOffset.UTC);
        LocalDateTime utcLocalDateTime = utcZdt.toLocalDateTime();
        log.info("Local date time generated from string is {}", utcLocalDateTime.toString());

        //LocalDateTime ldt = LocalDateTime.parse(dateString, DATE_TIME_FORMAT);
        return timeInUtcString(utcLocalDateTime);
    }

    public static LocalDateTime localDateTimeForQuery() {
        LocalDateTime localDateTime = LocalDateTime.now(); // or parse a string
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime utcZdt = zdt.withZoneSameInstant(ZoneOffset.UTC);
        LocalDateTime utcLocalDateTime = utcZdt.toLocalDateTime();
        log.info("Local date time generated from string is {}", utcLocalDateTime.toString());

        //LocalDateTime ldt = LocalDateTime.parse(dateString, DATE_TIME_FORMAT);
        return utcLocalDateTime;
    }

    //TODO Revisit the generate user key generation
    public static String generateUserKey(int length) {
        String newKey = "UK".concat(generateKey(18));
        //   while(!isUserKeyUnique(newKey))
        newKey = "K" + generateKey(length);
//        newKey = "K" + generateKey(19);
        return newKey;
    }

    public static String generateKey(int length) {
        StringBuilder keyBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            keyBuilder.append(getRandomCharacter());
        }
        return keyBuilder.toString();
    }
}
