package de.inoatec.solar.datalayer;

import de.inoatec.solar.datalayer.enties.DataManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLayerConfiguration {

    @Bean
    public DataManager newDataManager() {
        return new DataManager();
    }
}
