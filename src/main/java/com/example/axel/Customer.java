package com.example.axel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name="Customer")
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    private String name;
    private String email;
    private String phone;
    private String city;
    private String country;
    private String gender;

}
