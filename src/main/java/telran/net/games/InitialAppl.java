package telran.net.games;

import java.util.HashMap;
import java.util.List;

import org.hibernate.jpa.HibernatePersistenceProvider;

import jakarta.persistence.*;

public class InitialAppl {

	public static void main(String[] args)
	{
		HashMap<String, Object> map = new HashMap<>();
		map.put("hibernate.hbm2ddl.auto", "update"); // using existing table
		map.put("hibernate.show_sql", true);
		map.put("hibernate.format_sql", true);		
		
		EntityManagerFactory emFactory = new HibernatePersistenceProvider()
				.createContainerEntityManagerFactory(new BullsCowsPersistenceUnitInfo(), map);
		EntityManager em = emFactory.createEntityManager();
		
		Gamer gamer=em.find(Gamer.class, "gamer1");
		Game game=em.find(Game.class, "1001");
		System.out.println(gamer);
		System.out.println(game);
		List<Object[]> listObj=getMovePartitions(em);
		System.out.println("min_moves    max_moves     amount");
		for(Object[] o:listObj)
		{
			System.out.println( ((Long)o[0]).toString() + " ".repeat(14)+ ((Long)o[1]).toString() + " ".repeat(14)+ ((Long)o[2]).toString());
		}
		
	}
	
	public static List<Object[]> getMovePartitions(EntityManager entityManager)
	{
		String jpql = "SELECT FLOOR(p1.amount/5)*5 as Min_moves, " 
				+ "FLOOR(p1.amount/5)*5+4 as Max_Moves, count(*) " 
				+ "FROM ("
				+ "SELECT m.game_gamer_id as game_gamer_id, count(*) as amount "
				+ "FROM Move m "
				+ "GROUP BY m.game_gamer_id"
				+ ") as p1 "
				+ "GROUP BY Min_moves";
		return entityManager.createQuery(jpql, Object[].class).getResultList();
	}
}
