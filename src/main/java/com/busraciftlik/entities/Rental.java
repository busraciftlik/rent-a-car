package com.busraciftlik.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double dailyPrice;
    private int rentedForDays;
    private double totalPrice; // only read
    private LocalDateTime startDate;
    @ManyToOne
    private Car car;

}

/*1. Araçlar kiralanabilmelidir (rental)
      2. Kiraya gönderilen aracın durumu güncellenmelidir.
        3. Kirada veya bakımda olan bir araç kiralanamamalı.
        4. totalPrice alanı sadece okunabilmelidir.
        5. Kiradan dönen aracın durumu (rental delete işlemi ile) güncellenmelidir.

 */