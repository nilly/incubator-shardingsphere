/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.shardingjdbc.jdbc.core.datasource;

import io.shardingsphere.shardingjdbc.jdbc.adapter.WrapperAdapter;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

/**
 * Cached database meta data.
 *
 * @author zhangliang
 */
public final class CachedDatabaseMetaData extends WrapperAdapter implements DatabaseMetaData {
    
    private final String url;
    
    private final String userName;
    
    private final String databaseProductName;
    
    private final String databaseProductVersion;
    
    private final String driverName;
    
    private final String driverVersion;
    
    private final int driverMajorVersion;
    
    private final int driverMinorVersion;
    
    private final int databaseMajorVersion;
    
    private final int databaseMinorVersion;
    
    private final int jdbcMajorVersion;
    
    private final int jdbcMinorVersion;
    
    private final boolean isReadOnly;
    
    private final boolean allProceduresAreCallable;
    
    private final boolean allTablesAreSelectable;
    
    private final boolean nullsAreSortedHigh;
    
    private final boolean nullsAreSortedLow;
    
    private final boolean nullsAreSortedAtStart;
    
    private final boolean nullsAreSortedAtEnd;
    
    private final boolean usesLocalFiles;
    
    private final boolean usesLocalFilePerTable;
    
    private final boolean supportsMixedCaseIdentifiers;
    
    private final boolean storesUpperCaseIdentifiers;
    
    private final boolean storesLowerCaseIdentifiers;
    
    private final boolean storesMixedCaseIdentifiers;
    
    private final boolean supportsMixedCaseQuotedIdentifiers;
    
    private final boolean storesUpperCaseQuotedIdentifiers;
    
    private final boolean storesLowerCaseQuotedIdentifiers;
    
    private final boolean storesMixedCaseQuotedIdentifiers;
    
    private final String identifierQuoteString;
    
    private final String sqlKeywords;
    
    private final String numericFunctions;
    
    private final String stringFunctions;
    
    private final String systemFunctions;
    
    private final String timeDateFunctions;
    
    private final String searchStringEscape;
    
    private final String extraNameCharacters;
    
    private final boolean supportsAlterTableWithAddColumn;
    
    private final boolean supportsAlterTableWithDropColumn;
    
    private final boolean supportsColumnAliasing;
    
    private final boolean nullPlusNonNullIsNull;
    
    private final boolean supportsConvert;
    
    private final boolean supportsTableCorrelationNames;
    
    private final boolean supportsDifferentTableCorrelationNames;
    
    private final boolean supportsExpressionsInOrderBy;
    
    private final boolean supportsOrderByUnrelated;
    
    private final boolean supportsGroupBy;
    
    private final boolean supportsGroupByUnrelated;
    
    private final boolean supportsGroupByBeyondSelect;
    
    private final boolean supportsLikeEscapeClause;
    
    private final boolean supportsMultipleResultSets;
    
    private final boolean supportsMultipleTransactions;
    
    private final boolean supportsNonNullableColumns;
    
    private final boolean supportsMinimumSQLGrammar;
    
    private final boolean supportsCoreSQLGrammar;
    
    private final boolean supportsExtendedSQLGrammar;
    
    private final boolean supportsANSI92EntryLevelSQL;
    
    private final boolean supportsANSI92IntermediateSQL;
    
    private final boolean supportsANSI92FullSQL;
    
    private final boolean supportsIntegrityEnhancementFacility;
    
    private final boolean supportsOuterJoins;
    
    private final boolean supportsFullOuterJoins;
    
    private final boolean supportsLimitedOuterJoins;
    
    private final String schemaTerm;
    
    private final String procedureTerm;
    
    private final String catalogTerm;
    
    private final boolean isCatalogAtStart;
    
    private final String catalogSeparator;
    
    private final boolean supportsSchemasInDataManipulation;
    
    private final boolean supportsSchemasInProcedureCalls;
    
    private final boolean supportsSchemasInTableDefinitions;
    
    private final boolean supportsSchemasInIndexDefinitions;
    
    private final boolean supportsSchemasInPrivilegeDefinitions;
    
    private final boolean supportsCatalogsInDataManipulation;
    
    private final boolean supportsCatalogsInProcedureCalls;
    
    private final boolean supportsCatalogsInTableDefinitions;
    
    private final boolean supportsCatalogsInIndexDefinitions;
    
    private final boolean supportsCatalogsInPrivilegeDefinitions;
    
    private final boolean supportsPositionedDelete;
    
    private final boolean supportsPositionedUpdate;
    
    private final boolean supportsSelectForUpdate;
    
    private final boolean supportsStoredProcedures;
    
    private final boolean supportsSubqueriesInComparisons;
    
