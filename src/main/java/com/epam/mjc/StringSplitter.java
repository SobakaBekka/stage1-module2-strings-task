package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

public class StringSplitter {

    /**
     * Splits given string applying all delimiters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        if (source == null || source.isEmpty()) {
            return new ArrayList<>();
        }

        if (delimiters == null || delimiters.isEmpty()) {
            List<String> result = new ArrayList<>();
            result.add(source);
            return result;
        }

        // Создаем регулярное выражение для всех делимитеров
        StringJoiner regexJoiner = new StringJoiner("|");
        for (String delimiter : delimiters) {
            regexJoiner.add("\\Q" + delimiter + "\\E");
        }
        String regex = regexJoiner.toString();

        // Разделяем строку по регулярному выражению
        String[] parts = source.split(regex);

        // Помещаем части в список и возвращаем его
        List<String> result = new ArrayList<>();
        for (String part : parts) {
            if (!part.isEmpty()) {
                result.add(part);
            }
        }

        return result;
    }
}
