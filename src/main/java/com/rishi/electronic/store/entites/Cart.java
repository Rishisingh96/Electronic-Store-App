package com.rishi.electronic.store.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    private String cartId;
    private Date createdAt;
    @OneToOne
    private User user;

    //mapping cart-items
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
  //  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,orphanRemoval = true)  //if Duplicate recode error show
    private List<CartItem> items = new ArrayList<>();
}
