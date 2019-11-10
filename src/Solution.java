

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;
import java.util.ArrayList;

public class Solution {

    public static void main(String[] args) {
        int totalNum = 0;
        String expr = null;
        int n = 0;
        int m = 0;
        int j = 0;
        int k = 0;
        boolean ans = true;

        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(ir);
        try {
            expr = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] firstLine = expr.split(" ");
        n = Integer.parseInt(firstLine[0]);
        m = Integer.parseInt(firstLine[1]);

        ArrayList<Subexpression> expression = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            try {
                Subexpression ob = new Subexpression(in.readLine(), m);
                expression.add(ob);
            }
            //   expression.add(new Subexpression(in.readLine(),m)); }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        String minimal = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";
        for (j = 0; j < Math.pow(2, m); j++) {
            Configuration config = new Configuration(j, m);
            ans = true;
            for (k = 0; k < n; k++) {
                //expr=expression.get(k);
                //Subexpression ob=new Subexpression( );
                //expr=ob.getText();
                //System.out.println(expr);
                // Subexpression obj=new Subexpression(expr,m);
                Subexpression obj = expression.get(k);
                ans = obj.evaluate(config) && ans;
                if (!ans)
                    break;
            }
            if (ans) {
                boolean[] values = config.getValues();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < values.length; i++) {
                    if (values[i]) {
                        sb.append((char) ('A' + i));
                    }
                }
                String temp = sb.toString();
                if (temp.compareTo(minimal) < 0) {
                    minimal = temp;
                }
                totalNum++;
            }

        }
        System.out.println(totalNum);
        if (!minimal.equals("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ")) {
            System.out.println(minimal);
        }
    }
}

class Subexpression {

    private Boolean[] variables;

    //private String text;

    Subexpression(String subexpr, int m) {
        // text=new String(subexpr);
        //String text = subexpr;
        //System.out.println(subexpr+"22222222");
        //System.out.println(text+"22222");
        String[] tokens = subexpr.split(" ");
        //   System.out.println(Arrays.toString(tokens));
        variables = new Boolean[m];
        for (String token : tokens) {
            //boolean status = token.contains("!");
            int len = token.length();
            if (len == 1) {
                char variable = token.toCharArray()[0];
                variables[variable - 'A'] = true;
            } else if (len == 2) {
                char variable = token.toCharArray()[1];
                variables[variable - 'A'] = false;
            } else {
                System.out.println("iossiblr{");
            }
        }
        // System.out.println(Arrays.toString(variables));

    }

    public boolean evaluate(Configuration config) {
        int i;
        int m = variables.length;
        boolean answer = false;

        boolean[] values;
        values = config.getValues();
        for (i = 0; i < m; i++) {
            if (variables[i] == null)
                continue;
            if (values[i] == variables[i])
                return true;
        }
        return false;
        // return true if this subexpression evaluates to
        // true for config, and false otherwise
    }
}


class Configuration {
    private boolean[] values;

    Configuration(int j, int m) {
        values = new boolean[m];
        for (int i = 0; i < m; i++) {
            if (j % 2 == 1) {
                values[m - i - 1] = true;
            } else {
                values[m - i - 1] = false;
            }
            j = j / 2;
        }
    }

    public boolean[] getValues() {
        return values;
    }
}