    private final boolean supportsSubqueriesInIns;
    
    private final boolean supportsSubqueriesInQuantifieds;
    
    private final boolean supportsCorrelatedSubqueries;
    
    private final boolean supportsUnion;
    
    private final boolean supportsUnionAll;
    
    private final boolean supportsOpenCursorsAcrossCommit;
    
    private final boolean supportsOpenCursorsAcrossRollback;
    
    private final boolean supportsOpenStatementsAcrossCommit;
    
    private final boolean supportsOpenStatementsAcrossRollback;
    
    private final int maxBinaryLiteralLength;
    
    private final int maxCharLiteralLength;
    
    private final int maxColumnNameLength;
    
    private final int maxColumnsInGroupBy;
    
    private final int maxColumnsInIndex;
    
    private final int maxColumnsInOrderBy;
    
    private final int maxColumnsInSelect;
    
    private final int maxColumnsInTable;
    
    private final int maxConnections;
    
    private final int maxCursorNameLength;
    
    private final int maxIndexLength;
    
    private final int maxSchemaNameLength;
    
    private final int maxProcedureNameLength;
    
    private final int maxCatalogNameLength;
    
    private final int maxRowSize;
    
    private final boolean doesMaxRowSizeIncludeBlobs;
    
    private final int maxStatementLength;
    
    private final int maxStatements;
    
    private final int maxTableNameLength;
    
    private final int maxTablesInSelect;
    
    private final int maxUserNameLength;
    
    private final int defaultTransactionIsolation;
    
    private final boolean supportsTransactions;
    
    private final boolean supportsDataDefinitionAndDataManipulationTransactions;
    
    private final boolean supportsDataManipulationTransactionsOnly;
    
    private final boolean dataDefinitionCausesTransactionCommit;
    
    private final boolean dataDefinitionIgnoredInTransactions;
    
    private final boolean supportsBatchUpdates;
    
    private final boolean supportsSavepoints;
    
    private final boolean supportsNamedParameters;
    
    private final boolean supportsMultipleOpenResults;
    
    private final boolean supportsGetGeneratedKeys;
    
    private final int resultSetHoldability;
    
    private final int sqlStateType;
    
    private final boolean locatorsUpdateCopy;
    
    private final boolean supportsStatementPooling;
    
    private final boolean supportsStoredFunctionsUsingCallSyntax;
    
    private final boolean autoCommitFailureClosesAllResultSets;
    
    private final boolean generatedKeyAlwaysReturned;
    
    private final RowIdLifetime rowIdLifetime;
    
