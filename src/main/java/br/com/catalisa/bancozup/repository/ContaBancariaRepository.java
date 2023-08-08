package br.com.catalisa.bancozup.repository;

import br.com.catalisa.bancozup.model.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
}
