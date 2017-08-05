/**
 * 
 */
package com.mindtree.PlayerAuction.Exception;

/**
 * @author M1041940
 *
 */
public class NotABatsmanException extends Exception{
	String result;
	public NotABatsmanException(String res){
		result=res;
	}
	 public String toString(){
	     return (result);
	  }
}
