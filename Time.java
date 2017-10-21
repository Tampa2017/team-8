package random_fact_generator;

import java.util.Random;

public class Time 
{
	public static void main(String [] args)
	{
		//declare variables
		String [] time = {"daytime", "nighttime", "sunrise", "sunset"};
		
		int random_time = new Random().nextInt(time.length);
		System.out.print(time[random_time]);
	}
}
