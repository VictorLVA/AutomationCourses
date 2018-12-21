package Results;

public abstract class Result {

    private double result;

    Result(double value) {
        this.result = value;
    }

    public double getResult() {
        return result;
    }

    public String toString() {
        return "" + result;
    }
}
