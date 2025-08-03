import java.awt.Color;

public class ImageClassifier {
    private MultiPerceptron mp;
    private String[] classNames;
    private int width;
    private int height;
    private int inputLength;

    public ImageClassifier(String configFile) {
        In in = new In(configFile);
        width = in.readInt();
        height = in.readInt();
        int numClasses = in.readInt();
        classNames = new String[numClasses];
        for (int i = 0; i < numClasses; i++) {
            classNames[i] = in.readString();

        }
        inputLength = width * height;
        mp = new MultiPerceptron(numClasses, inputLength);
    }

    // Creates a feature vector (1D array) from the given picture.
    public double[] extractFeatures(Picture picture) {
        double[] FVector = new double[inputLength];
        int width = picture.width();
        int height = picture.height();
        if (width * height != inputLength) {
            throw new IllegalArgumentException("image dimensions DO NOT match");

        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = picture.get(i, j);
                int r = color.getRed();
                FVector[(i) * width + j] = r;
            }
        }
        return FVector;

    }

    // Trains the perceptron on the given training data file.
    public void trainClassifier(String trainFile) {
        In in = new In(trainFile);
        while (!in.isEmpty()) {
            Picture picture = new Picture(in.readString());
            int label = in.readInt();
            double[] inputVector = extractFeatures(picture);
            mp.trainMulti(inputVector, label);

        }
    }

    // Returns the name of the class for the given class label.
    public String classNameOf(int classLabel) {
        return classNames[classLabel];
    }

    // Returns the predicted class for the given picture.
    public int classifyImage(Picture picture) {
        double[] inputVector = extractFeatures(picture);
        return mp.predictMulti(inputVector);
    }

    // Returns the error rate on the given testing data file.
    // Also prints the misclassified examples - see specification.
    public double testClassifier(String testFile) {
        In in = new In(testFile);
        double count = 0.0;
        double errors = 0.0;

        while (!in.isEmpty()) {
            count++;
            String filepath = in.readString();
            Picture pic = new Picture(filepath);
            int label = in.readInt();
            int predictedClass = classifyImage(pic);
            if (label != predictedClass) {
                errors++;
                StdOut.println(
                        filepath + "," + "label = " + classNameOf(label) + "," + "predict = "
                                + classNameOf(predictedClass));

            }
        }
        return errors / count;
    }


    // Tests this class using a configuration file, training file and test file.
    // See below.
    public static void main(String[] args) {
        ImageClassifier classifier = new ImageClassifier(args[0]);
        classifier.trainClassifier(args[1]);
        StdOut.println("test error rate " + classifier.testClassifier(args[2]));
        // StdOut.println("constructor :" + classifier.mp);
        // StdOut.println("  numerOfClasses: " + classifier.mp.numberOfClasses());
        // StdOut.println(" numberOfInputs " + classifier.mp.numberOfInputs());


    }

}
