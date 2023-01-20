package com.gaaji.useditem.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TownToken {
    private String townId;

    private boolean isAuthenticated;

}
