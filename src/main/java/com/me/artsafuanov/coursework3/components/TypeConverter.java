package com.me.artsafuanov.coursework3.components;
import com.me.artsafuanov.coursework3.model.TypeOfOperation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeConverter implements Converter<String, TypeOfOperation> {
    @Override
    public TypeOfOperation convert (String source) {
        return TypeOfOperation.forValues(source);
    }
}
