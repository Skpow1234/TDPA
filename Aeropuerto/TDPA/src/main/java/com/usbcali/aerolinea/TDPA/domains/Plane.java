package com.usbcali.aerolinea.TDPA.domains;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "planes")
public class Plane {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plane_number")
    private String planeNumber;

    @Column(name = "plane_name")
    private String planeName;

    @Column(name = "plane_code")
    private String planeCode;
}
