/**
 * Copyright (C) 2014 Luka Obradovic.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.hoshi.collections.stacks.application;

import org.hoshi.collections.stacks.LinkedStack;
import org.hoshi.collections.stacks.Stack;

/**
 * Using two stacks, program evaluates expressions like:
 *  ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )
 *
 * read as program arguments. Doesn't work for - and /.
 *
 * @author Luka Obradovic (obradovic.luka.83@gmail.com)
 */
public class DijkstraEvaluate {
    public static void main(String[] args) {
        final Stack<Operation> operations = new LinkedStack<Operation>();
        final Stack<Double> values        = new LinkedStack<Double>();

        for (String arg : args) {
            // first parse for operation
            Operation o = Operation.create(arg);
            if (o != null) {
                operations.push(o);
                continue;
            }

            // now check if it's a number
            Double value;
            try {
                value = Double.parseDouble(arg);
                values.push(value);
                continue;
            } catch (NumberFormatException nfex) {

            }

            if (arg.equals(")")) {
                final double first  = values.pop();
                final double second = values.pop();
                final Operation operation = operations.pop();

                final double result = operation.evaluate(first, second);
                values.push(result);
            }

            // everything else is ignored...
        }

        System.out.println(values.pop());
    }
    
    private static abstract class Operation {
        public abstract double evaluate(double first, double second);

        public static Operation create(final String operator) {
            if (operator.equals("+")) {
                return new Addition();
            } else if (operator.equals("-")) {
                return new Subtraction();
            } else if (operator.equals("*")) {
                return new Multiplication();
            } else if (operator.equals("/")) {
                return new Division();
            } else {
                return null;
            }
        }

        private static class Addition extends Operation {
            private Addition() {

            }

            public double evaluate(final double first, final double second) {
                return first + second;
            }
        }

        private static class Subtraction extends Operation {
            private Subtraction() {

            }

            public double evaluate(final double first, final double second) {
                return first - second;
            }
        }

        private static class Division extends Operation {
            private Division() {

            }

            public double evaluate(final double first, final double second) {
                return first / second;
            }
        }

        private static class Multiplication extends Operation {
            private Multiplication() {

            }

            public double evaluate(final double first, final double second) {
                return first * second;
            }
        }
    }
}