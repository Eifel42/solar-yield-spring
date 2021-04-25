package de.inoatec.solar.datalayer.enties;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "yield")
public class Yield {
    private final static String DEFAULT_PLANT = "HOF-4711";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private Date date;
    private String plant;
    private BigDecimal yield;

    public Yield(Date date, BigDecimal yield) {
        this.date = date;
        this.plant = DEFAULT_PLANT;
        this.yield = yield;
    }

    public Yield() {
    }
    public Long getId() {
        return id;
    }

    public Date getTimestamp() {
        return date;
    }

    public String getPlant() {
        return plant;
    }

    public BigDecimal getYield() {
        return yield;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Yield that = (Yield) o;
        return id.equals(that.id) && date.equals(that.date) && plant.equals(that.plant) && yield.equals(that.yield);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, plant, yield);
    }
}
