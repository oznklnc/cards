package com.eazybytes.cards.config;

import com.eazybytes.cards.dto.CardsContactInfoDto;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {CardsContactInfoDto.class})
public class PropertiesConfig {
}
