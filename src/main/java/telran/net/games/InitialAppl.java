package telran.net.games;

import java.time.LocalDate;
import java.time.LocalTime;
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
		
//		Gamer gamer=em.find(Gamer.class, "gamer1");
//		Game game=em.find(Game.class, "1001");
//		System.out.println(gamer);
//		System.out.println(game);

//		List<Object[]> listObj=getMovePartitions(em);
//		System.out.println("min_moves    max_moves     amount");
//		for(Object[] o:listObj)
//		{
//			System.out.println( ((Long)o[0]).toString() + " ".repeat(14)+ ((Long)o[1]).toString() + " ".repeat(14)+ ((Long)o[2]).toString());
//		}
	JpqlQueriesRepository repository= new JpqlQueriesRepository(em);
//	List <Game> games =repository.getGamesFinished(false);
//	games.forEach(System.out::println);
//	List<DateTimeSequence> list=repository.getDateTimeSequence(LocalTime.of(12,0));
//	displayResult(list);
//	List<Integer> list=repository.getBullsinMovesGamersBornAfter(LocalDate.ofYearDay(2000, 1));
//	displayResult(list);
	
//	List<MinMaxAmount> list = 
//			repository.getDistributionGamesMoves(6);
//	displayResult(list);
	
//	List<Game> list=repository.getGamesAverageAgeMore(60);
//	displayResult(list);
	
//	List<GameAndMoves> list=repository.getGameAndWinnerMovesLess(5);
//	displayResult(list);
	
//	List<String> list=repository.getGamerMovesInOneGameLess(4);
//	displayResult(list);

	List<AverageMoves> list=repository.getAverageMoves(1);
	displayResult(list);
	}
	
	private static <T> void displayResult(List <T> list)
	{
		list.forEach(System.out::println);
	}
	
	
}