    public CachedDatabaseMetaData(final DatabaseMetaData databaseMetaData) throws SQLException {
        url = databaseMetaData.getURL();
        userName = databaseMetaData.getUserName();
        databaseProductName = databaseMetaData.getDatabaseProductName();
        databaseProductVersion = databaseMetaData.getDatabaseProductVersion();
        driverName = databaseMetaData.getDriverName();
        driverVersion = databaseMetaData.getDriverVersion();
        driverMajorVersion = databaseMetaData.getDriverMajorVersion();
        driverMinorVersion = databaseMetaData.getDriverMinorVersion();
        databaseMajorVersion = databaseMetaData.getDatabaseMajorVersion();
        databaseMinorVersion = databaseMetaData.getDatabaseMinorVersion();
        jdbcMajorVersion = databaseMetaData.getJDBCMajorVersion();
        jdbcMinorVersion = databaseMetaData.getJDBCMinorVersion();
        isReadOnly = databaseMetaData.isReadOnly();
        allProceduresAreCallable = databaseMetaData.allProceduresAreCallable();
        allTablesAreSelectable = databaseMetaData.allTablesAreSelectable();
        nullsAreSortedHigh = databaseMetaData.nullsAreSortedHigh();
        nullsAreSortedLow = databaseMetaData.nullsAreSortedLow();
        nullsAreSortedAtStart = databaseMetaData.nullsAreSortedAtStart();
        nullsAreSortedAtEnd = databaseMetaData.nullsAreSortedAtEnd();
        usesLocalFiles = databaseMetaData.usesLocalFiles();
        usesLocalFilePerTable = databaseMetaData.usesLocalFilePerTable();
        supportsMixedCaseIdentifiers = databaseMetaData.supportsMixedCaseIdentifiers();
        storesUpperCaseIdentifiers = databaseMetaData.storesUpperCaseIdentifiers();
        storesLowerCaseIdentifiers = databaseMetaData.storesLowerCaseIdentifiers();
        storesMixedCaseIdentifiers = databaseMetaData.storesMixedCaseIdentifiers();
        supportsMixedCaseQuotedIdentifiers = databaseMetaData.supportsMixedCaseQuotedIdentifiers();
        storesUpperCaseQuotedIdentifiers = databaseMetaData.storesUpperCaseQuotedIdentifiers();
        storesLowerCaseQuotedIdentifiers = databaseMetaData.storesLowerCaseQuotedIdentifiers();
        storesMixedCaseQuotedIdentifiers = databaseMetaData.storesMixedCaseQuotedIdentifiers();
        identifierQuoteString = databaseMetaData.getIdentifierQuoteString();
        sqlKeywords = databaseMetaData.getSQLKeywords();
        numericFunctions = databaseMetaData.getNumericFunctions();
        stringFunctions = databaseMetaData.getStringFunctions();
        systemFunctions = databaseMetaData.getSystemFunctions();
        timeDateFunctions = databaseMetaData.getTimeDateFunctions();
        searchStringEscape = databaseMetaData.getSearchStringEscape();
        extraNameCharacters = databaseMetaData.getExtraNameCharacters();
        supportsAlterTableWithAddColumn = databaseMetaData.supportsAlterTableWithAddColumn();
        supportsAlterTableWithDropColumn = databaseMetaData.supportsAlterTableWithDropColumn();
        supportsColumnAliasing = databaseMetaData.supportsColumnAliasing();
        nullPlusNonNullIsNull = databaseMetaData.nullPlusNonNullIsNull();
        supportsConvert = databaseMetaData.supportsConvert();
        supportsTableCorrelationNames = databaseMetaData.supportsTableCorrelationNames();
        supportsDifferentTableCorrelationNames = databaseMetaData.supportsDifferentTableCorrelationNames();
        supportsExpressionsInOrderBy = databaseMetaData.supportsExpressionsInOrderBy();
        supportsOrderByUnrelated = databaseMetaData.supportsOrderByUnrelated();
        supportsGroupBy = databaseMetaData.supportsGroupBy();
        supportsGroupByUnrelated = databaseMetaData.supportsGroupByUnrelated();
        supportsGroupByBeyondSelect = databaseMetaData.supportsGroupByBeyondSelect();
        supportsLikeEscapeClause = databaseMetaData.supportsLikeEscapeClause();
        supportsMultipleResultSets = databaseMetaData.supportsMultipleResultSets();
        supportsMultipleTransactions = databaseMetaData.supportsMultipleTransactions();
        supportsNonNullableColumns = databaseMetaData.supportsNonNullableColumns();
        supportsMinimumSQLGrammar = databaseMetaData.supportsMinimumSQLGrammar();
        supportsCoreSQLGrammar = databaseMetaData.supportsCoreSQLGrammar();
        supportsExtendedSQLGrammar = databaseMetaData.supportsExtendedSQLGrammar();
        supportsANSI92EntryLevelSQL = databaseMetaData.supportsANSI92EntryLevelSQL();
        supportsANSI92IntermediateSQL = databaseMetaData.supportsANSI92IntermediateSQL();
        supportsANSI92FullSQL = databaseMetaData.supportsANSI92FullSQL();
        supportsIntegrityEnhancementFacility = databaseMetaData.supportsIntegrityEnhancementFacility();
        supportsOuterJoins = databaseMetaData.supportsOuterJoins();
        supportsFullOuterJoins = databaseMetaData.supportsFullOuterJoins();
        supportsLimitedOuterJoins = databaseMetaData.supportsLimitedOuterJoins();
        schemaTerm = databaseMetaData.getSchemaTerm();
        procedureTerm = databaseMetaData.getProcedureTerm();
        catalogTerm = databaseMetaData.getCatalogTerm();
        isCatalogAtStart = databaseMetaData.isCatalogAtStart();
        catalogSeparator = databaseMetaData.getCatalogSeparator();
        supportsSchemasInDataManipulation = databaseMetaData.supportsSchemasInDataManipulation();
        supportsSchemasInProcedureCalls = databaseMetaData.supportsSchemasInProcedureCalls();
        supportsSchemasInTableDefinitions = databaseMetaData.supportsSchemasInTableDefinitions();
        supportsSchemasInIndexDefinitions = databaseMetaData.supportsSchemasInIndexDefinitions();
        supportsSchemasInPrivilegeDefinitions = databaseMetaData.supportsSchemasInPrivilegeDefinitions();
        supportsCatalogsInDataManipulation = databaseMetaData.supportsCatalogsInDataManipulation();
        supportsCatalogsInProcedureCalls = databaseMetaData.supportsCatalogsInProcedureCalls();
        supportsCatalogsInTableDefinitions = databaseMetaData.supportsCatalogsInTableDefinitions();
        supportsCatalogsInIndexDefinitions = databaseMetaData.supportsCatalogsInIndexDefinitions();
        supportsCatalogsInPrivilegeDefinitions = databaseMetaData.supportsCatalogsInPrivilegeDefinitions();
        supportsPositionedDelete = databaseMetaData.supportsPositionedDelete();
        supportsPositionedUpdate = databaseMetaData.supportsPositionedUpdate();
        supportsSelectForUpdate = databaseMetaData.supportsSelectForUpdate();
        supportsStoredProcedures = databaseMetaData.supportsStoredProcedures();
        supportsSubqueriesInComparisons = databaseMetaData.supportsSubqueriesInComparisons();
        supportsSubqueriesInIns = databaseMetaData.supportsSubqueriesInIns();
        supportsSubqueriesInQuantifieds = databaseMetaData.supportsSubqueriesInQuantifieds();
        supportsCorrelatedSubqueries = databaseMetaData.supportsCorrelatedSubqueries();
        supportsUnion = databaseMetaData.supportsUnion();
        supportsUnionAll = databaseMetaData.supportsUnionAll();
        supportsOpenCursorsAcrossCommit = databaseMetaData.supportsOpenCursorsAcrossCommit();
        supportsOpenCursorsAcrossRollback = databaseMetaData.supportsOpenCursorsAcrossRollback();
        supportsOpenStatementsAcrossCommit = databaseMetaData.supportsOpenStatementsAcrossCommit();
        supportsOpenStatementsAcrossRollback = databaseMetaData.supportsOpenStatementsAcrossRollback();
        maxBinaryLiteralLength = databaseMetaData.getMaxBinaryLiteralLength();
        maxCharLiteralLength = databaseMetaData.getMaxCharLiteralLength();
        maxColumnNameLength = databaseMetaData.getMaxColumnNameLength();
        maxColumnsInGroupBy = databaseMetaData.getMaxColumnsInGroupBy();
        maxColumnsInIndex = databaseMetaData.getMaxColumnsInIndex();
        maxColumnsInOrderBy = databaseMetaData.getMaxColumnsInOrderBy();
        maxColumnsInSelect = databaseMetaData.getMaxColumnsInSelect();
        maxColumnsInTable = databaseMetaData.getMaxColumnsInTable();
        maxConnections = databaseMetaData.getMaxConnections();
        maxCursorNameLength = databaseMetaData.getMaxCursorNameLength();
        maxIndexLength = databaseMetaData.getMaxIndexLength();
        maxSchemaNameLength = databaseMetaData.getMaxSchemaNameLength();
        maxProcedureNameLength = databaseMetaData.getMaxProcedureNameLength();
        maxCatalogNameLength = databaseMetaData.getMaxCatalogNameLength();
        maxRowSize = databaseMetaData.getMaxRowSize();
        doesMaxRowSizeIncludeBlobs = databaseMetaData.doesMaxRowSizeIncludeBlobs();
        maxStatementLength = databaseMetaData.getMaxStatementLength();
        maxStatements = databaseMetaData.getMaxStatements();
        maxTableNameLength = databaseMetaData.getMaxTableNameLength();
        maxTablesInSelect = databaseMetaData.getMaxTablesInSelect();
        maxUserNameLength = databaseMetaData.getMaxUserNameLength();
        defaultTransactionIsolation = databaseMetaData.getDefaultTransactionIsolation();
        supportsTransactions = databaseMetaData.supportsTransactions();
        supportsDataDefinitionAndDataManipulationTransactions = databaseMetaData.supportsDataDefinitionAndDataManipulationTransactions();
        supportsDataManipulationTransactionsOnly = databaseMetaData.supportsDataManipulationTransactionsOnly();
        dataDefinitionCausesTransactionCommit = databaseMetaData.dataDefinitionCausesTransactionCommit();
        dataDefinitionIgnoredInTransactions = databaseMetaData.dataDefinitionIgnoredInTransactions();
        supportsBatchUpdates = databaseMetaData.supportsBatchUpdates();
        supportsSavepoints = databaseMetaData.supportsSavepoints();
        supportsNamedParameters = databaseMetaData.supportsNamedParameters();
        supportsMultipleOpenResults = databaseMetaData.supportsMultipleOpenResults();
        supportsGetGeneratedKeys = databaseMetaData.supportsGetGeneratedKeys();
        resultSetHoldability = databaseMetaData.getResultSetHoldability();
        sqlStateType = databaseMetaData.getSQLStateType();
        locatorsUpdateCopy = databaseMetaData.locatorsUpdateCopy();
        supportsStatementPooling = databaseMetaData.supportsStatementPooling();
        supportsStoredFunctionsUsingCallSyntax = databaseMetaData.supportsStoredFunctionsUsingCallSyntax();
        autoCommitFailureClosesAllResultSets = databaseMetaData.autoCommitFailureClosesAllResultSets();
        boolean value = false;
        try {
            value = databaseMetaData.generatedKeyAlwaysReturned();
        } catch (final AbstractMethodError ignore) {
        }
        generatedKeyAlwaysReturned = value;
        rowIdLifetime = databaseMetaData.getRowIdLifetime();
    }
    
