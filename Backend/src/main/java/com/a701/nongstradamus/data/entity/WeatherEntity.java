package com.a701.nongstradamus.data.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name =  "weather")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherEntity {

    @Id
    @Column(name = "weatherId",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "avgTemperature",nullable = false)
    private int avgTemperature;

    @Column(name = "maxTemperature",nullable = false)
    private int maxTemperature;

    @Column(name = "minTemperature",nullable = false)
    private int minTemperature;

    @Column(name = "rain",nullable = false)
    private int rain;

    @Column(name = "wind",nullable = false)
    private int wind;

    @Column(name = "humidity",nullable = false)
    private int humidity;

    @Column(name = "sunDuration",nullable = false)
    private int sunDuration;

    @ManyToOne
    @JoinColumn(name = "productId")
    ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "code")
    OriginEntity origin;
}
