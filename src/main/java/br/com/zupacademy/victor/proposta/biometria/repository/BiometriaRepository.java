package br.com.zupacademy.victor.proposta.biometria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.victor.proposta.biometria.model.Biometria;

public interface BiometriaRepository extends JpaRepository<Biometria, Integer> {

}
