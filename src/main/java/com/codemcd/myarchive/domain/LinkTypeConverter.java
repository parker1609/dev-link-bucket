package com.codemcd.myarchive.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LinkTypeConverter implements AttributeConverter<LinkType, String> {

    @Override
    public String convertToDatabaseColumn(LinkType attribute) {
        return attribute.getLinkType();
    }

    @Override
    public LinkType convertToEntityAttribute(String dbData) {
        return LinkType.of(dbData);
    }
}
