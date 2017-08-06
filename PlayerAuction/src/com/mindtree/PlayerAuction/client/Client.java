/**
 * 
 */

package com.mindtree.PlayerAuction.client;

/**
 * @author M1041940
 *
 */
import java.sql.SQLException;
import java.util.*;

import com.mindtree.PlayerAuction.entity.*;
import com.mindtree.PlayerAuction.service.*;
public class Client {	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Scanner sc=new Scanner(System.in);
		Player player=new Player();
		Team team=new Team();
		int ch;
		Business b=new Business();
		do
		{
			System.out.println();
			System.out.println("====================================");
			System.out.println("Welcome to the !!!Player Auction!!!");
			System.out.println("====================================");
			System.out.println("Enter options\n1-ADD PLAYER\n2-DISPLAY PLAYER\n3-EXIT\n");
			ch=sc.nextInt();
			switch(ch)
			{
				case 1:
					sc.nextLine();
					System.out.println("Enter the playername");
					player.setName(sc.nextLine());
					sc.nextLine();
					System.out.println("Enter the category");
					player.setCategory(sc.nextLine());
					//for category validation
					boolean res=false;
					res=b.checkCategory(player.getCategory());
					if(res==true)
					{
						System.out.println("Enter the team name for the player");
						team.setTeam_name(sc.next());
						//check for team present in the database
						res=b.checkTeamName(team.getTeam_name());
						if(res==true)
						{
							//for duplicates
							res=b.checkDuplicate(player.getName(),player.getCategory(),team.getTeam_name());
							
							if(res==true)
							{
								System.out.println("Enter the highest score");
								player.setHighestscore(sc.nextInt());
								
								sc.nextLine();
								
								System.out.println("Enter the best figure");
								player.setBestfigure(sc.next());
								
								if(player.getCategory().equalsIgnoreCase("Batsman"))
								{
									b.checkBatsman(player.getCategory(),player.getHighestscore(),player.getBestfigure());
									b.store();
								}
								
								
								if(player.getCategory().equalsIgnoreCase("Bowler"))
								{
									b.checkBowler(player.getCategory(),player.getHighestscore(),player.getBestfigure());
									b.store();	
								}
								
							}
						}
					}
					break;
				case 2:
					//b.showtable();
					System.out.println("Display Players!!!!");
					System.out.println("-------------------");
					System.out.print("Enter team name:");
					sc.nextLine();
					b.showTeamPlayers(sc.nextLine());
					break;
				case 3:
					System.exit(1);
				default:
					break;		
			}
			
		}while(ch!=3);
		
	}
	

}
