package com.projects.expensetracker.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import com.projects.expensetracker.enumaration.ExpenseCategory;

public class StringToEnumConverter implements Converter<String, ExpenseCategory> {
    @Override
    public ExpenseCategory convert(String source) {
        return ExpenseCategory.valueOf(source.toUpperCase());
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
