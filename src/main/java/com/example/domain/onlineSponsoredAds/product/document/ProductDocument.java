package com.example.domain.onlineSponsoredAds.product.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Document(collection = "products")
public class ProductDocument {
    @Id
    String serialNumber;
    String title;
    String category;
    int price;
}
