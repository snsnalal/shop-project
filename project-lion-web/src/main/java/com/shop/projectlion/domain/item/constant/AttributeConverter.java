package com.shop.projectlion.domain.item.constant;

public interface AttributeConverter <X, Y>{
    Y convertToDatabaseColumn(X var1);

    X convertTOEntityAttribute(Y var1);

}