    @Override
    public String getURL() {
        return url;
    }
    
    @Override
    public String getUserName() {
        return userName;
    }
    
    @Override
    public String getDatabaseProductName() {
        return databaseProductName;
    }
    
    @Override
    public String getDatabaseProductVersion() {
        return databaseProductVersion;
    }
    
    @Override
    public String getDriverName() {
        return driverName;
    }
    
    @Override
    public String getDriverVersion() {
        return driverVersion;
    }
    
    @Override
    public int getDriverMajorVersion() {
        return driverMajorVersion;
    }
    
    @Override
    public int getDriverMinorVersion() {
        return driverMinorVersion;
    }
    
    @Override
    public int getDatabaseMajorVersion() {
        return databaseMajorVersion;
    }
    
    @Override
    public int getDatabaseMinorVersion() {
        return databaseMinorVersion;
    }
    
    @Override
    public int getJDBCMajorVersion() {
        return jdbcMajorVersion;
    }
    
    @Override
    public int getJDBCMinorVersion() {
        return jdbcMinorVersion;
    }
    
    @Override
    public boolean isReadOnly() {
        return isReadOnly;
    }
    
    @Override
    public boolean allProceduresAreCallable() {
        return allProceduresAreCallable;
    }
    
    @Override
    public boolean allTablesAreSelectable() {
        return allTablesAreSelectable;
    }
    
