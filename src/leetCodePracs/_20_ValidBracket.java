package leetCodePracs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

public class _20_ValidBracket {

    //以下仅为（）一种情况，如果（）{}[]都存在的话，可以通过hashmap来改善代码
    public static boolean isValid(String s) {
        //如果没有左，或没有右，肯定就无效
        if (!s.contains(")") || !s.contains("(")) return false;
        int len = s.length();
        Deque<Character> stack = new ArrayDeque();
        for (int i = 0; i < len; i++) {
            //碰见左括号即入栈
            if (s.charAt(i) == '(')
                stack.push(s.charAt(i));
            //如果在这儿，先判断if (stack.size() == 0)，会导致这样的结果无法通过"asdfasdf(((s)))"
            //碰见右括号
            if (s.charAt(i) == ')') {
                //如果栈为空，肯定就不存在左括号
                if (stack.size() == 0) {
                    return false;
                } else {
                    //不为空才出栈
                    Character pop = stack.pop();
                    //如果不等于，那肯定没戏
                    if (pop != '(') {
                        return false;
                    }
                }
            }
        }
        if (stack.size() == 0) {
            //对应这种情况"(((s)"
            return true;
        } else {
            return false;
        }
    }




    public static void main(String[] args) {
        System.out.println(isValid("(((s)))"));
        System.out.println(isValid(")(())(())("));
        System.out.println(isValid("(s))))"));
        System.out.println(isValid("(((s)"));
        System.out.println(isValid("((((()s))))"));
        System.out.println(isValid("asdfasdf(((s)))"));
    }
}
