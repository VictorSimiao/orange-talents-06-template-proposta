package br.com.zupacademy.victor.proposta.cartao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.victor.proposta.cartao.model.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Integer> {
	Optional<Cartao> findByNumeroCartao(String numeroCartao);
}
