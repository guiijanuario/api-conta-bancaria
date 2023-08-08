package br.com.catalisa.bancozup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_contas")
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agencia")
    private String agencia;

    @Column(name = "numero_conta")
    private String numeroConta;

    @Column(name = "tutular_conta")
    private String titularConta;

    @Column(name = "saldo")
    private double saldo;


}
