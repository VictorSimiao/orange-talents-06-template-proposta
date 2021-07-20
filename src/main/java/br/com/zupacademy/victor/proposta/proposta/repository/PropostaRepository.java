package br.com.zupacademy.victor.proposta.proposta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.victor.proposta.proposta.model.Proposta;

public interface PropostaRepository extends JpaRepository<Proposta, Integer> {
	public Optional<Proposta> findByDocumento(String documento);
}
