package com.elephant.dreamhi.configuration.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StringToEnumConverterFactory
        implements ConverterFactory<String, Enum> {

    private static class StringToEnumConverter<T extends Enum>
            implements Converter<String, T> {

        private Class<T> enumType;

        public StringToEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        public T convert(String source) {
            log.info("Source : {} {} {}",source ,source.toUpperCase(), (T)Enum.valueOf(this.enumType, source.toUpperCase()));
            return (T) Enum.valueOf(this.enumType, source.toUpperCase());
        }

    }

    @Override
    public <T extends Enum> Converter<String, T> getConverter(
            Class<T> targetType) {
        return new StringToEnumConverter(targetType);
    }

}
