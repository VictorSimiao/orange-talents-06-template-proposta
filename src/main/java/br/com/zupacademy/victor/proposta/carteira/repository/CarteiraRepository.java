package br.com.zupacademy.victor.proposta.carteira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.victor.proposta.cartao.model.Cartao;
import br.com.zupacademy.victor.proposta.carteira.model.Carteira;
import br.com.zupacademy.victor.proposta.carteira.model.TipoCarteiraDigital;

public interface CarteiraRepository extends JpaRepository<Carteira, Integer> {

	boolean existsByCartaoAndCarteira(Cartao cartao, TipoCarteiraDigital Carteira);
}
