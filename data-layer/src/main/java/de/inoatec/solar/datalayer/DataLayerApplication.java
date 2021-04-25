package de.inoatec.solar.datalayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.inoatec.solar.datalayer.enties.DataManager;
import de.inoatec.solar.datalayer.enties.Yield;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
@EnableSwagger2
@RestController
@RequestMapping(path = "api")
public class DataLayerApplication {

    @Autowired
    private DataManager dataManager;


    public static void main(String[] args) {
        SpringApplication.run(DataLayerApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    @PostMapping(path = "add", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String setYield(@RequestParam("date") String date, @RequestParam("power") String power) {
        Long id = dataManager.addYield(date,power);
        return "add with Id " + id.toString();
    }

    @GetMapping(path = "version", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getVersion() {
        return "Yield API Version 0.001";
    }

    @GetMapping(path = "yield", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getYield(@RequestParam("id") long id) {
        String returnValue = HttpStatus.NOT_FOUND.toString();

        final Yield yield = dataManager.findById(id);
        if (yield != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                returnValue = mapper.writeValueAsString(yield);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }

    @GetMapping(path = "all", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String getAll() {
        String returnValue = HttpStatus.NOT_FOUND.toString();
        final List<Yield> yields = dataManager.getYields();
        if (yields != null && !yields.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                returnValue = mapper.writeValueAsString(yields);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return returnValue;

    }
}
