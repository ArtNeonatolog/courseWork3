package com.me.artsafuanov.coursework3.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TypeOfOperation {
    ADD ("прием носков на склад"),
    OUTPUT("выдача носков со склада"),
    REMOVE_DEFECTED("списание дефектных носков со склада");

    private final String strValue;

    TypeOfOperation (String typeOfOperation) {
        this.strValue = typeOfOperation;
    }
    @JsonValue
    public String getStrValue() {
        return this.strValue;
    }
    @JsonCreator
    public static TypeOfOperation forValues(String value) {
        for (TypeOfOperation typeOfOperation : TypeOfOperation.values()) {
            if (typeOfOperation.getStrValue().equals(value)) {
                return typeOfOperation;
            }
        }
        return null;
    }
}
