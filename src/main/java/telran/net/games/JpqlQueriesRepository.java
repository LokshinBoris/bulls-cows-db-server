package telran.net.games;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;

public class JpqlQueriesRepository 
{
	private EntityManager em;

	public JpqlQueriesRepository(EntityManager em)
	{	
		this.em = em;
	}
	
	public List<Game> getGamesFinished(boolean isFinished)
	{
		TypedQuery<Game> query=em.createQuery(
				"select game from Game game where is_finished=?1",
				Game.class);
		List <Game> res=query.setParameter(1, isFinished).getResultList();
		return res;
	}
	
	public List<DateTimeSequence> getDateTimeSequence(LocalTime time)
	{
		TypedQuery<DateTimeSequence> query=em.createQuery(
				"select date, sequence "
				+ "from Game where cast(date as time) <:time"
				,DateTimeSequence.class);
		List<DateTimeSequence> res=query.setParameter("time", time).getResultList();
		return res;
	}
	
	public List<Integer> getBullsinMovesGamersBornAfter(LocalDate afterDate)
	{
		TypedQuery<Integer> query=em.createQuery(
				"select bulls from Move where gameGamer.gamer.birthdate > :afterDate",
				Integer.class);
		List<Integer> res=query.setParameter("afterDate", afterDate).getResultList();
		return res;
	}
	
	public List<MinMaxAmount> getDistributionGamesMoves(int interval)
	{
		TypedQuery<MinMaxAmount> query = em.createQuery(
				"select floor(game_moves / :interval) * :interval as min_moves, "
				+ "floor(game_moves / :interval) * :interval + (:interval - 1) as max_moves, "
				+ "count(*) as amount "
				+ "from "
				+ "(select count(*) as game_moves from Move "
				+ "group by gameGamer.game.id) "
				+ "group by min_moves, max_moves order by min_moves", MinMaxAmount.class);
		List<MinMaxAmount> res = query.setParameter("interval", interval).getResultList();
		return res;
	}
	
	public List<Game> getGamesAverageAgeMore(int ageMore)
	{
		TypedQuery<Game> query = em.createQuery(
		"select game from Game game where id in "	
		+ "(select gg.game.id from GameGamer gg group by gg.game.id "
		+ "having avg(year(current_date)-year(gg.gamer.birthdate)) > :ageMore)",
		Game.class);
		List<Game> res=query.setParameter("ageMore", ageMore).getResultList();
		return res;
	}
	
	public List<GameAndMoves> getGameAndWinnerMovesLess(int minMoves)
	{
		TypedQuery<GameAndMoves> query = em.createQuery(
		"select new telran.net.games.GameAndMoves( gg.game.id, count(*)) "
		+ "from GameGamer gg join Move mv on mv.gameGamer.id=gg.id "
		+ "where gg.is_winner "
		+ "group by gg.game.id having count(mv) < :minMoves",			
		GameAndMoves.class);
		List<GameAndMoves> res=query.setParameter("minMoves", minMoves).getResultList();
		return res;
	}
	
	public List<String> getGamerMovesInOneGameLess(int movesLess)
	{
		TypedQuery<String> query = em.createQuery(
		"select distinct gg.gamer.username "
		+ "from GameGamer gg "
		+ "join Move mv on gg.id=mv.gameGamer.id "
		+ "group by gg.game.id, gg.gamer.username having count(*) < :movesLess",
		String.class);
		List<String> res=query.setParameter("movesLess", movesLess).getResultList();
		return res;
	}
	
	public List<AverageMoves> getAverageMoves(int accuracy)
	{
		
// round not work in Jpql. I don't know how make accuracy in Jpql. I write function.
		TypedQuery<AverageMoves> query = em.createQuery(	
		"select new telran.net.games.AverageMoves(gg.game.id, avg(mv.moveCount)) "
		+ "from GameGamer gg join "
		+ "(select mv.gameGamer.id as gameGamerId, count(mv) as moveCount " 
		+ "from Move mv group by mv.gameGamer.id) "
		+ "mv on mv.gameGamerId = gg.id "
		+ "group by gg.game.id ",
		AverageMoves.class);
		List<AverageMoves> resFirst = query.getResultList();
		List<AverageMoves> res=resFirst.stream()
		.map( (aM)->new AverageMoves(aM.id(), myAccuracy(aM.moves(),accuracy)))  
		.collect(Collectors.toList() );
		return res;
	}
	
	private double myAccuracy(double value, int accuracy)
	{
		double sc=Math.pow(10, accuracy);
		return Math.round(value*sc)/sc;
	}
	
	
}
