package com.umang;

import java.util.HashSet;
import java.util.Scanner;

public class Main {

    // For keeping track of taken positions
    static HashSet<Integer> ur_set = new HashSet<Integer>();
    static HashSet<Integer> comp_set = new HashSet<Integer>();

    // Main Method
    public static void main(String[] args)
    {
        // Creating Game Board
	    String[][] gboard = {
                {"  "," | ","  "," | ","  "},
                {"--"," | ","--"," | ","--"},
                {"  "," | ","  "," | ","  "},
                {"--"," | ","--"," | ","--"},
                {"  "," | ","  "," | ","  "},
        };

	    // Printing Empty Board
	    print_gboard(gboard);


        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.print("Enter position 1-9 : ");
            int u_pos = sc.nextInt();

            while (ur_set.contains(u_pos) || comp_set.contains(u_pos))
            {
                System.out.println("Position already taken ! Try Again !");
                u_pos = sc.nextInt();
            }

            put_symbol(gboard, u_pos, "You");

            String res = check_winner(); // Going to work with Hash Sets
            if(res.length() > 0)
            {
                System.out.println(res);
                break;
            }

            // Computer Function
            int comp_pos = (int)(Math.random() * (9 - 1 + 1) + 1);

            while (ur_set.contains(comp_pos) || comp_set.contains(comp_pos))
                comp_pos = (int)(Math.random() * (9 - 1 + 1) + 1);

            put_symbol(gboard, comp_pos, "Comp");

            res = check_winner(); // Going to work with Hash Sets
            if(res.length() > 0)
            {
                System.out.println(res);
                break;
            }
        }

    }

    public static void print_gboard(String[][] gboard)
    {
        System.out.println();
        System.out.println("Board Updated!");
        System.out.println();
        int row = gboard.length;
        int col = gboard[0].length;

        for(int i = 0 ; i < row ; i++)
        {
            for(int j = 0 ; j < col ; j++)
                System.out.print(gboard[i][j]);

            System.out.println();
        }
    }

    public static void put_symbol(String[][] gboard, int pos, String user)
    {
        // You -> O & Comp -> X
        String  symbol;
        if(user.equals("You"))
        {
            symbol = " O";
            ur_set.add(pos);
        }

        else
        {
            symbol = " X";
            comp_set.add(pos);
        }

        // In the case of -> switch you don't need to include break and
        // it does not have fall-through behavior.
        switch (pos) {
            case 1 -> gboard[0][0] = symbol;
            case 2 -> gboard[0][2] = symbol;
            case 3 -> gboard[0][4] = symbol;
            case 4 -> gboard[2][0] = symbol;
            case 5 -> gboard[2][2] = symbol;
            case 6 -> gboard[2][4] = symbol;
            case 7 -> gboard[4][0] = symbol;
            case 8 -> gboard[4][2] = symbol;
            case 9 -> gboard[4][4] = symbol;
        }

        print_gboard(gboard);
    }

    public static String check_winner()
    {
        HashSet<Integer> r1 = new HashSet<>();
        r1.add(1); r1.add(2) ; r1.add(3);

        HashSet<Integer> r2 = new HashSet<>();
        r2.add(4); r2.add(5) ; r2.add(6);

        HashSet<Integer> r3 = new HashSet<>();
        r3.add(7); r3.add(8) ; r3.add(9);

        HashSet<Integer> c1 = new HashSet<>();
        c1.add(1); c1.add(4) ; c1.add(7);

        HashSet<Integer> c2 = new HashSet<>();
        c2.add(2); c2.add(5) ; c2.add(8);

        HashSet<Integer> c3 = new HashSet<>();
        c3.add(3); c3.add(6) ; c3.add(9);

        HashSet<Integer> d1 = new HashSet<>();
        d1.add(1); d1.add(5) ; d1.add(9);

        HashSet<Integer> d2 = new HashSet<>();
        d2.add(3); d2.add(5) ; d2.add(7);

        HashSet<HashSet> set = new HashSet<>();
        set.add(r1); set.add(r2); set.add(r3);
        set.add(c1); set.add(c2); set.add(c3);
        set.add(d1) ; set.add(d2);

        for(HashSet h: set)
        {
            if(ur_set.containsAll(h))
                return "YOU WON !!";

            else if(comp_set.containsAll(h))
                return "YOU LOST !!";
        }

        if(ur_set.size() + comp_set.size() == 9)
            return "Game Draw !!";

        return "";
    }
}
