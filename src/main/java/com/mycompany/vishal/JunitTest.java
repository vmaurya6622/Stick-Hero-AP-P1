package com.mycompany.vishal;

/* Junit test class */
import javafx.scene.shape.Line;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JunitTest {
    @Test
    public void Test1(){
        GameEndController gameEndController = new GameEndController();
        ArrayList<String> list = gameEndController.getList();
        File myFile = new File("HighScore.txt");
        Scanner sc;
        try {
            sc = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> list2 = new ArrayList<>();
        while(sc.hasNextLine()) {
            list2.add(sc.nextLine());
        }
        assertEquals(list,list2);
    }

    @Test
    public void Test2(){
        MainGamePlayEngine mainGamePlayEngine = new MainGamePlayEngine();
        Line line = mainGamePlayEngine.CreateStick();
        Boolean result = (int)(line.getEndY() - line.getStartY()) >= 0;
        assertTrue(result);
    }
}

