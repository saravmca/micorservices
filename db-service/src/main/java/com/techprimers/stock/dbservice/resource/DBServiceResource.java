package com.techprimers.stock.dbservice.resource;

import com.techprimers.stock.dbservice.model.Quote;
import com.techprimers.stock.dbservice.model.Quotes;
import com.techprimers.stock.dbservice.repository.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/rest/db")
public class DBServiceResource {

	@Autowired
	QuotesRepository quotesRepository;

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String username){
		return getQuotesByUserName(username);
	}

	@PostMapping("/add")
	public List<String> add(@RequestBody final Quotes quotes){
			quotes.getQuotes()
					.stream()
					.map(quote-> new Quote(quotes.getUserName(),quote))
					.forEach(quote->quotesRepository.save(quote));
		return getQuotesByUserName(quotes.getUserName());
	}
	@DeleteMapping("delete/{username}")
	public List<String> delete(@PathVariable("username") final String username){
		List<Quote> quotes =quotesRepository.findByuserName(username);
		quotesRepository.delete(quotes);
		return getQuotesByUserName(username);
	}
	private List<String> getQuotesByUserName(@PathVariable("username") final String username){
		return quotesRepository.findByuserName(username)
				.stream()
				.map(Quote::getQuote)
				.collect(Collectors.toList());
	}
}
