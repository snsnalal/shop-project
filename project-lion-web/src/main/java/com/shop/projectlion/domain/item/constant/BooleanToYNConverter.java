package com.shop.projectlion.domain.item.constant;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//ItemImage 대표 사진 여부가 DB에는 varchar타입이기 때문에 true -> Y로 false -> N으로 변경해준다.
@Converter
public class BooleanToYNConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Y".equalsIgnoreCase(dbData);
    }
}
