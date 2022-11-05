package ua.lviv.iot.dblabs.lab5.converters;

import org.springframework.core.convert.converter.Converter;
import ua.lviv.iot.dblabs.lab5.domain.enums.PaymentType;

public class StringToPaymentTypeConverter implements Converter<String, PaymentType> {
    @Override
    public PaymentType convert(String source) {
        try {
            return PaymentType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
