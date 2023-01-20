package com.me.artsafuanov.coursework3.controller;
import com.me.artsafuanov.coursework3.dto.SocksRequest;
import com.me.artsafuanov.coursework3.exception.InsufficientSocksQuantityException;
import com.me.artsafuanov.coursework3.exception.InvalidSocksException;
import com.me.artsafuanov.coursework3.model.Color;
import com.me.artsafuanov.coursework3.model.Size;
import com.me.artsafuanov.coursework3.model.Socks;
import com.me.artsafuanov.coursework3.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socks")
@Tag(name = "Товар (носки)", description = "CRUD-операции для работы с товаром на складе")
public class SocksController {
    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }

    @ExceptionHandler (InvalidSocksException.class)
    public ResponseEntity<String> handleInvalidException (InvalidSocksException invalidSocksException){
        return ResponseEntity.badRequest().body(invalidSocksException.getMessage());
    }

    @ExceptionHandler (InsufficientSocksQuantityException.class)
    public ResponseEntity<String> handleInvalidException (InsufficientSocksQuantityException insufficientSocksQuantityException){
        return ResponseEntity.badRequest().body(insufficientSocksQuantityException.getMessage());
    }

    @PostMapping
    @Operation(
            summary = "Добавление товара (носков) на склад",
            description = "При помощи данного метода можно регистрировать приход товара на склад")
    public void addSocks(@RequestBody SocksRequest socksRequest) {
        socksService.addSocks(socksRequest);
    }

    @GetMapping
    @Operation(
            summary = "Получение общего количества носков",
            description = "При помощи данного метода можно получить общее количество нсоков на складе необходимого цвета, размера и содержания хлопка"
    )
    public Integer getSocksQuantity(@RequestParam(required = false, name = "color") Color color,
                                    @RequestParam(required = false, name = "size") Size size,
                                    @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                                    @RequestParam(required = false, name = "cottonMax") Integer cottonMax) {
        return socksService.getSocksQuantity(color, size, cottonMin, cottonMax);
    }

    @PutMapping
    @Operation(
            summary = "Отпуск товара (носков) со склада",
            description = "При помощи данного метода можно отпускать носки со склада")
    public void outputSocks(@RequestBody SocksRequest socksRequest) {
        socksService.outputSocks(socksRequest);
    }

    @DeleteMapping
    @Operation(
            summary = "Cписание испорченных (бракованных) носков",
            description = "При помощи данного метода можно регистрировать cписание испорченных (бракованных) носков")
    public void removeDefectSocks (@RequestBody SocksRequest socksRequest) {
        socksService.removeDefectSocks(socksRequest);
    }


}
