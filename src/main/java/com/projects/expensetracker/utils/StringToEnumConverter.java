package com.projects.expensetracker.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.projects.expensetracker.enumaration.ExpenseType;

public class StringToEnumConverter implements Converter<String, ExpenseType> {
    @Override
    public ExpenseType convert(String source) {
        return ExpenseType.valueOf(source.toUpperCase());
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }

}
