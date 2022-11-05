package ua.lviv.iot.dblabs.lab5.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ua.lviv.iot.dblabs.lab5.converters.StringToFuelTypeConverter;
import ua.lviv.iot.dblabs.lab5.converters.StringToPaymentTypeConverter;
import ua.lviv.iot.dblabs.lab5.converters.StringToTransmissionTypeConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToFuelTypeConverter());
        registry.addConverter(new StringToPaymentTypeConverter());
        registry.addConverter(new StringToTransmissionTypeConverter());
    }
}
