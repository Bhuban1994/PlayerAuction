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
	static int pno=1;
	static String pcategory=null;
	static int phs;
	static String pbf=null;
	static String teamname=null;
	static Player p=new Player();
	static DBconnection database_con=new DBconnection();
    public static void checkDuplicate(String name,String category,String teamName) throws SQLException  
	{
		//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/player_db","root","Welcome123");
    	
    	Connection con=database_con.db();
	    Statement stmt=con.createStatement();
	    ResultSet rs=stmt.executeQuery("select * from player where Player_Name ='"+name+"' and category='"+category+"'");
	   // ResultSet rs1=stmt.executeQuery("select player_no from rs.getInt("Player_no")");
	    try{
			    if(!rs.next())
			    {
			    	pname=name;
			    	teamname=teamName;
			    	
			    }
			    else
			    {
			    	throw new DuplicateEntryException("Player details already exist in the database");
			    }
		    }
	    catch(DuplicateEntryException e){
	        System.out.println(e.toString());
	    }
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
	public static void checkTeamName(String team) throws SQLException
	{
		Connection con=database_con.db();
		Statement stmt=con.createStatement();
	    ResultSet rs=stmt.executeQuery("select Team_Name from team where Team_Name='"+team+"'");
	    
	    try{
	    	while(rs.next())
	    	{
		    	if(rs.getString("Team_Name")!=null)
		    	{
			    	teamname=rs.getString("Team_Name");
			    }
			    else
			    {
			    	throw new InvalidTeamNameException("Invalid team name,please check ur input");
			    }
	    	}
	    }
	    catch(InvalidTeamNameException e){
	    	System.out.println(e.toString());
	    }
	    
	}
	public static void checkCategory(String Category) throws SQLException 
	{
		// TODO Auto-generated method stub
		try{
			if(Category.equalsIgnoreCase("batsman") || Category.equalsIgnoreCase("bowler") || Category.equalsIgnoreCase("allrounder")){
				pcategory=Category;
			}
			else
			{
				throw new InvalidCategoryException("Invalid category name,  please check your input");
			}
		}
		
		catch(InvalidCategoryException e){
	        System.out.println(e.toString());
	    }
	}
	public static void store() throws SQLException
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

	    Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/player_db","root","Welcome123");
	    Statement stmt=con.createStatement();
	    ResultSet rs=stmt.executeQuery("select Player_no from player where Player_Name='"+pname+"'");
	    ResultSet rs1=stmt.executeQuery("select team_id from team where team_name='"+teamname+"'");
		PreparedStatement ins1 = con1.prepareStatement(
		         "insert into team_player(Player_no,Team_id) values(?,?)");
		ins1.setString(1,rs.getString("Player_no"));
		ins1.setString(2,rs1.getString("team_id"));
		ins1.executeUpdate();
	}
	public static void showtable() throws SQLException
	{
		Connection con=database_con.db();
	    Statement stmt=con.createStatement();
	    ResultSet rs=stmt.executeQuery("select * from player");
	      System.out.println("playerno\tplayerName\t\tcategory\t\thighestscore\t\tBestFigure");
	      
	      while (rs.next()) 
	      {
	         int id = rs.getInt("Player_No");
	         String name = rs.getString("Player_Name");
	         String category = rs.getString("Category");
	         String highscore=rs.getString("HighestScore");
	         int bestfigure=rs.getInt("BestFigure");
	         System.out.println(id + "\t" + name+"\t\t"+category+"\t\t"+highscore+"\t\t"+bestfigure);
	      }     
		
	}

	public static void storeBestFigure(String bestfigure) {
		// TODO Auto-generated method stub
		pbf=bestfigure;
	}
}
