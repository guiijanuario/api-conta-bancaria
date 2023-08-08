package br.com.catalisa.bancozup.repository;

import br.com.catalisa.bancozup.model.LogEventos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEventosRepository extends JpaRepository<LogEventos, Long> {
}
