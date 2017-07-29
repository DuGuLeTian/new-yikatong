package com.liutianhong.yikatong.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Duguletian.
 */
@Entity
@Table(name = "duguletian")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Duguletian implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "duguletian_name")
    private String duguletian_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDuguletian_name() {
        return duguletian_name;
    }

    public Duguletian duguletian_name(String duguletian_name) {
        this.duguletian_name = duguletian_name;
        return this;
    }

    public void setDuguletian_name(String duguletian_name) {
        this.duguletian_name = duguletian_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Duguletian duguletian = (Duguletian) o;
        if (duguletian.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, duguletian.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Duguletian{" +
            "id=" + id +
            ", duguletian_name='" + duguletian_name + "'" +
            '}';
    }
}
