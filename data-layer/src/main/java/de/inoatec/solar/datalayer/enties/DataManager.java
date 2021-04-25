package de.inoatec.solar.datalayer.enties;

import de.inoatec.solar.datalayer.util.ConvertUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class DataManager {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Long addYield(final String strDate, final String strYield) {

        if (strDate == null || strDate.isEmpty()) {
            throw new IllegalArgumentException("No Date for Yield is set!");
        }

        if (strYield == null || strYield.isEmpty()) {
            throw new IllegalArgumentException("No Yield is set!");
        }

        final Date date = ConvertUtils.convertStringToDate(strDate);
        final BigDecimal yield = ConvertUtils.convertStringToBigDecimal(strYield);

        final Yield entityYield = new Yield(date, yield);
        entityManager.persist(entityYield);

        return entityYield.getId();
    }

    public Yield findById(final Long id){
        final Yield yield = entityManager.find(Yield.class, id);
        return yield;
    }

    public List<Yield> getYields() {
        List<Yield> yield = entityManager.createQuery("SELECT y FROM Yield y", Yield.class).getResultList();
        return yield;
    }

}