    @Override
    public boolean nullsAreSortedHigh() {
        return nullsAreSortedHigh;
    }
    
    @Override
    public boolean nullsAreSortedLow() {
        return nullsAreSortedLow;
    }
    
    @Override
    public boolean nullsAreSortedAtStart() {
        return nullsAreSortedAtStart;
    }
    
    @Override
    public boolean nullsAreSortedAtEnd() {
        return nullsAreSortedAtEnd;
    }
    
    @Override
    public boolean usesLocalFiles() {
        return usesLocalFiles;
    }
    
    @Override
    public boolean usesLocalFilePerTable() {
        return usesLocalFilePerTable;
    }
    
    @Override
    public boolean supportsMixedCaseIdentifiers() {
        return supportsMixedCaseIdentifiers;
    }
    
    @Override
    public boolean storesUpperCaseIdentifiers() {
        return storesUpperCaseIdentifiers;
    }
    
    @Override
    public boolean storesLowerCaseIdentifiers() {
        return storesLowerCaseIdentifiers;
    }
    
    @Override
    public boolean storesMixedCaseIdentifiers() {
        return storesMixedCaseIdentifiers;
    }
    
    @Override
    public boolean supportsMixedCaseQuotedIdentifiers() {
        return supportsMixedCaseQuotedIdentifiers;
    }
    
    @Override
    public boolean storesUpperCaseQuotedIdentifiers() {
        return storesUpperCaseQuotedIdentifiers;
    }
    
    @Override
    public boolean storesLowerCaseQuotedIdentifiers() {
        return storesLowerCaseQuotedIdentifiers;
    }
    
    @Override
    public boolean storesMixedCaseQuotedIdentifiers() {
        return storesMixedCaseQuotedIdentifiers;
    }
    
    @Override
    public String getIdentifierQuoteString() {
        return identifierQuoteString;
    }
    
    @Override
    public String getSQLKeywords() {
        return sqlKeywords;
    }
    
    @Override
    public String getNumericFunctions() {
        return numericFunctions;
    }
    
    @Override
    public String getStringFunctions() {
        return stringFunctions;
    }
    
    @Override
    public String getSystemFunctions() {
        return systemFunctions;
    }
    
    @Override
    public String getTimeDateFunctions() {
        return timeDateFunctions;
    }
    
    @Override
    public String getSearchStringEscape() {
        return searchStringEscape;
    }
    
    @Override
    public String getExtraNameCharacters() {
        return extraNameCharacters;
    }
    
    @Override
    public boolean supportsAlterTableWithAddColumn() {
        return supportsAlterTableWithAddColumn;
    }
    
    @Override
    public boolean supportsAlterTableWithDropColumn() {
        return supportsAlterTableWithDropColumn;
    }
    
    @Override
    public boolean supportsColumnAliasing() {
        return supportsColumnAliasing;
    }
    
    @Override
    public boolean nullPlusNonNullIsNull() {
        return nullPlusNonNullIsNull;
    }
    
    @Override
    public boolean supportsConvert() {
        return supportsConvert;
    }
    
