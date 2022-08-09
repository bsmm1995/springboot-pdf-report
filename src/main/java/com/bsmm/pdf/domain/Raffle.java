package com.bsmm.pdf.domain;

import lombok.Data;

import java.util.List;

@Data
public class Raffle {
    private String candidates;
    private List<String> winners;
}
