package com.oauth2_client.api;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final Supplier<Long> accNoSupplier = () -> RandomUtils.secure().randomLong();

    @GetMapping
    public List<Long> getAccountNos(){
        return List.of(accNoSupplier.get(), accNoSupplier.get(), accNoSupplier.get());
    }
}