    @Override
    public boolean supportsConvert(final int fromType, final int toType) {
        return supportsConvert;
    }
    
    @Override
    public boolean supportsTableCorrelationNames() {
        return supportsTableCorrelationNames;
    }
    
    @Override
    public boolean supportsDifferentTableCorrelationNames() {
        return supportsDifferentTableCorrelationNames;
    }
    
    @Override
    public boolean supportsExpressionsInOrderBy() {
        return supportsExpressionsInOrderBy;
    }
    
    @Override
    public boolean supportsOrderByUnrelated() {
        return supportsOrderByUnrelated;
    }
    
    @Override
    public boolean supportsGroupBy() {
        return supportsGroupBy;
    }
    
    @Override
    public boolean supportsGroupByUnrelated() {
        return supportsGroupByUnrelated;
    }
    
    @Override
    public boolean supportsGroupByBeyondSelect() {
        return supportsGroupByBeyondSelect;
    }
    
    @Override
    public boolean supportsLikeEscapeClause() {
        return supportsLikeEscapeClause;
    }
    
    @Override
    public boolean supportsMultipleResultSets() {
        return supportsMultipleResultSets;
    }
    
    @Override
    public boolean supportsMultipleTransactions() {
        return supportsMultipleTransactions;
    }
    
    @Override
    public boolean supportsNonNullableColumns() {
        return supportsNonNullableColumns;
    }
    
    @Override
    public boolean supportsMinimumSQLGrammar() {
        return supportsMinimumSQLGrammar;
    }
    
    @Override
    public boolean supportsCoreSQLGrammar() {
        return supportsCoreSQLGrammar;
    }
    
    @Override
    public boolean supportsExtendedSQLGrammar() {
        return supportsExtendedSQLGrammar;
    }
    
    @Override
    public boolean supportsANSI92EntryLevelSQL() {
        return supportsANSI92EntryLevelSQL;
    }
    
    @Override
    public boolean supportsANSI92IntermediateSQL() {
        return supportsANSI92IntermediateSQL;
    }
    
    @Override
    public boolean supportsANSI92FullSQL() {
        return supportsANSI92FullSQL;
    }
    
    @Override
    public boolean supportsIntegrityEnhancementFacility() {
        return supportsIntegrityEnhancementFacility;
    }
    
    @Override
    public boolean supportsOuterJoins() {
        return supportsOuterJoins;
    }
    
    @Override
    public boolean supportsFullOuterJoins() {
        return supportsFullOuterJoins;
    }
    
    @Override
    public boolean supportsLimitedOuterJoins() {
        return supportsLimitedOuterJoins;
    }
    
    @Override
    public String getSchemaTerm() {
        return schemaTerm;
    }
    
    @Override
    public String getProcedureTerm() {
        return procedureTerm;
    }
    
    @Override
    public String getCatalogTerm() {
        return catalogTerm;
    }
    
    @Override
    public boolean isCatalogAtStart() {
        return isCatalogAtStart;
    }
    
    @Override
    public String getCatalogSeparator() {
        return catalogSeparator;
    }
    
    @Override
    public boolean supportsSchemasInDataManipulation() {
        return supportsSchemasInDataManipulation;
    }
    
    @Override
    public boolean supportsSchemasInProcedureCalls() {
        return supportsSchemasInProcedureCalls;
    }
    
    @Override
    public boolean supportsSchemasInTableDefinitions() {
        return supportsSchemasInTableDefinitions;
    }
    
    @Override
    public boolean supportsSchemasInIndexDefinitions() {
        return supportsSchemasInIndexDefinitions;
    }
    
    @Override
    public boolean supportsSchemasInPrivilegeDefinitions() {
        return supportsSchemasInPrivilegeDefinitions;
    }
    
    @Override
    public boolean supportsCatalogsInDataManipulation() {
        return supportsCatalogsInDataManipulation;
    }
    
    @Override
    public boolean supportsCatalogsInProcedureCalls() {
        return supportsCatalogsInProcedureCalls;
    }
    
    @Override
    public boolean supportsCatalogsInTableDefinitions() {
        return supportsCatalogsInTableDefinitions;
    }
    
    @Override
    public boolean supportsCatalogsInIndexDefinitions() {
        return supportsCatalogsInIndexDefinitions;
    }
    
    @Override
    public boolean supportsCatalogsInPrivilegeDefinitions() {
        return supportsCatalogsInPrivilegeDefinitions;
    }
    
    @Override
    public boolean supportsPositionedDelete() {
        return supportsPositionedDelete;
    }
    
    @Override
    public boolean supportsPositionedUpdate() {
        return supportsPositionedUpdate;
    }
    
