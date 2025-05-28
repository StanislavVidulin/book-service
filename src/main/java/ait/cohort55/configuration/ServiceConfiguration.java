package ait.cohort55.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                // разрешили работать с полями не через сеттеры, а напрямую вставлять в поле значение,
                // mapper может работать с полями
                .setFieldMatchingEnabled(true)
                // true для приватных полей
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                // настройка строгого соответствия полей
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }
}
