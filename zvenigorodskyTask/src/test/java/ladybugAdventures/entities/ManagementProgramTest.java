package ladybugAdventures.entities;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ladybugAdventures.entities.CommandImpl;
import ladybugAdventures.entities.Cycle;
import ladybugAdventures.entities.ManagementProgram;
import ladybugAdventures.entities.interfaces.Command;
import ladybugAdventures.enums.CommandType;
import ladybugAdventures.enums.Direction;

public class ManagementProgramTest extends TestCase {
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ManagementProgramTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ManagementProgramTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        ManagementProgram mp = new ManagementProgram("testAuthor", "C:/abc.map");
        CommandImpl c1 = new CommandImpl(Direction.DOWN,CommandType.JUMP);
        CommandImpl c2 = new CommandImpl(Direction.UP,CommandType.MOVE);
        List<Command> list = new LinkedList<>();
        list.add(c2);
        list.add(c1);
        Cycle cyc = new Cycle(2, list);
        mp.addCommand(c2);
        mp.addCommand(c1);
        mp.addCommand(cyc);
        mp.addCommand(c1);
//        System.out.println(mp.getCommandAmount());
        assertEquals(mp.getAllCommandAmountWithIterations(), 7);
//        assertEquals(mp.getAllCommandAmount(), mp.getCommandListInLine().size());
    }
}
