package main;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UserTest {

	User user;
	String name;
	double points;
	
	public UserTest(String name, double points){
		this.name = name;
		this.points = points;
		this.user = new User(name, points);
	}
	
    @Parameterized.Parameters
    public static Collection<Object[]> passwordsToCheck()
    {
        return Arrays.asList(new Object[][]
        {
            {
                "Kamil", 23
            },
            {
                "Klaudia",24
            },
            {
                "Kuba", 24
            },
            {
                "Sonia", 4543
            }
        });
    }

    @Test
    public void testName()
    {
        assertEquals(name, user.getName());
        assertEquals(points, user.getPoints(), 0.000000000001);
    }
}
