package br.com.catalisa.bancozup.model;

import br.com.catalisa.bancozup.enums.TipoLogEvento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "logeventos")
public class LogEventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "evento", length = 500)
    private String evento;
    @Column(name = "tipo_evento")
    @Enumerated(EnumType.STRING)
    private TipoLogEvento tipoLogEvento;
    @Column(name = "data_hora_evento")
    private LocalDateTime dataHoraEvento = LocalDateTime.now();

}