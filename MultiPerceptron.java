public class MultiPerceptron {
    private Perceptron[] pers;
    private int inputs;


    public MultiPerceptron(int m, int n) {
        inputs = n;
        pers = new Perceptron[m];
        for (int i = 0; i < m; i++) {
            pers[i] = new Perceptron(inputs);

        }
    }

    public int numberOfClasses() {
        return pers.length;
    }

    public int numberOfInputs() {
        return inputs;
    }


    public int predictMulti(double[] x) {
        double highest = Double.NEGATIVE_INFINITY;
        int index = -1;
        for (int i = 0; i < pers.length; i++) {
            double temp = pers[i].weightedSum(x);
            if (temp > highest) {
                highest = temp;
                index = i;
            }
        }
        return index;
    }

    // Trains this multi-perceptron on the labeled (b/w
    // 0 to 1) input
    public void trainMulti(double[] x, int classLabel) {
        int predictedPerceptron = predictMulti(x);
        if (predictedPerceptron == classLabel && pers[classLabel].predict(x) == +1) {
            return;
        }
        else {
            // you can uncomment this for loop later
            // for (int i = 0; i < pers.length; i++) {
            //     // StdOut.println("iteration " + i);
            //     if (i == classLabel) {
            //         pers[i].train(x, +1);
            //     }
            //     else {
            //         pers[i].train(x, -1);
            //     }
            // }

            // instead of training all m perceptrons, when there is a prediction error
            // (multiclass perceptron predicts i but correct label is k),
            // train only two perceptrons: train perceptron i(with label −1) and
            // perceptron k(with label +1)
            pers[predictedPerceptron].train(x, -1);
            pers[classLabel].train(x, +1);


        }

    }

    public String toString() {
        String s = "(";
        for (int i = 0; i < pers.length; i++) {

            s = s + pers[i].toString();
            if (i < pers.length - 1) {
                s = s + ", ";

            }
        }
        s = s + ")";
        return s;
    }

    public static void main(String[] args) {
        int m = 2;
        int n = 3;
        MultiPerceptron mp1 = new MultiPerceptron(2, 3);

        // Constructor Test #1 - create MultiPerceptron and print
        StdOut.printf("Constructor Test #1 - constructor MultiPerceptron(%d, %d)\n", m, n);
        StdOut.println("  expect: ((0.0, 0.0, 0.0), (0.0, 0.0, 0.0))");
        StdOut.println("  result: " + mp1);
        StdOut.println();

        // Constructor Test #2 - check number of classes
        StdOut.println("Constructor Test #2 - numberOfClasses");
        StdOut.printf("  expect: %d\n", m);
        StdOut.println("  result: " + mp1.numberOfClasses());
        StdOut.println();

        // Constructor Test #3 - check number of inputs
        StdOut.println("Constructor Test #3 - numberOfInputs");
        StdOut.printf("  expect: %d\n", n);
        StdOut.println("  result: " + mp1.numberOfInputs());
        StdOut.println();

        // training data
        double[] training1 = { 3.0, 4.0, 5.0 };  // class 1
        double[] training2 = { 2.0, 0.0, -2.0 };  // class 0
        double[] training3 = { -2.0, 0.0, 2.0 };  // class 1
        double[] training4 = { 5.0, 4.0, 3.0 };  // class 0


        // Training Test #1 - class 1
        StdOut.println("Training Test #1 class 1: trainMulti((3, 4, 5), 1)");
        mp1.trainMulti(training1, 1);
        StdOut.println("  expect: ((0.0, 0.0, 0.0), (3.0, 4.0, 5.0))");
        StdOut.println("  result: " + mp1);
        StdOut.println();

        // Training Test #2 - class 0
        StdOut.println("Training Test #2 class 0: trainMulti((2, 0, -2), 0)");
        mp1.trainMulti(training2, 0);
        StdOut.println("  expect: ((2.0, 0.0, -2.0), (3.0, 4.0, 5.0))");
        StdOut.println("  result: " + mp1);
        StdOut.println();

        // Training Test #3 - class 1
        StdOut.println("Training Test #3 class 1: trainMulti((-2, 0, 2), 1)");
        mp1.trainMulti(training3, 1);
        StdOut.println("  expect: ((2.0, 0.0, -2.0), (3.0, 4.0, 5.0))");
        StdOut.println("  result: " + mp1);
        StdOut.println();

        // Training Test #4 - class 0
        StdOut.println("Training Test #4 class 0: trainMulti(( 5, 4, 3 ), 0)");
        mp1.trainMulti(training4, 0);
        StdOut.println("  expect: ((2.0, 0.0, -2.0), (-2.0, 0.0, 2.0))");
        StdOut.println("  result: " + mp1);
        StdOut.println();

        // testing data
        double[] testing1 = { -1.0, 2.0, 3.0 };
        double[] testing2 = { 2.0, -5.0, 1.0 };
        double[] testing3 = { -4, 0.0, 4.0 };

        // Testing Test #1 - expect class 1
        StdOut.println("Testing Test #1 - predictMulti(-1, 2, 3)");
        StdOut.println("  expect class: 1");
        StdOut.println("  result result: " + mp1.predictMulti(testing1));
        StdOut.println();

        // Testing Test #2 - expect class 0
        StdOut.println("Testing Test #2 - predictMulti(2, -5, -1)");
        StdOut.println("  expect class: 0");
        StdOut.println("  result class: " + mp1.predictMulti(testing2));
        StdOut.println();
        // Testing Test #3 - expect class 1
        StdOut.println("Testing Test #3 - predictMulti(-4, 0, 4)");
        StdOut.println("  expect class: 1");
        StdOut.println("  result result: " + mp1.predictMulti(testing3));
        StdOut.println();

    }
}
