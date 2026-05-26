package com.duoc.gamehub.payment.repository;

import com.duoc.gamehub.payment.model.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    boolean existsByOrdenIdAndEstado(Long ordenId, String estado);
    Optional<Pago> findByOrdenId(Long ordenId);
}