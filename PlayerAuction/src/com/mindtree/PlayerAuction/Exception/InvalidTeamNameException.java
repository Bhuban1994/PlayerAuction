/**
 * 
 */
package com.mindtree.PlayerAuction.Exception;

/**
 * @author M1041940
 *
 */
public class InvalidTeamNameException extends Exception{
	String result;
	public InvalidTeamNameException(String res){
		result=res;
	}
	 public String toString(){
	     return (result);
	  }
}
