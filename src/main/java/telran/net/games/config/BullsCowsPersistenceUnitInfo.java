package telran.net.games.config;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceConfiguration;
import jakarta.persistence.SharedCacheMode;
import jakarta.persistence.ValidationMode;
import jakarta.persistence.spi.ClassTransformer;
import jakarta.persistence.spi.PersistenceProvider;
import jakarta.persistence.spi.PersistenceProviderResolver;
import jakarta.persistence.spi.PersistenceUnitInfo;
import jakarta.persistence.spi.PersistenceUnitTransactionType;
import jakarta.persistence.spi.ProviderUtil;

public class BullsCowsPersistenceUnitInfo
		implements PersistenceProvider, PersistenceProviderResolver, PersistenceUnitInfo {

	@Override
	public String getPersistenceUnitName()
	{
		return "bulls-cows-persistence-unit";
	}

	@Override
	public String getPersistenceProviderClassName() 
	{
		return "org.hibernate.jpa.HibernatePersistenceProvider";
	}

	@Override
	public String getScopeAnnotationName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getQualifierAnnotationNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersistenceUnitTransactionType getTransactionType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSource getJtaDataSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataSource getNonJtaDataSource()
	{
		HikariDataSource ds = new HikariDataSource();
		ds.setJdbcUrl("jdbc:postgresql://44.204.228.83/postgres");
		ds.setPassword("pinpinpin");
		ds.setUsername("postgres");
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setMaximumPoolSize(10);
		ds.setMinimumIdle(2);
		ds.setConnectionTimeout(30000);
		return ds;
	}

	@Override
	public List<String> getMappingFileNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<URL> getJarFileUrls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URL getPersistenceUnitRootUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getManagedClassNames() 
	{
		return List.of("telran.net.games.entities.Gamer",
				       "telran.net.games.entities.Game",
				       "telran.net.games.entities.Move",
				       "telran.net.games.entities.GameGamer");
	}

	@Override
	public boolean excludeUnlistedClasses() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SharedCacheMode getSharedCacheMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationMode getValidationMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Properties getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPersistenceXMLSchemaVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClassLoader getClassLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTransformer(ClassTransformer transformer) {
		// TODO Auto-generated method stub

	}

	@Override
	public ClassLoader getNewTempClassLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersistenceProvider> getPersistenceProviders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearCachedProviders() {
		// TODO Auto-generated method stub

	}

	@Override
	public EntityManagerFactory createEntityManagerFactory(String emName, Map<?, ?> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManagerFactory createEntityManagerFactory(PersistenceConfiguration configuration) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, Map<?, ?> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generateSchema(PersistenceUnitInfo info, Map<?, ?> map) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean generateSchema(String persistenceUnitName, Map<?, ?> map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProviderUtil getProviderUtil() {
		// TODO Auto-generated method stub
		return null;
	}

}
