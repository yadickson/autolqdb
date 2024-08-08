/*
 * Copyright (C) 2020 Yadickson Soto
 *
 * See <http://www.gnu.org/licenses/gpl-3.0.html>.
 */
package com.github.yadickson.autodblq.db.view.base;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.github.yadickson.autodblq.db.function.base.DataBaseFunctionBaseSort;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import com.github.yadickson.autodblq.db.connection.DriverConnection;
import com.github.yadickson.autodblq.db.connection.driver.Driver;
import com.github.yadickson.autodblq.db.sqlquery.SqlExecuteToGetList;
import com.github.yadickson.autodblq.db.view.base.model.ViewBase;
import com.github.yadickson.autodblq.db.view.base.model.ViewBaseBean;

/**
 *
 * @author Yadickson Soto
 */
@Named
public class DataBaseViewBaseReader {

    private static final Logger LOGGER = Logger.getLogger(DataBaseViewBaseReader.class);

    private final DataBaseViewBaseQueryFactory dataBaseViewQueryFactory;
    private final SqlExecuteToGetList sqlExecuteToGetList;
    private final DataBaseViewBaseMapper dataBaseViewMapper;

    private String sqlQuery;
    private List<ViewBaseBean> allViews;

    @Inject
    public DataBaseViewBaseReader(
            final DataBaseViewBaseQueryFactory dataBaseViewQueryFactory,
            final SqlExecuteToGetList sqlExecuteToGetList,
            final DataBaseViewBaseMapper dataBaseViewMapper
    ) {
        this.dataBaseViewQueryFactory = dataBaseViewQueryFactory;
        this.sqlExecuteToGetList = sqlExecuteToGetList;
        this.dataBaseViewMapper = dataBaseViewMapper;
    }

    public List<ViewBase> execute(
            final List<String> filter,
            final DriverConnection driverConnection
    ) {

        try {

            if (CollectionUtils.isEmpty(filter)) {
                return new ArrayList<>();
            }

            findSqlQuery(filter, driverConnection);
            findViews(driverConnection);
            return processViews(filter);

        } catch (RuntimeException ex) {
            throw new DataBaseViewBaseReaderException(ex);
        }
    }

    private void findSqlQuery(
            final List<String> filter,
            final DriverConnection driverConnection
    ) {
        final Driver driver = driverConnection.getDriver();
        final DataBaseViewBaseQuery query = dataBaseViewQueryFactory.apply(driver);
        sqlQuery = query.get(filter);
        LOGGER.debug("[DataBaseViewReader] SQL: " + sqlQuery);
    }

    private void findViews(final DriverConnection driverConnection) {
        LOGGER.info("[DataBaseViewReader] Starting");
        allViews = sqlExecuteToGetList.execute(driverConnection, sqlQuery, ViewBaseBean.class);
        LOGGER.info("[DataBaseViewReader] Total: " + allViews.size());
    }

    private List<ViewBase> processViews(final List<String> filter) {
        List<ViewBase> views = dataBaseViewMapper.apply(allViews);
        views.sort(new DataBaseViewBaseSort(filter));
        return views;
    }

}
