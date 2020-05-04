package implementations;

import interfaces.Solvable;

public class BalancedParentheses implements Solvable {
    private String parentheses;
    private ArrayDeque<String> stack;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
        this.stack = new ArrayDeque<>();
    }

    @Override
    public Boolean solve() {
        return checkParentheses();
    }

    private Boolean checkParentheses() {

        for (int i = 0; i < this.parentheses.length(); i++) {

            boolean balanced;
            switch (this.parentheses.charAt(i)) {
                case '(':
                case '[':
                case '{':
                    this.stack.offer("" + this.parentheses.charAt(i));
                    break;
                case ')':
                    balanced = this.stack.removeLast().equals("(");
                    if (!balanced) {
                        return false;
                    }
                    break;
                case ']':
                    balanced = this.stack.removeLast().equals("[");
                    if (!balanced) {
                        return false;
                    }
                    break;
                case '}':
                   balanced = this.stack.poll().equals("{");
                    if (!balanced) {
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
}
