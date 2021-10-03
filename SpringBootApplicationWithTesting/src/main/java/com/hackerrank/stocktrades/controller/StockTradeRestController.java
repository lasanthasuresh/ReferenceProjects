package com.hackerrank.stocktrades.controller;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/trades")
@RestController
public class StockTradeRestController {

    @Autowired
    private StockTradeRepository stockTradeRepository;


    @GetMapping
    public List<StockTrade> getTrades() {
        return stockTradeRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<StockTrade> getTrade(@PathVariable Integer id) {
        Optional<StockTrade> stockTrade = stockTradeRepository.findById(id);
        return stockTrade.map(stockTrade1 -> new ResponseEntity<>(stockTrade1,
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<StockTrade> createTrade(@RequestBody StockTrade stockTrade) {
        return new ResponseEntity<>(stockTradeRepository.save(stockTrade), HttpStatus.CREATED);
    }

    @PutMapping
    @PatchMapping
    @DeleteMapping
    public ResponseEntity<StockTrade> updateOrCreateTrade(@PathVariable Integer id) {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

}