    @Override
    public boolean supportsSelectForUpdate() {
        return supportsSelectForUpdate;
    }
    
    @Override
    public boolean supportsStoredProcedures() {
        return supportsStoredProcedures;
    }
    
    @Override
    public boolean supportsSubqueriesInComparisons() {
        return supportsSubqueriesInComparisons;
    }
    
    @Override
    public boolean supportsSubqueriesInExists() {
        return supportsSubqueriesInComparisons;
    }
    
    @Override
    public boolean supportsSubqueriesInIns() {
        return supportsSubqueriesInIns;
    }
    
    @Override
    public boolean supportsSubqueriesInQuantifieds() {
        return supportsSubqueriesInQuantifieds;
    }
    
    @Override
    public boolean supportsCorrelatedSubqueries() {
        return supportsCorrelatedSubqueries;
    }
    
    @Override
    public boolean supportsUnion() {
        return supportsUnion;
    }
    
    @Override
    public boolean supportsUnionAll() {
        return supportsUnionAll;
    }
    
    @Override
    public boolean supportsOpenCursorsAcrossCommit() {
        return supportsOpenCursorsAcrossCommit;
    }
    
    @Override
    public boolean supportsOpenCursorsAcrossRollback() {
        return supportsOpenCursorsAcrossRollback;
    }
    
    @Override
    public boolean supportsOpenStatementsAcrossCommit() {
        return supportsOpenStatementsAcrossCommit;
    }
    
    @Override
    public boolean supportsOpenStatementsAcrossRollback() {
        return supportsOpenStatementsAcrossRollback;
    }
    
    @Override
    public int getMaxBinaryLiteralLength() {
        return maxBinaryLiteralLength;
    }
    
    @Override
    public int getMaxCharLiteralLength() {
        return maxCharLiteralLength;
    }
    
    @Override
    public int getMaxColumnNameLength() {
        return maxColumnNameLength;
    }
    
    @Override
    public int getMaxColumnsInGroupBy() {
        return maxColumnsInGroupBy;
    }
    
    @Override
    public int getMaxColumnsInIndex() {
        return maxColumnsInIndex;
    }
    
    @Override
    public int getMaxColumnsInOrderBy() {
        return maxColumnsInOrderBy;
    }
    
    @Override
    public int getMaxColumnsInSelect() {
        return maxColumnsInSelect;
    }
    
    @Override
    public int getMaxColumnsInTable() {
        return maxColumnsInTable;
    }
    
    @Override
    public int getMaxConnections() {
        return maxConnections;
    }
    
    @Override
    public int getMaxCursorNameLength() {
        return maxCursorNameLength;
    }
    
    @Override
    public int getMaxIndexLength() {
        return maxIndexLength;
    }
    
    @Override
    public int getMaxSchemaNameLength() {
        return maxSchemaNameLength;
    }
    
    @Override
    public int getMaxProcedureNameLength() {
        return maxProcedureNameLength;
    }
    
    @Override
    public int getMaxCatalogNameLength() {
        return maxCatalogNameLength;
    }
    
    @Override
    public int getMaxRowSize() {
        return maxRowSize;
    }
    
    @Override
    public boolean doesMaxRowSizeIncludeBlobs() {
        return doesMaxRowSizeIncludeBlobs;
    }
    
    @Override
    public int getMaxStatementLength() {
        return maxStatementLength;
    }
    
    @Override
    public int getMaxStatements() {
        return maxStatements;
    }
    
    @Override
    public int getMaxTableNameLength() {
        return maxTableNameLength;
    }
    
    @Override
    public int getMaxTablesInSelect() {
        return maxTablesInSelect;
    }
    
    @Override
    public int getMaxUserNameLength() {
        return maxUserNameLength;
    }
    
    @Override
    public int getDefaultTransactionIsolation() {
        return defaultTransactionIsolation;
    }
    
    @Override
    public boolean supportsTransactions() {
        return supportsTransactions;
    }
    
    @Override
    public boolean supportsDataDefinitionAndDataManipulationTransactions() {
        return supportsDataDefinitionAndDataManipulationTransactions;
    }
    
    @Override
    public boolean supportsDataManipulationTransactionsOnly() {
        return supportsDataManipulationTransactionsOnly;
    }
    
    @Override
    public boolean dataDefinitionCausesTransactionCommit() {
        return dataDefinitionCausesTransactionCommit;
    }
    
    @Override
    public boolean dataDefinitionIgnoredInTransactions() {
        return dataDefinitionIgnoredInTransactions;
    }
    
    @Override
    public boolean supportsBatchUpdates() {
        return supportsBatchUpdates;
    }
    
    @Override
    public boolean supportsSavepoints() {
        return supportsSavepoints;
    }
    
