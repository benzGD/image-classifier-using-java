public class Perceptron {
    private int n;
    private double[] weights;

    // creates a perceptron with n inputs.
    public Perceptron(int n) {
        this.n = n;
        weights = new double[n];
    }

    public int numberOfInputs() {
        return n;
    }

    public double weightedSum(double[] x) {
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            sum += weights[i] * x[i];

        }
        return sum;
    }

    public int predict(double[] x) {
        double sum = weightedSum(x);
        if (sum <= 0) return -1;
        else return +1;
    }

    public void train(double[] x, int binaryLabel) {
        int p = predict(x);
        if (p == binaryLabel) {
            return;
        }
        else if (p == 1 && binaryLabel == -1) {
            for (int i = 0; i < n; i++) {
                weights[i] = weights[i] - x[i];

            }
        }
        else {
            for (int i = 0; i < n; i++) {
                weights[i] = weights[i] + x[i];

            }
        }

    }

    public String toString() {
        String s = "(";
        for (int i = 0; i < n; i++) {
            s = s + weights[i];
            if (i < n - 1) {
                s = s + ", ";
            }
        }
        s = s + ")";
        return s;

    }

    public static void main(String[] args) {
        // create a Perceptron with n inputs
        int n = 3;
        Perceptron perceptron = new Perceptron(n);
        StdOut.printf("Test #1 - constructor Perceptron(%d)\n", n);
        StdOut.println("  expect: (0.0, 0.0, 0.0)");
        StdOut.println("  result: " + perceptron);

        StdOut.println("Test #2 - numberOfInputs");
        StdOut.printf("  expect: %d\n", n);
        StdOut.println("  result: " + perceptron.numberOfInputs());

        double[] training1 = { 3.0, 4.0, 5.0 };  // +1 (yes)
        double[] training2 = { 2.0, 0.0, -2.0 };  // -1 (no)
        double[] training3 = { -2.0, 0.0, 2.0 };  // +1 (yes)
        double[] training4 = { 5.0, 4.0, 3.0 };  // -1 (no)

        // Test 3: perceptron.weightedSum(training1))
        //         perceptron.predict(training1))
        //         perceptron.train(training1, +1); // yes
        StdOut.println("Test #3a - weightedSum(3.0, 4.0, 5.0)");
        StdOut.printf("  expect: %2.1f\n", 0.0);
        StdOut.println("  result: " + perceptron.weightedSum(training1));

        StdOut.println("Test #3b - predict(3.0, 4.0, 5.0)");
        StdOut.printf("  expect: %d\n", -1);
        StdOut.println("  result: " + perceptron.predict(training1));

        StdOut.println("Test #3c - train(3.0, 4.0, 5.0, +1)");
        perceptron.train(training1, +1); // yes
        StdOut.println("  expect: (3.0, 4.0, 5.0)");
        StdOut.println("  result: " + perceptron);

        // Test 4: perceptron.weightedSum(training2))
        //         perceptron.predict(training2))
        //         perceptron.train(training2, -1); // no
        // Write these tests - similar to Test #3
        StdOut.println("Test #4a - weightedSum(2.0, 0.0, -2.0)");
        StdOut.printf("  expect: %2.1f\n", -4.0);
        StdOut.println("  result: " + perceptron.weightedSum(training2));

        StdOut.println("Test #4b - predict(2.0, 0.0, -2.0)");
        StdOut.printf("  expect: %d\n", -1);
        StdOut.println("  result: " + perceptron.predict(training2));

        StdOut.println("Test #4c - train(2.0, 0.0, -2.0, -1)");
        perceptron.train(training2, -1); // yes
        StdOut.println("  expect: (3.0, 4.0, 5.0)");
        StdOut.println("  result: " + perceptron);


        // Test 5: perceptron.weightedSum(training3))
        //         perceptron.predict(training3))
        //         perceptron.train(training3, +1); // yes
        // Write these tests - similar to Test #3
        StdOut.println("Test #5a - weightedSum(-2.0, 0.0, 2.0)");
        StdOut.printf("  expect: %2.1f\n", 4.0);
        StdOut.println("  result: " + perceptron.weightedSum(training3));

        StdOut.println("Test #5b - predict(-2.0, 0.0, 2.0)");
        StdOut.printf("  expect: %d\n", 1);
        StdOut.println("  result: " + perceptron.predict(training3));

        StdOut.println("Test #5c - train(-2.0, 0.0, 2.0, +1)");
        perceptron.train(training3, 1); // yes
        StdOut.println("  expect: (3.0, 4.0, 5.0)");
        StdOut.println("  result: " + perceptron);


        // Test 6: perceptron.weightedSum(training4))
        //         perceptron.predict(training4))
        //         perceptron.train(training4, -1); // no
        // Write these tests - similar to Test #3
        StdOut.println("Test #6a - weightedSum(5.0, 4.0, 3.0)");
        StdOut.printf("  expect: %2.1f\n", 46.0);
        StdOut.println("  result: " + perceptron.weightedSum(training4));

        StdOut.println("Test #6b - predict(5.0, 4.0, 3.0)");
        StdOut.printf("  expect: %d\n", -1);
        StdOut.println("  result: " + perceptron.predict(training4));

        StdOut.println("Test #6c - train(5.0, 4.0, 3.0, +1)");
        perceptron.train(training4, -1); // yes
        StdOut.println("  expect: (-2.0, 0.0, 2.0)");
        StdOut.println("  result: " + perceptron);


    }
}
