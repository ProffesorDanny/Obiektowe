import java.util.Random;

public class Lotto
{
	public static void main (String[] args)
	{
		Random los = new Random();
		for (int i = 0; i <6 ; i++)
		{
			System.out.println(los.nextInt(1,49));
		}
	}
}