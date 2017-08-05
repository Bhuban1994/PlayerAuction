/**
 * 
 */
package com.mindtree.PlayerAuction.Exception;

/**
 * @author M1041940
 *
 */
public class NotABowlerException extends Exception {
	String result;
	public NotABowlerException(String res){
		result=res;
	}
	 public String toString(){
	     return (result);
	  }
}
