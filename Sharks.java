
package random_fact_generator;

import java.util.Random;

public class Sharks 
{
	public static void main(String[] args)
	{
		//declare variable
		String [] facts = {"Sharks have adapted to living in a wide raanfe of aquatic habitats at various temperatures. \n", 
							"Most sharks are especially active in the evening and night when they hunt. \n",  
							"The jaws of sharks are not attatched to their skull, there are two upper an lower jaws that move seperatley. \n", 
							"Sharks have up to 3,000 teeth. \n", 
							"The majority of sharks had 8 ridgid fins. \n",  
							"Plastic is the most common element that is found in the ocean, and it is harmful for the environment. \n", 
							"Over one million seabirds are killed by ocean pollution each year."};
		int randomfact = new Random(). nextInt(facts.length);
				System.out.print(facts[randomfact]);
	
	}
	
}
