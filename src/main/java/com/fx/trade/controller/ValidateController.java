package com.fx.trade.controller;

import com.fx.trade.bean.AbstractDTO;
import com.fx.trade.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fxtrade")
public class ValidateController {

   @Autowired
   private ValidateService validateService;

   /*
      Create validate report therefore POST method is chosen
    */
   @PostMapping (path = "/validate",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Object> validate(
         @RequestBody
               AbstractDTO abstractDTO) throws Exception {

      return ResponseEntity.ok(validateService.validate(abstractDTO));
   }

   @ExceptionHandler(HttpMessageNotReadableException.class)
   @PostMapping (path = "/bulk/validate",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Object> validateBulk(
         @RequestBody
               List<AbstractDTO> abstractDTOs) throws Exception {
      return ResponseEntity.ok(validateService.validate(abstractDTOs));
   }

}