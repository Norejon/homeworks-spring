package com.example.homework1.models.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ClientUserDTO {
    private String username;
    private String password;
}
