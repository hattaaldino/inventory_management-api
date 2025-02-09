package co.id.hattaldino.modules;

import co.id.hattaldino.handlers.InventoryHandler;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.jdbi.v3.core.Jdbi;
import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;
import co.id.hattaldino.repositories.InventoryRepository;
import co.id.hattaldino.services.InventoryService;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(InventoryRepository.class);
        bind(InventoryService.class);
        bind(InventoryHandler.class);
    }

    @Provides
    @Singleton
    public DataSource provideDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(new String[]{"127.0.0.1"});
        dataSource.setDatabaseName("DbInventoryManagement");
        dataSource.setUser("postgres");
        dataSource.setPassword("hatta0sql~");
        return dataSource;
    }

    @Provides
    @Singleton
    public Jdbi provideJdbi(DataSource dataSource) {
        return Jdbi.create(dataSource);
    }
}