    @Override
    public boolean supportsNamedParameters() {
        return supportsNamedParameters;
    }
    
    @Override
    public boolean supportsMultipleOpenResults() {
        return supportsMultipleOpenResults;
    }
    
    @Override
    public boolean supportsGetGeneratedKeys() {
        return supportsGetGeneratedKeys;
    }
    
    @Override
    public int getResultSetHoldability() {
        return resultSetHoldability;
    }
    
    @Override
    public int getSQLStateType() {
        return sqlStateType;
    }
    
    @Override
    public boolean locatorsUpdateCopy() {
        return locatorsUpdateCopy;
    }
    
    @Override
    public boolean supportsStatementPooling() {
        return supportsStatementPooling;
    }
    
    @Override
    public boolean supportsStoredFunctionsUsingCallSyntax() {
        return supportsStoredFunctionsUsingCallSyntax;
    }
    
    @Override
    public boolean autoCommitFailureClosesAllResultSets() {
        return autoCommitFailureClosesAllResultSets;
    }
    
    @Override
    public boolean generatedKeyAlwaysReturned() {
        return generatedKeyAlwaysReturned;
    }
    
    @Override
    public RowIdLifetime getRowIdLifetime() {
        return rowIdLifetime;
    }
    
    @Override
    public boolean supportsTransactionIsolationLevel(final int level) {
        return true;
    }
    
    @Override
    public boolean supportsResultSetType(final int type) {
        return true;
    }
    
    @Override
    public boolean supportsResultSetConcurrency(final int type, final int concurrency) {
        return true;
    }
    
    @Override
    public boolean ownUpdatesAreVisible(final int type) {
        return true;
    }
    
    @Override
    public boolean ownDeletesAreVisible(final int type) {
        return true;
    }
    
    @Override
    public boolean ownInsertsAreVisible(final int type) {
        return true;
    }
    
    @Override
    public boolean othersUpdatesAreVisible(final int type) {
        return true;
    }
    
    @Override
    public boolean othersDeletesAreVisible(final int type) {
        return true;
    }
    
    @Override
    public boolean othersInsertsAreVisible(final int type) {
        return true;
    }
    
    @Override
    public boolean updatesAreDetected(final int type) {
        return true;
    }
    
    @Override
    public boolean deletesAreDetected(final int type) {
        return true;
    }
    
    @Override
    public boolean insertsAreDetected(final int type) {
        return true;
    }
    
    @Override
    public boolean supportsResultSetHoldability(final int holdability) {
        return true;
    }
    
    @Override
    public ResultSet getSuperTypes(final String catalog, final String schemaPattern, final String typeNamePattern) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getSuperTables(final String catalog, final String schemaPattern, final String tableNamePattern) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getAttributes(final String catalog, final String schemaPattern, final String typeNamePattern, final String attributeNamePattern) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getProcedures(final String catalog, final String schemaPattern, final String procedureNamePattern) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getProcedureColumns(final String catalog, final String schemaPattern, final String procedureNamePattern, final String columnNamePattern) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getTables(final String catalog, final String schemaPattern, final String tableNamePattern, final String[] types) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getSchemas() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getSchemas(final String catalog, final String schemaPattern) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getCatalogs() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getTableTypes() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getColumns(final String catalog, final String schemaPattern, final String tableNamePattern, final String columnNamePattern) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getColumnPrivileges(final String catalog, final String schema, final String table, final String columnNamePattern) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getTablePrivileges(final String catalog, final String schemaPattern, final String tableNamePattern) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getBestRowIdentifier(final String catalog, final String schema, final String table, final int scope, final boolean nullable) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getVersionColumns(final String catalog, final String schema, final String table) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getPrimaryKeys(final String catalog, final String schema, final String table) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getImportedKeys(final String catalog, final String schema, final String table) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getExportedKeys(final String catalog, final String schema, final String table) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getCrossReference(final String parentCatalog,
                                       final String parentSchema, final String parentTable, final String foreignCatalog, final String foreignSchema, final String foreignTable) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getTypeInfo() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getIndexInfo(final String catalog, final String schema, final String table, final boolean unique, final boolean approximate) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getUDTs(final String catalog, final String schemaPattern, final String typeNamePattern, final int[] types) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public Connection getConnection() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getClientInfoProperties() throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getFunctions(final String catalog, final String schemaPattern, final String functionNamePattern) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getFunctionColumns(final String catalog, final String schemaPattern, final String functionNamePattern, final String columnNamePattern) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
    
    @Override
    public ResultSet getPseudoColumns(final String catalog, final String schemaPattern, final String tableNamePattern, final String columnNamePattern) throws SQLException {
        throw new SQLFeatureNotSupportedException();
    }
}
