package com.example.task.quote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("quotes")
@RequiredArgsConstructor
@Api(tags = "Quotes controller")
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping("/{id}")
    @ApiOperation("Get quote by id")
    public ResponseEntity<QuoteDto> get(@PathVariable("id") long id) {
        return ResponseEntity.ok(quoteService.get(id));
    }

    @PostMapping
    @ApiOperation("Create quote")
    public ResponseEntity<?> create(UriComponentsBuilder b, @Valid @RequestBody QuoteDto quoteDto) {
        QuoteDto quoteDtoCreated = quoteService.create(quoteDto);
        UriComponents uriComponents = b.path("/quotes/{id}").buildAndExpand(quoteDtoCreated.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Update quote")
    public void update(@PathVariable long id, @Valid @RequestBody QuoteDto quoteDto) {
        quoteService.update(id, quoteDto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete quote")
    public void delete(@PathVariable("id") long id) {
        quoteService.delete(id);
    }
}