package io.pivotal.pa.restvalidation;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectController {

    @GetMapping("/")
    public DataObject validate(@Valid @RequestBody DataObject dataObject) {
        return dataObject;
    }
}