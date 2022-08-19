/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.kbratko.dal.base.repo.db.sql;

import hr.kbratko.dal.base.model.TableModel;
import hr.kbratko.dal.base.repo.TableModelRepository;
import hr.kbratko.dal.base.repo.db.DatabaseRepository;
import javax.sql.DataSource;
import java.sql.ResultSet;

/**
 *
 * @author kbratko
 * @param <TKey>
 * @param <TModel>
 */
public abstract class SqlDatabaseRepository<TKey, TModel extends TableModel<TKey>>
  implements DatabaseRepository,
             TableModelRepository<TKey, TModel> {

  @Override
  public DataSource getDataSource() {
    return DataSourceSingleton.getInstance();
  }

  public abstract TModel model(ResultSet resultSet) throws Exception;

}
