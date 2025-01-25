import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
      try {
            if (args.length != 1) {  // check count args
                throw new ArithmeticException();
            }
            if (checkRightExpression(args[0])){// check for right parenthesis expression
                testRes();
                System.out.println(printres(args[0]));
            }
        }
      catch (ArithmeticException e){  // exception handling
            System.out.println(e.getMessage());
         }
    }
    /** Function return true if right parenthesis expression
     * Function return false if empty string or invalid parenthesis expression
     */
    public static boolean checkRightExpression(String str) {
        if (str.isEmpty()) {  // check for empty string
            System.out.println("Error: empty string");
            return false;
        }
        StringTokenizer token = new StringTokenizer(str, str, true);
        int countParenthesis = 0;
        while (token.hasMoreTokens()) { // check right sequences of parenthesis
            String letter = token.nextToken();
            if (letter.equals("(")){
                countParenthesis += 1;
            }
            else if (letter.equals(")")){
                countParenthesis -= 1;
            }
            if (countParenthesis < 0){
                System.out.println("Invalid parenthesis expression");
                return false;
            }
        }
        if (countParenthesis == 0){
            return true;
        } else {
            System.out.println("Invalid parenthesis expression");
            return false;
        }
    }
    /** Function return vector indexes of ( and )
     */
    public static Vector<Integer> indexesOfSubstrings(String str) {

        String[] letters = str.split("");
        Vector<Integer> result = new Vector<>();
        int index = 0;
        boolean openParenthesis = false;  // flag to know ( or ) parenthesis
        for (int i = 0; i < letters.length; ++i) {
            if (letters[i].equals("(")){
                index = i;
                openParenthesis = true;
            }
            else if (letters[i].equals(")")){
                if (openParenthesis){
                     result.add(index);
                     result.add(i);
                     openParenthesis = false;
                }
            }
        }
        return result;
    }
    /** Function return string which was created by replace
     *  parenthesis expressions to "" in input string
     */
    public static String replaceString(String str, Vector<Integer> indexes) {
        StringBuilder strBuilder = new StringBuilder(str);
        int k = 0;
        for  (int i = 0; i < indexes.size(); i += 2){
            strBuilder.replace( indexes.elementAt(i) - k,  indexes.elementAt(i + 1) - k + 1, "");
            k += indexes.elementAt(i + 1) - indexes.elementAt(i) + 1;
        }
        return strBuilder.toString();
    }
    /** Test function return string which was created by replace
     *  parenthesis expressions to "" in input string
     */

    public static String printres(String str){
        Vector<Integer> arrayIndexes = indexesOfSubstrings(str);
        return replaceString(str, arrayIndexes);
    }

    public static void testRes(){
        String testValue = "sdffd(dsf)";
        String answer = "sdffd";
        if (printres(testValue).equals(answer)){
            System.out.println("Test1 - Ok");
        }else {
            System.out.println("Test1 - Fail");
        }
        testValue = "sdffd(dsf(dgs)sdg)";
        answer = "sdffd(dsfsdg)";
        if (printres(testValue).equals(answer)){
            System.out.println("Test2 - Ok");
        }else {
            System.out.println("Test2 - Fail");
        }
        testValue = "sdffd(dsf(dgs)sdg)dgsg(sdgdsg)";
        answer = "sdffd(dsfsdg)dgsg";
        if (printres(testValue).equals(answer)){
            System.out.println("Test3 - Ok");
        }else {
            System.out.println("Test3 - Fail");
        }
        testValue = "sdffd(dsf(dgs)sdg)dgsg(sdg(sdf)dsg)";
        answer = "sdffd(dsfsdg)dgsg(sdgdsg)";
        if (printres(testValue).equals(answer)){
            System.out.println("Test4 - Ok");
        }else {
            System.out.println("Test4 - Fail");
        }
        testValue = "sdffd";
        answer = "sdffd";
        if (printres(testValue).equals(answer)){
            System.out.println("Test5 - Ok");
        }else {
            System.out.println("Test5 - Fail");
        }
    }

    public static void testCheckRightExpression() {
        String testValue = "";
        boolean answer = false;
        if (checkRightExpression(testValue) == answer){
            System.out.println("Test1 - Ok");
        }else {
            System.out.println("Test1 - Fail");
        }
        testValue = ")hello(ggd(dsb))";
        if (checkRightExpression(testValue) == answer){
            System.out.println("Test2 - Ok");
        } else {
            System.out.println("Test2 - Fail");
        }
        testValue = "hello(ggd(dsb)))";
        if (checkRightExpression(testValue) == answer){
            System.out.println("Test3 - Ok");
        } else {
            System.out.println("Test3 - Fail");
        }
        testValue = "hello(gg)fjf(h(xnn)d)";
        answer = true;
        if (checkRightExpression(testValue) == answer){
            System.out.println("Test4 - Ok");
        } else {
            System.out.println("Test4 - Fail");
        }
        testValue = "hello(ggd(dsb))";
        if (checkRightExpression(testValue) == answer){
            System.out.println("Test5 - Ok");
        } else {
            System.out.println("Test5 - Fail");
        }
    }
}

