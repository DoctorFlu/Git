import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/*
 * Write into their code a Tree Object / Class
Inputs a HashMap<TYPE, String>
TYPE is also a String with only two possible values
blob
tree
Generates a SHA1 String given the whole map of key/value data
Saves the list of String (key/value) pairs
Writes pairs to a file in a folder called 'objects' 
The filename will be the SHA1 generated by the key/value data
String pairs will be delimited by :
Example tree file saved in objects as:  827fdb56e44ab6415f0a684d6bc85e16b8bc9c1f
blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f
blob : 01d82591292494afd1602d175e165f94992f6f5f
blob: f1d82236ab908c86ed095023b1d2e6ddf78a6d83
tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b
tree: e7d79898d3342fd15daf6ec36f4cb095b52fd976
 */

public class Tree {	
	public Tree(ArrayList<String> list) {
		String sum = "";
		for (int i = 0; i < list.size(); i++) {	
			sum += list.get(i);
			if(i<list.size()-1) {
				sum += "\n";
			}
		}
		
		String sha = getSHA1(sum);
	
        try {
        	if(!new File("objects/").exists()) {
	        	File dir = new File("objects");
	    		dir.mkdirs();
        	}
        	new File("objects/" + sha).delete();
            PrintWriter writer = new PrintWriter("objects/" + sha);
            for (String s : list) {
                writer.println(s);
            }
//	            
            writer.close();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
	    
	}
	
	public static String getSHA1(String input) {
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	
	
}