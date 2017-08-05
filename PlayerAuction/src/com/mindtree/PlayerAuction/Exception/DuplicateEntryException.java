/**
 * 
 */
package com.mindtree.PlayerAuction.Exception;
import com.mindtree.PlayerAuction.service.*;
/**
 * @author M1041940
 *
 */
public class DuplicateEntryException extends Exception{
	String result;
	public DuplicateEntryException(String res){
		result=res;
	}
	 public String toString(){
	     return (result);
	  }
}
