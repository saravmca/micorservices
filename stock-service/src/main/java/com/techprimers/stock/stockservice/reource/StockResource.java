package com.techprimers.stock.stockservice.reource;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {
    /*@Autowired
    RestTemplate restTemplate;*/
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    private YahooFinance yahooFinance;

    public StockResource() {
        yahooFinance= new YahooFinance();
    }

    @GetMapping("/{username}")
    public List<Quote> getStock(@PathVariable("username") final String username){
        ResponseEntity<List<String>> quoteResponse =
                            restTemplate().exchange("http://localhost:8300/rest/db/" + username,
                                    HttpMethod.GET,
                                    null,
                                    new ParameterizedTypeReference<List<String>>(){});
        List<String> quotes=quoteResponse.getBody();
        System.out.println(quotes);

        return quotes.stream()
                            .map(quote->{
                                Stock stock =getStockPrice(quote);
                                System.out.println("GetQuote:"+stock.getQuote());
                                return new Quote(quote,stock.getQuote().getPrice());
                            })
                            .collect(Collectors.toList());
    }
    private Stock getStockPrice(String quote){
        try{
            return YahooFinance.get(quote);
        }catch(IOException ioe){
            ioe.printStackTrace();
            return new Stock(quote);
        }
    }

    private class Quote implements Serializable {
        private String quote;
        private BigDecimal price;

        public Quote(String quote, BigDecimal price) {
            this.quote = quote;
            this.price = price;
        }

        public String getQuote() {
            return quote;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
