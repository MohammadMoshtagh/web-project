package edu.sharif.webproject.rabbit;

import edu.sharif.webproject.config.RabbitMQConfig;
import edu.sharif.webproject.country.CountryService;
import edu.sharif.webproject.country.entity.dto.CountryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class ApiRequestListener {

    private final CountryService countryService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public CountryDto handleApiRequest(String name) {
        try {
            return countryService.getCountry(name);
        } catch (ResponseStatusException rse) {
            throw new AmqpRejectAndDontRequeueException(rse);
        }
    }
}
