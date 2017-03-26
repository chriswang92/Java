import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class ListOp
{//This is a class of list operator
	Map<Integer,String> map;
	int key;
	public ListOp(){ //constructor of ListOp
		this.map = new HashMap<Integer,String>();//create a new map in order to store the players 
		this.key = 0;
	}
	
	/*the following methods use "synchronized" to let multiple clients 
	 * can perform appropriate actions simultaneously.
	 * "synchronized" making these methods synchronized has two effects:
		First is Mutual Exclusiveness: it is not possible for two invocations of synchronized methods 
		on the same object to interleave. When one thread is executing 
		a synchronized method for an object, all other threads that invoke 
		synchronized methods for the same object block (suspend execution) 
		until the first thread is done with the object.
		Second is Synchronization: when a synchronized method exits, it automatically establishes a 
		happens-before relationship with any subsequent invocation of a synchronized 
		method for the same object. This guarantees that changes to the state of the 
		object are visible to all threads.
	*/
	
	/*
	 * method join can let the player join the list by associates the 
	 * specified value with the specified key in this map, 
	 * and after each mapping,the key will increase to make sure each key has only 
	 * one value(socket).
	 */
	
	public synchronized int join(String localPort,Socket s){
		this.key ++;
		String str = "client IP: ";
		str += s.getInetAddress().toString();
		str += ", sender port#: ";
		str += s.getLocalPort();
		str += ", receiver port#: ";
		str += localPort;
		this.map.put(key,str);
		return key;
	}
	
	/*
	 * method leave can let the player leave from the list by removes 
	 * the mapping for a key from this map.
	 */
	public synchronized void leave(int key){
		this.map.remove(key);
	}
	
}
