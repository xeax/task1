package com.example.task.quote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quotes/search")
@RequiredArgsConstructor
@Api(tags = "Quotes search controller")
public class QuoteSearchController {

    private final QuoteSearchService quoteSearchService;

    @GetMapping("/random")
    @ApiOperation("Get random quote")
    public ResponseEntity<QuoteDto> getRandom() {
        return ResponseEntity.ok(quoteSearchService.getRandom());
    }

    @GetMapping("/latest10")
    @ApiOperation("Get 10 latest quote")
    public ResponseEntity<List<QuoteDto>> getLatest10() {
        return ResponseEntity.ok(quoteSearchService.getLatest10());
    }

    @GetMapping("/top10")
    @ApiOperation("Get 10 top rated quote")
    public ResponseEntity<List<QuoteDto>> getTop10() {
        return ResponseEntity.ok(quoteSearchService.getTop10());
    }

    @GetMapping("/flop10")
    @ApiOperation("Get 10 flop voted quote")
    public ResponseEntity<List<QuoteDto>> getFlop10() {
        return ResponseEntity.ok(quoteSearchService.getFlop10());
    }
}