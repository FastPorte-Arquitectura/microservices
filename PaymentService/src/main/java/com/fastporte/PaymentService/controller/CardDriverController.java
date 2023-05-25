package com.fastporte.PaymentService.controller;

import com.fastporte.PaymentService.entities.Card;
import com.fastporte.PaymentService.entities.CardDriver;
import com.fastporte.PaymentService.service.ICardDriverService;
import com.fastporte.PaymentService.service.ICardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/cardsDriver")
@Api(tags="Cards Driver", value="Web Service RESTful of Cards Driver")
public class CardDriverController {
    private final ICardDriverService cardDriverService;
    private final ICardService cardService;

    public CardDriverController(ICardDriverService cardDriverService, ICardService cardService) {
        this.cardDriverService = cardDriverService;
        this.cardService = cardService;
    }

    //Retornar todos los cardsDriver
    @GetMapping(value = "/all", produces = "application/json")
    @ApiOperation(value="List of Cards Driver", notes="Method to list all cards driver")
    @ApiResponses({
            @ApiResponse(code=201, message="Cards driver found"),
            @ApiResponse(code=404, message="Cards driver not found"),
            @ApiResponse(code=501, message="Internal server error")
    })
    public ResponseEntity<List<CardDriver>> findAll() {
        try {
            List<CardDriver> cardsDriver = cardDriverService.getAll();
            if (cardsDriver.size() > 0)
                return new ResponseEntity<>(cardsDriver, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Obtener los cards de un driver por id
    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value="Card Driver by Id", notes="Method to find a card driver by id")
    @ApiResponses({
            @ApiResponse(code=201, message="Card Driver found"),
            @ApiResponse(code=404, message="Card Driver not found"),
            @ApiResponse(code=501, message="Internal server error")
    })
    public ResponseEntity<List<CardDriver>> getCardsByDriverId(@PathVariable("id") Long id) {

        try {
            List<CardDriver> cardsDriver = cardDriverService.getAll();
            cardsDriver.removeIf(cardDriver -> !cardDriver.getDriverId().equals(id));
            if (cardsDriver.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cardsDriver, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Agregar un card a un driver
    @PostMapping(value = "/{idDriver}/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Insert Card Driver", notes="Method to insert a card driver")
    @ApiResponses({
            @ApiResponse(code=201, message="Card driver created"),
            @ApiResponse(code=404, message="Card driver not created"),
            @ApiResponse(code=501, message="Internal server error")
    })
    public ResponseEntity<CardDriver> addCardToDriver(@PathVariable("idDriver") Long idDriver,
                                                      @Valid @RequestBody Card card) {
        try {
            Long id = cardService.save(card).getId();
            System.out.println("Id: " + id);
            CardDriver cardDriver = new CardDriver();
            cardDriver.setDriverId(idDriver);
            cardDriver.setCard(cardService.getById(id).get());
            cardDriverService.save(cardDriver);
            id = 0L;
            return new ResponseEntity<>(cardDriver, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Eliminar un card de un driver
    @DeleteMapping(value = "/{idDriver}/delete/{idCard}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Delete Card Driver", notes="Method to delete a card driver")
    @ApiResponses({
            @ApiResponse(code=201, message="Card driver deleted"),
            @ApiResponse(code=404, message="Card driver not deleted"),
            @ApiResponse(code=501, message="Internal server error")
    })
    public ResponseEntity<HttpStatus> deleteCardFromDriver(@PathVariable("idDriver") Long idDriver,
                                                           @PathVariable("idCard") Long idCard) {
        try {

            Optional<Card> card = cardService.getById(idCard);

            if (card.isPresent()) {
                List<CardDriver> cardsDriver = cardDriverService.getAll();
                cardsDriver.removeIf(cardDriver ->
                        !(cardDriver.getDriverId().equals(idDriver) &&
                                cardDriver.getCard().getId().equals(idCard)));
                cardDriverService.delete(cardsDriver.get(0).getId());
                cardService.delete(idCard);

                return new ResponseEntity<>(HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}