package edu.school21.preprocessor;

import java.util.Locale;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String translateLetters(String message) {
        return message.toLowerCase();
    }
}
