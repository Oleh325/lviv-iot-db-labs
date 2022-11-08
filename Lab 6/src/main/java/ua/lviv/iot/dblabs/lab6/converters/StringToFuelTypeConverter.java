package ua.lviv.iot.dblabs.lab6.converters;

import org.springframework.core.convert.converter.Converter;
import ua.lviv.iot.dblabs.lab6.domain.enums.FuelType;

public class StringToFuelTypeConverter implements Converter<String, FuelType> {
    @Override
    public FuelType convert(String source) {
        try {
            return FuelType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
