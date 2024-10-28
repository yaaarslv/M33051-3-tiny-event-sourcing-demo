package ru.itmo.es.customer;

import lombok.Data;

@Data
public class CustomerDto {
    private String nickname;
    private String name;
    private String password;
}
