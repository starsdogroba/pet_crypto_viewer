package pet_crypto_viewer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Component
public class Service {
    @Autowired
    private DAO dao;

    @Autowired
    private DateTimeFormatter dtf;

    public void extractAndPushPriceBTC(String responseJSON) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(responseJSON);
        Integer price24h = rootNode.get("price_24h").asInt();
        BTC_price btcEntity = new BTC_price(price24h, dtf.format(Instant.now()));
        dao.saveBTC(btcEntity);
    }

}
