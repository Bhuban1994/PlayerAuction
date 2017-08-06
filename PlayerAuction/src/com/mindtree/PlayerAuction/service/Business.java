/**
 * 
 */
package com.mindtree.PlayerAuction.service;
import com.mindtree.PlayerAuction.entity.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;







import com.mindtree.PlayerAuction.DAO.DBconnection;
import com.mindtree.PlayerAuction.Exception.*;
import com.mindtree.PlayerAuction.entity.Player;
/**
 * @author M1041940
 *
 */
public class Business extends Exception{

	/**
	 * @param args 
	 * @throws SQLException 
	 */
	static String pname=null;
	int pno=1;
	static String pcategory=null;
	static int phs;
	static String pbf=null;
	static String teamname=null;
    Player p=new Player();
	static DBconnection database_con=new DBconnection();
    public static boolean checkDuplicate(String name,String category,String teamName) throws SQLException  
	{
    	Connection con=database_con.db();
	    Statement stmt=con.createStatement();
	    ResultSet rs=stmt.executeQuery("select p.player_name,p.category,t.team_name from player p join team_player tp join team t on p.player_no=tp.player_no && tp.team_id=t.team_id "
	    		+ "&& p.player_name='"+name+"' && p.category='"+category+"' && t.team_name='"+teamName+"'");
	    boolean res=false;
	    try
	    {
	    	if(!rs.next())
			{
	    		pname=name;
	    		teamname=teamName;
			    res=true;
			}
			else
			{
				throw new DuplicateEntryException("Player details already exist in the database");
			}
		}
	    catch(DuplicateEntryException e)
	    {
	        System.out.println(e.toString());
	    }
	    return res;
	}
	
	public  String checkBowler(String category,int hs,String bestFigure)throws SQLException
	{
		String res=null;
	    try{
		    if(category.equalsIgnoreCase("Bowler") && (hs>0) && bestFigure!=null)
		    {
		    	phs=hs;
		    	pbf=bestFigure;
		    }
		    else
		    {
		    	throw new NotABowlerException("Invalid bowler check ur input");
		    }
	    }
	    catch(NotABowlerException e)
		{
			System.out.println(e.toString());
		}
		return res;
	}
	public static void checkBatsman(String category,int hs,String bf)throws SQLException
	{
		// TODO Auto-generated method stub
		try{
 			if(category.equalsIgnoreCase("Batsman") && (hs >= 50 && hs<=200))
 			{
 				phs=hs;
 				pbf=bf;
  			}
 			else
 			{
 				throw new NotABatsmanException("Invalid Batsman,please check ur input");
 			}
		}
		catch(NotABatsmanException e){
	        System.out.println(e.toString());
	    }
	}
	public static boolean checkTeamName(String team) throws SQLException
	{
		Connection con=database_con.db();
		Statement stmt=con.createStatement();
	    ResultSet rs=stmt.executeQuery("select Team_Name from team where Team_Name='"+team+"'");
	    boolean res=false;
	    try{
	    		while(rs.next())
		    	{
	    			if(rs.getString("Team_Name").equalsIgnoreCase(team))
	    			{
	    				if(rs.getString("Team_Name")!=null)
				    	{
					    	teamname=rs.getString("Team_Name");
					    	res=true;
					    }
					    else
					    {
					    	throw new InvalidTeamNameException("Invalid team name,please check ur input");
					    }
	    			}
			    	
		    	}	
	    	}
	    catch(InvalidTeamNameException e){
	    	System.out.println(e.toString());
	    }
		return res;
	    
	}
	public static boolean checkCategory(String Category) throws SQLException 
	{
		// TODO Auto-generated method stub
		boolean res=false;
		try{
			if(Category.equalsIgnoreCase("batsman") || Category.equalsIgnoreCase("bowler") || Category.equalsIgnoreCase("allrounder")){
				pcategory=Category;
				res=true;
			}
			else
			{
				throw new InvalidCategoryException("Invalid category name,  please check your input");
			}
		}
		
		catch(InvalidCategoryException e){
	        System.out.println(e.toString());
	    }
		return res;
	}
	public void store() throws SQLException
	{
		// TODO Auto-generated method stub
		Connection con=database_con.db();
		PreparedStatement updateemp = con.prepareStatement(
		         "insert into player(Player_Name,Category,HighestScore,BestFigure) values(?,?,?,?)");
		
	    updateemp.setString(1,pname);
	    updateemp.setString(2,pcategory);
	    updateemp.setInt(3,phs);
	    updateemp.setString(4,pbf);
	    updateemp.executeUpdate();

	    System.out.println("");
	    PreparedStatement ins1 = con.prepareStatement(
		         "insert into team_player(Player_no,Team_id) values(?,?)");
	    ResultSet rs=ins1.executeQuery("select Player_no,team_id from player,team where player_name='"+pname+"' and team_name='"+teamname+"'");
	    //ResultSet rs1=ins1.executeQuery("select team_id from team where team_name='"+teamname+"'");
		while(rs.next()) {
			ins1.setString(1,rs.getString("Player_no"));
			ins1.setString(2,rs.getString("team_id"));
		}
		
		ins1.executeUpdate();
	}
	public static void showtable() throws SQLException
	{
		Connection con=database_con.db();
	    Statement stmt=con.createStatement();
	    ResultSet rs=stmt.executeQuery("select * from player");
	    System.out.println("|==============================================================================================|");
	    System.out.format("|%10s   |%20s   |%15s   |%15s   |%15s   |","playerno","playerName","category","highestScore","BestFigure");
	    System.out.println();
	    System.out.println("|==============================================================================================|");
	      while (rs.next()) 
	      {
	         int id = rs.getInt("Player_No");
	         String name = rs.getString("Player_Name");
	         String category = rs.getString("Category");
	         int highscore=rs.getInt("HighestScore");
	         String bestfigure=rs.getString("BestFigure");
	         System.out.format("|%10d   |%20s   |%15s   |%15d   |%15s   |",id,name,category,highscore,bestfigure);
	         System.out.println();
	      }     
	      System.out.println("|==============================================================================================|");
	}

	public static void storeBestFigure(String bestfigure) {
		// TODO Auto-generated method stub
		pbf=bestfigure;
	}

	public void showTeamPlayers(String team)throws SQLException {
		// TODO Auto-generated method stub
		Connection con=database_con.db();
	    Statement stmt=con.createStatement();
	    ResultSet rs=stmt.executeQuery("select p.player_name,p.category from team_player t join team te on t.team_id=te.team_id and team_name='"+team+"' join player p on p.player_no=t.player_no order by p.player_name");
	    System.out.println("|==========================================================|");
	    System.out.println("|         playerName         |         category            |");
	    System.out.println("|==========================================================|");
	    
	    while (rs.next()) 
	    {
	    	String name = rs.getString("Player_Name");
	        String category = rs.getString("Category");
	        System.out.format("|%20s        |       %10s            |",name,category);
	        System.out.println();
	    }     
	    System.out.println("|==========================================================|");
	}
}
