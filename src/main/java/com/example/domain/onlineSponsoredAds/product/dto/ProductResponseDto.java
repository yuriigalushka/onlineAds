package com.example.domain.onlineSponsoredAds.product.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductResponseDto {
    String serialNumber;
    String title;
    String category;
    int price;
}
