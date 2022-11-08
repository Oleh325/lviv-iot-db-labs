package ua.lviv.iot.dblabs.lab6.converters;

import org.springframework.core.convert.converter.Converter;
import ua.lviv.iot.dblabs.lab6.domain.enums.TransmissionType;

public class StringToTransmissionTypeConverter implements Converter<String, TransmissionType> {
    @Override
    public TransmissionType convert(String source) {
        try {
            return TransmissionType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
