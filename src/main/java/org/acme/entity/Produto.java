package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Produto extends PanacheEntity {
    public String nome;
    public BigDecimal valor;

    @UpdateTimestamp
    public Date dataCriacao;
    @UpdateTimestamp
    public Date dataAtualizada;

}
