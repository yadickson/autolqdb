<?xml version="1.0" encoding="UTF-8"?>

<!-- @GENERATOR.NAME@ -->
<!-- @GENERATOR.VERSION@ -->

<databaseChangeLog
    xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
    xmlns:ext="http://www.liquibase.org/xml/ns/dbchangelog-ext"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
    http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-${lqversion}.xsd
    http://www.liquibase.org/xml/ns/dbchangelog-ext
    http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-ext.xsd">
<#assign step = 1>

    <!-- Load Data Tables definitions -->

    <changeSet id="${step?string["0000"]}" author="${author}" runOnChange="false">
        <ext:tagDatabase tag="${version}-${file?string["00"]}.${step?string["0000"]}"/>
    </changeSet>

<#if tables?? >
<#list tables as table >
<#if table.generateData?? >
    <!-- ${table.fullName} -->
</#if>
</#list>

<#list tables as table >
<#if table.generateData?? >
<#assign step++ >
    <changeSet id="${step?string["0000"]}" author="${author}" dbms="${driverName}" runOnChange="false">
        <ext:tagDatabase tag="${version}-${file?string["00"]}.${step?string["0000"]}"/>

        <loadData
<#if table.schema?? >
            schemaName="${table.schema}"
</#if>
            encoding="${encode}"
            file="../csv/${table.name}.csv"
            relativeToChangelogFile="true"
            quotchar="${quotchar}"
            separator="${separator}"
        >

<#if table.fields?? >
<#list table.fields as column >
            <column name="${column.name}" type="<#if typeUtil.isString(column.type) >STRING<#else>NUMERIC</#if>" />
</#list>
</#if>

        </loadData>

        <rollback>
            <delete tableName="${table.name}"<#if table.schema?? > schemaName="${table.schema}"</#if>/>
        </rollback>

    </changeSet>

</#if>
</#list>
</#if>
</databaseChangeLog>