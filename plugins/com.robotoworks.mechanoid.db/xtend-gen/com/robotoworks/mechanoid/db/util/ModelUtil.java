package com.robotoworks.mechanoid.db.util;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.robotoworks.mechanoid.db.generator.SqliteDatabaseSnapshot;
import com.robotoworks.mechanoid.db.sqliteModel.AlterTableAddColumnStatement;
import com.robotoworks.mechanoid.db.sqliteModel.AlterTableRenameStatement;
import com.robotoworks.mechanoid.db.sqliteModel.CastExpression;
import com.robotoworks.mechanoid.db.sqliteModel.ColumnDef;
import com.robotoworks.mechanoid.db.sqliteModel.ColumnSource;
import com.robotoworks.mechanoid.db.sqliteModel.ColumnSourceRef;
import com.robotoworks.mechanoid.db.sqliteModel.ColumnType;
import com.robotoworks.mechanoid.db.sqliteModel.CreateTableStatement;
import com.robotoworks.mechanoid.db.sqliteModel.CreateViewStatement;
import com.robotoworks.mechanoid.db.sqliteModel.DDLStatement;
import com.robotoworks.mechanoid.db.sqliteModel.DatabaseBlock;
import com.robotoworks.mechanoid.db.sqliteModel.ExprConcat;
import com.robotoworks.mechanoid.db.sqliteModel.Expression;
import com.robotoworks.mechanoid.db.sqliteModel.Function;
import com.robotoworks.mechanoid.db.sqliteModel.InitBlock;
import com.robotoworks.mechanoid.db.sqliteModel.JoinSource;
import com.robotoworks.mechanoid.db.sqliteModel.JoinStatement;
import com.robotoworks.mechanoid.db.sqliteModel.MigrationBlock;
import com.robotoworks.mechanoid.db.sqliteModel.Model;
import com.robotoworks.mechanoid.db.sqliteModel.ResultColumn;
import com.robotoworks.mechanoid.db.sqliteModel.SelectCore;
import com.robotoworks.mechanoid.db.sqliteModel.SelectCoreExpression;
import com.robotoworks.mechanoid.db.sqliteModel.SelectExpression;
import com.robotoworks.mechanoid.db.sqliteModel.SelectList;
import com.robotoworks.mechanoid.db.sqliteModel.SelectStatement;
import com.robotoworks.mechanoid.db.sqliteModel.SingleSource;
import com.robotoworks.mechanoid.db.sqliteModel.SingleSourceTable;
import com.robotoworks.mechanoid.db.sqliteModel.SqliteDataType;
import com.robotoworks.mechanoid.db.sqliteModel.TableDefinition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class ModelUtil {
  public static <T extends DDLStatement> ArrayList<T> findPreviousStatementsOfType(final DDLStatement stmt, final Class<T> statementType, final boolean inclusive) {
    ArrayList<T> list = new ArrayList<T>();
    DatabaseBlock db = ModelUtil.<DatabaseBlock>getAncestorOfType(stmt, DatabaseBlock.class);
    EList<MigrationBlock> _migrations = db.getMigrations();
    for (final MigrationBlock migration : _migrations) {
      EList<DDLStatement> _statements = migration.getStatements();
      for (final DDLStatement ddl : _statements) {
        {
          if ((!inclusive)) {
            boolean _equals = Objects.equal(ddl, stmt);
            if (_equals) {
              return list;
            }
          }
          Class<? extends DDLStatement> _class = ddl.getClass();
          boolean _isAssignableFrom = statementType.isAssignableFrom(_class);
          if (_isAssignableFrom) {
            list.add(((T) ddl));
          }
          boolean _equals_1 = Objects.equal(ddl, stmt);
          if (_equals_1) {
            return list;
          }
        }
      }
    }
    InitBlock _ancestorOfType = ModelUtil.<InitBlock>getAncestorOfType(stmt, InitBlock.class);
    boolean _notEquals = (!Objects.equal(_ancestorOfType, null));
    if (_notEquals) {
      InitBlock _init = db.getInit();
      EList<DDLStatement> _statements_1 = _init.getStatements();
      for (final DDLStatement ddl_1 : _statements_1) {
        {
          if ((!inclusive)) {
            boolean _equals = Objects.equal(ddl_1, stmt);
            if (_equals) {
              return list;
            }
          }
          Class<? extends DDLStatement> _class = ddl_1.getClass();
          boolean _isAssignableFrom = statementType.isAssignableFrom(_class);
          if (_isAssignableFrom) {
            list.add(((T) ddl_1));
          }
          boolean _equals_1 = Objects.equal(ddl_1, stmt);
          if (_equals_1) {
            return list;
          }
        }
      }
    }
    return list;
  }
  
  public static <T extends DDLStatement> ArrayList<T> findPreviousStatementsOfType(final DatabaseBlock db, final DDLStatement stmt, final Class<T> statementType, final boolean inclusive) {
    ArrayList<T> list = new ArrayList<T>();
    EList<MigrationBlock> _migrations = db.getMigrations();
    for (final MigrationBlock migration : _migrations) {
      EList<DDLStatement> _statements = migration.getStatements();
      for (final DDLStatement ddl : _statements) {
        {
          if ((!inclusive)) {
            boolean _equals = Objects.equal(ddl, stmt);
            if (_equals) {
              return list;
            }
          }
          Class<? extends DDLStatement> _class = ddl.getClass();
          boolean _isAssignableFrom = statementType.isAssignableFrom(_class);
          if (_isAssignableFrom) {
            list.add(((T) ddl));
          }
          boolean _equals_1 = Objects.equal(ddl, stmt);
          if (_equals_1) {
            return list;
          }
        }
      }
    }
    InitBlock _init = db.getInit();
    boolean _notEquals = (!Objects.equal(_init, null));
    if (_notEquals) {
      InitBlock _init_1 = db.getInit();
      EList<DDLStatement> _statements_1 = _init_1.getStatements();
      for (final DDLStatement ddl_1 : _statements_1) {
        {
          if ((!inclusive)) {
            boolean _equals = Objects.equal(ddl_1, stmt);
            if (_equals) {
              return list;
            }
          }
          Class<? extends DDLStatement> _class = ddl_1.getClass();
          boolean _isAssignableFrom = statementType.isAssignableFrom(_class);
          if (_isAssignableFrom) {
            list.add(((T) ddl_1));
          }
          boolean _equals_1 = Objects.equal(ddl_1, stmt);
          if (_equals_1) {
            return list;
          }
        }
      }
    }
    return list;
  }
  
  public static <T extends DDLStatement> ArrayList<T> findStatementsOfTypeBetween(final Class<T> statementType, final DDLStatement from, final DDLStatement to) {
    ArrayList<T> list = new ArrayList<T>();
    boolean between = false;
    DatabaseBlock db = ModelUtil.<DatabaseBlock>getAncestorOfType(from, DatabaseBlock.class);
    EList<MigrationBlock> _migrations = db.getMigrations();
    for (final MigrationBlock migration : _migrations) {
      EList<DDLStatement> _statements = migration.getStatements();
      for (final DDLStatement ddl : _statements) {
        {
          boolean _equals = Objects.equal(ddl, to);
          if (_equals) {
            return list;
          }
          Class<? extends DDLStatement> _class = ddl.getClass();
          boolean _isAssignableFrom = statementType.isAssignableFrom(_class);
          if (_isAssignableFrom) {
            if (between) {
              list.add(((T) ddl));
            }
          }
          boolean _equals_1 = Objects.equal(ddl, from);
          if (_equals_1) {
            between = true;
          }
        }
      }
    }
    return list;
  }
  
  public static <T extends EObject> T getAncestorOfType(final EObject obj, final Class<T> ancestorType) {
    EObject temp = ((EObject) obj);
    while ((!Objects.equal(temp.eContainer(), null))) {
      {
        EObject _eContainer = temp.eContainer();
        temp = _eContainer;
        Class<? extends EObject> _class = temp.getClass();
        boolean _isAssignableFrom = ancestorType.isAssignableFrom(_class);
        if (_isAssignableFrom) {
          return ((T) temp);
        }
      }
    }
    return null;
  }
  
  /**
   * walks back and visits each previous statement from the given statement, returning
   * false will cancel the process
   */
  public static void forEachPreviousStatement(final DDLStatement stmt, final Function1<DDLStatement, Boolean> delegate) {
    EObject current = ((EObject) stmt);
    MigrationBlock migration = null;
    do {
      {
        while ((!Objects.equal(EcoreUtil2.getPreviousSibling(current), null))) {
          {
            EObject _previousSibling = EcoreUtil2.getPreviousSibling(current);
            current = _previousSibling;
            Boolean _apply = delegate.apply(((DDLStatement) current));
            boolean _not = (!(_apply).booleanValue());
            if (_not) {
              return;
            }
          }
        }
        EObject _eContainer = current.eContainer();
        EObject previousContainer = EcoreUtil2.getPreviousSibling(_eContainer);
        boolean _and = false;
        boolean _notEquals = (!Objects.equal(previousContainer, null));
        if (!_notEquals) {
          _and = false;
        } else {
          _and = (previousContainer instanceof MigrationBlock);
        }
        if (_and) {
          migration = ((MigrationBlock) previousContainer);
          EList<DDLStatement> _statements = migration.getStatements();
          DDLStatement _last = IterableExtensions.<DDLStatement>last(_statements);
          current = _last;
          boolean _equals = Objects.equal(current, null);
          if (_equals) {
            return;
          }
          Boolean _apply = delegate.apply(((DDLStatement) current));
          boolean _not = (!(_apply).booleanValue());
          if (_not) {
            return;
          }
        } else {
          migration = null;
        }
      }
    } while((!Objects.equal(migration, null)));
  }
  
  public static ArrayList<EObject> getAllReferenceableSingleSources(final SelectCoreExpression expr) {
    final ArrayList<EObject> items = Lists.<EObject>newArrayList();
    if ((expr instanceof SelectCore)) {
      SelectCoreExpression _left = ((SelectCore) expr).getLeft();
      ArrayList<EObject> _allReferenceableSingleSources = ModelUtil.getAllReferenceableSingleSources(_left);
      items.addAll(_allReferenceableSingleSources);
      SelectCoreExpression _right = ((SelectCore) expr).getRight();
      ArrayList<EObject> _allReferenceableSingleSources_1 = ModelUtil.getAllReferenceableSingleSources(_right);
      items.addAll(_allReferenceableSingleSources_1);
    } else {
      if ((expr instanceof SelectExpression)) {
        ArrayList<SingleSource> _findAllSingleSources = ModelUtil.findAllSingleSources(((SelectExpression) expr));
        items.addAll(_findAllSingleSources);
      }
    }
    return items;
  }
  
  public static ArrayList<SingleSource> findAllSingleSources(final SelectExpression expr) {
    final ArrayList<SingleSource> items = Lists.<SingleSource>newArrayList();
    JoinSource _source = expr.getSource();
    SingleSource _source_1 = _source.getSource();
    items.add(_source_1);
    JoinSource _source_2 = expr.getSource();
    EList<JoinStatement> _joinStatements = _source_2.getJoinStatements();
    for (final JoinStatement join : _joinStatements) {
      SingleSource _singleSource = join.getSingleSource();
      items.add(_singleSource);
    }
    return items;
  }
  
  public static ColumnType toColumnType(final SqliteDataType dt) {
    if (dt != null) {
      switch (dt) {
        case BLOB:
          return ColumnType.BLOB;
        case INTEGER:
          return ColumnType.INTEGER;
        case REAL:
          return ColumnType.REAL;
        default:
          break;
      }
    }
    return ColumnType.TEXT;
  }
  
  public static String toJavaTypeName(final ColumnType type) {
    if (type != null) {
      switch (type) {
        case TEXT:
          return "String";
        case INTEGER:
          return "long";
        case REAL:
          return "double";
        case BLOB:
          return "byte[]";
        case BOOLEAN:
          return "boolean";
        default:
          break;
      }
    }
    return "!!ERROR!!";
  }
  
  public static ColumnType getInferredColumnType(final ResultColumn col) {
    Expression expr = col.getExpression();
    boolean _matched = false;
    if (!_matched) {
      if (expr instanceof CastExpression) {
        _matched=true;
        SqliteDataType _type = ((CastExpression)expr).getType();
        return ModelUtil.toColumnType(_type);
      }
    }
    if (!_matched) {
      if (expr instanceof ColumnSourceRef) {
        _matched=true;
        ColumnSource _column = ((ColumnSourceRef)expr).getColumn();
        if ((_column instanceof ResultColumn)) {
          ColumnSource _column_1 = ((ColumnSourceRef)expr).getColumn();
          ColumnType _inferredColumnType = ModelUtil.getInferredColumnType(((ResultColumn) _column_1));
          return ((ColumnType) _inferredColumnType);
        } else {
          ColumnSource _column_2 = ((ColumnSourceRef)expr).getColumn();
          return ((ColumnDef) _column_2).getType();
        }
      }
    }
    if (!_matched) {
      if (expr instanceof ExprConcat) {
        _matched=true;
        return ColumnType.TEXT;
      }
    }
    if (!_matched) {
      if (expr instanceof Function) {
        _matched=true;
        boolean _or = false;
        boolean _or_1 = false;
        boolean _or_2 = false;
        boolean _or_3 = false;
        boolean _or_4 = false;
        String _name = ((Function)expr).getName();
        boolean _equalsIgnoreCase = _name.equalsIgnoreCase("count");
        if (_equalsIgnoreCase) {
          _or_4 = true;
        } else {
          String _name_1 = ((Function)expr).getName();
          boolean _equalsIgnoreCase_1 = _name_1.equalsIgnoreCase("length");
          _or_4 = _equalsIgnoreCase_1;
        }
        if (_or_4) {
          _or_3 = true;
        } else {
          String _name_2 = ((Function)expr).getName();
          boolean _equalsIgnoreCase_2 = _name_2.equalsIgnoreCase("random");
          _or_3 = _equalsIgnoreCase_2;
        }
        if (_or_3) {
          _or_2 = true;
        } else {
          String _name_3 = ((Function)expr).getName();
          boolean _equalsIgnoreCase_3 = _name_3.equalsIgnoreCase("last_insert_rowid");
          _or_2 = _equalsIgnoreCase_3;
        }
        if (_or_2) {
          _or_1 = true;
        } else {
          String _name_4 = ((Function)expr).getName();
          boolean _equalsIgnoreCase_4 = _name_4.equalsIgnoreCase("changes");
          _or_1 = _equalsIgnoreCase_4;
        }
        if (_or_1) {
          _or = true;
        } else {
          String _name_5 = ((Function)expr).getName();
          boolean _equalsIgnoreCase_5 = _name_5.equalsIgnoreCase("total_changes");
          _or = _equalsIgnoreCase_5;
        }
        if (_or) {
          return ColumnType.INTEGER;
        } else {
          boolean _or_5 = false;
          boolean _or_6 = false;
          boolean _or_7 = false;
          boolean _or_8 = false;
          boolean _or_9 = false;
          String _name_6 = ((Function)expr).getName();
          boolean _equalsIgnoreCase_6 = _name_6.equalsIgnoreCase("abs");
          if (_equalsIgnoreCase_6) {
            _or_9 = true;
          } else {
            String _name_7 = ((Function)expr).getName();
            boolean _equalsIgnoreCase_7 = _name_7.equalsIgnoreCase("avg");
            _or_9 = _equalsIgnoreCase_7;
          }
          if (_or_9) {
            _or_8 = true;
          } else {
            String _name_8 = ((Function)expr).getName();
            boolean _equalsIgnoreCase_8 = _name_8.equalsIgnoreCase("round");
            _or_8 = _equalsIgnoreCase_8;
          }
          if (_or_8) {
            _or_7 = true;
          } else {
            String _name_9 = ((Function)expr).getName();
            boolean _equalsIgnoreCase_9 = _name_9.equalsIgnoreCase("sum");
            _or_7 = _equalsIgnoreCase_9;
          }
          if (_or_7) {
            _or_6 = true;
          } else {
            String _name_10 = ((Function)expr).getName();
            boolean _equalsIgnoreCase_10 = _name_10.equalsIgnoreCase("total");
            _or_6 = _equalsIgnoreCase_10;
          }
          if (_or_6) {
            _or_5 = true;
          } else {
            String _name_11 = ((Function)expr).getName();
            boolean _equalsIgnoreCase_11 = _name_11.equalsIgnoreCase("likelihood");
            _or_5 = _equalsIgnoreCase_11;
          }
          if (_or_5) {
            return ColumnType.REAL;
          } else {
            boolean _or_10 = false;
            String _name_12 = ((Function)expr).getName();
            boolean _equalsIgnoreCase_12 = _name_12.equalsIgnoreCase("randomblob");
            if (_equalsIgnoreCase_12) {
              _or_10 = true;
            } else {
              String _name_13 = ((Function)expr).getName();
              boolean _equalsIgnoreCase_13 = _name_13.equalsIgnoreCase("zeroblob");
              _or_10 = _equalsIgnoreCase_13;
            }
            if (_or_10) {
              return ColumnType.BLOB;
            }
          }
        }
        return ColumnType.TEXT;
      }
    }
    return ColumnType.TEXT;
  }
  
  public static ArrayList<ColumnSource> getViewResultColumns(final CreateViewStatement stmt) {
    ArrayList<ColumnSource> result = new ArrayList<ColumnSource>();
    SelectStatement _selectStatement = stmt.getSelectStatement();
    SelectCoreExpression coreExpr = _selectStatement.getCore();
    if ((coreExpr instanceof SelectCore)) {
      SelectCore core = ((SelectCore) coreExpr);
      SelectCoreExpression _right = core.getRight();
      SelectList selectList = ((SelectExpression) _right).getSelectList();
      boolean _notEquals = (!Objects.equal(selectList, null));
      if (_notEquals) {
        EList<ColumnSource> _resultColumns = selectList.getResultColumns();
        final Function1<ColumnSource, Boolean> _function = new Function1<ColumnSource, Boolean>() {
          @Override
          public Boolean apply(final ColumnSource it) {
            boolean _and = false;
            String _name = it.getName();
            boolean _notEquals = (!Objects.equal(_name, null));
            if (!_notEquals) {
              _and = false;
            } else {
              String _name_1 = it.getName();
              boolean _equals = _name_1.equals("");
              boolean _not = (!_equals);
              _and = _not;
            }
            return Boolean.valueOf(_and);
          }
        };
        Iterable<ColumnSource> _filter = IterableExtensions.<ColumnSource>filter(_resultColumns, _function);
        Iterables.<ColumnSource>addAll(result, _filter);
      }
    } else {
      SelectList selectList_1 = ((SelectExpression) coreExpr).getSelectList();
      boolean _notEquals_1 = (!Objects.equal(selectList_1, null));
      if (_notEquals_1) {
        EList<ColumnSource> _resultColumns_1 = selectList_1.getResultColumns();
        final Function1<ColumnSource, Boolean> _function_1 = new Function1<ColumnSource, Boolean>() {
          @Override
          public Boolean apply(final ColumnSource it) {
            boolean _and = false;
            String _name = it.getName();
            boolean _notEquals = (!Objects.equal(_name, null));
            if (!_notEquals) {
              _and = false;
            } else {
              String _name_1 = it.getName();
              boolean _equals = _name_1.equals("");
              boolean _not = (!_equals);
              _and = _not;
            }
            return Boolean.valueOf(_and);
          }
        };
        Iterable<ColumnSource> _filter_1 = IterableExtensions.<ColumnSource>filter(_resultColumns_1, _function_1);
        Iterables.<ColumnSource>addAll(result, _filter_1);
      }
    }
    return result;
  }
  
  public static HashSet<CreateViewStatement> getAllViewsReferencingTable(final SqliteDatabaseSnapshot snapshot, final TableDefinition tableDef) {
    HashSet<CreateViewStatement> matches = new HashSet<CreateViewStatement>();
    Collection<CreateViewStatement> _views = snapshot.getViews();
    for (final CreateViewStatement view : _views) {
      boolean _isDefinitionReferencedByView = ModelUtil.isDefinitionReferencedByView(tableDef, view);
      if (_isDefinitionReferencedByView) {
        matches.add(view);
      }
    }
    return matches;
  }
  
  public static HashSet<CreateViewStatement> getAllViewsInConfigInitReferencingTable(final Model model, final TableDefinition tableDef) {
    HashSet<CreateViewStatement> matches = new HashSet<CreateViewStatement>();
    Collection<CreateViewStatement> _configInitViews = ModelUtil.getConfigInitViews(model);
    for (final CreateViewStatement view : _configInitViews) {
      boolean _isDefinitionReferencedByView = ModelUtil.isDefinitionReferencedByView(tableDef, view);
      if (_isDefinitionReferencedByView) {
        matches.add(view);
      }
    }
    return matches;
  }
  
  public static boolean isDefinitionReferencedByView(final TableDefinition tableDef, final CreateViewStatement view) {
    TreeIterator<EObject> _eAllContents = view.eAllContents();
    final Function1<EObject, Boolean> _function = new Function1<EObject, Boolean>() {
      @Override
      public Boolean apply(final EObject obj) {
        if ((obj instanceof SingleSourceTable)) {
          SingleSourceTable sourceTable = ((SingleSourceTable) obj);
          TableDefinition _tableReference = sourceTable.getTableReference();
          if ((!(_tableReference instanceof CreateViewStatement))) {
            TableDefinition _tableReference_1 = sourceTable.getTableReference();
            String _name = _tableReference_1.getName();
            String _name_1 = tableDef.getName();
            boolean _equals = _name.equals(_name_1);
            if (_equals) {
              return Boolean.valueOf(true);
            }
          } else {
            TableDefinition _tableReference_2 = sourceTable.getTableReference();
            return Boolean.valueOf(ModelUtil.isDefinitionReferencedByView(tableDef, ((CreateViewStatement) _tableReference_2)));
          }
        }
        return Boolean.valueOf(false);
      }
    };
    return IteratorExtensions.<EObject>exists(_eAllContents, _function);
  }
  
  public static boolean hasAndroidPrimaryKey(final CreateTableStatement stmt) {
    EList<ColumnSource> _columnDefs = stmt.getColumnDefs();
    final Function1<ColumnSource, Boolean> _function = new Function1<ColumnSource, Boolean>() {
      @Override
      public Boolean apply(final ColumnSource it) {
        String _name = it.getName();
        return Boolean.valueOf(_name.equals("_id"));
      }
    };
    return IterableExtensions.<ColumnSource>exists(_columnDefs, _function);
  }
  
  public static boolean hasAndroidPrimaryKey(final CreateViewStatement stmt) {
    ArrayList<ColumnSource> _viewResultColumns = ModelUtil.getViewResultColumns(stmt);
    final Function1<ColumnSource, Boolean> _function = new Function1<ColumnSource, Boolean>() {
      @Override
      public Boolean apply(final ColumnSource it) {
        boolean _and = false;
        String _name = it.getName();
        boolean _isEmpty = Strings.isEmpty(_name);
        boolean _not = (!_isEmpty);
        if (!_not) {
          _and = false;
        } else {
          String _name_1 = it.getName();
          boolean _equals = _name_1.equals("_id");
          _and = _equals;
        }
        return Boolean.valueOf(_and);
      }
    };
    return IterableExtensions.<ColumnSource>exists(_viewResultColumns, _function);
  }
  
  /**
   * Find column definitions from caller going back to the definition
   */
  public static ArrayList<EObject> findColumnDefs(final DDLStatement caller, final TableDefinition definition) {
    final ArrayList<EObject> columns = new ArrayList<EObject>();
    LinkedList<TableDefinition> tableHistory = ModelUtil.getHistory(definition);
    TableDefinition last = tableHistory.peekLast();
    if ((last instanceof CreateViewStatement)) {
      CreateViewStatement view = ((CreateViewStatement) last);
      ArrayList<ColumnSource> _viewResultColumns = ModelUtil.getViewResultColumns(view);
      columns.addAll(_viewResultColumns);
      return columns;
    }
    EList<ColumnSource> _columnDefs = ((CreateTableStatement) last).getColumnDefs();
    columns.addAll(_columnDefs);
    while ((!tableHistory.isEmpty())) {
      {
        final TableDefinition stmt = tableHistory.removeLast();
        ArrayList<AlterTableAddColumnStatement> _findStatementsOfTypeBetween = ModelUtil.<AlterTableAddColumnStatement>findStatementsOfTypeBetween(AlterTableAddColumnStatement.class, stmt, caller);
        final Function1<AlterTableAddColumnStatement, Boolean> _function = new Function1<AlterTableAddColumnStatement, Boolean>() {
          @Override
          public Boolean apply(final AlterTableAddColumnStatement it) {
            TableDefinition _table = it.getTable();
            return Boolean.valueOf(Objects.equal(_table, stmt));
          }
        };
        Iterable<AlterTableAddColumnStatement> _filter = IterableExtensions.<AlterTableAddColumnStatement>filter(_findStatementsOfTypeBetween, _function);
        final Procedure1<AlterTableAddColumnStatement> _function_1 = new Procedure1<AlterTableAddColumnStatement>() {
          @Override
          public void apply(final AlterTableAddColumnStatement it) {
            ColumnSource _columnDef = it.getColumnDef();
            columns.add(_columnDef);
          }
        };
        IterableExtensions.<AlterTableAddColumnStatement>forEach(_filter, _function_1);
      }
    }
    return columns;
  }
  
  public static LinkedList<TableDefinition> getHistory(final TableDefinition ref) {
    LinkedList<TableDefinition> refs = new LinkedList<TableDefinition>();
    TableDefinition current = ref;
    while ((current instanceof AlterTableRenameStatement)) {
      {
        refs.add(current);
        TableDefinition _table = ((AlterTableRenameStatement) current).getTable();
        current = _table;
      }
    }
    refs.add(current);
    return refs;
  }
  
  public static ArrayList<EObject> getAllReferenceableColumns(final SelectCoreExpression expr) {
    final ArrayList<EObject> items = Lists.<EObject>newArrayList();
    if ((expr instanceof SelectCore)) {
      SelectCoreExpression _left = ((SelectCore) expr).getLeft();
      ArrayList<EObject> _allReferenceableColumns = ModelUtil.getAllReferenceableColumns(_left);
      items.addAll(_allReferenceableColumns);
      SelectCoreExpression _right = ((SelectCore) expr).getRight();
      ArrayList<EObject> _allReferenceableColumns_1 = ModelUtil.getAllReferenceableColumns(_right);
      items.addAll(_allReferenceableColumns_1);
    } else {
      if ((expr instanceof SelectExpression)) {
        ArrayList<EObject> _allReferenceableColumns_2 = ModelUtil.getAllReferenceableColumns(((SelectExpression) expr), true);
        items.addAll(_allReferenceableColumns_2);
      }
    }
    return items;
  }
  
  public static ArrayList<EObject> getAllReferenceableColumns(final SelectExpression expr, final boolean includeAliases) {
    final ArrayList<EObject> items = Lists.<EObject>newArrayList();
    boolean _and = false;
    SelectList _selectList = expr.getSelectList();
    boolean _notEquals = (!Objects.equal(_selectList, null));
    if (!_notEquals) {
      _and = false;
    } else {
      _and = includeAliases;
    }
    if (_and) {
      SelectList _selectList_1 = expr.getSelectList();
      EList<ColumnSource> _resultColumns = _selectList_1.getResultColumns();
      final Function1<ColumnSource, Boolean> _function = new Function1<ColumnSource, Boolean>() {
        @Override
        public Boolean apply(final ColumnSource it) {
          String _name = it.getName();
          return Boolean.valueOf((!Objects.equal(_name, null)));
        }
      };
      Iterable<ColumnSource> _filter = IterableExtensions.<ColumnSource>filter(_resultColumns, _function);
      Iterables.<EObject>addAll(items, _filter);
    }
    ArrayList<SingleSource> _findAllSingleSources = ModelUtil.findAllSingleSources(expr);
    final Function1<SingleSource, Boolean> _function_1 = new Function1<SingleSource, Boolean>() {
      @Override
      public Boolean apply(final SingleSource item) {
        if ((item instanceof SingleSourceTable)) {
          String _name = ((SingleSourceTable) item).getName();
          return Boolean.valueOf(Objects.equal(_name, null));
        }
        return Boolean.valueOf(false);
      }
    };
    Iterable<SingleSource> _filter_1 = IterableExtensions.<SingleSource>filter(_findAllSingleSources, _function_1);
    final Procedure1<SingleSource> _function_2 = new Procedure1<SingleSource>() {
      @Override
      public void apply(final SingleSource item) {
        SingleSourceTable source = ((SingleSourceTable) item);
        DDLStatement _ancestorOfType = ModelUtil.<DDLStatement>getAncestorOfType(item, DDLStatement.class);
        TableDefinition _tableReference = source.getTableReference();
        ArrayList<EObject> _findColumnDefs = ModelUtil.findColumnDefs(_ancestorOfType, _tableReference);
        items.addAll(_findColumnDefs);
      }
    };
    IterableExtensions.<SingleSource>forEach(_filter_1, _function_2);
    return items;
  }
  
  public static Collection<CreateViewStatement> getConfigInitViews(final Model model) {
    final ArrayList<CreateViewStatement> items = Lists.<CreateViewStatement>newArrayList();
    DatabaseBlock _database = model.getDatabase();
    InitBlock _init = _database.getInit();
    boolean _notEquals = (!Objects.equal(_init, null));
    if (_notEquals) {
      DatabaseBlock _database_1 = model.getDatabase();
      InitBlock _init_1 = _database_1.getInit();
      EList<DDLStatement> _statements = _init_1.getStatements();
      Iterable<CreateViewStatement> _filter = Iterables.<CreateViewStatement>filter(_statements, CreateViewStatement.class);
      Iterables.<CreateViewStatement>addAll(items, _filter);
    }
    return items;
  }
  
  public static Collection<CreateTableStatement> getConfigInitTables(final Model model) {
    final ArrayList<CreateTableStatement> items = Lists.<CreateTableStatement>newArrayList();
    DatabaseBlock _database = model.getDatabase();
    InitBlock _init = _database.getInit();
    boolean _notEquals = (!Objects.equal(_init, null));
    if (_notEquals) {
      DatabaseBlock _database_1 = model.getDatabase();
      InitBlock _init_1 = _database_1.getInit();
      EList<DDLStatement> _statements = _init_1.getStatements();
      Iterable<CreateTableStatement> _filter = Iterables.<CreateTableStatement>filter(_statements, CreateTableStatement.class);
      Iterables.<CreateTableStatement>addAll(items, _filter);
    }
    return items;
  }
}
