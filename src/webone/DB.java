package webone;

import org.postgresql.ds.PGPoolingDataSource;

public class DB {

  public static PGPoolingDataSource source = new PGPoolingDataSource();

  static {
    source.setDataSourceName("WEBONE Data Source");
    source.setServerName("localhost");
    source.setDatabaseName("WEBONE");
    source.setUser("webone");
    source.setPassword("webone");
    source.setMaxConnections(10);
  }
}
