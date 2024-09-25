package telran.net.games.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import telran.net.games.entities.*;
import telran.net.games.exceptions.*;
import telran.net.games.model.*;
import telran.net.games.repo.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ServiceImplTest {
	static BullsCowsRepository repository;
	BullsCowsGameRunner runner =new BullsCowsGameRunner(4);
	BullsCowsServiceImpl impl=new BullsCowsServiceImpl(repository,runner);
	
	static {
		HashMap<String, Object> hibernateProperties = new HashMap<>();
		hibernateProperties.put("hibernate.hbm2ddl.auto", "create");
		repository = new BullsCowsRepositoryJpa
				(new BullsCowsTestPersistenceUnitInfo(), hibernateProperties);
	}
	
	static long gameId;
	static String username1="gamer1";
	static String username2="gamer2";
	@Test
	@Order(1)
	void createGameTest() 
	{
		gameId = impl.createGame();
		Game game = repository.getGame(gameId);
		assertNotNull(game);
		assertNull(game.getDate());
		assertFalse(game.isfinished());	
	}
	
	@Test
	@Order(2)
	void createGamerTest() 
	{
		impl.registerGamer(username1,LocalDate.of(2000,1,1));
		Gamer gamer1 = repository.getGamer(username1);
		assertNotNull(gamer1);
		impl.registerGamer(username2,LocalDate.of(1960,10,10));
		Gamer gamer2 = repository.getGamer(username2);
		assertNotNull(gamer2);
	}
	
	@Test
	@Order(3)
	void gamerJoinGameTest() 
	{
		impl.gamerJoinGame(gameId, username1);
		impl.gamerJoinGame(gameId, username2);
		List<String> gamers = repository.getGameGamers(gameId);
		assertEquals(2, gamers.size());
		assertTrue(gamers.contains(username1));
		assertTrue(gamers.contains(username2));
	}
	
	@Test
	@Order(4)
	void gameNotStartedTest() 
	{
		List<Long> gamesNotStared=impl.getNotStartedGames();
		assertEquals(1, gamesNotStared.size());
		assertEquals(gameId,gamesNotStared.get(0));
	}
	
	@Test
	@Order(5)
	void gameStartTest() 
	{
		impl.startGame(gameId);
		List<Long> gamesNotStared=impl.getNotStartedGames();
		assertTrue(gamesNotStared.isEmpty());
		assertTrue(repository.isGameStarted(gameId));
		assertThrowsExactly(GameAlreadyStartedException.class, ()->
				impl.startGame(gameId));
	}
	
	@Test
	@Order(6)
	void moveProcessingTest() 
	{
		assertThrowsExactly(IncorrectMoveSequenceException.class,()->
		impl.moveProcessing("123", gameId, username1));
		
		impl.moveProcessing("1234", gameId, username1);
		
		List<MoveData> moveData1=impl.moveProcessing("5678", gameId, username1);
		
		impl.moveProcessing("5678", gameId, username2);
		
		List<MoveData> moveData2=impl.moveProcessing("1234", gameId, username2);
		
		assertEquals(2,moveData1.size());
		assertEquals(2,moveData2.size());
		assertFalse(impl.gameOver(gameId));
		assertFalse(repository.isWinner(gameId, username2));

		List<MoveData> moveDataFinish=impl.moveProcessing(impl.getSequence(gameId), gameId, username1);
		assertEquals(3,moveDataFinish.size());
		assertTrue(impl.gameOver(gameId));
		assertTrue(repository.isWinner(gameId, username1));
		
		assertThrowsExactly(GameFinishedException.class,()->
			impl.moveProcessing("4567", gameId, username1));
	}
}
