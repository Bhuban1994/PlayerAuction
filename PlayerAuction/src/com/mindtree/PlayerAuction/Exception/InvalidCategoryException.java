/**
 * 
 */
package com.mindtree.PlayerAuction.Exception;

/**
 * @author M1041940
 *
 */
public class InvalidCategoryException extends Exception{
	String result;
	public InvalidCategoryException(String res){
		result=res;
	}
	 public String toString(){
	     return (result);
	  }
}
