package com.example.demo.covertDateFormat;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.YearMonth;

@Converter(autoApply = true)
public class YearMonthConverter implements AttributeConverter<YearMonth, String> {

    @Override
    public String convertToDatabaseColumn(YearMonth yearMonth) {
        return  yearMonth != null ? yearMonth.toString() : null;
    }

    @Override
    public YearMonth convertToEntityAttribute(String s) {
        return s != null ? YearMonth.parse(s) : null;
    }
}
