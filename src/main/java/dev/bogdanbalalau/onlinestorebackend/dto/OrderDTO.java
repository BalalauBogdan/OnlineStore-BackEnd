package dev.bogdanbalalau.onlinestorebackend.dto;

import dev.bogdanbalalau.onlinestorebackend.entity.Product;
import dev.bogdanbalalau.onlinestorebackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private User user;
    private List<Product> products;
}
