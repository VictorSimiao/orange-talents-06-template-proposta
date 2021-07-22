package br.com.zupacademy.victor.proposta.proposta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.zupacademy.victor.proposta.proposta.model.Proposta;

public interface PropostaRepository extends JpaRepository<Proposta, Integer> {
	
	public Optional<Proposta> findByDocumento(String documento);

	@Query("select p from Proposta p where p.status = 'ELEGIVEL' and p.cartao = null")
	List<Proposta> propostasElegiveisSemCartao();
}
