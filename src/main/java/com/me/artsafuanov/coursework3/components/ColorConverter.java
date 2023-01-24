package com.me.artsafuanov.coursework3.components;
import com.me.artsafuanov.coursework3.model.Color;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
@Component
public class ColorConverter implements Converter<String, Color> {
    @Override
    public Color convert (String source) {

        return Color.forValues(source);
    }
}
